package asilar.model.entity;

import asilar.model.entity.base.BaseEntity;
import java.util.Date;

public class Registro extends BaseEntity{
    
    private Date dataEntrada;
    private Date dataSaida;
    private Usuario usuarioEntrada;
    private Assistido assistido;
    private Usuario usuarioSaida;

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

    public Usuario getUsuarioEntrada() {
        return usuarioEntrada;
    }

    public void setUsuarioEntrada(Usuario usuarioEntrada) {
        this.usuarioEntrada = usuarioEntrada;
    }

    public Assistido getAssistido() {
        return assistido;
    }

    public void setAssistido(Assistido assistido) {
        this.assistido = assistido;
    }

    public Usuario getUsuarioSaida() {
        return usuarioSaida;
    }

    public void setUsuarioSaida(Usuario usuarioSaida) {
        this.usuarioSaida = usuarioSaida;
    }
}
