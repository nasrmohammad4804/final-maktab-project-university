package ir.maktab.project.repository;

import ir.maktab.project.domain.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query("select s from Student as s where (:checker='' or s not in (:users)) " )
     List<Student> dontExistOnCourse(@Param("users") Set<Student> users,String checker);

    @EntityGraph(attributePaths = "courseSet")
    @Query("select s from Student as s where s.id=:id")
     Optional<Student> findWithId(Long id);
}
