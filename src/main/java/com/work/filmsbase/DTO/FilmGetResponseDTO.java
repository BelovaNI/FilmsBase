package com.work.filmsbase.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmGetResponseDTO {
    private String keyword;
    private Integer pagesCount;
    private List<FilmDTO> films;
}
