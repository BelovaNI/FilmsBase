package com.work.filmsbase.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.work.filmsbase.client.RestTemplateClient;
import com.work.filmsbase.service.FilmServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@NoArgsConstructor
@RequestMapping("")
public class FilmController {
    FilmServiceImpl filmService;
    RestTemplateClient restTemplate;
    @Autowired
    public FilmController(FilmServiceImpl filmService, RestTemplateClient restTemplate) {
        this.filmService = filmService;
        this.restTemplate = restTemplate;
    }
//    @RequestMapping (value = "/films", method = RequestMethod.GET)  // сюда надо передавать параметры для поиска - сделать дто FilmFilter
//    public String saveToDataBase() throws JsonProcessingException {
////        ResponseEntity<String> list = filmServiceClient.executeRestCall();
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////        Film film = objectMapper.readValue(list.getBody(), Film.class);
//////        if(film != null) {
//////            filmServiceClient.save();   // убрать проверку на null
//////        }else {
//////            return "FFFailed!";
//////        }
//////        return "Save is successfully";
//    }
}
