package ir.maktab.project.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oAuth2User;
    private static final String GMAIL_SUFFIX="@gmail.com";

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        /* this method return name of user or name of google account */
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {

        String googleOauth = oAuth2User.getAttribute("email");
        String gitHubOauth = addGmailSuffix(Objects.requireNonNull(oAuth2User.getAttribute("login")));
        return googleOauth == null ? gitHubOauth : googleOauth;
    }
    private String addGmailSuffix(String gitHubId){

        return gitHubId.concat(GMAIL_SUFFIX);
    }
}
