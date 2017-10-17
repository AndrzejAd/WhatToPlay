package whattoplay.controllers.pageControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Andrzej
 */
@Controller
public class GameDetailsController {
    
    @RequestMapping(path = "/game/{id}", method = RequestMethod.GET)
    public String getGameDetailsController(){
        return "/templates/gameDetails.html";
    }
    
}
