package asilar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InstituicaoController {
    
    @RequestMapping (value = "/cadastro/instituicao/novo", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/cadastro/instituicao/form");
        return mv;
    }
    
    
}
