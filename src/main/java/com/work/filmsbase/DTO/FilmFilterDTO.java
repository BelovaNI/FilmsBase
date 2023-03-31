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
    Integer yearFrom;
    @NonNull
    Integer yearTo;
    @NonNull
    String type;
    @NonNull
    String keyword;
    @NonNull
    List<Integer> genres;

}
