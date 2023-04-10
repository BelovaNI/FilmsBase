package com.work.filmsbase.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "send")
public class MailDTO {
    private String toAddress;
    private String subject;
    private String message;
}
