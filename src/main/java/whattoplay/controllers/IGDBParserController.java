package whattoplay.controllers;

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

    private class Pair<T,R>{
        private final T x;
        private final R y;

        public Pair(T x, R y) {
            this.x = x;
            this.y = y;
        }
    }

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

    @RequestMapping(path = "/parseGameModes", method = RequestMethod.POST)
    public ResponseEntity<Pair<String, Long>> parseGameModes(){
        Long numbOfParsedGameModes = internetGameDatabaseCacher.saveAllGameModes();
        if ( numbOfParsedGameModes != -1 ){
            return new ResponseEntity<>( new Pair<>("Succesfully parsed game modes!", numbOfParsedGameModes) , HttpStatus.OK);
        } else{
            return new ResponseEntity<>( new Pair<>("Couldn't parse game modes!", numbOfParsedGameModes) , HttpStatus.NOT_MODIFIED);
        }

    }

    @RequestMapping(path = "/parseGenres", method = RequestMethod.POST)
    public ResponseEntity<Pair<String, Long>> parseGenres(){
        Long numbOfParsedGameModes = internetGameDatabaseCacher.saveAllGenres();
        if ( numbOfParsedGameModes != -1 ){
            return new ResponseEntity<>( new Pair<>("Succesfully parsed genres!", numbOfParsedGameModes) , HttpStatus.OK);
        } else{
            return new ResponseEntity<>( new Pair<>("Couldn't parse genres!", numbOfParsedGameModes) , HttpStatus.NOT_MODIFIED);
        }
    }

}

