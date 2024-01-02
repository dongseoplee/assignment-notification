package mobile.gachonapp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoDataResult {

    private int statusCode;
    private String message;

    public NoDataResult(Response response) {
        message = response.getMessage();
        statusCode = response.getStatusCode();
    }
}
