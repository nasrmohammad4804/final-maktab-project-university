package ir.maktab.project.service.impl;

import ir.maktab.project.domain.*;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.domain.dto.TestAnswerDTO;
import ir.maktab.project.domain.embeddable.Option;
import ir.maktab.project.domain.embeddable.StudentExamState;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.exception.StudentNotFoundException;
import ir.maktab.project.repository.StudentRepository;
import ir.maktab.project.service.ExamQuestionService;
import ir.maktab.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final double WRONG_ANSWER_SCORE = 0;

    @Autowired
    private StudentRepository repository;

    @Autowired
    private ExamQuestionService examQuestionService;

    @Override
    public List<Student> dontExistOnCourse(Set<Student> users, String checker) {
        return repository.dontExistOnCourse(users, checker).stream()
                .filter(x -> x.getRegisterState().equals(RegisterState.CONFIRM))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student findWithId(Long id) {
        return repository.findWithId(id).orElseThrow(() -> new StudentNotFoundException("student not found"));
    }

    @Override
    public Optional<Student> findWithAllStudentExamState(Long id) {
        return repository.findWithAllStudentExamState(id);
    }

    @Override
    @Transactional
    public void changeExamState(Student student, StudentExamState studentExamState) {

        student.getStudentExamStates().stream().filter(myStudentExamState -> myStudentExamState.getExamId().equals(studentExamState.getExamId()))
                .findFirst().ifPresentOrElse(myStudentExamState -> myStudentExamState.setExamState(studentExamState.getExamState()),
                () -> student.getStudentExamStates().add(studentExamState)
        );
    }

    @Override
    public Optional<Student> findWithAllAnswerList(Long id) {
        return repository.findWithAllAnswerList(id);
    }

    @Override
    @Transactional
    public void saveTestAnswer(Student student, Long examQuestionId, TestAnswerDTO newAnswer) {

        Optional<Option> option = newAnswer.getOptions().stream().filter(Option::getIsAnswered).findFirst();
        ExamQuestion examQuestion = examQuestionService.findById(examQuestionId);
        TestAnswer masterAnswer = (TestAnswer) examQuestion.getAnswerList().get(0);

        student.getAnswerList().stream().filter(answer -> answer.getQuestion().getId().equals(examQuestionId))
                .findFirst().ifPresentOrElse(

                answer -> {

                    answer.setScore(calculateScore(masterAnswer, option.get(), examQuestion.getScore()));

                    ((TestAnswer) answer).getOptions().forEach(myOption -> {

                        myOption.setIsAnswered(myOption.getContent().equals(option.get().getContent()));

                    });
                }

                , () -> {

                    TestAnswer myTestAnswer = new TestAnswer(newAnswer.getOptions());
                    myTestAnswer.setQuestion(new ExamQuestion(examQuestionId));
                    myTestAnswer.setStudent(student);
                    myTestAnswer.setScore(calculateScore(masterAnswer, option.get(), examQuestion.getScore()));
                    student.getAnswerList().add(myTestAnswer);
                }
        );
    }

    //if answer of student then get score else his score : 0
    private double calculateScore(TestAnswer masterAnswer, Option option, double questionScore) {
        Optional<Option> myOption = masterAnswer.getOptions().stream().filter(x -> x.getIsAnswered().equals(Boolean.TRUE) && (x.getContent().equals(option.getContent())))
                .findFirst();

        if (myOption.isPresent())
            return questionScore;

        return WRONG_ANSWER_SCORE;

    }

    @Override
    @Transactional
    public void saveDescriptiveAnswer(Student student, Long examQuestionId, DescriptiveAnswerDTO descriptiveAnswer) {
        student.getAnswerList().stream().filter(answer -> answer.getQuestion().getId().equals(examQuestionId))
                .findFirst().ifPresentOrElse(answer -> ((DescriptiveAnswer) answer).setAnswerText(descriptiveAnswer.getAnswerText()),
                () -> {
                    DescriptiveAnswer myDescriptiveAnswer = new DescriptiveAnswer();
                    myDescriptiveAnswer.setQuestion(new ExamQuestion(examQuestionId));
                    myDescriptiveAnswer.setAnswerText(descriptiveAnswer.getAnswerText());
                    myDescriptiveAnswer.setStudent(student);
                    student.getAnswerList().add(myDescriptiveAnswer);
                }
        );
    }

    @Override
    public List<Answer> findAllAnswerOfStudentExam(Long studentId, Long examId) {
        return repository.findAllAnswerOfExam(studentId, examId);
    }

    @Override
    public List<DescriptiveAnswer> findAllDescriptiveAnswerOfStudentExam(Long studentId, Long examId) {

        return findAllAnswerOfStudentExam(studentId, examId)
                .stream().filter(answer -> answer instanceof DescriptiveAnswer)
                .map((answer) -> (DescriptiveAnswer) (answer)).collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudentWitchParticipatedInExam(Long examId) {
        return repository.getAllStudentWitchParticipatedInExam(examId);
    }
}
