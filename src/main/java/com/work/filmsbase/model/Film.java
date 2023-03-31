package com.work.filmsbase.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Film(@NonNull Long filmId, @NonNull String filmName, @NonNull Integer year, @NonNull Double rating, @NonNull String description) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.year = year;
        this.rating = rating;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(filmId, film.filmId) && Objects.equals(filmName, film.filmName) && Objects.equals(year, film.year) && Objects.equals(rating, film.rating) && Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, filmName, year, rating, description);
    }
}
