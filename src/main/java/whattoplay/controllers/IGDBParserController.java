package whattoplay.controllers;


import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.InternetGameDatabaseService;


@RestController
public class IGDBParserController {
    InternetGameDatabaseService internetGameDatabaseService;

    @Autowired
    public IGDBParserController(InternetGameDatabaseService internetGameDatabaseService) {
        this.internetGameDatabaseService = internetGameDatabaseService;
    }

    @RequestMapping(path = "/parseDevelopers", method = RequestMethod.POST)
    public ResponseEntity<String> parseDevelopers(){
        internetGameDatabaseService.saveAllDevelopers();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/parseGames", method = RequestMethod.POST)
    public ResponseEntity<String> parseGames(){
        try {
            internetGameDatabaseService.saveAllGames();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        //internetGameDatabaseService.saveAllFranchises();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
