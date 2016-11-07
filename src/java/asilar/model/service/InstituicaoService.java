package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.ServiceLocator;
import asilar.model.criteria.InstituicaoCriteria;
import asilar.model.dao.InstituicaoDAO;
import asilar.model.entity.Instituicao;
import asilar.model.service.base.BaseInstituicaoService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Instituicao> readByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Instituicao> entityList = new ArrayList<Instituicao>();
        InstituicaoDAO dao = new InstituicaoDAO();
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
        
        Map<String, String> errors = new HashMap<String, String>();
        Instituicao instituicao = (Instituicao) fields.get("instituicao");
        
        if (instituicao.getNome() == null || instituicao.getNome().trim().equals("")) {
            errors.put("nome", "Campo obrigatório");
        }else if(instituicao.getCnpj() == null || instituicao.getCnpj().trim().equals("")){
            errors.put("cnpj", "Campo obrigatório");
        }else if(instituicao.getEmail() == null || instituicao.getEmail().trim().equals("")){
            errors.put("email", "Campo obrigatório");
        }else if(instituicao.getTelefone() == null || instituicao.getTelefone().trim().equals("")){
            errors.put("telefone", "Campo obrigatório");
        }else if(instituicao.getRua() == null || instituicao.getRua().trim().equals("")){
            errors.put("rua", "Campo obrigatório");
        }else if(instituicao.getNumero() == null || instituicao.getNumero().trim().equals("")){
            errors.put("numero", "Campo obrigatório");
        }else if(instituicao.getBairro() == null || instituicao.getBairro().trim().equals("")){
            errors.put("bairro", "Campo obrigatório");
        }else if(instituicao.getCep() == null || instituicao.getCep().trim().equals("")){
            errors.put("cep", "Campo obrigatório");
        }else if(instituicao.getCidade() == null || instituicao.getCidade().trim().equals("")){
            errors.put("cidade", "Campo obrigatório");
        }else if(instituicao.getEstado() == null || instituicao.getEstado().trim().equals("")){
            errors.put("estado", "Campo obrigatório");
        }else{
        
            Map<Long, Object> criteria = new HashMap<>();
            Long id = (Long) fields.get("id");
            String nome = (String) fields.get("nome");
            String usuario = (String) fields.get("usuario");
            if (id != null && id > 0) {
                criteria.put(InstituicaoCriteria.ID_NE, id);
            }
            if (nome != null && !nome.isEmpty()) {
                criteria.put(InstituicaoCriteria.NOME_ILIKE, nome);
            }
            

            List<Instituicao> instituicaoList = ServiceLocator.getInstituicaoService().readByCriteria(criteria, null);

            if (!instituicaoList.isEmpty()) {
                errors.put("nome", "Este nome ja se encontra em uso!");
            }
        }
        return errors;
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
