package com.work.filmsbase.configuration;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Configuration
@ConfigurationProperties(prefix = "api")
@Component
public class ConfigProperties {
    private String key;
    private String url;

    public String getKey() {
        return key;
    }
    public String getUrl() {
        return url;
    }
}
