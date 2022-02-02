package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.Question;
import ir.maktab.project.repository.QuestionRepository;
import ir.maktab.project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl extends BaseServiceImpl<Question,Long,String,QuestionRepository> implements QuestionService {



    public QuestionServiceImpl(QuestionRepository repository) {
        super(repository);
    }

    @Override
    public List<Question> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public boolean checkExamHasQuestion(Exam exam, Question question) {

        Optional<ExamQuestion> examQuestion = exam.getExamQuestionList().stream().filter(x -> x.getQuestionText().equals(question.getQuestionText())
                && x.getTitle().equals(question.getTitle())).findAny();
        return examQuestion.isPresent();
    }

    @Override
    @Transactional
    public void changeScore(Exam exam, List<Double> updatedScore) {

        AtomicInteger index = new AtomicInteger(0);
        exam.getExamQuestionList().forEach(examQuestion -> {
            int myIndex = index.getAndIncrement();
            if (exam.getExamQuestionList().get(myIndex).getScore() != updatedScore.get(myIndex))
                exam.getExamQuestionList().get(myIndex).setScore(updatedScore.get(myIndex));
            index.set(++myIndex);
        });
    }

    @Override
    public Class<Question> entityClass() {
        return Question.class;
    }
}
