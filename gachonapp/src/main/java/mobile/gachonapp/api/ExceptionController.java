package mobile.gachonapp.api;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.ErrorResponse;
import mobile.gachonapp.api.response.ResultError;
import mobile.gachonapp.api.response.ResultSuccess;
import mobile.gachonapp.exception.ConnectionTimeoutException;
import mobile.gachonapp.exception.NotFindSessionException;
import mobile.gachonapp.exception.SessionExpiredException;
import mobile.gachonapp.exception.WrongLoginUserException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    //로그인시 userId 와 password가 입력되지 않은 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultError loginValidException(MethodArgumentNotValidException ex) {
        String errorField = ex.getBindingResult().getFieldError().getField();

        if (errorField.equals("userId")) {
            return new ResultError(ErrorResponse.LOGIN_FAIL_EMPTY_NAME);
        }

        if (errorField.equals("password")) {
            return new ResultError(ErrorResponse.LOGIN_FAIL_EMPTY_PASSWORD);
        }
        return null;
    }

    @ExceptionHandler(WrongLoginUserException.class)
    public ResultError WrongLoginUserException(WrongLoginUserException ex) {
        return new ResultError(ErrorResponse.LOGIN_FAIL);
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ResultError SessionExpiredException(SessionExpiredException ex) {
        return new ResultError(ErrorResponse.NO_DATA);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResultError NoSuchElementException(NoSuchElementException ex) {
        return new ResultError(ErrorResponse.NO_DATA);
    }


    @ExceptionHandler(ConnectionTimeoutException.class)
    public ResultError ConnectionTimeoutException(ConnectionTimeoutException ex) {
        return new ResultError(ErrorResponse.CONNECTION_TIME_OUT);
    }

    @ExceptionHandler(NotFindSessionException.class)
    public ResultError NotFindSessionException(NotFindSessionException ex) {
        return new ResultError(ErrorResponse.NotFindSessionException);
    }

}
