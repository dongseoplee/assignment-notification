package mobile.gachonapp.interceptor;

import lombok.RequiredArgsConstructor;
import mobile.gachonapp.domain.User;
import mobile.gachonapp.exception.NotFindSessionException;
import mobile.gachonapp.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*String moodleSession = WebUtils.getCookie(request, "MoodleSession").getValue();
        //TODO:에러를 던져도 컨트롤러 어드바이스에서 받질 못한다ㅣ.
        User user = userRepository.findBySession(moodleSession)
                .orElseThrow(NotFindSessionException::new);

        request.setAttribute("user",user);*/

        return true;
    }
}
