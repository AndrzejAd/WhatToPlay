package whattoplay.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import whattoplay.domain.dto.UserDto;
import whattoplay.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import whattoplay.exceptions.NotValidPasswordException;
import whattoplay.services.UserDatabaseService;

/**
 * @author Andrzej
 */


@RestController
public class UsersController {
    private UserDatabaseService userDatabaseService;

    @Autowired
    public UsersController(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserByName(@RequestParam(value = "username", required = true) final String username) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OK", "200");
        return new ResponseEntity<>(userDatabaseService.getUserByUsername(username), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> postUserToDatabaseController(@RequestBody final UserDto user) throws NotValidPasswordException, ConstraintViolationException {
        try {
            userDatabaseService.saveUser(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>("Successfully added user to database", responseHeaders, HttpStatus.CREATED);
        } catch (NotValidPasswordException exc) {
            throw exc;
        } catch (ConstraintViolationException exc) {
            throw exc;
        }
    }

    @RequestMapping(path = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserFromDatabase(@RequestBody final UserDto user) {
        //TODO
        System.out.println(user);
        //userDatabaseService.deleteUser(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>("Successfully deleted user ", responseHeaders, HttpStatus.ACCEPTED);
    }

}
