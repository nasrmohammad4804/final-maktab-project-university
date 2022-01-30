package ir.maktab.project.smsprovider;

import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsSenderImpl implements SmsSender {

    private final String username = "xx";
    private final String password = "xxx";

    @Override
    public Response send(SmsRequest smsRequest) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "username="+username+"&password="+password+"&to="
                +smsRequest.getTo()+"&text=" + smsRequest.getText() + "&isflash="+smsRequest.isFlash());

        Request request = new Request.Builder()
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
