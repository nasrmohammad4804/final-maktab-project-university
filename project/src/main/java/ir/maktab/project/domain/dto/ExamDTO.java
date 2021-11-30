package ir.maktab.project.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDTO {

    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalTime endTime;
}
