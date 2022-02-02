package ir.maktab.project.service.impl;

import ir.maktab.project.base.service.impl.BaseServiceImpl;
import ir.maktab.project.controller.ManagerController;
import ir.maktab.project.domain.*;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.domain.enumeration.UserType;
import ir.maktab.project.exception.UserNotFoundException;
import ir.maktab.project.repository.UserRepository;
import ir.maktab.project.service.EmailService;
import ir.maktab.project.service.RoleService;
import ir.maktab.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
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
public class UserServiceImpl extends BaseServiceImpl<User,Long,String,UserRepository> implements UserService {

    @Autowired
    private RoleService roleService;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> findUserByUserName(String userName) {
        return repository.findUserByUserName(userName);
    }

    @Transactional
    protected void changeProfile(User user, String newRoleName) {

        saveOrUpdate(user);
        updateEntityNameOfTable(newRoleName, user.getId());
    }

    @Override
    public List<User> findAllMaster() {
        return repository.findAllMaster();
    }

    @Override
    @Transactional
    public void updateEntityNameOfTable(String name, Long id) {
        repository.updateEntityNameOfTable(name, id);
    }

    @Override
    public Page<UserSearchResponseDTO> searchUser(int pageNumber,String sortField,String sortDirection, UserSearchRequestDTO dto) {

        Pageable pageable= PageRequest.of(pageNumber-1, ManagerController.DEFAULT_PAGE_SIZE,getSort(sortField,sortDirection));

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(getPredicate(dto, builder, root));
        List<User> users= entityManager.createQuery(criteriaQuery).getResultList().stream().filter(x -> !(x instanceof Manager))
                .collect(Collectors.toList());

        return new PageImpl<>(
                findUserSearchResponseDtoOfPage(pageNumber,ManagerController.DEFAULT_PAGE_SIZE),pageable,users.size()
        );
    }

    private List<UserSearchResponseDTO> findUserSearchResponseDtoOfPage(int pageNumber,int pageSize){

       return entityManager.createQuery( "select new ir.maktab.project.domain.dto.UserSearchResponseDTO(u.id,u.firstName,u.lastName,u.userName,u.registerState) from User as u where u.role.name in('master','student')")
                .setMaxResults(pageSize).setFirstResult(pageNumber * pageSize)
                .getResultList();
    }

    @Override
    @Transactional
    public String register(User user) {
        if (user.getUserType().equals(UserType.MASTER)) {

            Master master = Master.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                    .userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword())).isActive(user.getIsActive())
                    .registerState(RegisterState.WAITING).phoneNumber(user.getPhoneNumber()).build();

            master.setRole(roleService.findRoleByName("master"));
            saveOrUpdate(master);

            return "master/masterPanel";
        } else {
            Student student = Student.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                    .userName(user.getUserName()).password(passwordEncoder.encode(user.getPassword())).isActive(user.getIsActive())
                    .registerState(RegisterState.WAITING).phoneNumber(user.getPhoneNumber()).build();
            student.setRole(roleService.findRoleByName("student"));
            saveOrUpdate(student);

            return "student/studentPanel";
        }
    }

    @Override
    public boolean isRegisterValid(String userName) {

        return findUserByUserName(userName).isPresent() || userName.equals(EmailService.UNIVERSITY_EMAIL);
    }

    @Override
    public Page<User> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Pageable pageable= PageRequest.of(pageNumber-1,pageSize,getSort(sortField,sortDirection));

       return repository.findAllByEntityNameContains(new String[] {"Master","Student"},pageable);
    }
    private Sort getSort(String sortField,String sortDirection){

       return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
             Sort.by(sortField).ascending() : Sort.by(sortField).descending();
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

            } else this.saveOrUpdate(user);

        } else {

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());

            if (updatedUser.getUserType().equals(UserType.MASTER)) {
                user.setRole(roleService.findRoleByName("master"));
                this.changeProfile(user, "Master");

            } else saveOrUpdate(user);

        }

    }

    @Override
    public Optional<User> getByResetPasswordToken(String token) {
        return repository.findByResetPasswordToken(token);
    }

    @Override
    @Transactional
    public void updateResetPasswordToken(String token, String email) {
        Optional<User> user = repository.findUserByUserName(email);
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
        repository.save(user);
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

    @Override
    public Class<User> entityClass() {
        return User.class;
    }
}
