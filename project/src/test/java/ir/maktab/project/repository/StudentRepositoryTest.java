package ir.maktab.project.repository;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Student;
import ir.maktab.project.domain.embeddable.StudentExamState;
import ir.maktab.project.domain.enumeration.ExamState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    private final String checker;
    private List<Student> studentList;

    {
        checker = "data";
    }

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        studentList = Arrays.asList(
                Student.builder().firstName("javad")
                        .lastName("zare").build(), Student.builder().firstName("mohammad")
                        .lastName("nasr").build(), Student.builder().firstName("aida")
                        .lastName("fallah").build(), Student.builder().firstName("ali")
                        .lastName("hoseini").build()
        );
        studentRepository.saveAll(studentList);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void dontExistOnCourse() {


        Set<Student> students = new HashSet<>(studentList);
        assertThat(studentRepository.dontExistOnCourse(students, checker))
                .isEmpty();
        assertThat(studentRepository.dontExistOnCourse(null, ""))
                .isNotEmpty();
    }


    @Test
    void getAllStudentWitchParticipatedInExam() {

        Exam exam = Exam.builder().title("divide").description("i want to get divide exam from you are")
                .build();
        saveExamStateOfStudentExam(exam);

        assertThat(studentRepository.getAllStudentWitchParticipatedInExam(exam.getId()))
                /*
                 * total student is 4 and 2 number of student participate in exam then our expected is two */
                .hasSize(2);
    }

    @Transactional
    public void saveExamStateOfStudentExam(Exam exam) {


        entityManager.persist(exam);
        studentList.get(1).getStudentExamStates().add(new StudentExamState(exam.getId(), ExamState.TRYING));
        studentList.get(2).getStudentExamStates().add(new StudentExamState(exam.getId(), ExamState.FINISHED));
    }
}