package ir.maktab.project.service;

import ir.maktab.project.domain.Exam;
import org.springframework.stereotype.Service;

@Service
public interface ExamService {

    Exam findById(Long id);

    void updateExamIsBeingHeld(Exam exam, Exam updatedExam);

    void deleteExam(Exam exam);

    void updateExamNotStarted(Exam exam, Exam updateExam);

    Exam findWithId(Long examId);
}
