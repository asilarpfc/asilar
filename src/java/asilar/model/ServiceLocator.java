package asilar.model;

import asilar.model.service.AssistidoService;
import asilar.model.service.UsuarioService;
import asilar.model.service.base.BaseAssistidoService;
import asilar.model.service.base.BaseUsuarioService;

public class ServiceLocator {
    
    public static BaseAssistidoService getAssistidoService(){
        return new AssistidoService();
    }
    
    public static BaseUsuarioService getUsuarioService(){
        return new UsuarioService();
    }
    
}
