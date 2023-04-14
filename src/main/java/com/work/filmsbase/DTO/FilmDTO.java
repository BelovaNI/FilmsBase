package com.work.filmsbase.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.work.filmsbase.model.Genre;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.Column;


@Data
@TypeDef(name = "jsonb", typeClass = JsonType.class)
public class FilmDTO {
    @JsonSetter
    @Column(name = "id")
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
}
