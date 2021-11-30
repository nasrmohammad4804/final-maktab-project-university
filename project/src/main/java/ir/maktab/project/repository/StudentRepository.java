package ir.maktab.project.repository;

import ir.maktab.project.domain.Answer;
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
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("select s from Student as s where (:checker='' or s not in (:users)) ")
    List<Student> dontExistOnCourse(@Param("users") Set<Student> users, String checker);

    @EntityGraph(attributePaths = "courseSet")
    @Query("select s from Student as s where s.id=:id")
    Optional<Student> findWithId(Long id);

    @EntityGraph(attributePaths = "studentExamStates")
    @Query("select s from Student as s where s.id=:id")
    Optional<Student> findWithAllStudentExamState(Long id);

    @EntityGraph(attributePaths = "answerList")
    @Query("select s from Student as s where s.id=:id")
    Optional<Student> findWithAllAnswerList(Long id);

    @Query("select a from Exam  as e join  e.examQuestionList as eq join eq.answerList as a join a.student as s where s.id=:studentId and e.id=:examId")
    List<Answer> findAllAnswerOfExam(Long studentId,Long examId);

    @Query("select s from Student as s left join s.studentExamStates as ses where ses.examId=:examId and (ses.examState in ('TRYING','FINISHED') )")
     List<Student> getAllStudentWitchParticipatedInExam(Long examId);
}
