package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.domain.Exam;
import ir.maktab.project.exception.ExamNotFoundException;
import ir.maktab.project.repository.ExamRepository;
import ir.maktab.project.service.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional(readOnly = true)
public class ExamServiceImpl extends BaseServiceImpl<Exam, Long, String, ExamRepository> implements ExamService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Class<Exam> entityClass() {
        return Exam.class;
    }

    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
    }

    @Override
    public Exam findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ExamNotFoundException("this exam dont exists"));
    }

    @Override
    @Transactional
    public void updateExamIsBeingHeld(Exam exam, Exam updatedExam) {
        exam.setEndTime(updatedExam.getEndTime());
        exam.setDescription(updatedExam.getDescription());
    }

    @Override
    @Transactional
    public void deleteExam(Exam exam) {

        entityManager.remove(exam);
    }

    @Override
    @Transactional
    public void updateExamNotStarted(Exam exam, Exam updateExam) {
        exam.setStartTime(updateExam.getStartTime());
        exam.setEndTime(updateExam.getEndTime());
        exam.setDescription(updateExam.getDescription());
    }

    @Override
    public Exam findWithId(Long examId) {
        return repository.findWithId(examId).orElseThrow(() -> new ExamNotFoundException("this exam dont exists"));
    }
}
