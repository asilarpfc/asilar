package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.dao.RegistroDAO;
import asilar.model.entity.Registro;
import asilar.model.service.base.BaseRegistroService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistroService implements BaseRegistroService{

    @Override
    public void create(Registro entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
        
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
    public Registro readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
        Registro entity = new Registro();
        
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
    public List<Registro> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
        List<Registro> entityList = new ArrayList<Registro>();
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
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
    public void update(Registro entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
        
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
        RegistroDAO dao = new RegistroDAO();
        
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
