package ir.maktab.project.service;

import ir.maktab.project.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public interface CourseService {

    void addCourse(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAll();
    void deleteUser(Course course, Student student);
    void addStudent(Course course, Student student);
    List<Course> findByMaster(Master master);
    Optional<Course> findWithId(Long id);
    void addExam(Course course, Exam exam);
    boolean checkCreateCourse(Course course);

}
