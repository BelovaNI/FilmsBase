package com.work.filmsbase.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@TypeDef(name = "jsonb", typeClass = JsonType.class)
public class Film implements Serializable {
    @Id
    @JsonSetter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonSetter("genres")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Genre[] genres;
    @JsonIgnore
    private boolean viewed;

    @Override
    public String toString() {
        return "Фильм " + '\n' +
                "filmId - " + filmId + '\n' +
                "название - " + filmName + '\n' +
                "год - " + year + '\n' +
                "рейтинг - " + rating + '\n' +
                "описание - " + description + '\n' +
                "жанр - " + genres + '\n';
    }
}
