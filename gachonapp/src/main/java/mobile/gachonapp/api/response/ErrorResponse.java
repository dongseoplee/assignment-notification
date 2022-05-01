package mobile.gachonapp.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorResponse implements Response{

    LOGIN_FAIL(401,"로그인_실패"),
    LOGIN_FAIL_EMPTY_NAME(401,"로그인_실패_비어있는_이름"),
    LOGIN_FAIL_EMPTY_PASSWORD(401,"로그인_실패_비어있는_패스워드"),
    SESSION_EXPIRED(401,"세션_만료");

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
