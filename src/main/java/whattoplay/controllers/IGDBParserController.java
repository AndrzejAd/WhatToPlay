package whattoplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.IgdbRequesterService;


@RestController
public class IGDBParserController {
    IgdbRequesterService igdbRequesterService;

    private class Pair<T,R>{
        private final T x;
        private final R y;

        public Pair(T x, R y) {
            this.x = x;
            this.y = y;
        }
    }

    @Autowired
    public IGDBParserController(IgdbRequesterService igdbRequesterService) {
        this.igdbRequesterService = igdbRequesterService;
    }

    @RequestMapping(path = "/parseDevelopers", method = RequestMethod.POST)
    public ResponseEntity<String> parseDevelopers(){
        igdbRequesterService.saveAllDevelopers();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/parseGames", method = RequestMethod.POST)
    public ResponseEntity<String> parseGames(){
        igdbRequesterService.saveAllGames();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/parseCollections", method = RequestMethod.POST)
    public ResponseEntity<String> parseCollections(){
        igdbRequesterService.saveAllCollections();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/parseGameModes", method = RequestMethod.POST)
    public ResponseEntity<Pair<String, Long>> parseGameModes(){
        Long numbOfParsedGameModes = igdbRequesterService.saveAllGameModes();
        if ( numbOfParsedGameModes != -1 ){
            return new ResponseEntity<>( new Pair<>("Succesfully parsed game modes!", numbOfParsedGameModes) , HttpStatus.OK);
        } else{
            return new ResponseEntity<>( new Pair<>("Couldn't parse game modes!", numbOfParsedGameModes) , HttpStatus.NOT_MODIFIED);
        }

    }

    @RequestMapping(path = "/parseGenres", method = RequestMethod.POST)
    public ResponseEntity<Pair<String, Long>> parseGenres(){
        Long numbOfParsedGameModes = igdbRequesterService.saveAllGenres();
        if ( numbOfParsedGameModes != -1 ){
            return new ResponseEntity<>( new Pair<>("Succesfully parsed genres!", numbOfParsedGameModes) , HttpStatus.OK);
        } else{
            return new ResponseEntity<>( new Pair<>("Couldn't parse genres!", numbOfParsedGameModes) , HttpStatus.NOT_MODIFIED);
        }
    }

}

