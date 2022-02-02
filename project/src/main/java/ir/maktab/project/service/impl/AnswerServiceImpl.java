package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.domain.Answer;
import ir.maktab.project.repository.AnswerRepository;
import ir.maktab.project.service.AnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AnswerServiceImpl extends BaseServiceImpl<Answer, Long, String, AnswerRepository> implements AnswerService {


    @Override
    public Class<Answer> entityClass() {
        return Answer.class;
    }

    public AnswerServiceImpl(AnswerRepository repository) {
        super(repository);
    }
}
