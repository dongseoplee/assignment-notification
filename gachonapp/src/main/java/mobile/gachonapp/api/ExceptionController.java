package mobile.gachonapp.api;

import lombok.extern.slf4j.Slf4j;
import mobile.gachonapp.api.response.ErrorResponse;
import mobile.gachonapp.api.response.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result validException(MethodArgumentNotValidException ex) {
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
}
