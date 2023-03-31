package com.work.filmsbase.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmGetResponseDTO {
    private long total;
    private long totalPages;
    private List<FilmDTO> items;
}
