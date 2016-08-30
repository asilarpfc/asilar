package asilar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView OpenHome(){
        ModelAndView mv = new ModelAndView("/home");
        return mv;
    }
    
}
