package ir.maktab.project.monitor;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static ir.maktab.project.util.ReadProjectInfoUtil.readProjectInformation;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class InfoEndpointWebExtension {

    @ReadOperation
    public WebEndpointResponse<Object> getInfoApplication() throws IOException {

        return new WebEndpointResponse<>(readProjectInformation(), HttpStatus.OK.value());
    }

}
