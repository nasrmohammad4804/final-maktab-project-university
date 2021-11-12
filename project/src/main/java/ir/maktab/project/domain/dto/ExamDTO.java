package ir.maktab.project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Long id;

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalTime endTime;
}
