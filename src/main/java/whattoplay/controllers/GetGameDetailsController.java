package whattoplay.controllers;

import whattoplay.domain.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.ProductDatabaseService;

/**
 *
 * @author Andrzej
 */
@RestController
public class GetGameDetailsController {
    private ProductDatabaseService productDatabaseService;
    
    @Autowired
    public GetGameDetailsController(ProductDatabaseService productDatabaseService) {
        this.productDatabaseService = productDatabaseService;
    }
    
    @RequestMapping(path = "/getGame/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<GameDto> getGameById(@PathVariable("gameId") long gameId) throws EmptyResultDataAccessException {
        try{
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Server", "Tomcat");
            return new ResponseEntity<GameDto>(productDatabaseService.getGameById(gameId), responseHeaders, HttpStatus.CREATED);
        } catch( EmptyResultDataAccessException exc ){
            throw exc;
        }
        
    }
    
}
