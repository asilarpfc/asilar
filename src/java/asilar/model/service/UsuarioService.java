package asilar.model.service;

import asilar.model.entity.Usuario;
import asilar.model.service.base.BaseUsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioService implements BaseUsuarioService{

    private Long sequence = 0L;
    private List<Usuario> entityList = new ArrayList<Usuario>();
    
    private static UsuarioService instance;
    public static UsuarioService getInstance(){
        if (instance == null){
            instance = new UsuarioService();
        }
        return instance;
    }
    
    @Override
    public void create(Usuario entity) throws Exception {
        sequence++;
        entity.setId(sequence);
        entityList.add(entity);
        System.out.println(sequence);
    }

    @Override
    public Usuario readyById(Long id) throws Exception {
        Usuario entity = new Usuario();
        for(Usuario aux : entityList){
            if(aux.getId().equals(id)){
                entity = aux;
                break;
            }
        }
        System.out.println("read: "+ sequence);
        return entity;
    }

    @Override
    public List<Usuario> readyByCriteria(Map<Long, Object> Criteria, Long offset) throws Exception {
        return entityList;
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        count = Long.valueOf(entityList.size());
        return count;
    }

    @Override
    public void update(Usuario entity) throws Exception {
        for(Usuario aux : entityList){
            if(aux.getId().equals(entity.getId())){
                entityList.remove(aux);
                entityList.add(entity);
                break;
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        for(Usuario aux : entityList){
            if(aux.getId().equals(id)){
                entityList.remove(aux);
                break;
            }
        }
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
