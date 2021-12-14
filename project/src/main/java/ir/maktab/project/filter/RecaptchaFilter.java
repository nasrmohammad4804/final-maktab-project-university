package ir.maktab.project.filter;

import ir.maktab.project.exception.RecaptchaNotValidException;
import ir.maktab.project.service.RecaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecaptchaFilter extends UsernamePasswordAuthenticationFilter {


    private final RecaptchaService captchaService;


    public RecaptchaFilter(RecaptchaService recaptchaService) {
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
        this.captchaService=recaptchaService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

       String recaptchaResponse= request.getParameter("g-recaptcha-response");

        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
                captchaService.verifyRecaptcha(ip, recaptchaResponse);

        if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {

            throw new RecaptchaNotValidException(captchaVerifyMessage);
        }

        return super.attemptAuthentication(request,response);
    }
}

