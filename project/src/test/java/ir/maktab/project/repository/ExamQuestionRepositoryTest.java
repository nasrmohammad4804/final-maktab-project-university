package ir.maktab.project.repository;

import ir.maktab.project.domain.*;
import ir.maktab.project.domain.embeddable.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ExamQuestionRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;


    @Test
    void getAllExamQuestion() {
        ExamQuestion examQuestion = new ExamQuestion("2*5+1", "Multiplication");
        Exam exam= setExamAndAnswerForQuestion(examQuestion);

        assertThat(
                examQuestionRepository.getAllExamQuestion(exam.getId())
        ).isNotEmpty();
    }
    private Exam  setExamAndAnswerForQuestion(ExamQuestion examQuestion){
        List<Option> options = new ArrayList<>();
        options.add(new Option(false, "6"));
        options.add(new Option(false, "8"));
        options.add(new Option(true, "11"));
        options.add(new Option(false, "9"));
        Answer masterAnswer = new TestAnswer(options);
        masterAnswer.setQuestion(examQuestion);
        examQuestion.getAnswerList().add(masterAnswer);

        TestAnswer studentAnswer = new TestAnswer();
        List<Option> studentOptions = new ArrayList<>();
        studentOptions.add(new Option(false, "6"));
        studentOptions.add(new Option(true, "8"));
        studentOptions.add(new Option(false, "11"));
        studentOptions.add(new Option(false, "9"));
        studentAnswer.setOptions(studentOptions);
        examQuestion.getAnswerList().add(studentAnswer);
        studentAnswer.setQuestion(examQuestion);

        Exam exam = Exam.builder().title("quiz1").description("i want to get quiz from multiple")
                .build();

        examQuestion.setExam(exam);
        exam.getExamQuestionList().add(examQuestion);
        entityManager.persist(exam);
        return exam;
    }
}