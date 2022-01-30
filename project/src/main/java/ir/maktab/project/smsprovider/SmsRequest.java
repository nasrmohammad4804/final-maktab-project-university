package ir.maktab.project.smsprovider;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsRequest {

    private String to;
    private boolean isFlash;
    private String text;
}
