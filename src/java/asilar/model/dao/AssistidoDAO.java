package asilar.model.dao;

import asilar.model.criteria.AssistidoCriteria;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Assistido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssistidoDAO implements BaseDAO<Assistido> {

    @Override
    public void update(Connection conn, Assistido entity) throws Exception {
        String sql = "UPDATE assistido SET profissao=?, nacionalidade=?, nome=?, rg=?, cpf=?, telfixo=?, \n"
                + "celular=?, banco=?, agencia=?, conta=?, naturalidade=?, estado_civil=?, \n"
                + "mae=?, pai=?, rua=?, bairro=?, numero=?, cidade=?, estado=?, \n"
                + "sexo=?, data_nascimento=?, observacoes=?, procedencia=?, cartao_sus=?, no_do_beneficio=? WHERE id = ?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getProfissao());
        statement.setString(++i, entity.getNacionalidade());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getRg());
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getTelfixo());
        statement.setString(++i, entity.getCelular());
        statement.setString(++i, entity.getBanco());
        statement.setString(++i, entity.getAgencia());
        statement.setString(++i, entity.getConta());
        statement.setString(++i, entity.getNaturalidade());
        statement.setString(++i, entity.getEstadoCivil());
        statement.setString(++i, entity.getMae());
        statement.setString(++i, entity.getPai());
        statement.setString(++i, entity.getRua());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getNumero());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        statement.setString(++i, entity.getSexo());
        statement.setDate(++i, new java.sql.Date(entity.getDataNascimento().getTime()));
        statement.setString(++i, entity.getObservacoes());
        statement.setString(++i, entity.getProcedencia());
        statement.setString(++i, entity.getCartaoSus());
        statement.setString(++i, entity.getNoDoBeneficio());
        statement.setLong(++i, entity.getId());

        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM assistido WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

    @Override
    public void create(Connection conn, Assistido entity) throws Exception {
        String sql = "INSERT INTO assistido(profissao, nacionalidade, nome, rg, cpf, telfixo, celular, \n"
                + "banco, agencia, conta, naturalidade, estado_civil, mae, pai, \n"
                + "rua, bairro, numero, cidade, estado, sexo, data_nascimento, observacoes, \n"
                + "procedencia, cartao_sus, no_do_beneficio)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getProfissao());
        statement.setString(++i, entity.getNacionalidade());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getRg());
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getTelfixo());
        statement.setString(++i, entity.getCelular());
        statement.setString(++i, entity.getBanco());
        statement.setString(++i, entity.getAgencia());
        statement.setString(++i, entity.getConta());
        statement.setString(++i, entity.getNaturalidade());
        statement.setString(++i, entity.getEstadoCivil());
        statement.setString(++i, entity.getMae());
        statement.setString(++i, entity.getPai());
        statement.setString(++i, entity.getRua());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getNumero());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        statement.setString(++i, entity.getSexo());
        statement.setDate(++i, new java.sql.Date(entity.getDataNascimento().getTime()));
        statement.setString(++i, entity.getObservacoes());
        statement.setString(++i, entity.getProcedencia());
        statement.setString(++i, entity.getCartaoSus());
        statement.setString(++i, entity.getNoDoBeneficio());

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }

        rs.close();
        statement.close();
    }

    @Override
    public Assistido readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT id, profissao, nacionalidade, nome, rg, cpf, telfixo, celular, \n"
                + "banco, agencia, conta, naturalidade, estado_civil, mae, pai, \n"
                + "rua, bairro, numero, cidade, estado, sexo, data_nascimento, observacoes, \n"
                + "procedencia, cartao_sus, no_do_beneficio FROM assistido WHERE id =?;";

        int i = 0;
        Assistido entity = new Assistido();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            entity.setId(rs.getLong("id"));
            entity.setProfissao(rs.getString("profissao"));
            entity.setNacionalidade(rs.getString("nacionalidade"));
            entity.setNome(rs.getString("nome"));
            entity.setRg(rs.getString("rg"));
            entity.setCpf(rs.getString("cpf"));
            entity.setTelfixo(rs.getString("telfixo"));
            entity.setCelular(rs.getString("celular"));
            entity.setBanco(rs.getString("banco"));
            entity.setAgencia(rs.getString("agencia"));
            entity.setConta(rs.getString("conta"));
            entity.setNaturalidade(rs.getString("naturalidade"));
            entity.setEstadoCivil(rs.getString("estado_civil"));
            entity.setMae(rs.getString("mae"));
            entity.setPai(rs.getString("pai"));
            entity.setRua(rs.getString("rua"));
            entity.setBairro(rs.getString("bairro"));
            entity.setNumero(rs.getString("numero"));
            entity.setCidade(rs.getString("cidade"));
            entity.setEstado(rs.getString("estado"));
            entity.setSexo(rs.getString("sexo"));
            entity.setDataNascimento(rs.getDate("data_nascimento"));
            entity.setObservacoes(rs.getString("observacoes"));
            entity.setProcedencia(rs.getString("procedencia"));
            entity.setCartaoSus(rs.getString("cartao_sus"));
            entity.setNoDoBeneficio(rs.getString("no_do_beneficio"));
        }

        rs.close();
        statement.close();

        return entity;
    }

    @Override
    public List<Assistido> readByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT id, profissao, nacionalidade, nome, rg, cpf, telfixo, celular,\n"
                + "banco, agencia, conta, naturalidade, estado_civil, mae, pai,\n"
                + "rua, bairro, numero, cidade, estado, sexo, data_nascimento, observacoes,\n"
                + "procedencia, cartao_sus, no_do_beneficio  FROM assistido WHERE 1=1";

        Statement statement = conn.createStatement();
        if (criteria != null && criteria.size() > 0) {
            String nome = (String) criteria.get(AssistidoCriteria.NOME_ILIKE);
            if (nome != null && !nome.isEmpty()) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(AssistidoCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and assistido.id != '" + idNe + "'";
            }
            String cpfEq = (String) criteria.get(AssistidoCriteria.CPF_EQ);
            if (cpfEq != null && !cpfEq.isEmpty()) {
                sql += " and cpf = '" + cpfEq + "'";
            }
            String presentes = (String) criteria.get(AssistidoCriteria.PRESENTES);
            if(presentes != null && !presentes.isEmpty()){
                if(presentes.equals("presentes")){
                    sql += " and assistido. id in (select distinct assistido_fk from movimento_assistido where data_saida is null)";
                }else if(presentes.equals("ausentes")){
                    sql += " and assistido.id not in (select distinct assistido_fk from movimento_assistido where data_saida is null)";
                }
            }
        }
        sql += " ORDER BY id DESC";

        //paginando
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }

        ResultSet rs = statement.executeQuery(sql);
        List<Assistido> entityList = new ArrayList<Assistido>();
        while (rs.next()) {
            Assistido entity = new Assistido();
            entity.setId(rs.getLong("id"));
            entity.setProfissao(rs.getString("profissao"));
            entity.setNacionalidade(rs.getString("nacionalidade"));
            entity.setNome(rs.getString("nome"));
            entity.setRg(rs.getString("rg"));
            entity.setCpf(rs.getString("cpf"));
            entity.setTelfixo(rs.getString("telfixo"));
            entity.setCelular(rs.getString("celular"));
            entity.setBanco(rs.getString("banco"));
            entity.setAgencia(rs.getString("agencia"));
            entity.setConta(rs.getString("conta"));
            entity.setNaturalidade(rs.getString("naturalidade"));
            entity.setEstadoCivil(rs.getString("estado_civil"));
            entity.setMae(rs.getString("mae"));
            entity.setPai(rs.getString("pai"));
            entity.setRua(rs.getString("rua"));
            entity.setBairro(rs.getString("bairro"));
            entity.setNumero(rs.getString("numero"));
            entity.setCidade(rs.getString("cidade"));
            entity.setEstado(rs.getString("estado"));
            entity.setSexo(rs.getString("sexo"));
            entity.setDataNascimento(rs.getDate("data_nascimento"));
            entity.setObservacoes(rs.getString("observacoes"));
            entity.setProcedencia(rs.getString("procedencia"));
            entity.setCartaoSus(rs.getString("cartao_sus"));
            entity.setNoDoBeneficio(rs.getString("no_do_beneficio"));
            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*)  FROM assistido WHERE 1=1";

        Statement statement = conn.createStatement();
        if (criteria != null && criteria.size() > 0) {
            String nome = (String) criteria.get(AssistidoCriteria.NOME_ILIKE);
            if (nome != null) {
                sql += " AND nome ilike '%" + nome + "%'";
            }
            Long idNe = (Long) criteria.get(AssistidoCriteria.ID_NE);
            if (idNe != null && idNe > 0) {
                sql += " and assistido.id != '" + idNe + "'";
            }
            String presentes = (String) criteria.get(AssistidoCriteria.PRESENTES);
            if(presentes != null && !presentes.isEmpty()){
                if(presentes.equals("presentes")){
                    sql += " and assistido. id in (select distinct assistido_fk from movimento_assistido where data_saida is null)";
                }else if(presentes.equals("ausentes")){
                    sql += " and assistido.id not in (select distinct assistido_fk from movimento_assistido where data_saida is null)";
                }
            }
        }

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
