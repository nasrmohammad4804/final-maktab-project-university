package ir.maktab.project.domain;

import ir.maktab.project.domain.embeddable.StudentExamState;
import ir.maktab.project.domain.enumeration.RegisterState;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    private static final String STUDENT_CODE = "student_code";
    private static final String FATHER_NAME = "father_name";
    public static final String STUDENT_ID = "student_id";
    private static final String STUDENT_EXAM_STATE = "student_exam_state";

    @Column(name = FATHER_NAME)
    private String fatherName;

    @Column(name = STUDENT_CODE)
    private String studentCode;

    @Builder
    public Student(String firstName, String lastName, String userName, String password, RegisterState registerState, Boolean isActive, String phoneNumber) {
        super(firstName, lastName, userName, password, registerState, isActive, phoneNumber);
    }

    @ManyToMany(mappedBy = "students")
    private Set<Course> courseSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Answer> answerList = new ArrayList<>();

    @ElementCollection
    @JoinTable(name = STUDENT_EXAM_STATE, joinColumns = @JoinColumn(name = STUDENT_ID))
    private List<StudentExamState> studentExamStates = new ArrayList<>();
}
