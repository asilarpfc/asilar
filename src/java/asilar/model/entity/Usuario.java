
package asilar.model.entity;

import asilar.model.entity.base.BaseEntity;


public class Usuario extends BaseEntity{
    
    private String nome;
    private String login;
    private String senha;
    private String rg;
    private String cpf;
    private String telfixo;
    private String celular;
    private String email;
<<<<<<< HEAD
    private Integer tipoUsuario;
=======
    private int tipoUsuario;
>>>>>>> 4a5a206eee62d91d093c0e0b994b0baf5169b238

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelfixo() {
        return telfixo;
    }

    public void setTelfixo(String telfixo) {
        this.telfixo = telfixo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< HEAD
    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    
    
    
=======
    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

>>>>>>> 4a5a206eee62d91d093c0e0b994b0baf5169b238
    
}
