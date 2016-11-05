package asilar.model.service.base;

import asilar.model.entity.Assistido;
import org.json.simple.JSONArray;

public interface BaseAssistidoService extends BaseService<Assistido>{
    public JSONArray readCidade(String estado)throws Exception;
}
