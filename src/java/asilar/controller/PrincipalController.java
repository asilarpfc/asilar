package asilar.controller;

import asilar.email.Email;
import asilar.model.ServiceLocator;
import asilar.model.criteria.UsuarioCriteria;
import asilar.model.entity.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {

        ModelAndView mv = new ModelAndView("/home");
        return mv;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("/cadastro");
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = null;
        mv = new ModelAndView("/login");
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
            mv = new ModelAndView("redirect:/");
        } else {
            mv = new ModelAndView("/login");
            mv.addObject("erro", erro);
        }

        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }

    @RequestMapping(value = "/erro", method = RequestMethod.GET)
    public ModelAndView erroMessage() {
        ModelAndView mv = new ModelAndView("/erro");
        return mv;
    }

    @RequestMapping(value = "/recuperarSenha", method = RequestMethod.GET)
    public ModelAndView recuperaSenha() {
        ModelAndView mv = new ModelAndView("/recuperasenha");
        return mv;
    }

    @RequestMapping(value = "/recuperarSenha", method = RequestMethod.POST)
    public ModelAndView recuperaSenha(String email) {
        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, email);
        List<Usuario> entityList = new ArrayList<Usuario>();
        ModelAndView mv = null;
        try {
            entityList = ServiceLocator.getUsuarioService().readByCriteria(criteria, null);
            Usuario entity = new Usuario();
            if (entityList != null && entityList.size() > 0 && (email != null && !email.isEmpty())) {
                entity = entityList.get(0);
                entity.setSenha(ServiceLocator.getUsuarioService().gerarSenha());
                ServiceLocator.getUsuarioService().update(entity);
                Email sendEmail = new Email(entity);
                sendEmail.start();
                mv = new ModelAndView("redirect:/");
            } else {
                mv = new ModelAndView("/recuperasenha");
                mv.addObject("erro", "Este email não existe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/redefinir/{id}/{senha}", method = RequestMethod.GET)
    public ModelAndView redefinir(@PathVariable Long id, @PathVariable String senha) {
        ModelAndView mv = null;
        Usuario entity = new Usuario();
        try {
            entity = ServiceLocator.getUsuarioService().readyById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (entity.getSenha().equals(senha)) {
            mv = new ModelAndView("/redefinir");
            mv.addObject("usuario", entity.getUsuario());
        } else {
            mv = new ModelAndView("redirect:/");
        }

        return mv;
    }

    @RequestMapping(value = "/redefinir/{id}/{senh}", method = RequestMethod.POST)
    public ModelAndView redefinir(@PathVariable Long id, @PathVariable String senh, String senha, String confirma, HttpSession session) {
        ModelAndView mv = null;
        Usuario entity = new Usuario();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        try {
            entity = ServiceLocator.getUsuarioService().readyById(id);
            if (senha.equals(confirma)) {
                entity.setSenha(ServiceLocator.getUsuarioService().encodePassword(senha));
                ServiceLocator.getUsuarioService().update(entity);
                if (usuarioLogado != null && usuarioLogado.getId().equals(entity.getId())) {
                    session.invalidate();
                }
                mv = new ModelAndView("redirect:/");
            } else {
                mv = new ModelAndView("/redefinir");
                mv.addObject("erro", "Os dois campos devem conter a mesma senha!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mv;
    }

}
