package ir.maktab.project.service;

import ir.maktab.project.base.service.BaseService;
import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService  extends BaseService<Question,String,Long> {
    List<Question> findByTitle(String title);

    boolean checkExamHasQuestion(Exam exam,Question question);
    void changeScore(Exam exam, List<Double> updatedScore);
}
