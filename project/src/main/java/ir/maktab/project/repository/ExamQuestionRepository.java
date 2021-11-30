package ir.maktab.project.repository;

import ir.maktab.project.domain.ExamQuestion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {

    @EntityGraph(attributePaths = "answerList")
    @Query("select eq from ExamQuestion as eq join eq.answerList as a where a.student is null and eq.exam.id=:examId")
    List<ExamQuestion> getAllExamQuestion(@Param("examId") Long examId);


}
