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

    List<User> findAllMaster();

    void update(User user);

    void updateEntityNameOfTable(String name, Long id);

    List<User> searchUser(UserSearchRequestDTO dto);

    String register(User user);

    void changeProfile(User user, User updatedUser);

    Optional<User> getByResetPasswordToken(String token);

    void updateResetPasswordToken(String token, String email);

    void updatePassword(User user, String newPassword);

    boolean isRegisterValid(String userName);

}