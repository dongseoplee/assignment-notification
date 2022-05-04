package mobile.gachonapp.api.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResultSuccess<T> extends Result {
    private T data;

    public ResultSuccess(Response response, T data) {
        super(response);
        this.data = data;
    }

}
