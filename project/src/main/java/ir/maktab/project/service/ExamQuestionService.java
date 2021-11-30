package ir.maktab.project.service;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ExamQuestionService {

    void addExamQuestionFromQuestionBank(Exam exam, Question question);
    void addNewDescriptiveQuestion(Exam exam, ExamQuestion examQuestion);
    void addNewTestQuestion(Exam exam, Map<String,?> map);
    ExamQuestion findById(Long id);
    List<ExamQuestion> getAllQuestion(Long examId);

    List<ExamQuestion> findAllDescriptiveQuestion(Long examId);
}
