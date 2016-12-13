package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.AssistidoCriteria;
import asilar.model.entity.Assistido;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AssistidoController {
    
    
    @RequestMapping (value = "/cadastro/assistido/novo", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/cadastro/assistido/form");
        String ativo = "assistido";
        mv.addObject("ativo", ativo);
        return mv;
    }
    
    @RequestMapping (value = "/cadastro/assistido/novo", method = RequestMethod.POST)
    public ModelAndView create(WebRequest request){
        
        Assistido entity = new Assistido();
        
        entity.setNome(request.getParameter("nome"));
        entity.setRg(request.getParameter("rg"));
        entity.setCpf(request.getParameter("cpf"));
        entity.setCartaoSus(request.getParameter("cartaoSus"));
        entity.setNoDoBeneficio(request.getParameter("noDoBeneficio"));
        entity.setTelfixo(request.getParameter("telfixo"));
        entity.setCelular(request.getParameter("celular"));
        entity.setEstadoCivil(request.getParameter("estadoCivil"));
        entity.setProfissao(request.getParameter("profissao"));
        entity.setNaturalidade(request.getParameter("naturalidade"));
        entity.setNacionalidade(request.getParameter("nacionalidade"));
        entity.setSexo(request.getParameter("sexo"));
        entity.setPai(request.getParameter("pai"));
        entity.setMae(request.getParameter("mae"));
        entity.setObservacoes(request.getParameter("observacoes"));
        entity.setProcedencia(request.getParameter("procedencia"));
        entity.setRua(request.getParameter("rua"));
        entity.setNumero(request.getParameter("numero"));
        entity.setBairro(request.getParameter("bairro"));
        entity.setCidade(request.getParameter("cidade"));
        entity.setEstado(request.getParameter("estado"));
        entity.setCep(request.getParameter("cep"));
        entity.setBanco(request.getParameter("banco"));
        entity.setAgencia(request.getParameter("agencia"));
        entity.setConta(request.getParameter("conta"));
        
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            data = format.parse(request.getParameter("dataNascimento"));
            entity.setDataNascimento(data);
        } catch (ParseException ex) {
            }
        
        
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
        String tab = "info";
        mv.addObject("tab", tab);
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
    public ModelAndView update(WebRequest request){
        Assistido assistido = new Assistido();
        
        assistido.setId(Long.parseLong(request.getParameter("id")));
        assistido.setNome(request.getParameter("nome"));
        assistido.setRg(request.getParameter("rg"));
        assistido.setCpf(request.getParameter("cpf"));
        assistido.setCartaoSus(request.getParameter("cartaoSus"));
        assistido.setNoDoBeneficio(request.getParameter("noDoBeneficio"));
        assistido.setTelfixo(request.getParameter("telfixo"));
        assistido.setCelular(request.getParameter("celular"));
        assistido.setEstadoCivil(request.getParameter("estadoCivil"));
        assistido.setProfissao(request.getParameter("profissao"));
        assistido.setNaturalidade(request.getParameter("naturalidade"));
        assistido.setNacionalidade(request.getParameter("nacionalidade"));
        assistido.setSexo(request.getParameter("sexo"));
        assistido.setPai(request.getParameter("pai"));
        assistido.setMae(request.getParameter("mae"));
        assistido.setObservacoes(request.getParameter("observacoes"));
        assistido.setProcedencia(request.getParameter("procedencia"));
        assistido.setRua(request.getParameter("rua"));
        assistido.setNumero(request.getParameter("numero"));
        assistido.setBairro(request.getParameter("bairro"));
        assistido.setCidade(request.getParameter("cidade"));
        assistido.setEstado(request.getParameter("estado"));
        assistido.setCep(request.getParameter("cep"));
        assistido.setBanco(request.getParameter("banco"));
        assistido.setAgencia(request.getParameter("agencia"));
        assistido.setConta(request.getParameter("conta"));
        
        Date data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            data = format.parse(request.getParameter("dataNascimento"));
            assistido.setDataNascimento(data);
        } catch (ParseException ex) {
            }
        
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
    public ModelAndView readbyCriteria(String nome, String cpf, String presentes, Long offset){

        
        boolean redirect = false;
        if (offset == null || offset < 0) {
            offset = 0L;
            redirect = true;
        }
        
        List<Assistido> assistidoList = null;
        Long count = 0L;
        
        String uri = "";
        
        if(presentes == null || presentes.isEmpty()){
            presentes = "todos";
        }
        if(nome != null && !nome.isEmpty()){
            uri +="nome="+nome;
        }
        if(cpf != null && !cpf.isEmpty()){
            uri +="&cpf="+cpf;
        }if(presentes != null && !presentes.isEmpty()){
            uri +="&presentes="+presentes;
        }
        uri +="&offset="+offset;
        
        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(AssistidoCriteria.NOME_ILIKE, nome);
        criteria.put(AssistidoCriteria.CPF_EQ, cpf);
        criteria.put(AssistidoCriteria.PRESENTES, presentes);
        
        try {
            assistidoList = ServiceLocator.getAssistidoService().readByCriteria(criteria, offset);
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
            mv.addObject("presentes", presentes);
        }
                
        return mv;
        
    }
    
    
}
