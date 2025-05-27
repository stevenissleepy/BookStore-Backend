package fun.steven.bookstore.utils.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fun.steven.bookstore.utils.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull Object handler
    ) throws Exception {
        /* 放行 OPTIONS 请求 */
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        /* 检查 session 中是否有用户 ID */
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            throw new LoginException("Session 中没有用户 ID");
        }
        return true;
    }
}
