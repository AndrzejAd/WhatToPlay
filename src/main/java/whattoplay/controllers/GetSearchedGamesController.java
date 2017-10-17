package whattoplay.controllers;

import whattoplay.domain.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.ProductDatabaseService;

import java.util.List;

/**
 *
 * @author Andrzej
 */
@RestController
public class GetSearchedGamesController {
    private ProductDatabaseService productDatabaseService;
    
    @Autowired
    public GetSearchedGamesController(ProductDatabaseService productDatabaseService) {
        this.productDatabaseService = productDatabaseService;
    }
    
    @RequestMapping("/getGameByGenre/{gameGenre}")
    public List<GameDto> returnGamesByGenre(@PathVariable("gameGenre") String genre ){
        return productDatabaseService.getGamesByGenre(genre);
    }
}
