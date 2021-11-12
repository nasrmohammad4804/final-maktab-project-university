package ir.maktab.project.service.impl;

import ir.maktab.project.domain.*;
import ir.maktab.project.repository.CourseRepository;
import ir.maktab.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
