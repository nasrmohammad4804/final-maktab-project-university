package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Answer;
import ir.maktab.project.repository.AnswerRepository;
import ir.maktab.project.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Override
    @Transactional
    public void saveOrUpdate(Answer answer) {
        repository.save(answer);
    }
}
