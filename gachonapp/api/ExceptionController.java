package mobile.gachonapp.api;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.ErrorResponse;
import mobile.gachonapp.api.response.ErrorResult;
import mobile.gachonapp.api.response.Result;
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
    public ErrorResult loginValidException(MethodArgumentNotValidException ex) {
        String errorField = ex.getBindingResult().getFieldError().getField();

        if (errorField.equals("userId")) {
            return new ErrorResult(ErrorResponse.LOGIN_FAIL_EMPTY_NAME);
        }

        if (errorField.equals("password")) {
            return new ErrorResult(ErrorResponse.LOGIN_FAIL_EMPTY_PASSWORD);
        }
        return null;
    }

    @ExceptionHandler(WrongLoginUserException.class)
    public ErrorResult WrongLoginUserException(WrongLoginUserException ex) {
        return new ErrorResult(ErrorResponse.LOGIN_FAIL);
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ErrorResult SessionExpiredException(SessionExpiredException ex) {
        return new ErrorResult(ErrorResponse.NO_DATA);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResult NoSuchElementException(NoSuchElementException ex) {
        return new ErrorResult(ErrorResponse.NO_DATA);
    }


    @ExceptionHandler(ConnectionTimeoutException.class)
    public ErrorResult ConnectionTimeoutException(ConnectionTimeoutException ex) {
        return new ErrorResult(ErrorResponse.CONNECTION_TIME_OUT);
    }

    @ExceptionHandler(NotFindSessionException.class)
    public ErrorResult NotFindSessionException(NotFindSessionException ex) {
        return new ErrorResult(ErrorResponse.NotFindSessionException);
    }

}
