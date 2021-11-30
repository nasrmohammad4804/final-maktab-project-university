package ir.maktab.project.service.impl;

import ir.maktab.project.domain.*;
import ir.maktab.project.domain.embeddable.Option;
import ir.maktab.project.repository.ExamQuestionRepository;
import ir.maktab.project.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExamQuestionServiceImpl implements ExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Override
    @Transactional
    public void addExamQuestionFromQuestionBank(Exam exam, Question question) {

        Answer answer;
        Answer answerBankQuestion = question.getAnswerList().get(0);
        if (answerBankQuestion instanceof TestAnswer) {

            List<Option> options = ((TestAnswer) answerBankQuestion).getOptions().stream().map(x -> new Option(x.getIsAnswered(), x.getContent()))
                    .collect(Collectors.toList());
            answer = new TestAnswer(options);
        } else answer = new DescriptiveAnswer();

        ExamQuestion examQuestion = new ExamQuestion(question.getQuestionText(), question.getTitle());
        examQuestion.getAnswerList().add(answer);
        answer.setQuestion(examQuestion);
        examQuestion.setExam(exam);
        exam.getExamQuestionList().add(examQuestion);

    }

    @Override
    @Transactional
    public void addNewDescriptiveQuestion(Exam exam, ExamQuestion examQuestion) {

        Answer answer = new DescriptiveAnswer();
        answer.setQuestion(examQuestion);

        examQuestion.getAnswerList().add(answer);

        examQuestion.setExam(exam);
        exam.getExamQuestionList().add(examQuestion);
        Question question = Question.builder().questionText(examQuestion.getQuestionText())
                .title(examQuestion.getTitle()).build();


        Answer answer2 = new DescriptiveAnswer();

        question.getAnswerList().add(answer2);

        answer2.setQuestion(question);
        exam.getCourse().getQuestionBank().add(question);
    }

    @Override
    @Transactional
    public void addNewTestQuestion(Exam exam, Map<String, ?> map) {

        ExamQuestion examQuestion = new ExamQuestion();

        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) map.get("question");
        linkedHashMap.keySet().forEach(x -> {
            if (x.equals("questionText"))
                examQuestion.setQuestionText(linkedHashMap.get(x));
            else
                examQuestion.setTitle(linkedHashMap.get(x));
        });
        ArrayList<Object> options = (ArrayList<Object>) map.get("option");
        ArrayList<Option> myOption = new ArrayList<>();

        options.forEach(x -> {
            LinkedHashMap<String, String> hashMap = ((LinkedHashMap<String, String>) x);
            Option option = new Option();
            hashMap.keySet().forEach(myMap -> {
                if (myMap.equals("content"))
                    option.setContent(hashMap.get(myMap));
                else option.setIsAnswered((Boolean.valueOf(hashMap.get(myMap))));

            });
            myOption.add(option);
        });

        Answer answer = new TestAnswer(myOption);

        examQuestion.getAnswerList().add(answer);

        answer.setQuestion(examQuestion);
        examQuestion.setExam(exam);
        exam.getExamQuestionList().add(examQuestion);

        Question question = Question.builder().questionText(examQuestion.getQuestionText())
                .title(examQuestion.getTitle()).build();

        Answer answer2 = new TestAnswer(myOption);
        question.getAnswerList().add(answer2);

        answer2.setQuestion(question);

        exam.getCourse().getQuestionBank().add(question);
    }

    @Override
    public ExamQuestion findById(Long id) {
        return examQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException("examQuestionNotFound"));
    }

    @Override
    public List<ExamQuestion> getAllQuestion(Long examId) {
        return examQuestionRepository.getAllExamQuestion(examId);
    }

    @Override
    public List<ExamQuestion> findAllDescriptiveQuestion(Long examId) {

        //first answer of question always answer of master
        return getAllQuestion(examId).stream().filter(examQuestion -> examQuestion.getAnswerList().get(0) instanceof DescriptiveAnswer)
                .collect(Collectors.toList());
    }

}
