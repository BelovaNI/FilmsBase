package com.work.filmsbase.DTO;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FilmFilterDTO {
    @NonNull
    String keyword;
    @NonNull
    Integer page;
}
