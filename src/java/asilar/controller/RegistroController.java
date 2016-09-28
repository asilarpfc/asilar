package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.RegistroCriteria;
import asilar.model.entity.Assistido;
import asilar.model.entity.Registro;
import asilar.model.entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
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
    
    @RequestMapping(value = "/cadastro/assistido/{id}/novoregistro", method = RequestMethod.POST)
    public ModelAndView create(@PathVariable Long id, WebRequest request, HttpSession session){
        
        Registro registro = new Registro();
        Assistido assistido = new Assistido();
        
        Date dataEntrada = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            dataEntrada = format.parse(request.getParameter("dataEntrada"));
            registro.setDataEntrada(dataEntrada);
            registro.setUsuarioEntrada((Usuario)session.getAttribute("usuarioLogado"));
        } catch (ParseException ex) {
        }
        
        Date dataSaida = new Date();
        format.setLenient(false);
        try {
            dataSaida = format.parse(request.getParameter("dataSaida"));
            registro.setDataSaida(dataSaida);
            registro.setUsuarioSaida((Usuario)session.getAttribute("usuarioLogado"));
        } catch (ParseException ex) {
        }
        
        
        
        try {
            assistido = ServiceLocator.getAssistidoService().readyById(id);
            registro.setAssistido(assistido);
            ServiceLocator.getRegistroService().create(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/" + id +"/registro");
        return mv;
    }
    
    @RequestMapping(value = "/cadastro/assistido/{id}/{idr}/editarregistro", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, @PathVariable Long idr, WebRequest request, HttpSession session){
        
        Registro registro = new Registro();
        Assistido assistido = new Assistido();
        Usuario usuarioEntrada = new Usuario();
        
        Date dataEntrada = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            dataEntrada = format.parse(request.getParameter("dataEntrada"));
            registro.setDataEntrada(dataEntrada);
        } catch (ParseException ex) {
        }
        
        Date dataSaida = new Date();
        format.setLenient(false);
        try {
            dataSaida = format.parse(request.getParameter("dataSaida"));
            registro.setDataSaida(dataSaida);
            registro.setUsuarioSaida((Usuario)session.getAttribute("usuarioLogado"));
        } catch (ParseException ex) {
        }
        usuarioEntrada.setId(Long.parseLong(request.getParameter("usuarioEntrada")));
        registro.setUsuarioEntrada(usuarioEntrada);
        registro.setId(idr);
        
        
        try {
            assistido = ServiceLocator.getAssistidoService().readyById(id);
            registro.setAssistido(assistido);
            ServiceLocator.getRegistroService().update(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/" + id +"/registro");
        return mv;
    }
    
}
