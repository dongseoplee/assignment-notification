package mobile.gachonapp.api.response;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum ErrorResponse implements Response{

    LOGIN_FAIL(401,"로그인_실패"),
    LOGIN_FAIL_EMPTY_NAME(401,"로그인_실패_비어있는_이름"),
    LOGIN_FAIL_EMPTY_PASSWORD(401,"로그인_실패_비어있는_패스워드"),
    SESSION_EXPIRED(401,"세션_만료"),
    NO_DATA(401,"비어있는_데이터_접근"),
    CONNECTION_TIME_OUT(401,"연결시간초과"),
    NotFindSessionException(401,"세션이_존재하지_않습니다");

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
