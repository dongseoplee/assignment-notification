package mobile.gachonapp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {

    private int statusCode;
    private String message;
    private T data;

    public Result(Response response,T data) {
        message = response.getMessage();
        statusCode = response.getStatusCode();
        this.data = data;
    }

}
