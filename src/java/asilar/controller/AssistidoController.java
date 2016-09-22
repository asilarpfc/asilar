package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.AssistidoCriteria;
import asilar.model.entity.Assistido;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssistidoController {
    
    
    @RequestMapping (value = "/cadastro/assistido/novo", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/cadastro/assistido/form");
        String ativo = "assistido";
        mv.addObject("ativo", ativo);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/assistido/novo", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Assistido entity){
        ModelAndView mv = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("assistido", entity);
            fields.put("id", entity.getId());
            fields.put("cpf", entity.getCpf());
            
            Map <String, String> errors = ServiceLocator.getAssistidoService().validateForCreate(fields);
            if(errors.isEmpty()){
                ServiceLocator.getAssistidoService().create(entity);
                mv = new ModelAndView("redirect:/cadastro/assistido/lista");
            }else{
                mv = new ModelAndView("cadastro/assistido/form");
                mv.addObject("assistido", entity);
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String ativo = "assistido";
        mv.addObject("ativo", ativo);
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
        String ativo = "assistido";
        mv.addObject("ativo", ativo);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/assistido/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(Assistido assistido){
        ModelAndView mv = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("assistido", assistido);
            fields.put("id", assistido.getId());
            fields.put("cpf", assistido.getCpf());
            
            Map <String, String> errors = ServiceLocator.getAssistidoService().validateForUpdate(fields);
            if(errors.isEmpty()){
                ServiceLocator.getAssistidoService().update(assistido);
                mv = new ModelAndView("redirect:/cadastro/assistido/lista");
            }else{
                mv = new ModelAndView("/cadastro/assistido/form");
                mv.addObject("assistido", assistido);
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
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
    public ModelAndView readbyCriteria(String nome, String cpf ,Long offset){
        
        boolean redirect = false;
        if (offset == null || offset < 0) {
            offset = 0L;
            redirect = true;
        }
        
        List<Assistido> assistidoList = null;
        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(AssistidoCriteria.NOME_ILIKE, nome);
        criteria.put(AssistidoCriteria.CPF_EQ, cpf);
        Long count = 0L;
        
        String uri = "";
        
        if(nome != null && !nome.isEmpty()){
            uri +="nome="+nome;
        }
        if(cpf != null && !cpf.isEmpty()){
            uri +="&cpf="+cpf;
        }
        uri +="&offset="+offset;
        try {
            assistidoList = ServiceLocator.getAssistidoService().readyByCriteria(criteria, offset);
            count = ServiceLocator.getAssistidoService().countByCriteria(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = null;
        
        if(redirect){
                mv = new ModelAndView("redirect:/cadastro/assistido/lista?" + uri);
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
