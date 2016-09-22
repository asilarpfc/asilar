package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.UsuarioCriteria;
import asilar.model.entity.Usuario;
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
public class UsuarioController {

    @RequestMapping(value = "/cadastro/usuario/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("/cadastro/usuario/form");
        return mv;
    }

    @RequestMapping(value = "/cadastro/usuario/novo", method = RequestMethod.POST)
    public ModelAndView create(Usuario entity) {
        ModelAndView mv = null;
        try{
           Map<String, Object> fields = new HashMap<>();
           fields.put("usuario", entity);
           fields.put("id", entity.getId());
           fields.put("cpf", entity.getCpf());
           fields.put("mesmoUsuario", entity.getUsuario());
           fields.put("mesmoEmail", entity.getEmail());
           entity.setInstituicao(ServiceLocator.getInstituicaoService().readyByCriteria(null, null).get(0));
           
           Map<String, String> errors = ServiceLocator.getUsuarioService().validateForCreate(fields);
           if(errors.isEmpty()){
               entity.setSenha(ServiceLocator.getUsuarioService().encodePassword(entity.getSenha()));
               ServiceLocator.getUsuarioService().create(entity);
               mv = new ModelAndView("redirect:/cadastro/usuario/lista");               
           }else{
               mv = new ModelAndView("cadastro/usuario/form");
               mv.addObject("usuario", entity);
               mv.addObject("errors", errors);
           }
            
        }catch (Exception ex) {
            ex.printStackTrace();
            
        }
        
        return mv;
    }

    @RequestMapping(value = "/cadastro/usuario/{id}/info", method = RequestMethod.GET)
    public ModelAndView readById(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        try {
            usuario = ServiceLocator.getUsuarioService().readyById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("/cadastro/usuario/info");
        mv.addObject("usuario", usuario);
        String ativo = "usuario";
        mv.addObject("ativo", ativo);
        return mv;
    }

    @RequestMapping(value = "/cadastro/usuario/lista", method = RequestMethod.GET)
    public ModelAndView readByCriteria(String nome, Long offset) {

        boolean redirect = false;
        if (offset == null || offset < 0) {
            offset = 0L;
            redirect = true;
        }

        List<Usuario> usuarioList = new ArrayList<Usuario>();
        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(UsuarioCriteria.NOME_ILIKE, nome);
        Long count = 0L;
        try {
            usuarioList = ServiceLocator.getUsuarioService().readyByCriteria(criteria, offset);
            count = ServiceLocator.getUsuarioService().countByCriteria(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = null;

        if (redirect) {
            if (nome == null || nome.isEmpty()) {
                mv = new ModelAndView("redirect:/cadastro/usuario/lista?offset=" + offset + "");
            } else {
                mv = new ModelAndView("redirect:/cadastro/usuario/lista?nome=" + nome + "&offset=" + offset + "");
            }
        } else {
            mv = new ModelAndView("/cadastro/usuario/lista");
            mv.addObject("usuarioList", usuarioList);
            mv.addObject("count", count);
            mv.addObject("offset", offset);
            String ativo = "usuario";
            mv.addObject("ativo", ativo);

        }

        return mv;
    }

    @RequestMapping(value = "/cadastro/usuario/{id}/alterar", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        try {
            usuario = ServiceLocator.getUsuarioService().readyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("/cadastro/usuario/form");
        mv.addObject("usuario", usuario);
        String ativo = "usuario";
        mv.addObject("ativo", ativo);
        return mv;
    }

    @RequestMapping(value = "/cadastro/usuario/{id}/alterar", method = RequestMethod.POST)
    public ModelAndView update(Usuario usuario) {
        ModelAndView mv = null;
        try {
            Map<String, Object> fields = new HashMap<>();
           fields.put("usuario", usuario);
           fields.put("id", usuario.getId());
           fields.put("cpf", usuario.getCpf());
           fields.put("mesmoUsuario", usuario.getUsuario());
           fields.put("mesmoEmail", usuario.getEmail());
           
           Map<String, String> errors = ServiceLocator.getUsuarioService().validateForUpdate(fields);
           if(errors.isEmpty()){
               usuario.setSenha(ServiceLocator.getUsuarioService().encodePassword(usuario.getSenha()));
               ServiceLocator.getUsuarioService().update(usuario);
               mv = new ModelAndView("redirect:/cadastro/usuario/lista");               
           }else{
               mv = new ModelAndView("cadastro/usuario/form");
               mv.addObject("usuario", usuario);
               mv.addObject("errors", errors);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/cadastro/usuario/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        try {
            ServiceLocator.getUsuarioService().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("redirect:/cadastro/usuario/lista");
        return mv;
    }

}
