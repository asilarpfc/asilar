package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.entity.Instituicao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InstituicaoController {
    
    @RequestMapping (value = "/cadastro/instituicao/novo", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/cadastro/instituicao/form");
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/instituicao/novo", method = RequestMethod.POST)
    public ModelAndView create(WebRequest request){
        
        Instituicao entity = new Instituicao();
        entity.setCnpj(request.getParameter("cnpj"));
        entity.setNome(request.getParameter("nome"));
        entity.setEmail(request.getParameter("email"));
        entity.setTelefone(request.getParameter("telefone"));
        entity.setRua(request.getParameter("rua"));
        entity.setNumero(request.getParameter("numero"));
        entity.setBairro(request.getParameter("bairro"));
        entity.setCep(request.getParameter("cep"));
        entity.setCidade(request.getParameter("cidade"));
        entity.setEstado(request.getParameter("estado"));

        ModelAndView mv = null;    
                
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("instituicao", entity);
            fields.put("id", entity.getId());
            fields.put("cnpj", entity.getCnpj());
                        
            Map <String, String> errors = ServiceLocator.getInstituicaoService().validateForCreate(fields);
            if(errors.isEmpty()){
                ServiceLocator.getInstituicaoService().create(entity);
                mv = new ModelAndView("redirect:/");
            }else{
                mv = new ModelAndView("cadastro/instituicao/form");
                mv.addObject("instituicao", entity);
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public ModelAndView update(WebRequest request){
        
        Instituicao instituicao = new Instituicao();
        
        instituicao.setId(Long.parseLong(request.getParameter("id")));
        instituicao.setCnpj(request.getParameter("cnpj"));
        instituicao.setNome(request.getParameter("nome"));
        instituicao.setEmail(request.getParameter("email"));
        instituicao.setTelefone(request.getParameter("telefone"));
        instituicao.setRua(request.getParameter("rua"));
        instituicao.setNumero(request.getParameter("numero"));
        instituicao.setBairro(request.getParameter("bairro"));
        instituicao.setCep(request.getParameter("cep"));
        instituicao.setCidade(request.getParameter("cidade"));
        instituicao.setEstado(request.getParameter("estado"));
        
        ModelAndView mv = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("instituicao", instituicao);
            fields.put("id", instituicao.getId());
            fields.put("cnpj", instituicao.getCnpj());
            Map <String, String> errors = ServiceLocator.getInstituicaoService().validateForUpdate(fields);
            if(errors.isEmpty()){
                ServiceLocator.getInstituicaoService().update(instituicao);
                mv = new ModelAndView("redirect:");
            }else{
                mv = new ModelAndView("/cadastro/instituicao/form");
                mv.addObject("instituicao", instituicao);
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return mv;
    }
    
}
