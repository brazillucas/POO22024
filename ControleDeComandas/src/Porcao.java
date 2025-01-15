public class Porcao extends Produto {
    @SuppressWarnings("unused")
    private final double peso;
    @SuppressWarnings("unused")
    private final int qPessoas;

    public Porcao(double peso, int quantVend, String nome, double valorUnit, int qPessoas) {
        super(valorUnit, quantVend, nome);
        this.peso = peso;
        this.qPessoas = qPessoas;
    }

    public Porcao(double peso, int qPessoas, double valorUnit, String nome) {
        super(valorUnit, nome);
        this.peso = peso;
        this.qPessoas = qPessoas;
    }
    
}
