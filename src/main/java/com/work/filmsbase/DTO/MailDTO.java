package com.work.filmsbase.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Component
public class MailDTO {
    private String toAddress;
    private String subject;
    private String message;
    private String attachment;
}
