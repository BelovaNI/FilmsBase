package com.work.filmsbase.DTO;
import lombok.Data;
import java.io.Serializable;

@Data
public class GenreFilterDTO implements Serializable {
    private String genres;
}
