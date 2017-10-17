package whattoplay.controllers;

import whattoplay.domain.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.services.ProductDatabaseService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author Andrzej
 */

@RestController
public class GetRandomGamesController {
    private ResourceLoader resourceLoader;
    private ProductDatabaseService productDatabaseService;
    
    @Autowired
    public GetRandomGamesController(ResourceLoader resourceLoader, ProductDatabaseService productDatabaseService) {
        this.resourceLoader = resourceLoader;
        this.productDatabaseService = productDatabaseService;
    }
    
    @RequestMapping( path = "/getGames", method = RequestMethod.GET, produces = "application/json")
    public List<GameDto> returnRandomGames(){
        return productDatabaseService.getRandomGames(9);
    }
    
    @RequestMapping( path="/getGamePhoto/{gamePath}", method= RequestMethod.GET)
    public ResponseEntity<byte[]> returnImage(@PathVariable("gamePath") String gamePath ) throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:static/css/images/gameImages/" + gamePath + ".jpg");
        File file = fileResource.getFile();
        byte[] image = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
    
}
