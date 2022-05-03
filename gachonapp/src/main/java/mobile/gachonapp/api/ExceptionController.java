package mobile.gachonapp.api;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.ErrorResponse;
import mobile.gachonapp.api.response.Result;
import mobile.gachonapp.exception.ConnectionTimeoutException;
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
    public Result loginValidException(MethodArgumentNotValidException ex) {
        String errorField = ex.getBindingResult().getFieldError().getField();

        if (errorField.equals("userId")) {
            return new Result(ErrorResponse.LOGIN_FAIL_EMPTY_NAME, null);
        }

        if (errorField.equals("password")) {
            return new Result(ErrorResponse.LOGIN_FAIL_EMPTY_PASSWORD, null);
        }
        return null;
    }

    @ExceptionHandler(WrongLoginUserException.class)
    public Result WrongLoginUserException(WrongLoginUserException ex) {
        return new Result(ErrorResponse.LOGIN_FAIL, null);
    }

    @ExceptionHandler(SessionExpiredException.class)
    public Result SessionExpiredException(SessionExpiredException ex) {
        return new Result(ErrorResponse.NO_DATA, null);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Result NoSuchElementException(NoSuchElementException ex) {
        return new Result(ErrorResponse.NO_DATA, null);
    }


    @ExceptionHandler(ConnectionTimeoutException.class)
    public Result ConnectionTimeoutException(ConnectionTimeoutException ex) {
        return new Result(ErrorResponse.CONNECTION_TIME_OUT, null);
    }

}
