package com.work.filmsbase.DTO;
import lombok.*;
import org.springframework.stereotype.Component;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Component
public class FilmFilterDTO {
    @NonNull
    String keyword;
    @NonNull
    Integer page;

}
