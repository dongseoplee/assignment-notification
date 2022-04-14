package mobile.gachonapp.api;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.custom_exception.WrongLoginUserException;
import mobile.gachonapp.api.response.ErrorResponse;
import mobile.gachonapp.api.response.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {


    //로그인시 userId 와 password가 입력되지 않은 경우
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result loginValidException(MethodArgumentNotValidException ex) {
        String errorField = ex.getBindingResult().getFieldError().getField();
        log.info(errorField);

        if (errorField.equals("userId")) {
            return new Result(ErrorResponse.LOGIN_FAIL_EMPTY_NAME, null);
        }

        if (errorField.equals("password")) {
            return new Result(ErrorResponse.LOGIN_FAIL_EMPTY_PASSWORD, null);
        }

        //로직상 여기 안오는데 임시 처리
        return null;
    }

    //로그인 정보가 잘못될시 에러처리
    @ExceptionHandler({WrongLoginUserException.class})
    public Result WrongLoginUserException(WrongLoginUserException ex) {
        return new Result(ErrorResponse.LOGIN_FAIL, null);
    }
}
