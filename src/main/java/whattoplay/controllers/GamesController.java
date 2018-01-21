package whattoplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whattoplay.domain.dto.BaseGameDto;
import whattoplay.services.persistance.GameDatabaseService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

@RestController
public class GamesController {
    private ResourceLoader resourceLoader;
    private GameDatabaseService gameDatabaseService;

    @Autowired
    public GamesController(ResourceLoader resourceLoader, GameDatabaseService gameDatabaseService) {
        this.resourceLoader = resourceLoader;
        this.gameDatabaseService = gameDatabaseService;
    }

    @RequestMapping(path = "/getGame/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<BaseGameDto> getGameDetailsById(@PathVariable("gameId") final long gameId) throws EmptyResultDataAccessException {
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Server", "Tomcat");
            return new ResponseEntity<>(gameDatabaseService.getGameById(gameId), responseHeaders, HttpStatus.CREATED);
        } catch( EmptyResultDataAccessException exc ){
            throw exc;
        }
    }

    @RequestMapping( path = "/getGames", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<BaseGameDto>> getRandomGames(){
        return new ResponseEntity<>(gameDatabaseService.getRandomGames(9), HttpStatus.OK);
    }

    @RequestMapping( path="/getGamePhoto/{gamePath}", method= RequestMethod.GET)
    public ResponseEntity<byte[]> getGameImageByImagePath(@PathVariable("gamePath") String gamePath ) throws IOException {
        Random rand = new Random();
        gamePath = Integer.toString(rand.nextInt(4) + 1);
        final Resource fileResource = resourceLoader.getResource("classpath:static/assets/img/gameImages/" + gamePath + ".jpg");
        File file = fileResource.getFile();
        byte[] image = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @RequestMapping(path="/getGameByGenre/{gameGenre}", method= RequestMethod.GET)
    public ResponseEntity<List<BaseGameDto>> getGamesByGenre(@PathVariable("gameGenre") final String genre ){
        return new ResponseEntity<>(gameDatabaseService.getGamesByGenre(genre), HttpStatus.OK);
    }

    @RequestMapping(path = "/getGamesByGameName/{gameName}", method = RequestMethod.GET)
    public ResponseEntity<List<BaseGameDto>> getGamesByGameName(@PathVariable("gameName") final String gameName){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(gameDatabaseService.getGamesByGameName(gameName), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/addGame", method = RequestMethod.POST)
    public ResponseEntity<String>  addGameToDatabaseController(@RequestBody final BaseGameDto game){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        if ( gameDatabaseService.persistGame(game) ) {
            return new ResponseEntity<>("Successfully added game to database.", responseHeaders, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>("GameJson is already in database. ", responseHeaders, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(path = "/deleteGame/{gameName}", method = RequestMethod.DELETE)
    public ResponseEntity<String>  deleteGameFromDatabase(@PathVariable("gameName") final String gameName){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        if ( gameDatabaseService.deleteGameFromDatabase(gameName) ) {
            return new ResponseEntity<>("Successfully deleted game from database.", responseHeaders, HttpStatus.OK);
        } else{
            return new ResponseEntity<>("There is no such game in database. ", responseHeaders, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(path = "/updateGame", method = RequestMethod.POST)
    public ResponseEntity<String> updateGame(@RequestBody final BaseGameDto game){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        if ( gameDatabaseService.updateGame(game) != null ){
            return new ResponseEntity<>("Successfully updated game!", responseHeaders, HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Couldn't update game...", responseHeaders, HttpStatus.NOT_MODIFIED);
        }
    }


}
