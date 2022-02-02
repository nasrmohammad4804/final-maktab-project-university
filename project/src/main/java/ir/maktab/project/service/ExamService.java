package ir.maktab.project.service;

import ir.maktab.project.base.service.BaseService;
import ir.maktab.project.domain.Exam;
import org.springframework.stereotype.Service;

@Service
public interface ExamService extends BaseService<Exam,String,Long> {

    void updateExamIsBeingHeld(Exam exam, Exam updatedExam);

    void deleteExam(Exam exam);

    void updateExamNotStarted(Exam exam, Exam updateExam);

    Exam findWithId(Long examId);
}
