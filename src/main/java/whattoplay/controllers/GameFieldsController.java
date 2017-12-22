package whattoplay.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import whattoplay.persistence.GameFieldsDatabaseRepository;

@RestController
public class GameFieldsController {
    private GameFieldsDatabaseRepository gameFieldsDatabaseRepository;

    @Autowired
    public GameFieldsController(GameFieldsDatabaseRepository gameFieldsDatabaseRepository) {
        this.gameFieldsDatabaseRepository = gameFieldsDatabaseRepository;
    }

    @RequestMapping(path = "/test1", method = RequestMethod.GET)
    public void test(){
        gameFieldsDatabaseRepository.getAllGameModes().forEach(System.out::println);

    }
}
