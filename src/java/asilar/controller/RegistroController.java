package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.RegistroCriteria;
import asilar.model.entity.Assistido;
import asilar.model.entity.Registro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistroController {
    
    @RequestMapping(value = "/cadastro/assistido/{id}/registro", method = RequestMethod.GET)
    public ModelAndView readByCriteria(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("/registro/list");
        
        Map <Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(RegistroCriteria.ASSISTIDO_ID_EQ, id);
        List<Registro> registroList = new ArrayList<Registro>();
        Assistido assistido = new Assistido();
        try {
            registroList = ServiceLocator.getRegistroService().readyByCriteria(criteria, null);
            assistido = ServiceLocator.getAssistidoService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("registroList", registroList);
        mv.addObject("assistido", assistido);
        String ativo = "assistido";
        mv.addObject("ativo", ativo);
        String tab = "registro";
        mv.addObject("tab", tab);
        return mv;
    }
    
}
