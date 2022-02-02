package ir.maktab.project.config;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.enumeration.RegisterState;
import ir.maktab.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        Optional<User> optional = userService.findUserByUserName(username);
        if (optional.isEmpty())
            throw new UsernameNotFoundException("this userName not found");

        if (optional.get().getRegisterState().equals(RegisterState.WAITING))
            throw new DisabledException("this user not yet confirmed");

        return new SecurityUser(optional.get());
    }
}
