package asilar.controller;

import asilar.model.ServiceLocator;
import asilar.model.criteria.MovimentoAssistidoCriteria;
import asilar.model.entity.Assistido;
import asilar.model.entity.MovimentoAssistido;
import asilar.model.entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MovimentoAssistidoController {

    @RequestMapping(value = "/cadastro/assistido/{id}/registro", method = RequestMethod.GET)
    public ModelAndView readByCriteria(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/registro/list");

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(MovimentoAssistidoCriteria.ASSISTIDO_ID_EQ, id);
        List<MovimentoAssistido> registroList = new ArrayList<MovimentoAssistido>();
        Assistido assistido = new Assistido();
        try {
            registroList = ServiceLocator.getMovimentoAssistidoService().readByCriteria(criteria, null);
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
    public ModelAndView create(@PathVariable Long id, WebRequest request, HttpSession session, RedirectAttributes redir) {

        MovimentoAssistido registro = new MovimentoAssistido();
        Assistido assistido = new Assistido();
        String strDataEntrada = "";
        String strDataSaida = "";

        Date dataEntrada = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            dataEntrada = format.parse(request.getParameter("dataEntrada"));
            registro.setDataEntrada(dataEntrada);
            registro.setUsuarioEntrada((Usuario) session.getAttribute("usuarioLogado"));
        } catch (ParseException ex) {
            strDataEntrada = "Digite uma data de entrada valida!";
        }

        Date dataSaida = new Date();
        format.setLenient(false);
        try {
            dataSaida = format.parse(request.getParameter("dataSaida"));
            registro.setDataSaida(dataSaida);
            registro.setUsuarioSaida((Usuario) session.getAttribute("usuarioLogado"));
        } catch (ParseException ex) {
             if (!request.getParameter("dataSaida").trim().equals("")) {
                strDataSaida = "Digite uma data de saida valida";
            }
        }

        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/" + id + "/registro");
        
        
        
        try {
            Map<String, Object> fields = new HashMap<>();

            fields.put("registro", registro);
            fields.put("dataEntrada", strDataEntrada);
            fields.put("dataSaida", strDataSaida);

            assistido = ServiceLocator.getAssistidoService().readyById(id);
            registro.setAssistido(assistido);
            Map<String, String> errors = ServiceLocator.getMovimentoAssistidoService().validateForCreate(fields);
            if (errors.isEmpty()) {
                ServiceLocator.getMovimentoAssistidoService().create(registro);
            }else {
                redir.addFlashAttribute("errors", errors);
                redir.addFlashAttribute("registroModal", registro);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        return mv;
    }

    @RequestMapping(value = "/cadastro/assistido/{id}/{idr}/editarregistro", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Long id, @PathVariable Long idr, WebRequest request, HttpSession session, RedirectAttributes redir) {

        MovimentoAssistido registro = new MovimentoAssistido();
        MovimentoAssistido antigo; //= new MovimentoAssistido();
        Assistido assistido; //= new Assistido();
        Usuario usuarioEntrada = new Usuario();
        Usuario usuarioSaida = new Usuario();
        String strDataEntrada = "";
        String strDataSaida = "";

        Date dataEntrada; //= new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            dataEntrada = format.parse(request.getParameter("dataEntrada"));
            registro.setDataEntrada(dataEntrada);
        } catch (ParseException ex) {
            strDataEntrada = "Digite uma data de entrada valida!";
        }

        Date dataSaida = new Date();
        format.setLenient(false);
        try {
            dataSaida = format.parse(request.getParameter("dataSaida"));
            registro.setDataSaida(dataSaida);
        } catch (ParseException ex) {
            if (!request.getParameter("dataSaida").trim().equals("")) {
                strDataSaida = "Digite uma data de saida valida";
            }
        }
        usuarioEntrada.setId(Long.parseLong(request.getParameter("usuarioEntrada")));
        usuarioSaida.setId(Long.parseLong(request.getParameter("usuarioSaida")));
        if (usuarioSaida.getId() == 0) {
            usuarioSaida = null; //para atualizar somente quando o registro de data de entrada estiver preenchido
        }
        registro.setUsuarioEntrada(usuarioEntrada);
        registro.setUsuarioSaida(usuarioSaida);
        registro.setId(idr);

        try {
            Map<String, Object> fields = new HashMap<>();

            fields.put("registro", registro);
            fields.put("dataEntrada", strDataEntrada);
            fields.put("dataSaida", strDataSaida);
            
            antigo = ServiceLocator.getMovimentoAssistidoService().readyById(idr);
            if (registro.getDataEntrada() != null && antigo.getDataEntrada().compareTo(registro.getDataEntrada()) != 0) {
                registro.setUsuarioEntrada((Usuario) session.getAttribute("usuarioLogado"));
            }
            if (antigo.getDataSaida() == null && registro.getDataSaida() != null) {
                registro.setUsuarioSaida((Usuario) session.getAttribute("usuarioLogado"));
            } else if (registro.getDataSaida() != null && antigo.getDataSaida().compareTo(registro.getDataSaida()) != 0) {
                registro.setUsuarioSaida((Usuario) session.getAttribute("usuarioLogado"));
            }
            assistido = ServiceLocator.getAssistidoService().readyById(id);
            registro.setAssistido(assistido);
            Map<String, String> errors = ServiceLocator.getMovimentoAssistidoService().validateForUpdate(fields);
            if(errors.isEmpty()){
                ServiceLocator.getMovimentoAssistidoService().update(registro);
            }else {
                redir.addFlashAttribute("errors", errors);
                redir.addFlashAttribute("registroModal", registro);
                redir.addFlashAttribute("idr", idr);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("redirect:/cadastro/assistido/" + id + "/registro");
        return mv;
    }

}
