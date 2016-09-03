package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
    
    @RequestMapping(value = "/cadastro/usuario/novo", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/cadastro/usuario/form");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro/usuario/novo", method = RequestMethod.POST)
    public ModelAndView create(Usuario usuario){
        try {
            ServiceLocator.getUsuarioService().create(usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/cadastro/usuario/lista");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro/usuario/{id}/info", method = RequestMethod.GET)
    public ModelAndView readById(@PathVariable Long id){
        Usuario usuario = new Usuario();
        try {
            usuario = ServiceLocator.getUsuarioService().readyById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("/cadastro/usuario/info");
        mv.addObject("usuario", usuario);
        return mv;
    }
    
    @RequestMapping(value = "/cadastro/usuario/lista", method = RequestMethod.GET)
    public ModelAndView readByCriteria(){
        List<Usuario> usuarioList = new ArrayList<Usuario>();
        Long count = 0L;
        try {
            usuarioList = ServiceLocator.getUsuarioService().readyByCriteria(null, null);
            count = ServiceLocator.getUsuarioService().countByCriteria(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("/cadastro/usuario/lista");
        mv.addObject("usuarioList", usuarioList);
        mv.addObject("count", count);
        String ativo = "usuario";
        mv.addObject("ativo", ativo);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/usuario/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id){
        Usuario usuario = new Usuario();
        try {
            usuario = ServiceLocator.getUsuarioService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("/cadastro/usuario/form");
        mv.addObject("usuario", usuario);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/usuario/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(Usuario usuario){
        try {
            ServiceLocator.getUsuarioService().update(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("redirect:/cadastro/usuario/lista");
        return mv;
    }
    
     @RequestMapping (value = "/cadastro/usuario/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id){
        try {
            ServiceLocator.getUsuarioService().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("redirect:/cadastro/usuario/lista");
        return mv;
    }
    
}
