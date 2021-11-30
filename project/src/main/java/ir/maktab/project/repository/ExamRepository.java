package ir.maktab.project.repository;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.ExamQuestion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {

    @EntityGraph(attributePaths = "examQuestionList")
    @Query("select e from Exam as e where e.id=:examId ")
    Optional<Exam> findWithId( Long examId );


}
