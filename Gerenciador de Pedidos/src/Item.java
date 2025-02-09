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
    protected int codigo;
    protected String nome;
    protected TipoItem tipo; // Uniforme, EPI, Almoxarifado

    // Construtor
    public Item(int codigo, String nome, TipoItem tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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