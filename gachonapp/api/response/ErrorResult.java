package mobile.gachonapp.api.response;

public class ErrorResult {
    private int statusCode;
    private String message;

    public ErrorResult(Response response) {
        message = response.getMessage();
        statusCode = response.getStatusCode();
    }
}
