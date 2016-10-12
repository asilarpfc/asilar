package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.ProdutoCriteria;
import asilar.model.entity.Produto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    @RequestMapping(value = "/estoque/produto/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/estoque/produto/form");
        return mv;

    }
    
    @RequestMapping(value = "/estoque/produto/novo", method = RequestMethod.POST)
    public ModelAndView create(WebRequest request) throws Exception {
        
        Produto entity = new Produto();
        
        entity.setNome(request.getParameter("nome"));
        entity.setQuantidadeMaxima(Long.parseLong(request.getParameter("quantidadeMaxima")));
        entity.setQuantidadeMinima(Long.parseLong(request.getParameter("quantidadeMinima")));
        entity.setUnidadeMedida(request.getParameter("unidadeMedida"));
        
        ServiceLocator.getProdutoService().create(entity);
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;

    }

    
}
