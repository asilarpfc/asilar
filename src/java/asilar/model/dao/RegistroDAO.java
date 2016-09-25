package asilar.model.dao;

import asilar.model.criteria.RegistroCriteria;
import asilar.model.dao.base.BaseDAO;
import asilar.model.entity.Assistido;
import asilar.model.entity.Registro;
import asilar.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistroDAO implements BaseDAO<Registro>{

    @Override
    public void update(Connection conn, Registro entity) throws Exception {
        String sql = "UPDATE registro SET data_entrada=?, data_saida=?, usuario_entrada_fk=?, assistido_fk=?, usuario_saida_fk=? WHERE id=?;;";
        
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setDate(++i, new java.sql.Date(entity.getDataEntrada().getTime()));
        statement.setDate(++i, new java.sql.Date(entity.getDataSaida().getTime()));
        statement.setLong(++i, entity.getUsuarioEntrada().getId());
        statement.setLong(++i, entity.getAssistido().getId());
        statement.setLong(++i, entity.getUsuarioSaida().getId());
        
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM registro WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

    @Override
    public void create(Connection conn, Registro entity) throws Exception {
        String sql = "INSERT INTO registro(data_entrada, data_saida, usuario_entrada_fk, assistido_fk, usuario_saida_fk) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        int i = 0;
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(++i, new java.sql.Date(entity.getDataEntrada().getTime()));
        statement.setDate(++i, new java.sql.Date(entity.getDataSaida().getTime()));
        statement.setLong(++i, entity.getUsuarioEntrada().getId());
        statement.setLong(++i, entity.getAssistido().getId());
        statement.setLong(++i, entity.getUsuarioSaida().getId());
        
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        
        rs.close();
        statement.close();
    }

    @Override
    public Registro readyById(Connection conn, Long id) throws Exception {
        String sql = "SELECT registro.id registro_id, registro.data_entrada registro_data_entrada, registro.data_saida registro_data_saida, \n" +
        "       entrada.id entrada_id, entrada.email entrada_email, entrada.telefone_celular entrada_telefone_celular, \n" +
        "       entrada.rg entrada_rg, entrada.nome entrada_nome, entrada.cpf entrada_cpf, entrada.telefone_residencial entrada_telefone_residencial, \n" +
        "       entrada.usuario entrada_usuario, entrada.senha entrada_senha, entrada.tipo_usuario entrada_tipo_usuario,\n" +
        "       saida.id saida_id, saida.email saida_email, saida.telefone_celular saida_telefone_celular, \n" +
        "       saida.rg saida_rg, saida.nome saida_nome, saida.cpf saida_cpf, saida.telefone_residencial saida_telefone_residencial, \n" +
        "       saida.usuario saida_usuario, saida.senha saida_senha, saida.tipo_usuario saida_tipo_usuario,\n" +
        "       assistido.id assistido_id, assistido.profissao assistido_profissao, assistido.nacionalidade assistido_nacionalidade, \n" +
        "       assistido.nome assistido_nome, assistido.rg assistido_rg, assistido.cpf assistido_cpf, \n" +
        "       assistido.telfixo assistido_telfixo, assistido.celular assistido_celular, assistido.banco assistido_banco, \n" +
        "       assistido.agencia assistido_agencia, assistido.conta assistido_conta, assistido.naturalidade assistido_naturalidade, \n" +
        "       assistido.estado_civil assistido_estado_civil, assistido.mae assistido_mae, assistido.pai assistido_pai, \n" +
        "       assistido.rua assistido_rua, assistido.bairro assistido_bairro, assistido.numero assistido_numero, \n" +
        "       assistido.cidade assistido_cidade, assistido.estado assistido_estado, assistido.sexo assistido_sexo, assistido.data_nascimento assistido_data_nascimento, \n" +
        "       assistido.observacoes assistido_observacoes, assistido.procedencia assistido_procedencia, assistido.cartao_sus assistio_cartao_sus, assistido.no_do_beneficio assistido_no_do_beneficio\n" +
        "  FROM registro\n" +
        "  LEFT JOIN usuario entrada ON registro.usuario_entrada_fk=entrada.id\n" +
        "  LEFT JOIN usuario saida ON registro.usuario_saida_fk=saida.id\n" +
        "  LEFT JOIN assistido ON registro.assistido_fk=assistido.id\n" +
        "  WHERE registro.id = ?";
        
        int i = 0;
        Registro entity = new Registro();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        
        if(rs.next()){
            entity.setId(rs.getLong("registro_id"));
            entity.setDataEntrada(rs.getDate("registro_data_entrada"));
            entity.setDataSaida(rs.getDate("registro_data_saida"));
            
            Usuario entrada = new Usuario();
            entrada.setId(rs.getLong("entrada_id"));
            entrada.setEmail(rs.getString("entrada_email"));
            entrada.setCelular(rs.getString("entrada_telefone_celular"));
            entrada.setRg(rs.getString("entrada_rg"));
            entrada.setNome(rs.getString("entrada_nome"));
            entrada.setCpf(rs.getString("entrada_cpf"));
            entrada.setTelfixo(rs.getString("entrada_telefone_residencial"));
            entrada.setUsuario(rs.getString("entrada_usuario"));
            entrada.setSenha(rs.getString("entrada_senha"));
            entrada.setTipoUsuario(rs.getInt("entrada_tipo_usuario"));
            entity.setUsuarioEntrada(entrada);
            
            Usuario saida = new Usuario();
            saida.setId(rs.getLong("saida_id"));
            saida.setEmail(rs.getString("saida_email"));
            saida.setCelular(rs.getString("saida_telefone_celular"));
            saida.setRg(rs.getString("saida_rg"));
            saida.setNome(rs.getString("saida_nome"));
            saida.setCpf(rs.getString("saida_cpf"));
            saida.setTelfixo(rs.getString("saida_telefone_residencial"));
            saida.setUsuario(rs.getString("saida_usuario"));
            saida.setSenha(rs.getString("saida_senha"));
            saida.setTipoUsuario(rs.getInt("saida_tipo_usuario"));
            entity.setUsuarioSaida(saida);
            
            Assistido assistido = new Assistido();
            assistido.setId(rs.getLong("assistido_id"));
            assistido.setProfissao(rs.getString("assistido_profissao"));
            assistido.setNacionalidade(rs.getString("assistido_nacionalidade"));
            assistido.setNome(rs.getString("assistido_nome"));
            assistido.setRg(rs.getString("assistido_rg"));
            assistido.setCpf("assistido_cpf");
            assistido.setTelfixo(rs.getString("assistido_telfixo"));
            assistido.setCelular(rs.getString("assistido_celular"));
            assistido.setBanco(rs.getString("assistido_banco"));
            assistido.setAgencia(rs.getString("assistido_agencia"));
            assistido.setConta(rs.getString("assistido_conta"));
            assistido.setNaturalidade(rs.getString("assistido_naturalidade"));
            assistido.setEstadoCivil(rs.getString("assistido_estado_civil"));
            assistido.setMae(rs.getString("assistido_mae"));
            assistido.setPai(rs.getString("assistido_pai"));
            assistido.setRua(rs.getString("assistido_rua"));
            assistido.setBairro(rs.getString("assistido_bairro"));
            assistido.setNumero(rs.getString("assistido_numero"));
            assistido.setCidade(rs.getString("assistido_cidade"));
            assistido.setEstado(rs.getString("assistido_estado"));
            assistido.setSexo(rs.getString("assistido_sexo"));
            assistido.setDataNascimento(rs.getDate("assistido_data_nascimento"));
            assistido.setObservacoes(rs.getString("assistido_observacoes"));
            assistido.setProcedencia(rs.getString("assistido_procedencia"));
            assistido.setCartaoSus(rs.getString("assistido_cartao_sus"));
            assistido.setNoDoBeneficio(rs.getString("assistido_no_do_beneficio"));
            entity.setAssistido(assistido);
        }
        rs.close();
        statement.close();
        
        return entity;
    }

    @Override
    public List<Registro> readyByCriteria(Connection conn, Map<Long, Object> criteria, Long offset) throws Exception {
        String sql = "SELECT registro.id registro_id, registro.data_entrada registro_data_entrada, registro.data_saida registro_data_saida, \n" +
        "       entrada.id entrada_id, entrada.email entrada_email, entrada.telefone_celular entrada_telefone_celular, \n" +
        "       entrada.rg entrada_rg, entrada.nome entrada_nome, entrada.cpf entrada_cpf, entrada.telefone_residencial entrada_telefone_residencial, \n" +
        "       entrada.usuario entrada_usuario, entrada.senha entrada_senha, entrada.tipo_usuario entrada_tipo_usuario,\n" +
        "       saida.id saida_id, saida.email saida_email, saida.telefone_celular saida_telefone_celular, \n" +
        "       saida.rg saida_rg, saida.nome saida_nome, saida.cpf saida_cpf, saida.telefone_residencial saida_telefone_residencial, \n" +
        "       saida.usuario saida_usuario, saida.senha saida_senha, saida.tipo_usuario saida_tipo_usuario,\n" +
        "       assistido.id assistido_id, assistido.profissao assistido_profissao, assistido.nacionalidade assistido_nacionalidade, \n" +
        "       assistido.nome assistido_nome, assistido.rg assistido_rg, assistido.cpf assistido_cpf, \n" +
        "       assistido.telfixo assistido_telfixo, assistido.celular assistido_celular, assistido.banco assistido_banco, \n" +
        "       assistido.agencia assistido_agencia, assistido.conta assistido_conta, assistido.naturalidade assistido_naturalidade, \n" +
        "       assistido.estado_civil assistido_estado_civil, assistido.mae assistido_mae, assistido.pai assistido_pai, \n" +
        "       assistido.rua assistido_rua, assistido.bairro assistido_bairro, assistido.numero assistido_numero, \n" +
        "       assistido.cidade assistido_cidade, assistido.estado assistido_estado, assistido.sexo assistido_sexo, assistido.data_nascimento assistido_data_nascimento, \n" +
        "       assistido.observacoes assistido_observacoes, assistido.procedencia assistido_procedencia, assistido.cartao_sus assistio_cartao_sus, assistido.no_do_beneficio assistido_no_do_beneficio\n" +
        "  FROM registro\n" +
        "  LEFT JOIN usuario entrada ON registro.usuario_entrada_fk=entrada.id\n" +
        "  LEFT JOIN usuario saida ON registro.usuario_saida_fk=saida.id\n" +
        "  LEFT JOIN assistido ON registro.assistido_fk=assistido.id\n" +
        "  WHERE 1=1";
        
        Statement statement = conn.createStatement();
        
        if(criteria != null && criteria.size() > 0){
            
            Long assistidoIdEq = (Long) criteria.get(RegistroCriteria.ASSISTIDO_ID_EQ);
            if (assistidoIdEq != null && assistidoIdEq > 0) {
                sql += " and assistido.id != '" + assistidoIdEq + "'";
            }
            
        }
        
        sql += " ORDER BY registro.id ASC";
        
        if (offset != null && offset >= 0) {
            sql += " limit 10 offset " + offset + "";
        }
        
        ResultSet rs = statement.executeQuery(sql);
        List <Registro> entityList = new ArrayList<Registro>();
        
        while(rs.next()){
            
            Registro entity = new Registro();
            
            entity.setId(rs.getLong("registro_id"));
            entity.setDataEntrada(rs.getDate("registro_data_entrada"));
            entity.setDataSaida(rs.getDate("registro_data_saida"));
            
            Usuario entrada = new Usuario();
            entrada.setId(rs.getLong("entrada_id"));
            entrada.setEmail(rs.getString("entrada_email"));
            entrada.setCelular(rs.getString("entrada_telefone_celular"));
            entrada.setRg(rs.getString("entrada_rg"));
            entrada.setNome(rs.getString("entrada_nome"));
            entrada.setCpf(rs.getString("entrada_cpf"));
            entrada.setTelfixo(rs.getString("entrada_telefone_residencial"));
            entrada.setUsuario(rs.getString("entrada_usuario"));
            entrada.setSenha(rs.getString("entrada_senha"));
            entrada.setTipoUsuario(rs.getInt("entrada_tipo_usuario"));
            entity.setUsuarioEntrada(entrada);
            
            Usuario saida = new Usuario();
            saida.setId(rs.getLong("saida_id"));
            saida.setEmail(rs.getString("saida_email"));
            saida.setCelular(rs.getString("saida_telefone_celular"));
            saida.setRg(rs.getString("saida_rg"));
            saida.setNome(rs.getString("saida_nome"));
            saida.setCpf(rs.getString("saida_cpf"));
            saida.setTelfixo(rs.getString("saida_telefone_residencial"));
            saida.setUsuario(rs.getString("saida_usuario"));
            saida.setSenha(rs.getString("saida_senha"));
            saida.setTipoUsuario(rs.getInt("saida_tipo_usuario"));
            entity.setUsuarioSaida(saida);
            
            Assistido assistido = new Assistido();
            assistido.setId(rs.getLong("assistido_id"));
            assistido.setProfissao(rs.getString("assistido_profissao"));
            assistido.setNacionalidade(rs.getString("assistido_nacionalidade"));
            assistido.setNome(rs.getString("assistido_nome"));
            assistido.setRg(rs.getString("assistido_rg"));
            assistido.setCpf("assistido_cpf");
            assistido.setTelfixo(rs.getString("assistido_telfixo"));
            assistido.setCelular(rs.getString("assistido_celular"));
            assistido.setBanco(rs.getString("assistido_banco"));
            assistido.setAgencia(rs.getString("assistido_agencia"));
            assistido.setConta(rs.getString("assistido_conta"));
            assistido.setNaturalidade(rs.getString("assistido_naturalidade"));
            assistido.setEstadoCivil(rs.getString("assistido_estado_civil"));
            assistido.setMae(rs.getString("assistido_mae"));
            assistido.setPai(rs.getString("assistido_pai"));
            assistido.setRua(rs.getString("assistido_rua"));
            assistido.setBairro(rs.getString("assistido_bairro"));
            assistido.setNumero(rs.getString("assistido_numero"));
            assistido.setCidade(rs.getString("assistido_cidade"));
            assistido.setEstado(rs.getString("assistido_estado"));
            assistido.setSexo(rs.getString("assistido_sexo"));
            assistido.setDataNascimento(rs.getDate("assistido_data_nascimento"));
            assistido.setObservacoes(rs.getString("assistido_observacoes"));
            assistido.setProcedencia(rs.getString("assistido_procedencia"));
            assistido.setCartaoSus(rs.getString("assistido_cartao_sus"));
            assistido.setNoDoBeneficio(rs.getString("assistido_no_do_beneficio"));
            entity.setAssistido(assistido);
            
            entityList.add(entity);
        }
        
        rs.close();
        statement.close();
        
        return entityList;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT count(*) FROM registro\n" +
                    "  LEFT JOIN usuario entrada ON registro.usuario_entrada_fk=entrada.id\n" +
                    "  LEFT JOIN usuario saida ON registro.usuario_saida_fk=saida.id\n" +
                    "  LEFT JOIN assistido ON registro.assistido_fk=assistido.id" +
                    "  WHERE 1=1";
        
        Statement statement = conn.createStatement();
        
        if(criteria != null && criteria.size() > 0){
            
            Long assistidoIdEq = (Long) criteria.get(RegistroCriteria.ASSISTIDO_ID_EQ);
            if (assistidoIdEq != null && assistidoIdEq > 0) {
                sql += " and assistido.id != '" + assistidoIdEq + "'";
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
