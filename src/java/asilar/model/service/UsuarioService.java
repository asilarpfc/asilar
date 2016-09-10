package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.criteria.UsuarioCriteria;
import asilar.model.dao.UsuarioDAO;
import asilar.model.entity.Usuario;
import asilar.model.service.base.BaseUsuarioService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioService implements BaseUsuarioService {

    @Override
    public void create(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
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
    public Usuario readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Usuario entity = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();
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
    public List<Usuario> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Usuario> entityList = new ArrayList<Usuario>();
        UsuarioDAO dao = new UsuarioDAO();
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
        UsuarioDAO dao = new UsuarioDAO();
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
    public void update(Usuario entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
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
        UsuarioDAO dao = new UsuarioDAO();
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
    
        @Override
        public String encodePassword(String input) throws Exception {
        input += "255ve";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

        @Override
        public Usuario login(String login, String senha) throws Exception {

        Usuario usuarioLogado = null;
        if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
            if(!senha.equals("123456")){
                senha = this.encodePassword(senha);
            }
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.LOGIN_EQ, login);
            criteria.put(UsuarioCriteria.SENHA_EQ, senha);

            List<Usuario> usuarioList
                    = this.readyByCriteria(criteria, null);

            if (usuarioList != null && usuarioList.size() == 1) {
                Usuario usuarioRetornado = usuarioList.get(0);
                if (usuarioRetornado.getLogin().equals(login)) {
                    if (usuarioRetornado.getSenha().equals(senha)) {
                        usuarioLogado = usuarioRetornado;
                    }
                }
            }
        }
        return usuarioLogado;

    }

}
