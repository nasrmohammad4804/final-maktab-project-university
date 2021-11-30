package ir.maktab.project.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionsWithStudentDescriptiveAnswerDTO {

    private List<QuestionDTO> questionDTOList;
    private List<DescriptiveAnswerDTO> descriptiveAnswerDTOList;
}
