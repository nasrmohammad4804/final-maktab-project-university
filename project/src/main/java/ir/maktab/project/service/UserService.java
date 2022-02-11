package ir.maktab.project.service;

import ir.maktab.project.base.service.BaseService;
import ir.maktab.project.config.oauth.CustomOAuth2User;
import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserSearchRequestDTO;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends BaseService<User,String,Long> {

    Optional<User> findUserByUserName(String userName);


    List<User> findAllMaster();

    void updateEntityNameOfTable(String name, Long id);

    Page<UserSearchResponseDTO> searchUser(int pageNumber,String sortField,String sortDirection, UserSearchRequestDTO dto);

    String register(User user);

    void changeProfile(User user, User updatedUser);

    Optional<User> getByResetPasswordToken(String token);

    void updateResetPasswordToken(String token, String email);

    void updatePassword(User user, String newPassword);

    boolean isRegisterValid(String userName);

    Page<User> findPaginated(int pageNumber,int pageSize,String sortField,String sortDirection);

    void oauthAuthentication(CustomOAuth2User oAuth2User);
}