package ir.maktab.project.mapper;

import ir.maktab.project.domain.Exam;
import ir.maktab.project.domain.dto.ExamDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapper {
    ExamDTO convertEntityToExamDTO(Exam exam);
}
