package asilar.model.dao;

import asilar.model.criteria.LoteCriteria;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Lote;
import asilar.model.entity.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoteDAO implements BaseDAO<Lote>{

    @Override
    public void update(Connection conn, Lote entity) throws Exception {
        String sql = "UPDATE lote SET validade=?, quantidade=?, produto_fk=? WHERE id=?;";
        
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(++i, new java.sql.Date(entity.getValidade().getTime()));
        statement.setLong(++i, entity.getQuantidade());
        statement.setLong(++i, entity.getProduto().getId());
        statement.setLong(++i, entity.getId());
        
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM lote WHERE id=?;";
        
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        
        statement.execute();
        statement.close();
    }

    @Override
    public void create(Connection conn, Lote entity) throws Exception {
        String sql = "INSERT INTO lote(validade, quantidade, produto_fk) VALUES (?, ?, ?) RETURNING id;";
        
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setDate(++i, new java.sql.Date(entity.getValidade().getTime()));
        statement.setLong(++i, entity.getQuantidade());
        statement.setLong(++i, entity.getProduto().getId());
        
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            entity.setId(rs.getLong("id"));
        }
        
        rs.close();
        statement.close();
    }

    @Override
    public Lote readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT lote.id lote_id, lote.validade lote_validade, lote.quantidade lote_quantidade, \n" +
                    "produto.id produto_id, produto.nome produto_nome, produto.quantidade_minima produto_quantidade_minima, \n" +
                    "produto.quantidade_maxima produto_quantidade_maxima, \n" +
                    "produto.unidade_medida FROM lote  \n" +
                    "LEFT JOIN produto ON lote.produto_fk=produto.id \n" +
                    "WHERE lote.id = ?;";
        
        int i = 0;
        
        Lote entity = new Lote();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        
        ResultSet rs = statement.executeQuery();
        
        if(rs.next()){
            entity.setId(rs.getLong("lote_id"));
            entity.setValidade(rs.getDate("lote_validade"));
            entity.setQuantidade(rs.getLong("lote_quantidade"));
            
            Produto produto = new Produto();
            produto.setId(rs.getLong("produto_id"));
            produto.setNome(rs.getString("produto_nome"));
            produto.setQuantidadeMinima(rs.getLong("produto_quantidade_minima"));
            produto.setQuantidadeMaxima(rs.getLong("produto_quantidade_maxima"));
            entity.setProduto(produto);
        }
        rs.close();
        statement.close();
        
        return entity;
    }

    @Override
    public List<Lote> readByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT lote.id lote_id, lote.validade lote_validade, lote.quantidade lote_quantidade, \n" +
                    "produto.id produto_id, produto.nome produto_nome, produto.quantidade_minima produto_quantidade_minima, \n" +
                    "produto.quantidade_maxima produto_quantidade_maxima, \n" +
                    "produto.unidade_medida FROM lote  \n" +
                    "LEFT JOIN produto ON lote.produto_fk=produto.id \n" +
                    "WHERE 1=1";
        
        Statement statement = conn.createStatement();
        
        if(criteria != null && criteria.size() > 0){
            
            Long produtoIdEq = (Long) criteria.get(LoteCriteria.PRODUTO_ID_EQ);
            if(produtoIdEq != null && produtoIdEq > 0){
                sql += " AND lote.produto_fk = '" + produtoIdEq +"'";
            }
            
        }
        
        sql += " ORDER BY lote.validade ASC";
        
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }
        
        
        ResultSet rs = statement.executeQuery(sql);
        List <Lote> entityList = new ArrayList<Lote>();
        
        while (rs.next()){
            Lote entity = new Lote();
            
            entity.setId(rs.getLong("lote_id"));
            entity.setValidade(rs.getDate("lote_validade"));
            entity.setQuantidade(rs.getLong("lote_quantidade"));
            
            Produto produto = new Produto();
            produto.setId(rs.getLong("produto_id"));
            produto.setNome(rs.getString("produto_nome"));
            produto.setQuantidadeMinima(rs.getLong("produto_quantidade_minima"));
            produto.setQuantidadeMaxima(rs.getLong("produto_quantidade_maxima"));
            entity.setProduto(produto);
            
            entityList.add(entity);
        }
        
        rs.close();
        statement.close();
        
       return entityList; 
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT lote.id lote_id, lote.validade lote_validade, lote.quantidade lote_quantidade, \n" +
                    "produto.id produto_id, produto.nome produto_nome, produto.quantidade_minima produto_quantidade_minima, \n" +
                    "produto.quantidade_maxima produto_quantidade_maxima, \n" +
                    "produto.unidade_medida FROM lote  \n" +
                    "LEFT JOIN produto ON lote.produto_fk=produto.id \n" +
                    "WHERE 1=1";
        
        Statement statement = conn.prepareStatement(sql);
        
        if(criteria != null && criteria.size() > 0){
            
            Long produtoIdEq = (Long) criteria.get(LoteCriteria.PRODUTO_ID_EQ);
            if(produtoIdEq != null && produtoIdEq > 0){
                sql += " AND lote.produto_fk = '" + produtoIdEq +"'";
            }
        }
        
        sql += " ORDER BY lote.validade ASC";
        
        ResultSet rs = statement.executeQuery(sql);
        Long count = 0L;
        if (rs.next()) {
            count = rs.getLong("count");
        }
        
        rs.close();
        statement.close();
        return count;
    }
    
}
