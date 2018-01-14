package whattoplay.controllers;


import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.InternetGameDatabaseCacher;


@RestController
public class IGDBParserController {
    InternetGameDatabaseCacher internetGameDatabaseCacher;

    @Autowired
    public IGDBParserController(InternetGameDatabaseCacher internetGameDatabaseCacher) {
        this.internetGameDatabaseCacher = internetGameDatabaseCacher;
    }

    @RequestMapping(path = "/parseDevelopers", method = RequestMethod.POST)
    public ResponseEntity<String> parseDevelopers(){
        internetGameDatabaseCacher.saveAllDevelopers();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/parseGames", method = RequestMethod.POST)
    public ResponseEntity<String> parseGames(){
        internetGameDatabaseCacher.saveAllGames();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/parseCollections", method = RequestMethod.POST)
    public ResponseEntity<String> parseCollections(){
        internetGameDatabaseCacher.saveAllCollections();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
