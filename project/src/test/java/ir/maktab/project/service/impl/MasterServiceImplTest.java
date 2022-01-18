package ir.maktab.project.service.impl;

import ir.maktab.project.domain.DescriptiveAnswer;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.dto.DescriptiveAnswerDTO;
import ir.maktab.project.repository.MasterRepository;
import ir.maktab.project.repository.UserRepository;
import ir.maktab.project.service.MasterService;
import ir.maktab.project.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MasterServiceImplTest {

    final double firstScore = 2;
    final double newScore = 3.5;
    final Long studentId = 1L;
    final Long examId = 1L;

    @Mock
    private MasterRepository masterRepository;

    @Mock
    private StudentService studentService;

    private MasterService masterService;

    @BeforeEach
    void setUp() {
        masterService = new MasterServiceImpl(masterRepository);
        ((MasterServiceImpl) masterService).setStudentService(studentService);
    }

    @Test
    void scoreToAnswer() {

        List<DescriptiveAnswer> descriptiveAnswers = createFakeDescriptiveAnswerStudent();
        when(studentService.findAllDescriptiveAnswerOfStudentExam(studentId, examId))
                .thenReturn(descriptiveAnswers);

        masterService.scoreToAnswer(
                Collections.singletonList(
                        DescriptiveAnswerDTO.builder().questionId(1L).answerText("2^3+1").score(newScore).build()
                ), studentId, examId);

        descriptiveAnswers.stream().filter(descriptiveAnswer -> descriptiveAnswer.getQuestion().getId().equals(1L))
                .findFirst().ifPresent(descriptiveAnswer -> assertThat(descriptiveAnswer.getScore()).isNotEqualTo(firstScore));

    }

    private List<DescriptiveAnswer> createFakeDescriptiveAnswerStudent() {

        DescriptiveAnswer descriptiveAnswer = DescriptiveAnswer.builder().answerText("2^3=8")
                .question(new ExamQuestion(1L)).build();
        descriptiveAnswer.setScore(firstScore);

        return Arrays.asList(
                descriptiveAnswer,
                DescriptiveAnswer.builder().answerText("4*3+1=13").question(new ExamQuestion(2L)).build(),
                DescriptiveAnswer.builder().answerText("f'(x)=2x^3 -> f(x)=6x^2").question(new ExamQuestion(3L)).build()
        );


    }
}