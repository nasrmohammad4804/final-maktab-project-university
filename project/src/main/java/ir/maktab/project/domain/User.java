package ir.maktab.project.domain;

import ir.maktab.project.base.BaseEntity;
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
public  class User extends BaseEntity<Long> {

    public static final String ENTITY_NAME = "entity_name";
    public static final String USER_ID="user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String lAST_NAME = "last_name";
    public static final String USER_NAME = "user_name";
    private static final String RESET_PASSWORD_TOKEN="reset_password_token";

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

    @Column(name =RESET_PASSWORD_TOKEN )
    protected String resetPasswordToken;

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

    public User(Long id) {
        super(id);
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
