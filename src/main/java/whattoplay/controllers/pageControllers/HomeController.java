package whattoplay.controllers.pageControllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Andrzej
 */

@Controller
public class HomeController {
        
    @RequestMapping( path = "/home" )
    public String homeControllerHome(){
        return "/templates/home.html";
    }
    
    @RequestMapping( path = "/" )
    public String homeController(){
        return "/templates/home.html";
    }
    
}
