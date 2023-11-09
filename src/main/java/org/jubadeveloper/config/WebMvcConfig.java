package org.jubadeveloper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${properties.serverUrl}")
    String serverUrl;
    private static final String urlPattern = "http://%s:%d";
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowCredentials(true)
                .exposedHeaders("*", "authorization", "content-type")
                .allowedHeaders("*", "authorization", "content-type")
                .allowedOrigins(String.format(urlPattern, serverUrl, 80), String.format(urlPattern, serverUrl, 5000));
    }
}
