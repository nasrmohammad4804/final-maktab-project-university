package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.Question;
import ir.maktab.project.repository.QuestionRepository;
import ir.maktab.project.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionRepository);
    }

    @Test
    void checkExamHasQuestion() {
        Exam exam = getExam();
        Question questionDoesNotExists = new ExamQuestion("2/5+1", "divide");
        assertThat(questionService.checkExamHasQuestion(exam, questionDoesNotExists))
                .isFalse();

        Question questionDoesExists = new ExamQuestion("4*6-1", "multiple");
        assertThat(questionService.checkExamHasQuestion(exam, questionDoesExists)).isTrue();
    }

    private Exam getExam() {

        Exam exam = Exam.builder().title("multiple").description("i want to get exam from multiple")
                .build();
        exam.getExamQuestionList().add(new ExamQuestion("2*3", "multiple"));
        exam.getExamQuestionList().add(new ExamQuestion("4*6-1", "multiple"));
        exam.getExamQuestionList().add(new ExamQuestion("5-1*3", "multiple"));
        return exam;
    }

    @Test
    void changeScore() {
        Exam exam = getExam();
        assertThat(exam.getExamQuestionList().get(0).getScore()).isZero();
        List<Double> scores = Stream.of(4d, 1.5, 2d).collect(Collectors.toList());
        questionService.changeScore(exam, scores);
        /* after changing score then score of first question is 4 */
        assertThat(exam.getExamQuestionList().get(0).getScore()).isNotZero();
        scores.remove(2);

        /* expect to get exception because remove one score then number of score not equal with question and get error out of bound */
        assertThatThrownBy(() -> questionService.changeScore(exam, scores));
    }
}