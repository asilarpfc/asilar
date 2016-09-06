
package asilar.model.dao;

import asilar.model.criteria.InstituicaoCriteria;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Instituicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class InstituicaoDAO implements BaseDAO<Instituicao>{

    @Override
    public void update(Connection conn, Instituicao entity) throws Exception {
    
        String sql = "UPDATE instituição SET cnpg=?, nome=?, email=?, telefone=?, rua=?, numero=?, bairro=?, \n" +
                     "cep=?, cidade=?, estado=? WHERE id=?;";        
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getCnpj());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getTelefone());
        statement.setString(++i, entity.getRua());
        statement.setString(++i, entity.getNumero());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getCep());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        
        statement.execute();
        statement.close();
    }
    
    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM instituicao WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

    @Override
    public void create(Connection conn, Instituicao entity) throws Exception {
        String sql = "INSERT INTO instituição(cnpg, nome, email, telefone, rua, numero, bairro, cep, cidade, estado)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getCnpj());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getTelefone());
        statement.setString(++i, entity.getRua());
        statement.setString(++i, entity.getNumero());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getCep());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            entity.setId(rs.getLong("id"));
        }        
        rs.close();
        statement.close();
            
    }

    @Override
    public Instituicao readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT id, cnpg, nome, email, telefone, rua, numero, bairro, cep, cidade, estado\n" +
                     "FROM instituição WHERE id=?;";
        int i = 0;
        Instituicao entity = new Instituicao();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        
        if(rs.next()){
            entity.setId(rs.getLong("id"));
            entity.setCnpj(rs.getString("cnpj"));
            entity.setCnpj(rs.getString("nome"));
            entity.setCnpj(rs.getString("email"));
            entity.setCnpj(rs.getString("telefone"));
            entity.setCnpj(rs.getString("rua"));
            entity.setCnpj(rs.getString("numero"));
            entity.setCnpj(rs.getString("bairro"));
            entity.setCnpj(rs.getString("cep"));
            entity.setCnpj(rs.getString("cidade"));
            entity.setCnpj(rs.getString("estado"));
            
        }
        rs.close();
        statement.close();
        
        return entity;
    }

    @Override
    public List<Instituicao> readyByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT id, cnpg, nome, email, telefone, rua, numero, bairro, cep, cidade, estado\n" +
                     "FROM instituição WHERE 1=1;";
        Statement statement = conn.createStatement();
        if(criteria != null && criteria.size() > 0){
            String nome = (String) criteria.get(InstituicaoCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(InstituicaoCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and instituicao.id != '" + idNe + "'";
            }
        }
        sql += "ORDER BY id ASC";
        
        //paginando
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }
        ResultSet rs = statement.executeQuery(sql);
        List<Instituicao> entityList = new ArrayList<Instituicao>();
        while(rs.next()){
            Instituicao entity = new Instituicao();
            entity.setId(rs.getLong("id"));
            entity.setCnpj(rs.getString("cnpj"));
            entity.setCnpj(rs.getString("nome"));
            entity.setCnpj(rs.getString("email"));
            entity.setCnpj(rs.getString("telefone"));
            entity.setCnpj(rs.getString("rua"));
            entity.setCnpj(rs.getString("numero"));
            entity.setCnpj(rs.getString("bairro"));
            entity.setCnpj(rs.getString("cep"));
            entity.setCnpj(rs.getString("cidade"));
            entity.setCnpj(rs.getString("estado"));
            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;      
        
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*)  FROM instituicao WHERE 1=1";
        
        Statement statement = conn.createStatement();
        if(criteria != null && criteria.size() > 0){
            String nome = (String) criteria.get(InstituicaoCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(InstituicaoCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and assistido.id != '" + idNe + "'";
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
