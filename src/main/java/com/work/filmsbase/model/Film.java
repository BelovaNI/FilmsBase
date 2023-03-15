package com.work.filmsbase.model;
import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NonNull
    @Column
    private Long filmId;
    @NonNull
    @Column
    private String filmName;
    @NonNull
    @Column
    private Integer year;
    @NonNull
    @Column
    private Double rating;
    @NonNull
    @Column
    private String description;

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
}
