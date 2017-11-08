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
import org.springframework.web.multipart.MultipartFile;
import whattoplay.domain.dto.GameDto;
import whattoplay.services.ProductDatabaseService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Andrzej on 2017-11-07.
 */

@RestController
public class GamesController {
    private ResourceLoader resourceLoader;
    private ProductDatabaseService productDatabaseService;

    @Autowired
    public GamesController(ResourceLoader resourceLoader, ProductDatabaseService productDatabaseService) {
        this.resourceLoader = resourceLoader;
        this.productDatabaseService = productDatabaseService;
    }

    @RequestMapping(path = "/getGame/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<GameDto> getGameDetailsById(@PathVariable("gameId") long gameId) throws EmptyResultDataAccessException {
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Server", "Tomcat");
            return new ResponseEntity<>(productDatabaseService.getGameById(gameId), responseHeaders, HttpStatus.CREATED);
        } catch( EmptyResultDataAccessException exc ){
            throw exc;
        }
    }

    @RequestMapping( path = "/getGames", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<GameDto>> getRandomGames(){
        return new ResponseEntity<>(productDatabaseService.getRandomGames(9), HttpStatus.OK);
    }

    @RequestMapping( path="/getGamePhoto/{gamePath}", method= RequestMethod.GET)
    public ResponseEntity<byte[]> getGameImageByImagePath(@PathVariable("gamePath") String gamePath ) throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:static/assets/img/gameImages/" + gamePath + ".jpg");
        File file = fileResource.getFile();
        byte[] image = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @RequestMapping(path="/getGameByGenre/{gameGenre}", method= RequestMethod.GET)
    public ResponseEntity<List<GameDto>> getGamesByGenre(@PathVariable("gameGenre") String genre ){
        return new ResponseEntity<>(productDatabaseService.getGamesByGenre(genre), HttpStatus.OK);
    }

    @RequestMapping(path = "/addGame", method = RequestMethod.POST)
    public ResponseEntity<String>  addGameToDatabaseController(@RequestBody final GameDto game){
        productDatabaseService.saveGameToDatabase(game);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>("Successfully added game to database", responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/addGameImage", method = RequestMethod.POST)
    public @ResponseBody
    String addImageOfTheGame(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("test")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + "!";
            } catch (Exception e) {
                return "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + " because the file was empty.";
        }
    }


}
