package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.entity.Lote;
import asilar.model.entity.Produto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoteController {
    
    @RequestMapping(value = "estoque/produto/{id}/lote/novo", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable Long id){
        Produto produto = new Produto();
        try {
            produto = ServiceLocator.getProdutoService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("/estoque/lote/form");
        mv.addObject("produto", produto);
        return mv;
    }
    
    @RequestMapping(value = "estoque/produto/{id}/lote/novo", method = RequestMethod.POST)
    public ModelAndView create(@PathVariable Long id, WebRequest request){
        Lote lote = new Lote();
        
        if(request.getParameter("quantidade") != null && !request.getParameter("quantidade").isEmpty()) lote.setQuantidade(Long.parseLong(request.getParameter("quantidade")));
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            data = format.parse(request.getParameter("validade"));
            lote.setValidade(data);
        } catch (ParseException ex) {
        }
        Produto produto = new Produto();
        produto.setId(id);
        lote.setProduto(produto);
        
        try {
            ServiceLocator.getLoteService().create(lote);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("redirect:/estoque/produto/"+ id + "/info");
        return mv;
    }
    
    
    
}
