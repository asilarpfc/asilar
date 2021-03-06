package asilar.model.dao;

import asilar.model.criteria.UsuarioCriteria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Instituicao;
import asilar.model.entity.Usuario;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class UsuarioDAO implements BaseDAO<Usuario> {

    @Override
    public void update(Connection conn, Usuario entity) throws Exception {
        String sql = "UPDATE usuario SET email=?, telefone_celular=?, rg=?, nome=?, cpf=?, \n"
                + "telefone_residencial=?, usuario=?, senha=?, tipo_usuario=? WHERE id=?;";

        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getCelular());
        statement.setString(++i, entity.getRg());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getTelfixo());
        statement.setString(++i, entity.getUsuario());
        statement.setString(++i, entity.getSenha());
        statement.setInt(++i, entity.getTipoUsuario());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

    @Override
    public void create(Connection conn, Usuario entity) throws Exception {
        String sql = "INSERT INTO usuario(email, telefone_celular, rg, nome, cpf, telefone_residencial, \n"
                + "usuario, senha, tipo_usuario, instituicao_fk)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getCelular());
        statement.setString(++i, entity.getRg());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getTelfixo());
        statement.setString(++i, entity.getUsuario());
        statement.setString(++i, entity.getSenha());
        statement.setInt(++i, entity.getTipoUsuario());
        statement.setLong(++i, entity.getInstituicao().getId());

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        statement.close();

    }

    @Override
    public Usuario readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT usuario.id, usuario.email, usuario.telefone_celular, usuario.rg, usuario.nome, usuario.cpf, usuario.telefone_residencial, usuario.usuario, usuario.senha, \n"
                + "usuario.tipo_usuario, instituicao.id instituicao_id, instituicao.cnpj instituicao_cmpj, instituicao.nome instituicao_nome, instituicao.email instituicao_email,\n"
                + "instituicao.telefone instituicao_telefone, instituicao.rua instituicao_rua, instituicao.numero instituicao_numero, instituicao.bairro instituicao_bairro,\n"
                + "instituicao.cep instituicao_cep, instituicao.cidade instituicao_cidade, instituicao.estado instituicao_estado\n"
                + "FROM usuario \n"
                + "LEFT JOIN instituicao ON usuario.instituicao_fk=instituicao.id\n"
                + "WHERE usuario.id=?";
        Usuario entity = new Usuario();
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            entity.setId(rs.getLong("id"));
            entity.setEmail(rs.getString("email"));
            entity.setCelular(rs.getString("telefone_celular"));
            entity.setRg(rs.getString("rg"));
            entity.setNome(rs.getString("nome"));
            entity.setCpf(rs.getString("cpf"));
            entity.setTelfixo(rs.getString("telefone_residencial"));
            entity.setUsuario(rs.getString("usuario"));
            entity.setSenha(rs.getString("senha"));
            entity.setTipoUsuario(rs.getInt("tipo_usuario"));
            
            Instituicao instituicao = new Instituicao();
            instituicao.setId(rs.getLong("instituicao_id"));
            instituicao.setCnpj(rs.getString("instituicao_cmpj"));
            instituicao.setNome(rs.getString("instituicao_nome"));
            instituicao.setEmail(rs.getString("instituicao_email"));
            instituicao.setTelefone(rs.getString("instituicao_telefone"));
            instituicao.setRua(rs.getString("instituicao_rua"));
            instituicao.setNumero(rs.getString("instituicao_numero"));
            instituicao.setBairro(rs.getString("instituicao_bairro"));
            instituicao.setCep(rs.getString("instituicao_cep"));
            instituicao.setCidade(rs.getString("instituicao_cidade"));
            instituicao.setEstado(rs.getString("instituicao_estado"));

        }
        rs.close();
        statement.close();

        return entity;
    }

    @Override
    public List<Usuario> readByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT id, email, telefone_celular, rg, nome, cpf, telefone_residencial, \n"
                + "usuario, senha, tipo_usuario FROM usuario WHERE 1=1";
        Statement statement = conn.createStatement();
        if (criteria != null && criteria.size() > 0) {
            String nome = (String) criteria.get(UsuarioCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }

            Long idNe = (Long) criteria.get(UsuarioCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and usuario.id != '" + idNe + "'";
            }

            String usuarioEq = (String) criteria.get(UsuarioCriteria.USUARIO_EQ);
            if (usuarioEq != null) {
                sql += " and usuario.usuario = '" + usuarioEq + "'";
            }

            String emailEq = (String) criteria.get(UsuarioCriteria.EMAIL_EQ);
            if (emailEq != null) {
                sql += " and usuario.email = '" + emailEq + "'";
            }

            String senhaEq = (String) criteria.get(UsuarioCriteria.SENHA_EQ);
            if (senhaEq != null) {
                sql += " and usuario.senha = '" + senhaEq + "'";
            }
            String cpfEq = (String) criteria.get(UsuarioCriteria.CPF_EQ);
            if (cpfEq != null) {
                sql += " and usuario.cpf = '" + cpfEq + "'";
            }
        }

        sql += "ORDER BY id ASC";

        //paginando
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }
        ResultSet rs = statement.executeQuery(sql);
        List<Usuario> entityList = new ArrayList<Usuario>();

        while (rs.next()) {
            Usuario entity = new Usuario();
            entity.setId(rs.getLong("id"));
            entity.setEmail(rs.getString("email"));
            entity.setCelular(rs.getString("telefone_celular"));
            entity.setRg(rs.getString("rg"));
            entity.setNome(rs.getString("nome"));
            entity.setCpf(rs.getString("cpf"));
            entity.setTelfixo(rs.getString("telefone_residencial"));
            entity.setUsuario(rs.getString("usuario"));
            entity.setSenha(rs.getString("senha"));
            entity.setTipoUsuario(rs.getInt("tipo_usuario")); /*Ver se está certo*/

            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;

    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {

        String sql = "SELECT count(*)  FROM usuario WHERE 1=1";
        Statement statement = conn.createStatement();
        if (criteria != null && criteria.size() > 0) {
            String nome = (String) criteria.get(UsuarioCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(UsuarioCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and usuario.id != '" + idNe + "'";
            }
        }
        ResultSet rs = statement.executeQuery(sql);
        Long count = 0L;
        if (rs.next()) {
            count = rs.getLong("count");
        }
        return count;
    }

}
