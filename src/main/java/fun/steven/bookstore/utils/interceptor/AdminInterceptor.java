package fun.steven.bookstore.utils.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            // Check if the method has the AdminOnly annotation
            AdminOnly adminOnly = handlerMethod.getMethodAnnotation(AdminOnly.class);
            if (adminOnly != null) {
                String userRole = (String) request.getSession().getAttribute("userRole");
                if(userRole == null || !userRole.equals("admin")) {
                    throw new LoginException("Access denied: Admin role required.");
                }
            }
        }
        return true;
    }
}
