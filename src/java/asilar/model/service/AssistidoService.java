package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.dao.AssistidoDAO;
import asilar.model.entity.Assistido;
import asilar.model.service.base.BaseAssistidoService;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssistidoService implements BaseAssistidoService{

    @Override
    public void create(Assistido entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        try {
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Assistido readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        Assistido entity = null;
        try {
            entity = dao.readyById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return entity;
    }

    @Override
    public List<Assistido> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        List<Assistido> entityList = null;
        try {
            entityList = dao.readyByCriteria(conn, Criteria, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return entityList;
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<String, String>();
        
        Assistido assistido = (Assistido) fields.get("assistido");
        if(assistido.getNome() == null || assistido.getNome().isEmpty()){
            errors.put("nome", "Campo obrigatório");
        }
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        Long count = 0L;
        try {
            count = dao.countByCriteria(conn, criteria);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return count;
    }

    @Override
    public void update(Assistido entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        try {
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        try {
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
