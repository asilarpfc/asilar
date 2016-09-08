package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.dao.InstituicaoDAO;
import asilar.model.entity.Instituicao;
import asilar.model.service.base.BaseInstituicaoService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstituicaoService implements BaseInstituicaoService{

    @Override
    public void create(Instituicao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InstituicaoDAO dao = new InstituicaoDAO();
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
    public Instituicao readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InstituicaoDAO dao = new InstituicaoDAO();
        Instituicao entity = new Instituicao();
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
    public List<Instituicao> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Instituicao> entityList = new ArrayList<Instituicao>();
        InstituicaoDAO dao = new InstituicaoDAO();
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
        Long count = 0L;
        InstituicaoDAO dao = new InstituicaoDAO();
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
    public void update(Instituicao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        InstituicaoDAO dao = new InstituicaoDAO();
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
        InstituicaoDAO dao = new InstituicaoDAO();
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
