package ir.maktab.project.repository;

import ir.maktab.project.domain.Course;
import ir.maktab.project.domain.Master;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course,Long> {

     long countByName(String courseName);

     @EntityGraph(attributePaths = "students")
     Optional<Course> findById(Long id);

     @EntityGraph(attributePaths = "examList")
     @Query("select c from Course as c where c.id=:id")
     Optional<Course> findWithId(@Param("id") Long id);

     List<Course> findByMaster(Master master);
}
