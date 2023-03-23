package com.work.filmsbase.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO {
    @JsonIgnore
    private Long id;
    @JsonSetter("kinopoiskId")
    private Long filmId;
    @JsonSetter("nameEn")
    private String filmName;
    @JsonSetter("year")
    private Integer year;
    @JsonSetter("ratingKinopoisk")
    private Double rating;
    @JsonSetter("shortDescription")
    private String description;
}
