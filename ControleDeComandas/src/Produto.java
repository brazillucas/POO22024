public class Produto {
    private final double valorUnit;
    private int quantVend;
    private final String nome;

    public Produto(double valorUnit, int quantVend, String nome) {
        this.valorUnit = valorUnit;
        this.quantVend = quantVend;
        this.nome = nome;
    }

    // Utilizado para cadastrar o menu
    public Produto(double valorUnit, String nome) {
        this.valorUnit = valorUnit;
        this.nome = nome;
        this.quantVend = 0;
    }

    public String getNome() {
        return this.nome;
    }

    public double getValorUnit() {
        return this.valorUnit;
    }

    public int getQuantVend() {
        return this.quantVend;
    }

    public void setQuantVend(int quantVend) {
        this.quantVend += quantVend;
    }

    @Override
    public String toString() {
        return this.nome + "\t | " + this.valorUnit;
    }
}
