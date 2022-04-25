package mobile.gachonapp.api.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SuccessResponse implements Response {
    LOGIN_SUCCESS(200,"로그인_성공"),
    ASSIGNMENT_LIST_SUCCESS(200,"과제_리스트_성공");

    private final int statusCode;
    private final String message;

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
