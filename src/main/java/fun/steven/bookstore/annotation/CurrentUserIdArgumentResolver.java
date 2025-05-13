package fun.steven.bookstore.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import fun.steven.bookstore.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class CurrentUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        /* 检查参数是否标注了 @CurrentUserId 注解 */
        return parameter.hasParameterAnnotation(CurrentUserId.class);
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter, 
        @Nullable ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, 
        @Nullable WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            throw new LoginException("未登录或登录超时");
        }
        return userId;
    }
}
