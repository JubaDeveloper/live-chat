package io.github.jubadeveloper.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${properties.serverUrl}")
    String serverUrl;
    @Value("${properties.serverPort}")
    int port;
    private static final String urlPattern = "http://%s:%d";
    private static final Logger logger = LogManager.getLogger(WebMvcConfig.class);
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Global server url: " + serverUrl);
        registry
                .addMapping("/**")
                .allowCredentials(true)
                .exposedHeaders("*", "authorization", "content-type")
                .allowedHeaders("*", "authorization", "content-type")
                .allowedOrigins(String.format(urlPattern, serverUrl, 80), String.format(urlPattern, serverUrl, port));
    }
}
