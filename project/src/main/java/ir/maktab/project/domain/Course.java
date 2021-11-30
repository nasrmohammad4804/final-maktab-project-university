package ir.maktab.project.domain;

import ir.maktab.project.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity<Long> {

    private static final String START_COURSE_DATE = "course_started_date";
    private static final String FINISHED_COURSE_DATE = "course_finished_date";
    private static final String TEMP_STUDENT_COURSE = "course_student";
    private static final String COURSE_NUMBER = "course_number";
    private static final String GROUP_NUMBER = "group_number";
    public static final String COURSE_ID = "course_id";


    private String name;

    @Column(name = COURSE_NUMBER,nullable = false)
    private Long courseNumber;

    @Column(name = GROUP_NUMBER)
    private Long groupNumber;


    @Column(name = START_COURSE_DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate courseStartedDate;

    @Column(name = FINISHED_COURSE_DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate courseFinishedDate;

    @ManyToOne
    @JoinColumn(name = Master.MASTER_ID)
    private Master master;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = TEMP_STUDENT_COURSE)
    private Set<Student> students = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Exam> examList = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = COURSE_ID)
    private List<Question> questionBank = new ArrayList<>();
}
