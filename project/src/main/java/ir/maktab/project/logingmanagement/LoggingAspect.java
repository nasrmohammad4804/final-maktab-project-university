package ir.maktab.project.logingmanagement;

import ir.maktab.project.smsprovider.SmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(ir.maktab.project.logingmanagement.AdviceLog)")
    public void matchAllAdviceMethod() {

    }

    @Around("execution(public * ir.maktab.project.config.CustomAuthenticationProvider.authenticate(..))")
    public Object adviceAuthenticationMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("before authenticate method execute with argument {}", Arrays.toString(joinPoint.getArgs()));

        Authentication authentication;

        try {
            Object result = joinPoint.proceed();
            authentication = (Authentication) result;

            log.info("authentication with userName : {}   successfully", authentication.getPrincipal());

            return authentication;

        } catch (Exception throwable) {
            log.warn("with username and password dont authenticate");
            throw throwable;
        }

    }

    @AfterThrowing(pointcut = "matchAllAdviceMethod()", throwing = "ex")
    public void adviceFailingSendSms(JoinPoint joinPoint, Throwable ex) {
        SmsRequest smsRequest = (SmsRequest) joinPoint.getArgs()[0];
        log.error("can't send sms to {} with cause by : {} ", smsRequest.getTo(), ex.getMessage());
    }

    @AfterReturning(pointcut = "matchAllAdviceMethod()")
    public void adviceSuccessfulSendSms(JoinPoint joinPoint) {
        SmsRequest smsRequest = (SmsRequest) joinPoint.getArgs()[0];
        log.info("sms successfully sent to : {}", smsRequest.getTo());
    }

    @Around("execution(public * ir.maktab.project.service.EmailService.sendEmail(..))")
    public Object adviceSendEmailForChangePassword(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("before send email ");
        String emailAddress = String.valueOf(joinPoint.getArgs()[0]);

        try {
            Object result = joinPoint.proceed();
            log.info("sent email successfully to : {}", emailAddress);
            return result;

        } catch (Throwable e) {
            log.error("can't send email with token for changing password account to : {}", emailAddress);
            throw e;
        }

    }

}
