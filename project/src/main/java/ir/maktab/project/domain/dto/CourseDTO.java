package ir.maktab.project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseDTO {

    private Long id;
    private String name;
    private LocalDate courseStartedDate;
    private LocalDate courseFinishedDate;



}
