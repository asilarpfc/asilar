package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.criteria.MovimentoAssistidoCriteria;
import asilar.model.dao.MovimentoAssistidoDAO;
import asilar.model.entity.MovimentoAssistido;
import asilar.model.service.base.BaseMovimentoAssistidoService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovimentoAssistidoService implements BaseMovimentoAssistidoService {

    @Override
    public void create(MovimentoAssistido entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        MovimentoAssistidoDAO dao = new MovimentoAssistidoDAO();

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
    public MovimentoAssistido readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        MovimentoAssistidoDAO dao = new MovimentoAssistidoDAO();
        MovimentoAssistido entity = new MovimentoAssistido();

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
    public List<MovimentoAssistido> readByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        MovimentoAssistidoDAO dao = new MovimentoAssistidoDAO();
        List<MovimentoAssistido> entityList = new ArrayList<MovimentoAssistido>();

        try {
            entityList = dao.readByCriteria(conn, Criteria, offset);
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

        String dataEntrada = (String) fields.get("dataEntrada");
        String dataSaida = (String) fields.get("dataSaida");

        if (dataEntrada != null && !dataEntrada.isEmpty()) {
            errors.put("dataEntrada", dataEntrada);
        }
        if (dataSaida != null && !dataSaida.isEmpty()) {
            errors.put("dataSaida", dataSaida);
        }

        Date hoje = new Date();

        MovimentoAssistido registro = (MovimentoAssistido) fields.get("registro");

        if (registro.getDataEntrada() == null) {
            errors.put("dataEntrada", "Digite uma data de entrada valida!");
        } else if (registro.getDataEntrada() != null) {
            if (registro.getDataEntrada().after(hoje)) {
                errors.put("dataEntrada", "Digite uma data anterior ao dia de hoje");
            }
        }

        if (registro.getDataSaida() != null) {
            if (registro.getDataSaida().after(hoje)) {
                errors.put("dataSaida", "Digite uma data anterior ao dia de hoje");
            }
            if (registro.getDataEntrada().after(registro.getDataSaida())) {
                errors.put("dataSaida", "Digite uma data maior que a data de entrada");
            }
        }

        Map<Long, Object> criteria = new HashMap<Long, Object>();
        criteria.put(MovimentoAssistidoCriteria.ASSISTIDO_ID_EQ, registro.getAssistido().getId());

        List<MovimentoAssistido> ultimos = this.readByCriteria(criteria, null);

        if (ultimos != null && ultimos.size() > 0) {
            MovimentoAssistido ultimo = new MovimentoAssistido();
            for (MovimentoAssistido aux : ultimos) {
                if (aux.getDataSaida() != null) {
                    if (registro.getId() == null) {
                        ultimo = aux;
                    } else if (!registro.getId().equals(aux.getId())) {
                        ultimo = aux;
                    }
                    if(registro.getId() !=null){
                        if(registro.getId().equals(aux.getId())){
                            if(registro.getDataSaida() == null && aux.getDataSaida() != null){
                                errors.put("dataSaida", "Este campo deve conter um valor");
                            }
                        }
                    }
                }
            }

            if (ultimo.getDataSaida() != null && registro.getDataEntrada() != null) {
                if (ultimo.getDataSaida().after(registro.getDataEntrada())) {
                    errors.put("dataEntrada", "Coloque uma data posterior a ultima saida");
                }
            }

        }
        return errors;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        MovimentoAssistidoDAO dao = new MovimentoAssistidoDAO();
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
    public void update(MovimentoAssistido entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        MovimentoAssistidoDAO dao = new MovimentoAssistidoDAO();

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
        MovimentoAssistidoDAO dao = new MovimentoAssistidoDAO();

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
