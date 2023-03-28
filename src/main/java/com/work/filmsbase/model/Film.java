package com.work.filmsbase.model;
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
    private Long id;
@Column
    private Long filmId;

    private String filmName;

    private Integer year;

    private Double rating;

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
