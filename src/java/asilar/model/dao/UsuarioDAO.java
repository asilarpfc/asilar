
package asilar.model.dao;

import asilar.model.criteria.UsuarioCriteria;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UsuarioDAO implements BaseDAO<Usuario>{

    @Override
    public void update(Connection conn, Usuario entity) throws Exception {
        String sql = "UPDATE usuario SET id=?, email=?, telefone_celular=?, rg=?, nome=?, cpf=?, \n" +
                "telefone_residencial=?, login=?, senha=?, tipo_usuario=?, WHERE id=?;";
        
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, entity.getId());
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getCelular());
        statement.setString(++i, entity.getRg());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getTelfixo());
        statement.setString(++i, entity.getLogin());
        statement.setString(++i, entity.getSenha());
        statement.setInt(++i, entity.getTipoUsuario());
        
        statement.execute();
        statement.close();;
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
        String sql = "INSERT INTO usuario(id, email, telefone_celular, rg, nome, cpf, telefone_residencial, \n" +
                     "login, senha, tipo_usuario)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, entity.getId());
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getCelular());
        statement.setString(++i, entity.getRg());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getTelfixo());
        statement.setString(++i, entity.getLogin());
        statement.setString(++i, entity.getSenha());
        statement.setInt(++i, entity.getTipoUsuario());
        
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            entity.setId(rs.getLong("id"));
        }
        
        rs.close();
        statement.close();
        
    }

    @Override
    public Usuario readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT id, email, telefone_celular, rg, nome, cpf, telefone_residencial, login, senha, \n" +
                     "tipo_usuario FROM usuario;";
        Usuario entity = new Usuario();
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        
        if(rs.next()){
            entity.setId(rs.getLong("id"));
            entity.setEmail(rs.getString("email"));
            entity.setCelular(rs.getString("telefone_celular"));
            entity.setRg(rs.getString(rs.getString("rg")));
            entity.setNome(rs.getString(rs.getString("nome")));
            entity.setCpf(rs.getString("cpf"));
            entity.setTelfixo(rs.getString("telefone_residencial"));
            entity.setLogin(rs.getString("login"));
            entity.setSenha(rs.getString("senha"));
            entity.setTipoUsuario(rs.getInt("tipo_usuario"));  /*Ver se está certo*/
            
        } 
        rs.close();
        statement.close();
        
        return entity;
    }

    @Override
    public List<Usuario> readyByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT id, email, telefone_celular, rg, nome, cpf, telefone_residencial, \n" +
                     "login, senha, tipo_usuario FROM usuario;";
        Statement statement = conn.createStatement();
        if(criteria != null && criteria.size() > 0){
            String nome = (String) criteria.get(UsuarioCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(UsuarioCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and assistido.id != '" + idNe + "'";
            }
        }
        sql += "ORDER BY id ASC";
        
        //paginando
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }
        ResultSet rs = statement.executeQuery(sql);
        List<Usuario> entityList = new ArrayList<Usuario>();
        
        while(rs.next()){
            Usuario entity = new Usuario();
            entity.setId(rs.getLong("id"));
            entity.setEmail(rs.getString("email"));
            entity.setCelular(rs.getString("telefone_celular"));
            entity.setRg(rs.getString(rs.getString("rg")));
            entity.setNome(rs.getString(rs.getString("nome")));
            entity.setCpf(rs.getString("cpf"));
            entity.setTelfixo(rs.getString("telefone_residencial"));
            entity.setLogin(rs.getString("login"));
            entity.setSenha(rs.getString("senha"));
            entity.setTipoUsuario(rs.getInt("tipo_usuario")); /*Ver se está certo*/
            
        }
        rs.close();
        statement.close();
        return entityList;
                
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        
        String sql = "SELECT count(*)  FROM usuario WHERE 1=1";
        Statement statement = conn.createStatement();
        if(criteria != null && criteria.size() > 0){
            String nome = (String) criteria.get(UsuarioCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(UsuarioCriteria.ID_NE);
            if(idNe != null && idNe > 0){
                sql += " and usuario.id != '" + idNe + "'";
            }
        }
        ResultSet rs = statement.executeQuery(sql);
        Long count = 0L;
        if (rs.next()){
            count = rs.getLong("count");
        }
        return count;
    }
    
    
}
