package asilar.model.entity;

import asilar.model.entity.base.BaseEntity;

public class Produto extends BaseEntity {

    private String nome;
    private Long quantidadeMaxima;
    private Long quantidadeMinima;
    private String unidadeMedida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Long quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public Long getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Long quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    

}
