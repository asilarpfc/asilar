package asilar.model.dao;

import asilar.model.criteria.ProdutoCriteria;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProdutoDAO implements BaseDAO<Produto> {

    @Override
    public void update(Connection conn, Produto entity) throws Exception {
        String sql = "UPDATE produto\n"
                + "   SET nome=?, quantidade_minima=?,"
                + " quantidade_maxima=?, unidade_medida=?\n"
                + " WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getNome());
        statement.setLong(++i, entity.getQuantidadeMinima());
        statement.setLong(++i, entity.getQuantidadeMaxima());
        statement.setString(++i, entity.getUnidadeMedida());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();

    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM produto\n" + " WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();

    }

    @Override
    public void create(Connection conn, Produto entity) throws Exception {
        String sql = "INSERT INTO produto( nome, quantidade_minima, quantidade_maxima, unidade_medida) \n"
                + "VALUES (?, ?, ?, ?) RETURNING id";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getNome());
        statement.setLong(++i, entity.getQuantidadeMaxima());
        statement.setLong(++i, entity.getQuantidadeMinima());
        statement.setString(++i, entity.getUnidadeMedida());

        
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*)  FROM produto WHERE 1=1";
        Statement statement = conn.createStatement();
        if (criteria != null && criteria.size() > 0) {
            String produto = (String) criteria.get(ProdutoCriteria.NOME_ILIKE);
            if (produto != null) {
                sql += " AND nome ilike '%" + produto + "%'";
            }
            Long idNe = (Long) criteria.get(ProdutoCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and produto.id != '" + idNe + "'";
            }
        }
        
        ResultSet rs = statement.executeQuery(sql);
        Long count = 0L;
        if (rs.next()) {
            count = rs.getLong("count");
        }
        return count;
    }

    @Override
    public List<Produto> readyByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT id, nome, quantidade_minima, quantidade_maxima, unidade_medida \n"
                + "FROM produto WHERE 1=1";
        Statement statement = conn.createStatement();
        if (criteria != null && criteria.size() > 0) {
            String nome = (String) criteria.get(ProdutoCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";

            }
            String quantidadeMaximaEQ = (String) criteria.get(ProdutoCriteria.QUANTIDADE_MAXIMA_EQ);
            if (quantidadeMaximaEQ != null) {
                sql += "AND quantidadeMaxima '%" + quantidadeMaximaEQ + "%'";

            }
            String quantidadeMinimaEQ = (String) criteria.get(ProdutoCriteria.QUANTIDADE_MINIMA_EQ);
            if (quantidadeMinimaEQ != null) {
                sql += "AND quantidadeMinima '%" + quantidadeMinimaEQ + "%'";

            }
            String unidadeMedidaEQ = (String) criteria.get(ProdutoCriteria.UNIDADE_MEDIDA_EQ);
            if (unidadeMedidaEQ != null) {
                sql += "AND unidadeMedida '%" + unidadeMedidaEQ + "%'";
            }
        }
        sql += "ORDER BY nome ASC";

        //paginando
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }
        ResultSet rs = statement.executeQuery(sql);
        List<Produto> entityList = new ArrayList<Produto>();

        while (rs.next()) {
            Produto entity = new Produto();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setQuantidadeMaxima(rs.getLong("quantidade_maxima"));//arrumar igual ao banco ok Lucas
            entity.setQuantidadeMinima(rs.getLong("quantidade_minima"));
            entity.setUnidadeMedida(rs.getString("unidade_medida"));

            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;

    }

    @Override
    public Produto readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT id, nome, quantidade_minima, quantidade_maxima, unidade_medida \n"
                + "FROM produto \n"
                + "WHERE produto.id=?";
        Produto entity = new Produto();

        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setQuantidadeMaxima(rs.getLong("quantidade_maxima"));//atualizar igual ao banco OK Lucas
            entity.setQuantidadeMinima(rs.getLong("quantidade_minima"));
            entity.setUnidadeMedida(rs.getString("unidade_medida"));

        }
        rs.close();
        statement.close();

        return entity;
    }
}
