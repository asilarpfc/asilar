package asilar.model.dao.base;

import asilar.model.entity.base.BaseEntity;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseDAO <E extends BaseEntity>{
    
    public void update (Connection conn, E entity) throws Exception;
    public void delete (Connection conn, Long id) throws Exception;
    public void create (Connection conn,E entity) throws Exception;
    public E readyById (Connection conn, Long id) throws Exception;
    public List<E> readByCriteria (Connection conn, Map<Long, Object> criteria, Long offset) throws Exception; 
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception;
}
