package whattoplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import whattoplay.domain.dto.GameDto;
import whattoplay.services.ProductDatabaseService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Andrzej
 */

@RestController
public class AddGameController {
    private ProductDatabaseService productDatabaseService;
    
    @Autowired
    public AddGameController(ProductDatabaseService productDatabaseService) {
        this.productDatabaseService = productDatabaseService;
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
