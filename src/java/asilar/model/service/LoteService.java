package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.dao.LoteDAO;
import asilar.model.entity.Lote;
import asilar.model.service.base.BaseLoteService;
import asilar.model.service.base.BaseService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoteService implements BaseLoteService{

    @Override
    public void create(Lote entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
         LoteDAO dao = new LoteDAO();
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
    public Lote readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Lote entity = new Lote();
        LoteDAO dao = new LoteDAO();
        
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
    public List<Lote> readByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Lote> entityList = new ArrayList<Lote>();
        LoteDAO dao = new LoteDAO();
        
        try {
            entityList = dao.readByCriteria(conn, Criteria, offset);
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
        Long count = 0L;
        LoteDAO dao = new LoteDAO();
        
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
    public void update(Lote entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        LoteDAO dao = new LoteDAO();
        
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
        LoteDAO dao = new LoteDAO();
        
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
