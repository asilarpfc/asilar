package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.entity.Instituicao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping (value = "/cadastro/instituicao/novo", method = RequestMethod.POST)
    public ModelAndView create(Instituicao entity){
        try {
            ServiceLocator.getInstituicaoService().create(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/instituicao/{id}/info", method = RequestMethod.GET)
    public ModelAndView readById(@PathVariable Long id){
        Instituicao instituicao = null;
        try {
            instituicao = ServiceLocator.getInstituicaoService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("/cadastro/instituicao/info");
        mv.addObject("instituicao", instituicao);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/instituicao/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id){
        Instituicao instituicao = null;
        try {
            instituicao = ServiceLocator.getInstituicaoService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("/cadastro/instituicao/form");
        mv.addObject("instituicao", instituicao);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/instituicao/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(Instituicao instituicao){
        
        
        try {
            ServiceLocator.getInstituicaoService().update(instituicao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }
    
}
