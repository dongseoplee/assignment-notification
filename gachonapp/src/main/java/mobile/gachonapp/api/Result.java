package mobile.gachonapp.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Result<T> {

    private int statusCode;
    private ResponseMessage responseMessage;
    private T data;

}
