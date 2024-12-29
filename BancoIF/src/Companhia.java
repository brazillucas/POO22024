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
    private int calcularAluguel(int somaDados) {
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

    @Override
    public void acao(Jogador jogador) {
        if (proprietario == null) {
            // Jogador pode comprar a companhia
            System.out.println("Companhia disponível para compra: " + getNome());
            // Implementar lógica de compra
            System.out.println("Deseja comprar a companhia? (s/n)");
            String escolha = System.console().readLine();
            if (escolha.toLowerCase().equals("s")) {
                if (jogador.getSaldo() >= valorCompra) {
                    jogador.comprarCompanhia(this);
                    System.out.println("Jogador " + jogador.getNome() + " comprou a companhia " + getNome());
                } else {
                    System.out.println("Saldo insuficiente para comprar a companhia");
                }
            } else {
                System.out.println("Jogador " + jogador.getNome() + " não comprou a propriedade " + getNome());
            }

        } else if (this.proprietario != jogador) {
            // Jogador deve pagar aluguel
            int somaDados = jogador.rolarDados();
            double valorAluguel = this.calcularAluguel(somaDados);
            jogador.pagarAluguel(valorAluguel);
            this.proprietario.receber(valorAluguel);
            System.out.println("Jogador " + jogador.getNome() + " pagou R$" + valorAluguel + " de aluguel para " + proprietario.getNome());
        }
    }

}
