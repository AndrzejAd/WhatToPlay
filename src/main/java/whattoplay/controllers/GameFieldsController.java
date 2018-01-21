package whattoplay.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.domain.entities.Developer;
import whattoplay.domain.entities.GameMode;
import whattoplay.domain.entities.Genre;
import whattoplay.persistence.GameFieldsDatabaseRepository;

import java.util.List;

@RestController
public class GameFieldsController {
    private GameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Autowired
    public GameFieldsController(GameFieldsDatabaseRepository gameFieldsDatabaseRepository) {
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
    }

    @RequestMapping(path = "/getAllGameModes", method = RequestMethod.GET)
    public ResponseEntity<List<GameMode>> getAllGameModes() {
        return new ResponseEntity<>(gameFieldsDatabaseRepository.getAllGameModes(), HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllGenres", method = RequestMethod.GET)
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<>(gameFieldsDatabaseRepository.getAllGenres(), HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllDevelopers", method = RequestMethod.GET)
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return new ResponseEntity<>(gameFieldsDatabaseRepository.getAllDevelopers(), HttpStatus.OK);
    }

}
