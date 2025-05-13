package fun.steven.bookstore.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fun.steven.bookstore.annotation.CurrentUserIdArgumentResolver;
import fun.steven.bookstore.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* 配置拦截器 */
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        List<String> excludePath = List.of(
                "/user/login",
                "/user/logout/**",
                "/user/register",
                "/book");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") /* 拦截所有路径 */
                .excludePathPatterns(excludePath);
    }

    /* 配置解析器 */
    private final CurrentUserIdArgumentResolver currentUserIdArgumentResolver;

    public WebConfig(CurrentUserIdArgumentResolver currentUserIdArgumentResolver) {
        this.currentUserIdArgumentResolver = currentUserIdArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserIdArgumentResolver);
    }
}