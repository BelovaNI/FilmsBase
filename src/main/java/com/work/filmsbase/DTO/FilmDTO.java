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
    private Long filmId;
    private String filmName;
    private Integer year;
    private Double rating;
    private String description;
}
