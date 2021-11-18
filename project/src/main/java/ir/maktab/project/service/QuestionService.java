package ir.maktab.project.service;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionService {
    List<Question> findByTitle(String title);

    Optional<Question> findById(Long id);
    boolean checkExamHasQuestion(Exam exam,Question question);
    void changeScore(Exam exam, List<Double> updatedScore);
}
