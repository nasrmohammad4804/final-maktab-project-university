package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.repository.ExamRepository;
import ir.maktab.project.service.ExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    private static final String DESCRIPTION = "i want to get exam from multiple in math";
    private ExamService examService;

    private Exam exam;

    @Mock
    private ExamRepository examRepository;

    @BeforeEach
    void setUp() {
        examService = new ExamServiceImpl(examRepository);
        exam = Exam.builder().title("multiple")
                .description(DESCRIPTION)
                .startTime(LocalDateTime.now())
                .endTime(LocalTime.now().plusHours(2L)).build();

    }


    @Test
    void updateExamNotStarted() {
        Exam updatedExam = Exam.builder().startTime(LocalDateTime.parse("2016-05-11T22:31:40"))
                .endTime(LocalTime.parse("23:41:30")).description("new description").build();
        examService.updateExamNotStarted(exam, updatedExam);
        assertThat(exam.getDescription()).isNotEqualTo(DESCRIPTION);

    }
}