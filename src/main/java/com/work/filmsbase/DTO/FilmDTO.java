package com.work.filmsbase.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FilmDTO {
    @JsonIgnore
    private Long id;
    private Long kinopoiskId;
    private String nameEn;
    private Integer year;
    private Double ratingKinopoisk;
    private String shortDescription;
}
