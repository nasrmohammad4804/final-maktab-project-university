package ir.maktab.project.repository;

import ir.maktab.project.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "select  * from Question as q where q.title like  %:title% and q.question_type='Question'",nativeQuery = true)
    List<Question> findByTitle(String title);

}
