package ir.maktab.project.service.impl;

import ir.maktab.project.domain.*;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.domain.enumeration.UserType;
import ir.maktab.project.exception.UserNotFoundException;
import ir.maktab.project.repository.UserRepository;
import ir.maktab.project.service.EmailService;
import ir.maktab.project.service.RoleService;
import ir.maktab.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public List<User> findAllUserExceptManager() {
        return userRepository.findAll().stream().filter(x -> !(x instanceof Manager))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Transactional
    protected void changeProfile(User user, String newRoleName) {

        save(user);
        updateEntityNameOfTable(newRoleName, user.getId());
    }

    @Override
    public List<User> findAllMaster() {
        return userRepository.findAllMaster();
    }


    @Override
    @Transactional
    public void update(User user) {
        save(user);
    }

    @Override
    @Transactional
    public void updateEntityNameOfTable(String name, Long id) {
        userRepository.updateEntityNameOfTable(name, id);
    }

    @Override
    public List<User> searchUser(UserSearchRequestDTO dto) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(getPredicate(dto, builder, root));
        return entityManager.createQuery(criteriaQuery).getResultList().stream().filter(x -> !(x instanceof Manager))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String register(User user) {
        if (user.getUserType().equals(UserType.MASTER)) {

            Master master = Master.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                    .userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword())).isActive(user.getIsActive())
                    .registerState(RegisterState.WAITING).build();

            master.setRole(roleService.findRoleByName("master"));
            save(master);

            return "master/masterPanel";
        } else {
            Student student = Student.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                    .userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword())).isActive(user.getIsActive())
                    .registerState(RegisterState.WAITING).build();
            student.setRole(roleService.findRoleByName("student"));
            save(student);

            return "student/studentPanel";
        }
    }

    @Override
    public boolean isRegisterValid(String userName) {

        return findUserByUserName(userName).isPresent() || userName.equals(EmailService.UNIVERSITY_EMAIL);
    }

    @Override
    @Transactional
    public void changeProfile(User user, User updatedUser) {
        if (user.getRole().getName().equals("master")) {

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());

            if (updatedUser.getUserType().equals(UserType.STUDENT)) {

                user.setRole(roleService.findRoleByName("student"));
                this.changeProfile(user, "Student");

            } else this.save(user);

        } else {

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());

            if (updatedUser.getUserType().equals(UserType.MASTER)) {
                user.setRole(roleService.findRoleByName("master"));
                this.changeProfile(user, "Master");

            } else save(user);

        }

    }

    @Override
    public Optional<User> getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    @Transactional
    public void updateResetPasswordToken(String token, String email) {
        Optional<User> user = userRepository.findUserByUserName(email);
        user.ifPresentOrElse(myUser ->
                myUser.setResetPasswordToken(token), () -> {
            throw new UserNotFoundException("could not found email with " + email);
        });
    }

    @Override
    @Transactional
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    private Predicate getPredicate(UserSearchRequestDTO dto, CriteriaBuilder builder, Root<User> root) {
        List<Predicate> predicates = new ArrayList<>();

        searchingWithFirstName(predicates, builder, root, dto.getFirstName());
        searchingWithLastName(predicates, builder, root, dto.getLastName());
        searchingWithRole(predicates, builder, root, dto.getRole());
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private void searchingWithFirstName(List<Predicate> predicates, CriteriaBuilder builder, Root<User> root, String firstName) {

        if (!firstName.isEmpty()) {
            predicates.add(builder.like(root.get("firstName"), "%" + firstName + "%"));
        }
    }

    private void searchingWithLastName(List<Predicate> predicates, CriteriaBuilder builder, Root<User> root, String lastName) {
        if (!lastName.isEmpty()) {
            predicates.add(builder.like(root.get("lastName"), "%" + lastName + "%"));
        }
    }

    private void searchingWithRole(List<Predicate> predicates, CriteriaBuilder builder, Root<User> root, String role) {
        if (!role.isEmpty()) {
            Join<User, Role> join = root.join("role");
            predicates.add(builder.like(join.get("name"), "%" + role + "%"));
        }
    }
}
