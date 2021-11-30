package ir.maktab.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.maktab.project.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Exam extends BaseEntity<Long> {

    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    public static final String EXAM_ID = "exam_id";

    protected String title;
    protected String description;

    @Column(name = START_TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime startTime;

    @Column(name = END_TIME)
    @DateTimeFormat(pattern = "HH:mm")
    protected LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = Course.COURSE_ID)
    protected Course course;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "exam")
    @JsonManagedReference
    protected List<ExamQuestion> examQuestionList = new LinkedList<>();

    @Column(columnDefinition = "tinyint(1)")
    protected boolean isComplete;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", course=" + course +
                ", examQuestionList=" + examQuestionList +
                ", isComplete=" + isComplete +
                '}';
    }
}
