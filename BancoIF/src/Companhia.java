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
    public void acao(Jogador jogador, int somaDados) {
        if (proprietario == null) {
            // Jogador pode comprar a companhia
            System.out.println("Companhia disponível para compra: " + getNome());
            System.out.println("Valor de compra: R$" + valorCompra);
            // Implementar lógica de compra
            System.out.print("Deseja comprar a companhia? (s/n) ");
            String escolha = System.console().readLine();

            if (escolha.toLowerCase().equals("s")) {
                // Verifica se o jogador tem saldo suficiente para comprar a companhia
                if (jogador.getSaldo() >= valorCompra) {
                    jogador.comprarCompanhia(this);
                    System.out.println("Jogador " + jogador.getNome() + " comprou a companhia " + getNome());
                } else {
                    System.out.println("Saldo insuficiente para comprar a companhia");
                }
            } else {
                System.out.println("Jogador " + jogador.getNome() + " não comprou a propriedade " + getNome());
            }

        } else if(this.proprietario == jogador) {
            // Informar que caiu em uma propriedade própria
            System.out.println("Jogador " + jogador.getNome() + " caiu na sua própria companhia: " + getNome() + ".");

        } else if (this.proprietario != jogador) {
            // Informar que caiu em uma propriedade com dono e que deve pagar aluguel
            System.out.println("Jogador " + jogador.getNome() + " caiu em " + getNome() + " que pertence a " + proprietario.getNome());
            // Jogador deve pagar aluguel
            double valorAluguel = this.calcularAluguel(somaDados);
            // Verificar se o jogador tem saldo suficiente para pagar o aluguel
            if (jogador.getSaldo() < valorAluguel) {
                System.out.println("Jogador " + jogador.getNome() + " não tem saldo suficiente para pagar o aluguel de R$" + valorAluguel);
                jogador.setFalencia();
                return;
            }
            // Pagar aluguel
            jogador.pagar(valorAluguel);
            this.proprietario.receber(valorAluguel);
            System.out.println("Jogador " + jogador.getNome() + " pagou R$" + valorAluguel + " de aluguel para " + proprietario.getNome());

            Jogo.imprimirLinha();
            // Mostra o saldo do proprietário
            System.out.println("Novo saldo do proprietário " + proprietario.getNome() + ": R$" + proprietario.getSaldo());
        }
    }

}
