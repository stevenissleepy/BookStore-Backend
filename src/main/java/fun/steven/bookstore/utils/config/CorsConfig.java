package fun.steven.bookstore.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration /* 配置 cors */
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        final String[] origins = {
                "http://127.0.0.1:5173",
                "http://localhost:5173" };

        final String[] methods = {
                "GET", "POST", "PUT", "DELETE", "OPTIONS" };

        registry.addMapping("/**")          /* 允许所有 api 路径 */
                .allowedOrigins(origins)
                .allowedMethods(methods)
                .allowedHeaders("*")        /* 允许的请求头 */
                .allowCredentials(true);    /* 是否允许发送 Cookie */
    }
}
