package ir.maktab.project.domain;

import ir.maktab.project.base.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Exam extends BaseEntity<Long> {

    private static final String START_TIME="start_time";
    private static final String END_TIME="end_time";
    private static final String EXAM_ID="exam_id";

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = EXAM_ID)
    private List<ExamQuestion> examQuestionList=new LinkedList<>();

}
