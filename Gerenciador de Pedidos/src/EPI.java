public class EPI extends Item {
    private int setorDestino;
    private String tamanho;
    private String ca; // Certificado de Aprovação

    // Construtor
    public EPI(int codigo, String nome, int setorDestino, String tamanho, String ca) {
        super(codigo, nome, TipoItem.EPI);
        this.setorDestino = setorDestino;
        this.tamanho = tamanho;
        this.ca = ca;
    }

    public EPI(int codigo, String nome, String tamanho, String ca) {
        super(codigo, nome, TipoItem.EPI);
        this.tamanho = tamanho;
        this.ca = ca;
    }

    // Getters e Setters
    public int getSetorDestino() {
        return setorDestino;
    }

    public void setSetorDestino(int setorDestino) {
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
        System.out.println("EPI - Código: " + codigo + ", Nome: " + nome +
                ", Setor Destino: " + setorDestino + ", Tamanho: " + tamanho + ", CA: " + ca);
    }
}