/*
 * Item
 * Atributos:
 * codigo
 * descricao
 * tipo (Uniforme, EPI, Almoxarifado)
 * Subclasses:
 * Uniforme
 * setorDestino
 * tamanho
 * EPI
 * setorDestino
 * tamanho
 * ca (Certificado de Aprovação)
 * Almoxarifado
 * (nenhum atributo adicional específico)
 */

public abstract class Item {
    protected String codigo;
    protected String descricao;
    protected TipoItem tipo; // Uniforme, EPI, Almoxarifado

    // Construtor
    public Item(String codigo, String descricao, TipoItem tipo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    // Método abstrato para forçar implementação nas subclasses
    public abstract void exibirDetalhes();
}