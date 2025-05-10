package fun.steven.bookstore.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.exception.LoginException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull Object handler
    ) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new LoginException("未登录或登录已过期，请重新登录");
        }
        return true;
    }
}
