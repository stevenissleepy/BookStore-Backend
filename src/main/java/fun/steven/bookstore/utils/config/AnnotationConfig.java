package fun.steven.bookstore.utils.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fun.steven.bookstore.utils.annotation.CurrentUserIdArgumentResolver;

@Configuration /* 配置自定义注解解析器 */
public class AnnotationConfig implements WebMvcConfigurer {

    private final CurrentUserIdArgumentResolver currentUserIdArgumentResolver;

    public AnnotationConfig(CurrentUserIdArgumentResolver currentUserIdArgumentResolver) {
        this.currentUserIdArgumentResolver = currentUserIdArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserIdArgumentResolver);
    }
}
