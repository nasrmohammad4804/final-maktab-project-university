package ir.maktab.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private static final String START_COURSE_DATE ="course_started_date";
    private static final String FINISHED_COURSE_DATE = "course_finished_date";
    private static final String TEMP_STUDENT_COURSE="course_student";
    private static final String COURSE_NUMBER="course_number";
    private static final String GROUP_NUMBER="group_number";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name =Master.MASTER_ID )
    private Master master;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = TEMP_STUDENT_COURSE)
    private Set<Student> students=new HashSet<>();

    public Course(Long id) {
        this.id = id;
    }
}
