package ir.maktab.project.mapper;

import ir.maktab.project.domain.DescriptiveAnswer;
import ir.maktab.project.domain.ExamQuestion;
import ir.maktab.project.domain.dto.ExamQuestionsWithStudentDescriptiveAnswerDTO;

import java.util.List;

public interface ExamQuestionsWithStudentDescriptiveAnswersMapper {

    ExamQuestionsWithStudentDescriptiveAnswerDTO convertEntityToExamQuestionWithDescriptiveAnswer(List<ExamQuestion> examQuestions, List<DescriptiveAnswer> answers);

}
