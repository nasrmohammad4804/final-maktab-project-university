package ir.maktab.project.domain;

import ir.maktab.project.domain.enumeration.ExamState;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Exam {

    private static final String START_TIME="start_time";
    private static final String END_TIME="end_time";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = START_TIME)

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @Column(name = END_TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = Course.COURSE_ID)
    private Course course;

}
