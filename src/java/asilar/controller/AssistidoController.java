package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.AssistidoCriteria;
import asilar.model.entity.Assistido;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssistidoController {
    
    @RequestMapping (value = "/cadastro/assistido/novo", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/cadastro/assistido/form");
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/assistido/novo", method = RequestMethod.POST)
    public ModelAndView create(Assistido entity){
        
        try {
            ServiceLocator.getAssistidoService().create(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/lista");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro/assistido/{id}/info", method = RequestMethod.GET)
    public ModelAndView readById(@PathVariable Long id){
        Assistido assistido = new Assistido();
        try {
            assistido = ServiceLocator.getAssistidoService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("/cadastro/assistido/info");
        mv.addObject("assistido", assistido);
        return mv;
    }
    
    
    @RequestMapping (value = "/cadastro/assistido/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id){
        Assistido assistido = new Assistido();
        
        try {
            assistido = ServiceLocator.getAssistidoService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("/cadastro/assistido/form");
        mv.addObject("assistido", assistido);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/assistido/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(Assistido assistido){
        
        try {
            ServiceLocator.getAssistidoService().update(assistido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/lista");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro/assistido/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        try {
            ServiceLocator.getAssistidoService().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/lista");
        return mv;
    }
    
    
    @RequestMapping (value = "/cadastro/assistido/lista", method = RequestMethod.GET)
    public ModelAndView readbyCriteria(String nome, Long offset){
        
        boolean redirect = false;
        if (offset == null || offset < 0) {
            offset = 0L;
            redirect = true;
        }
        
        List<Assistido> assistidoList = null;
        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(AssistidoCriteria.NOME_ILIKE, nome);
        Long count = 0L;
        
        try {
            assistidoList = ServiceLocator.getAssistidoService().readyByCriteria(criteria, offset);
            count = ServiceLocator.getAssistidoService().countByCriteria(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = null;
        
        if(redirect){
            if(nome == null || nome.isEmpty()){
                mv = new ModelAndView("redirect:/cadastro/assistido/lista?offset=" + offset +"");
            }else{
                mv = new ModelAndView("redirect:/cadastro/assistido/lista?nome=" + nome + "&offset=" + offset +"");
            }
        }else{
            mv = new ModelAndView("/cadastro/assistido/lista");
            mv.addObject("nome", nome);
            mv.addObject("assistidoList", assistidoList);
            mv.addObject("offset", offset);
            mv.addObject("count", count);
            String ativo = "assistido";
            mv.addObject("ativo", ativo);
        }
                
        return mv;
        
    }
    
}
