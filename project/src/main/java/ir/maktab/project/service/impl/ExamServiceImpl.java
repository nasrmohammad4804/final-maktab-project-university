package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.exception.ExamNotFoundException;
import ir.maktab.project.repository.ExamRepository;
import ir.maktab.project.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository repository;


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
        repository.delete(exam);
    }

    @Override
    @Transactional
    public void updateExamNotStarted(Exam exam, Exam updateExam) {
        exam.setStartTime(updateExam.getStartTime());
        exam.setEndTime(updateExam.getEndTime());
        exam.setDescription(updateExam.getDescription());
    }
}