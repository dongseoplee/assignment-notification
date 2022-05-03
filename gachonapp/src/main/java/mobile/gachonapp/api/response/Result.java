package mobile.gachonapp.api.response;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class Result<T> {

    private int statusCode;
    private String message;
    private T data;

    public Result(Response response,T data) {
        statusCode = response.getStatusCode();
        message = response.getMessage();
        this.data = data;
    }
}
