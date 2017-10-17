package whattoplay.controllers.pageControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Andrzej
 */
@Controller
@RequestMapping("/addGame")
public class AddGameSuccessfullyController {
    
    @RequestMapping( path ="/gameAddedSuccessfully", method = RequestMethod.GET )
    public String gameAddedSuccessfullyHome(){
        return "/templates/gameAddedSuccessfully.html";
    }
    
}
