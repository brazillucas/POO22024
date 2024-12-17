public class Produto {
    private double valorUnit;
    private int quantVend;
    private String nome;

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

    public double getValorUnit() {
        return this.valorUnit;
    }

    @Override
    public String toString() {
        return this.nome + "\t | " + this.valorUnit;
    }
}
