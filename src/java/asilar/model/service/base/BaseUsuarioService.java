package asilar.model.service.base;

import asilar.model.entity.Usuario;

public interface BaseUsuarioService extends BaseService<Usuario>{
     
    public Usuario login(String email, String senha) throws Exception;
    public String encodePassword(String input) throws Exception;

}
