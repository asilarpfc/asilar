package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.entity.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("/home");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("/cadastro");
        return mv;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("/login");
        return mv;
    }
    
        @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String login, String senha, HttpSession session) {
            
        Usuario usuarioLogado = null;
        String erro = "Login ou senha incorretos!!";
        ModelAndView mv = null;
        try {
            usuarioLogado = ServiceLocator.getUsuarioService().login(login, senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (usuarioLogado != null) {
            session.setAttribute("usuarioLogado", usuarioLogado);
            if (!usuarioLogado.getSenha().equals("123456")) {
                mv = new ModelAndView("redirect:/");
            } else {
                mv = new ModelAndView("redirect:/login/novasenha/" + usuarioLogado.getId() + "");
            }
        } else {
            mv = new ModelAndView("/login");
            mv.addObject("erro", erro);
        }

        return mv;
    }
    
    @RequestMapping(value = "/erro", method = RequestMethod.GET)
    public ModelAndView erroMessage(){
        ModelAndView mv = new ModelAndView("/erro");
        return mv;
    }
    
}
