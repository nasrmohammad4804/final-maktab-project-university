package ir.maktab.project.mapper.impl;

import ir.maktab.project.domain.DescriptiveAnswer;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.domain.dto.ExamQuestionsWithStudentDescriptiveAnswerDTO;
import ir.maktab.project.domain.dto.QuestionDTO;
import ir.maktab.project.mapper.ExamQuestionsWithStudentDescriptiveAnswersMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExamQuestionsWithStudentDescriptiveAnswersMapperImpl implements ExamQuestionsWithStudentDescriptiveAnswersMapper {

    @Override
    public ExamQuestionsWithStudentDescriptiveAnswerDTO convertEntityToExamQuestionWithDescriptiveAnswer(List<ExamQuestion> examQuestions, List<DescriptiveAnswer> answers) {
        if (examQuestions == null || answers == null)
            return null;

        ExamQuestionsWithStudentDescriptiveAnswerDTO dto = new ExamQuestionsWithStudentDescriptiveAnswerDTO(new ArrayList<>(),new ArrayList<>());
        calculateAllExamQuestion(dto, examQuestions);
        calculateAllStudentDescriptiveAnswer(dto, answers);
        return dto;

    }

    private QuestionDTO questionDTO(ExamQuestion examQuestion) {
        return QuestionDTO.builder().questionText(examQuestion.getQuestionText())
                .id(examQuestion.getId()).score(examQuestion.getScore())
                .build();
    }

    private DescriptiveAnswerDTO answerDTO(DescriptiveAnswer answer) {

        return DescriptiveAnswerDTO.builder().answerText(answer.getAnswerText())
                .questionId(answer.getQuestion().getId()).score(answer.getScore()).build();
    }

    private void calculateAllExamQuestion(ExamQuestionsWithStudentDescriptiveAnswerDTO dto, List<ExamQuestion> examQuestions) {

        examQuestions.forEach(examQuestion -> dto.getQuestionDTOList().add(questionDTO(examQuestion)));
    }

    private void calculateAllStudentDescriptiveAnswer(ExamQuestionsWithStudentDescriptiveAnswerDTO dto, List<DescriptiveAnswer> answers) {

        answers.forEach(answer -> dto.getDescriptiveAnswerDTOList().add(answerDTO(answer)));
    }
}
