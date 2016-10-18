package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.ServiceLocator;
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
import java.util.Random;

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

        Map<String, String> errors = new HashMap<String, String>();
        Usuario usuario = (Usuario) fields.get("entity");
        if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
            errors.put("nome", "Campo Obrigatório");
        } else if (usuario.getUsuario() == null || usuario.getUsuario().trim().equals("")) {
            errors.put("usuario", "Campo Obrigatório");
        } else if (usuario.getRg() == null || usuario.getRg().trim().equals("")) {
            errors.put("rg", "Campo Obrigatório");
        } else if (usuario.getCpf() == null || usuario.getCpf().trim().equals("")) {
            errors.put("cpf", "Campo Obrigatório");
        } else if (usuario.getTelfixo() == null || usuario.getTelfixo().trim().equals("")) {
            errors.put("telfixo", "Campo Obrigatório");
        } else if (usuario.getCelular() == null || usuario.getCelular().trim().equals("")) {
            errors.put("celular", "Campo Obrigatório");
        } else if (usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
            errors.put("email", "Campo Obrigatório");
        } else if (usuario.getTipoUsuario() == null || usuario.getTipoUsuario().equals("")) {
            errors.put("tipoUsuario", "Campo Obrigatorio");
        } else {

            Map<Long, Object> criteria = new HashMap<>();
            Long id = (Long) fields.get("id");
            String cpf = (String) fields.get("cpf");
            if (id != null && id > 0) {
                criteria.put(UsuarioCriteria.ID_NE, id);
            }
            if (cpf != null && !cpf.isEmpty()) {
                criteria.put(UsuarioCriteria.CPF_EQ, cpf);
            }

            List<Usuario> usuarioList = ServiceLocator.getUsuarioService().readyByCriteria(criteria, null);

            if (!usuarioList.isEmpty()) {
                errors.put("cpf", "este CPF já se encontra em uso");
            }
            usuarioList.clear();
            criteria.clear();
            String mesmoUsuario = (String) fields.get("usuario");
            if (id != null && id > 0) {
                criteria.put(UsuarioCriteria.ID_NE, id);
            }
            if (mesmoUsuario != null && !mesmoUsuario.isEmpty()) {
                criteria.put(UsuarioCriteria.USUARIO_EQ, mesmoUsuario);
            }

            usuarioList = ServiceLocator.getUsuarioService().readyByCriteria(criteria, null);

            if (!usuarioList.isEmpty()) {
                errors.put("usuario", "este Usuario já se encontra em uso");
            }

            criteria.clear();
            usuarioList.clear();
            String mesmoEmail = (String) fields.get("email");
            if (id != null && id > 0) {
                criteria.put(UsuarioCriteria.ID_NE, id);
            }
            if (mesmoEmail != null && !mesmoEmail.isEmpty()) {
                criteria.put(UsuarioCriteria.EMAIL_EQ, mesmoEmail);
            }
            usuarioList = ServiceLocator.getUsuarioService().readyByCriteria(criteria, null);

            if (!usuarioList.isEmpty()) {
                errors.put("email", "este e-mail já se encontra em uso");
            }

        }
        return errors;
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
        return this.validateForCreate(fields);
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
            senha = this.encodePassword(senha);
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.USUARIO_EQ, login);
            criteria.put(UsuarioCriteria.SENHA_EQ, senha);

            List<Usuario> usuarioList
                    = this.readyByCriteria(criteria, null);

            if (usuarioList != null && usuarioList.size() == 1) {
                Usuario usuarioRetornado = usuarioList.get(0);
                if (usuarioRetornado.getUsuario().equals(login)) {
                    if (usuarioRetornado.getSenha().equals(senha)) {
                        usuarioLogado = usuarioRetornado;
                    }
                }
            }
        }
        return usuarioLogado;

    }

    @Override
    public String gerarSenha() throws Exception {
        Random rand = new Random();
        String senha = String.valueOf(Long.toHexString(rand.nextLong()));
        return senha;
    }

}
