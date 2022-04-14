package mobile.gachonapp.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Result<T> {

    private Response response;
    private T data;

}
