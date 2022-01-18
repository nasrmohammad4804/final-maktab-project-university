package ir.maktab.project.repository;

import ir.maktab.project.domain.Course;
import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.enumeration.RegisterState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MasterRepository masterRepository;

    private  Course course;
    private final String courseName="javad";

    @BeforeEach
    void setUp() {

        course = Course.builder().courseNumber(321L).name(courseName)
                .build();
        courseRepository.save(course);
    }

    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
        course=null;
    }

    @Test
    void countByName() {

        assertThat(courseRepository.countByName(courseName))
        .isNotZero();
    }

    @Test
    void findByMaster() {
        Master master=Master.builder().registerState(RegisterState.CONFIRM)
                .firstName("javad")
                .lastName("zare").userName("javad1234")
                .build();

        course.setMaster(master);
        masterRepository.save(master);

        assertThat(
                courseRepository.findByMaster(master)
        ).isNotEmpty();
    }
}