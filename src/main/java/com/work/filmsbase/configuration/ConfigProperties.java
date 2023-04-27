package com.work.filmsbase.configuration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Configuration
@ConfigurationProperties(prefix = "api")
@Component
@Getter
public class ConfigProperties {
    private String key;
    private String url;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

}
