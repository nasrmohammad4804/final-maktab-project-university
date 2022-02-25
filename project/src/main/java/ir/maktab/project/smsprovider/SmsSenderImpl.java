package ir.maktab.project.smsprovider;

import com.squareup.okhttp.*;
import ir.maktab.project.logingmanagement.AdviceLog;
import ir.maktab.project.util.SmsRequestUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsSenderImpl implements SmsSender {

    @Override
    @AdviceLog
    public Response send(SmsRequest smsRequest) throws IOException {

        return SmsRequestUtil.sendSmsRequest(smsRequest);
    }
}
