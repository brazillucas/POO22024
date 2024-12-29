/**
 * Classe Companhia (Herda de Posicao):
 * Atributos:
 * valorCompra: double (valor de compra da companhia)
 * fatorAluguel: int (fator multiplicador do aluguel)
 * proprietario: Jogador (jogador proprietário da companhia)
 * Métodos:
 * calcularAluguel(int somaDados): double (calcula o aluguel com base no valor do dado)
 * getProprietario(): Jogador (retorna o proprietário da companhia)
 * setProprietario(Jogador jogador): void (define o proprietário da companhia)
 * getValorCompra(): double (retorna o valor de compra da companhia)
 * 
 */

public class Companhia extends Posicao{
    // Atributos
    private double valorCompra;
    private int fatorAluguel;
    private Jogador proprietario;

    // Construtor

    public Companhia(String nome, double preco, int fator) {
        super(nome, "Companhia");
        this.valorCompra = preco;
        this.fatorAluguel = fator;
        this.proprietario = null;
    }

    // Métodos
    public int calcularAluguel(int somaDados) {
        if (proprietario != null) {
            return somaDados * fatorAluguel;
        } else {
            return 0;
        }
    }

    public Jogador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jogador jogador) {
        this.proprietario = jogador;
    }

    public double getValorCompra() {
        return valorCompra;
    }

}
