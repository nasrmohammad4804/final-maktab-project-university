package ir.maktab.project.service.impl;

import ir.maktab.project.domain.Manager;
import ir.maktab.project.domain.Role;
import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import ir.maktab.project.repository.UserRepository;
import ir.maktab.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository  userRepository;

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

/*    @Override
    public Page<User> findAll(Optional<Integer> page,Optional<String> sortBy) {

      return  userRepository.findAll(PageRequest.of(page.orElse(0),5,Sort.Direction.ASC,sortBy.orElse("id")));
    }*/

    @Override
    public List<User> findAllUserExceptManager() {
        return userRepository.findAll().stream().filter(x -> !(x instanceof Manager))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void changeProfile(User user,String newRoleName) {

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
        userRepository.updateEntityNameOfTable(name,id);
    }

    @Override
    public List<User> searchUser(UserSearchRequestDTO dto) {
        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=builder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(getPredicate(dto,builder,root));
        return entityManager.createQuery(criteriaQuery).getResultList().stream().filter(x -> !(x instanceof Manager))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    private Predicate getPredicate(UserSearchRequestDTO dto, CriteriaBuilder builder, Root<User> root) {
        List<Predicate> predicates=new ArrayList<>();

        searchingWithFirstName(predicates,builder,root,dto.getFirstName());
        searchingWithLastName(predicates,builder,root,dto.getLastName());
        searchingWithRole(predicates,builder,root,dto.getRole());
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private void searchingWithFirstName(List<Predicate> predicates, CriteriaBuilder builder, Root<User> root, String firstName) {

        if(!firstName.isEmpty()){
            predicates.add(builder.like(root.get("firstName"),"%"+firstName+"%"));
        }
    }

    private void searchingWithLastName(List<Predicate> predicates, CriteriaBuilder builder, Root<User> root, String lastName) {
        if(!lastName.isEmpty()){
            predicates.add(builder.like(root.get("lastName"),"%"+lastName+"%"));
        }
    }

    private void searchingWithRole(List<Predicate> predicates, CriteriaBuilder builder, Root<User> root, String role) {
        if(!role.isEmpty()){
           Join<User, Role> join=  root.join("role");
            predicates.add(builder.like(join.get("name"),"%"+role+"%"));
        }
    }
}
