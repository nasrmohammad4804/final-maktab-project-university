package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Course;
import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.Student;
import ir.maktab.project.repository.CourseRepository;
import ir.maktab.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    @Override
    public void addCourse(Course course) {
        long count = courseRepository.countByName(course.getName());
        course.setGroupNumber(++count);
        courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Course course, Student student) {

        course.getStudents().forEach(x -> {
            if(x.getId().equals(student.getId()))
                course.getStudents().remove(x);
        } );

    }

    @Override
    @Transactional
    public void addStudent(Course course, Student student) {
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    @Override
    public List<Course> findByMaster(Master master) {
        return courseRepository.findByMaster(master);
    }

    @Override
    public Optional<Course> findWithId(Long id) {
        return courseRepository.findWithId(id);
    }

    @Override
    @Transactional
    public void addExam(Course course, Exam exam) {
        course.getExamList().add(exam);
        exam.setCourse(course);
    }

    @Override
    public boolean checkCreateCourse(Course course) {

        Predicate<LocalDate> predicate= (x -> LocalDate.now().isAfter(x)  );
        Predicate<LocalDate> finalPredicate= predicate.or(x -> course.getCourseFinishedDate().isBefore(x));

      return   finalPredicate.test(course.getCourseStartedDate());
    }
}
