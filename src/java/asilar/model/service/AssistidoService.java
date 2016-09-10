package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.ServiceLocator;
import asilar.model.criteria.AssistidoCriteria;
import asilar.model.dao.AssistidoDAO;
import asilar.model.entity.Assistido;
import asilar.model.service.base.BaseAssistidoService;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssistidoService implements BaseAssistidoService{

    @Override
    public void create(Assistido entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        try {
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
    public Assistido readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        Assistido entity = null;
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
    public List<Assistido> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        List<Assistido> entityList = null;
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
        
        Assistido assistido = (Assistido) fields.get("assistido");
        if(assistido.getNome() == null || assistido.getNome().trim().equals("")){
            errors.put("nome", "Campo obrigatório");
        }else if(assistido.getProfissao() == null || assistido.getProfissao().trim().equals("")){
            errors.put("profissao", "Campo obrigatório");
        }else if(assistido.getNacionalidade() == null || assistido.getNacionalidade().trim().equals("")){
            errors.put("nacionalidade", "Campo obrigatório");
        }else if(assistido.getRg() == null || assistido.getRg().trim().equals("")){
            errors.put("nacionalidade", "Campo obrigatório");
        }else if(assistido.getCpf() == null || assistido.getCpf().trim().equals("")){
            errors.put("cpf", "Campo obrigatório");
        }else if(assistido.getBanco() == null || assistido.getBanco().trim().equals("")){
            errors.put("banco", "Campo obrigatório");
        }else if(assistido.getAgencia() == null || assistido.getAgencia().trim().equals("")){
            errors.put("agencia", "Campo obrigatório");
        }else if(assistido.getConta() == null || assistido.getConta().trim().equals("")){
            errors.put("conta", "Campo obrigatório");
        }else if(assistido.getNaturalidade() == null || assistido.getNaturalidade().trim().equals("")){
            errors.put("naturalidade", "Campo obrigatório");
        }else if(assistido.getEstadoCivil() == null || assistido.getEstadoCivil().trim().equals("")){
            errors.put("estadocivil", "Campo obrigatorio");
        }else if(assistido.getMae() == null || assistido.getMae().trim().equals("")){
            errors.put("mae", "Campo obrigatório");
        }else if(assistido.getPai() == null || assistido.getPai().trim().equals("")){
            errors.put("pai", "Campo obrigatório");
        }else if(assistido.getRua() == null || assistido.getRua().trim().equals("")){
            errors.put("rua", "Campo obrigatório");
        }else if(assistido.getBairro() == null || assistido.getBairro().trim().equals("")){
            errors.put("bairro", "Campo obrigatório");
        }else if(assistido.getNumero() == null || assistido.getNumero().trim().equals("")){
            errors.put("numero", "Campo obrigatório");
        }else if(assistido.getCidade() == null || assistido.getCidade().trim().equals("")){
            errors.put("cidade", "Campo obrigatório");
        }else if(assistido.getSexo() == null || assistido.getSexo().trim().equals("")){
            errors.put("sexo", "Campo obrigatório");
        }else if(assistido.getDataNascimento() == null || assistido.getDataNascimento().trim().equals("")){
            errors.put("nascimento", "Campo obrigatório");
        }else if(assistido.getObservacoes() == null || assistido.getObservacoes().trim().equals("")){
            errors.put("observacoes", "Campo obrigatório");
        }else if(assistido.getProcedencia() == null || assistido.getProcedencia().trim().equals("")){
            errors.put("cartaoSus", "Campo obrigatório");
        }else if(assistido.getNoDoBeneficio() == null || assistido.getNoDoBeneficio().trim().equals("")){
            errors.put("noDoBeneficio", "Campo obrigatório");
        }else{
            Map<Long, Object> criteria = new HashMap<>();
            Long id = (Long) fields.get("id");
            String cpf = (String) fields.get("cpf");
            String usuario = (String) fields.get("usuario");
            if (id != null && id > 0) {
                criteria.put(AssistidoCriteria.ID_NE, id);
            }
            if (cpf != null && !cpf.isEmpty()) {
                criteria.put(AssistidoCriteria.CPF_EQ, cpf);
            }
            

            List<Assistido> pacienteList
                    = ServiceLocator.getAssistidoService().readyByCriteria(criteria, null);

            if (!pacienteList.isEmpty()) {
                errors.put("cpf", "Este CPF ja se encontra em uso!");
            }
        }
        return errors;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
        Long count = 0L;
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
    public void update(Assistido entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AssistidoDAO dao = new AssistidoDAO();
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
        AssistidoDAO dao = new AssistidoDAO();
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
    
}
