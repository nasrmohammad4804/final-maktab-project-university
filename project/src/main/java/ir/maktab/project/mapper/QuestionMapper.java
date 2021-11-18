package ir.maktab.project.mapper;

import ir.maktab.project.domain.Question;
import ir.maktab.project.domain.dto.QuestionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<QuestionDTO> convertEntitiesToQuestionDTO(List<Question> list);
}
