package asilar.model.service.base;

import asilar.model.entity.Produto;

public interface BaseProdutoService extends BaseService<Produto>{    

    public String encodePassword(String nome);

}
