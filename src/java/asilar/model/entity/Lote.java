package asilar.model.entity;

import asilar.model.entity.base.BaseEntity;
import java.util.Date;

public class Lote extends BaseEntity{
    
    private Date validade;
    private Long quantidade;
    private Produto produto;
    
    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
