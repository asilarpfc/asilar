package asilar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView Home(){
        ModelAndView mv = new ModelAndView("/home");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("/cadastro");
        return mv;
    }
    
}
