package ir.maktab.project.service;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {

   User save(User user);

   Optional<User> findUserByUserName(String userName);


    List<User> findAllUserExceptManager();

    Optional<User> findById(Long id);

    void changeProfile(User user,String newRoleName);

    List<User> findAllMaster();


    void update(User user);

    void updateEntityNameOfTable( String name, Long id);

    List<User> searchUser(UserSearchRequestDTO dto);

    List<User> getAll();

}