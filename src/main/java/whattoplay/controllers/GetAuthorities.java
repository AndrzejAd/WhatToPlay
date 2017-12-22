package whattoplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.persistence.UserDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 2017-11-04.
 */
@RestController
public class GetAuthorities {
    @Autowired
    private UserDatabaseRepository userDatabaseRepository;

    @RequestMapping(path = "/getAuthorities", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAndrzejAuthorities(){
        ArrayList<String> list = new ArrayList<>();
        userDatabaseRepository.findByUsername("Andrzej").getRoles().forEach( (x) -> list.add(x.getRoleName()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
