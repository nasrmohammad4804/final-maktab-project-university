package ir.maktab.project.domain;

import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = User.ENTITY_NAME)
@Table(uniqueConstraints = @UniqueConstraint(name = "define_unique",columnNames = {User.USER_NAME}))
public  class User {

    public static final String ENTITY_NAME = "entity_name";

    private static final String FIRST_NAME = "first_name";
    private static final String lAST_NAME = "last_name";
    public static final String USER_NAME = "user_name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(name = FIRST_NAME)
    protected String firstName;

    @Column(name = lAST_NAME)
    protected String lastName;

    @Column(name = USER_NAME)
    protected String userName;

    protected String password;

    @Enumerated(value = EnumType.STRING)
    protected RegisterState registerState;

    @Column(columnDefinition = "tinyint(1)")
    protected Boolean isActive;


    @ManyToOne
    @JoinColumn(name = Role.ROLE_ID)
    protected Role role;

    @Transient
    private UserType userType;

    public User(String firstName, String lastName, String userName, String password, RegisterState registerState, Boolean isActive, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.registerState = registerState;
        this.isActive = isActive;
        this.role = role;
    }


    public User(String firstName, String lastName, String userName, String password, RegisterState registerState, Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.registerState = registerState;
        this.isActive = isActive;
    }


}
