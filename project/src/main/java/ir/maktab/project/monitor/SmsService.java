package ir.maktab.project.monitor;

import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import ir.maktab.project.smsprovider.SmsRequest;
import ir.maktab.project.util.SmsRequestUtil;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements HealthIndicator {

    @Override
    public Health health() {
        if (isSmsServiceGood(new SmsRequest("09000000000", true, "monitoring sms service ")))
            return Health.up().withDetail("sms service", "sms service is good working").build();

        return Health.down().withDetail("sms service", "sms service dont available").build();
    }

    public boolean isSmsServiceGood(SmsRequest smsRequest) {

        boolean isWork = false;
        try (ResponseBody body = SmsRequestUtil.sendSmsRequest(smsRequest).body()) {

            isWork = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isWork;
    }
}
