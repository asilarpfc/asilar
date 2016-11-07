package asilar.model.service.base;

import asilar.model.entity.base.BaseEntity;
import java.util.List;
import java.util.Map;

public interface BaseService <E extends BaseEntity>{
    
    public void create (E entity) throws Exception;
    public E readyById (Long id) throws Exception;
    public List<E> readByCriteria (Map<Long, Object> Criteria, Long offset) throws Exception;
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception;  
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception;
    public void update (E entity) throws Exception;
    public void delete (Long id) throws Exception;
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception;
    
}
