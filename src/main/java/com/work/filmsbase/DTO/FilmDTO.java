package com.work.filmsbase.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FilmDTO {
    @JsonIgnore
    private Long id;
    @JsonSetter("filmId")
    private Long filmId;
    @JsonSetter("nameRu")
    private String filmName;
    @JsonSetter("year")
    private Integer year;
    @JsonSetter("rating")
    private Double rating;
    @JsonSetter("description")
    private String description;

    @Override
    public String toString() {
        return "FilmDTO{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
