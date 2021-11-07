package ir.maktab.project.repository;

import ir.maktab.project.domain.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course,Long> {

     long countByName(String courseName);

     @EntityGraph(attributePaths = "students")
     Optional<Course> findById(Long id);
}
