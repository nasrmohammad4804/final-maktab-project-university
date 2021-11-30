package ir.maktab.project.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescriptiveAnswerDTO {

    private String answerText;
    private Long questionId;
    private double score;

}
