package com.work.filmsbase.DTO;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Component
public class FilmFilterDTO {
    @NonNull
    String order;
    @NonNull
    String type;
    @NonNull
    String keyword;

    @Override
    public String toString() {
        return "FilmFilterDTO{" +
                "order='" + order + '\'' +
                ", type='" + type + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
