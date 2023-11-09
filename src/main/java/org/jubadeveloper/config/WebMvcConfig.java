package org.jubadeveloper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowCredentials(true)
                .exposedHeaders("*", "authorization", "content-type")
                .allowedHeaders("*", "authorization", "content-type")
                .allowedOrigins("http://127.0.0.1:5173");
    }
}
