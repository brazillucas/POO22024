public class EPI extends Item {
    private String setorDestino;
    private String tamanho;
    private String ca; // Certificado de Aprovação

    // Construtor
    public EPI(String codigo, String descricao, TipoItem tipo, String setorDestino, String tamanho, String ca) {
        super(codigo, descricao, tipo);
        this.setorDestino = setorDestino;
        this.tamanho = tamanho;
        this.ca = ca;
    }

    // Getters e Setters
    public String getSetorDestino() {
        return setorDestino;
    }

    public void setSetorDestino(String setorDestino) {
        this.setorDestino = setorDestino;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    // Implementação do método abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("EPI - Código: " + codigo + ", Descrição: " + descricao +
                ", Setor Destino: " + setorDestino + ", Tamanho: " + tamanho + ", CA: " + ca);
    }
}