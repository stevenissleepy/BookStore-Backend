package fun.steven.bookstore.utils.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fun.steven.bookstore.utils.interceptor.AdminInterceptor;
import fun.steven.bookstore.utils.interceptor.LoginInterceptor;

@Configuration /* 配置拦截器 */
public class InterceptorConfig implements WebMvcConfigurer {

    /* 登录拦截器 */
    @Autowired
    private LoginInterceptor loginInterceptor;

    /* admin 拦截器 */
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        List<String> loginExcludePath = List.of(
                "/user/login",
                "/user/register",
                "/book",
                "/book/**");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") /* 拦截所有路径 */
                .excludePathPatterns(loginExcludePath);

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/book/**");
    }

}