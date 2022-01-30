package ir.maktab.project.smsprovider;

import com.squareup.okhttp.Response;

import java.io.IOException;

public interface SmsSender {
    Response send(SmsRequest request) throws IOException;
}
