package fun.steven.bookstore.utils.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fun.steven.bookstore.utils.interceptor.LoginInterceptor;

@Configuration /* 配置拦截器 */
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        List<String> excludePath = List.of(
                "/user/login",
                "/user/register",
                "/book",
                "/book/**");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") /* 拦截所有路径 */
                .excludePathPatterns(excludePath);
    }

}