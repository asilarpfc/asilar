package asilar.model.entity;

import asilar.model.entity.base.BaseEntity;
import java.util.Date;

public class Registro extends BaseEntity{
    
    private Date dataEntrada;
    private Date dataSaida;
    private Usuario usuario;
    private Assistido assistido;

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Assistido getAssistido() {
        return assistido;
    }

    public void setAssistido(Assistido assistido) {
        this.assistido = assistido;
    }
}
