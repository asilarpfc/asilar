package asilar.model.service;

import asilar.model.ConnectionManager;
import asilar.model.criteria.RegistroCriteria;
import asilar.model.dao.RegistroDAO;
import asilar.model.entity.Registro;
import asilar.model.service.base.BaseRegistroService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroService implements BaseRegistroService {

    @Override
    public void create(Registro entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();

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
    public Registro readyById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
        Registro entity = new Registro();

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
    public List<Registro> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();
        List<Registro> entityList = new ArrayList<Registro>();

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

        String dataEntrada = (String) fields.get("dataEntrada");
        String dataSaida = (String) fields.get("dataSaida");

        if (dataEntrada != null && !dataEntrada.isEmpty()) {
            errors.put("dataEntrada", dataEntrada);
        }
        if (dataSaida != null && !dataSaida.isEmpty()) {
            errors.put("dataSaida", dataSaida);
        }

        Date hoje = new Date();

        Registro registro = (Registro) fields.get("registro");

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
        criteria.put(RegistroCriteria.ASSISTIDO_ID_EQ, registro.getAssistido().getId());

        List<Registro> ultimos = this.readyByCriteria(criteria, null);

        if (ultimos != null && ultimos.size() > 0) {
            Registro ultimo = new Registro();
            for (Registro aux : ultimos) {
                if (aux.getDataSaida() != null) {
                    if (registro.getId() == null) {
                        ultimo = aux;
                    } else if (!registro.getId().equals(aux.getId())) {
                        ultimo = aux;
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
        RegistroDAO dao = new RegistroDAO();
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
    public void update(Registro entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RegistroDAO dao = new RegistroDAO();

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
        RegistroDAO dao = new RegistroDAO();

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
