package whattoplay.controllers;

import whattoplay.domain.dto.UserDto;
import whattoplay.exceptions.NotValidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.UserDatabaseService;

/**
 *
 * @author Andrzej
 */


@RestController
public class AddUserController {
    private UserDatabaseService userDatabaseService;
    
    @Autowired
    public AddUserController(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }
    
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUserToDatabaseController(@RequestBody final UserDto user) throws NotValidPasswordException{
        try{
            userDatabaseService.saveUser(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>("Successfully added user to database", responseHeaders, HttpStatus.CREATED);
        } catch( NotValidPasswordException exc ){
            throw exc;
        }
    }
    
}
