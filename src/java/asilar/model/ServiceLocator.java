package asilar.model;

import asilar.model.service.AssistidoService;
import asilar.model.service.InstituicaoService;
import asilar.model.service.LoteService;
import asilar.model.service.ProdutoService;
import asilar.model.service.MovimentoAssistidoService;
import asilar.model.service.UsuarioService;
import asilar.model.service.base.BaseAssistidoService;
import asilar.model.service.base.BaseInstituicaoService;
import asilar.model.service.base.BaseLoteService;
import asilar.model.service.base.BaseProdutoService;
import asilar.model.service.base.BaseMovimentoAssistidoService;
import asilar.model.service.base.BaseUsuarioService;

public class ServiceLocator {
    
    public static BaseAssistidoService getAssistidoService(){
        return new AssistidoService();
    }
    
    public static BaseUsuarioService getUsuarioService(){
        return new UsuarioService();
    }
    
    public static BaseInstituicaoService getInstituicaoService(){
        return new InstituicaoService();
    }
    
    public static BaseMovimentoAssistidoService getMovimentoAssistidoService(){
        return new MovimentoAssistidoService();
    }
    
    public static BaseProdutoService getProdutoService(){
        return new ProdutoService();
    }
    
    public static BaseLoteService getLoteService(){
        return new LoteService();
    }
}
