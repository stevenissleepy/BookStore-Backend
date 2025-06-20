package fun.steven.bookstore.utils.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import fun.steven.bookstore.utils.annotation.UserOnly;
import fun.steven.bookstore.utils.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            // Check if the method has the UserOnly annotation
            UserOnly userOnly = handlerMethod.getMethodAnnotation(UserOnly.class);
            if (userOnly != null) {
                String username = (String) request.getSession().getAttribute("username");
                if(username.equals("admin")) {
                    throw new LoginException("Access denied: User role required.");
                }
            }
        }
        return true;
    }
}

