package asilar.model.dao;

import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class RegistroDAO implements BaseDAO<Registro>{

    @Override
    public void update(Connection conn, Registro entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Connection conn, Registro entity) throws Exception {
        String sql = "INSERT INTO registro(data_entrada, data_saida, usuario_fk, assistido_fk)  VALUES (?, ?, ?, ?) RETURNING id;";
        int i = 0;
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(++i, new java.sql.Date(entity.getDataEntrada().getTime()));
        statement.setDate(++i, new java.sql.Date(entity.getDataSaida().getTime()));
        statement.setLong(++i, entity.getUsuario().getId());
        statement.setLong(++i, entity.getAssistido().getId());
        
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        
        rs.close();
        statement.close();
    }

    @Override
    public Registro readyById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Registro> readyByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
