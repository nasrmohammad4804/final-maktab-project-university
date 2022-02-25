package ir.maktab.project.util;

import com.squareup.okhttp.*;
import ir.maktab.project.smsprovider.SmsRequest;

import java.io.IOException;

public class SmsRequestUtil {

    public static final String USERNAME = "xx";
    public static final String PASSWORD = "xxx";

    public static Response sendSmsRequest(SmsRequest smsRequest) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "username=" + USERNAME + "&password=" + PASSWORD + "&to="
                + smsRequest.getTo() + "&text=" + smsRequest.getText() + "&isflash=" + smsRequest.isFlash());

        Request request= new Request.Builder()
                /*dont get url and username and password for security problem*/
                .url("xxx")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "c26ca3b0-9f44-3cdf-9da3-60c86a9f75b3")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

       return client.newCall(request).execute();
    }
}
