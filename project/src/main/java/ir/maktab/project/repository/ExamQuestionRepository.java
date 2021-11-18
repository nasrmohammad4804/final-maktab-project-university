package ir.maktab.project.repository;

import ir.maktab.project.domain.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Long> {
}
