public class Uniforme extends Item {
    private int setorDestino;
    private String tamanho;

    // Construtor
    public Uniforme(int codigo, String nome, int setorDestino, String tamanho) {
        super(codigo, nome, TipoItem.UNIFORME);
        this.setorDestino = setorDestino;
        this.tamanho = tamanho;
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

    // Implementação do método abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("Uniforme - Código: " + codigo + ", Nome: " + nome +
                ", Setor Destino: " + setorDestino + ", Tamanho: " + tamanho);
    }
}