package whattoplay.controllers;

import whattoplay.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.UserDatabaseService;

/**
 *
 * @author Andrzej
 */


@RestController
public class GetUserController {
    private UserDatabaseService userDatabaseService;
    
    @Autowired
    public GetUserController(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }
    
    @RequestMapping("/user")
    public ResponseEntity<UserEntity> getUserByName(@RequestParam(value="username", required = true) String username){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OK", "200");
        return new ResponseEntity<>(userDatabaseService.getUserByUsername(username), responseHeaders, HttpStatus.OK);
    }
    
}
