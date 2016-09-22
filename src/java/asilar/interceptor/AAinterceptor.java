package asilar.interceptor;

import asilar.model.ServiceLocator;
import asilar.model.entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AAinterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean shallPass = false;
        String uri = request.getRequestURI();
        String context = "/asilar";
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        int permissoes = 0;
        if(ServiceLocator.getInstituicaoService().countByCriteria(null) < 1){
            permissoes = 1;
        }else if(ServiceLocator.getUsuarioService().countByCriteria(null) < 1){
            permissoes = 2;
        }else if(ServiceLocator.getUsuarioService().countByCriteria(null) > 0 && ServiceLocator.getInstituicaoService().countByCriteria(null) > 0){
            permissoes = 3;
        }
        //UsuarioLogado
        if (usuarioLogado != null) {
            shallPass = true;
        } else {
            //resourses
            if (uri.startsWith(context + "/css/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/js/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/fonts/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/fonts/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/fonts/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/fonts/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/img/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/img/")) {
                shallPass = true;
            }

            if (!shallPass) {
                //Livres
                if(permissoes == 1 && uri.equals(context + "/cadastro/instituicao/novo")) shallPass = true;
                else if(permissoes == 2 && uri.equals(context + "/cadastro/usuario/novo")) shallPass = true;
                else if (permissoes == 3 && uri.equals(context + "/login")) shallPass = true;
                else if (uri.equals(context + "/erro")) shallPass = true;
            }

            if (!shallPass) {
                if(permissoes == 1) response.sendRedirect(context + "/cadastro/instituicao/novo");
                else if(permissoes == 2) response.sendRedirect(context + "/cadastro/usuario/novo");
                else if(permissoes == 3) response.sendRedirect(context + "/login");
            }
        }
        return shallPass;
    }
}

