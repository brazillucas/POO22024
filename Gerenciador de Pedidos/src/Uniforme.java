public class Uniforme extends Item {
    private String setorDestino;
    private String tamanho;

    // Construtor
    public Uniforme(String codigo, String descricao, TipoItem tipo, String setorDestino, String tamanho) {
        super(codigo, descricao, tipo);
        this.setorDestino = setorDestino;
        this.tamanho = tamanho;
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

    // Implementação do método abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("Uniforme - Código: " + codigo + ", Descrição: " + descricao +
                ", Setor Destino: " + setorDestino + ", Tamanho: " + tamanho);
    }
}