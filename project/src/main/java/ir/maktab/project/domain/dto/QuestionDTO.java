package ir.maktab.project.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {

    private Long id;
    private String questionText;
    private String title;
    private double score;
}
