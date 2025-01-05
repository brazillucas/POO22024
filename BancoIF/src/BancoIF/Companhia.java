package BancoIF;

/**
 * Representa uma companhia no tabuleiro do jogo.
 * 
 *  <p>
 * Esta classe herda de {@link Posicao} e adiciona funcionalidades específicas
 * para companhias, como valor de compra e fator multiplicador do aluguel.
 * Além disso, define um proprietário para a companhia.
 * </p>
 */

public class Companhia extends Posicao{
    // Atributos
    /**
     * O valor de compra da companhia.
     */
    private double valorCompra;
    /**
     * O fator multiplicador do aluguel.
     * <p>
     * O aluguel é calculado multiplicando o fator pelo valor do lançamento dos
     * dados.
     * </p>
     * <p>
     * Exemplo: fator = 4, lançamento dos dados = 7, aluguel = 4 * 7 = 28.
     * </p>
     */
    private int fatorAluguel;
    /**
     * O proprietário da companhia.
     */
    private Jogador proprietario;

    // Construtor
    /**
     * Construtor da classe Companhia.
     * <p>
     * Cria uma nova companhia com um nome, valor de compra e fator de aluguel.
     * O proprietário da companhia é inicializado como <code>null</code>.
     * </p>
     * 
     * @param nome   O nome da companhia.
     * @param preco  O valor de compra da companhia.
     * @param fator  O fator multiplicador do aluguel.
     */
    public Companhia(String nome, double preco, int fator) {
        super(nome, "Companhia");
        this.valorCompra = preco;
        this.fatorAluguel = fator;
        this.proprietario = null;
    }

    // Métodos
    /**
     * Calcula o valor do aluguel a ser pago por um jogador que caiu na companhia.
     * 
     * <p>
     * O valor do aluguel é calculado multiplicando o fator de aluguel pelo valor do
     * lançamento dos dados.
     * </p>
     * 
     * 
     * @param somaDados A soma dos valores dos dados.
     * 
     * @return O valor do aluguel a ser pago.
     */
    private int calcularAluguel(int somaDados) {
        return somaDados * this.fatorAluguel;
    }

    /**
     * Define o proprietário da companhia.
     * 
     * @param jogador O novo proprietário da companhia.
     */
    public void setProprietario(Jogador jogador) {
        this.proprietario = jogador;
    }

    /**
     * Retorna o valor de compra da companhia.
     * 
     * @return O valor de compra da companhia.
     */
    public double getValorCompra() {
        return this.valorCompra;
    }

    /**
     * Solicita o pagamento de aluguel ao jogador.
     * 
     * <p>
     * O método calcula o valor do aluguel e solicita o pagamento ao jogador.
     * Caso o jogador não tenha saldo suficiente para pagar o aluguel, ele é declarado
     * falido.
     * </p>
     * 
     * @param jogador O jogador que caiu na propriedade.
     * @param somaDados O resultado da soma dos dados lançados usado para calcular o aluguel.
     */
    public void solicitarAluguel(Jogador jogador, int somaDados) {
        // Informar que caiu em uma propriedade com dono e que deve pagar aluguel
        System.out.println("Jogador " + jogador.getNome() + " caiu em " + getNome() + " que pertence a " + this.proprietario.getNome());
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
        System.out.println("Jogador " + jogador.getNome() + " pagou R$" + valorAluguel + " de aluguel para " + this.proprietario.getNome());

        Jogo.imprimirLinha();
        // Mostra o saldo do proprietário
        System.out.println("Novo saldo do proprietário " + this.proprietario.getNome() + ": R$" + this.proprietario.getSaldo());
    }

    /**
     * Executa a ação da companhia.
     * 
     * <p>
     * Se a companhia não tiver um proprietário, o jogador pode comprá-la.
     * Caso contrário, o jogador deve pagar o aluguel ao proprietário.
     * </p>
     * 
     * @param jogador   O jogador que caiu na companhia.
     * @param somaDados O resultado da soma dos dados lançados e é usado para calular o aluguel.
     */
    @Override
    public void acao(Jogador jogador, int somaDados) {
        // Verificar se a companhia está disponível para compra
        if (this.proprietario == null) {
            // Jogador pode comprar a companhia
            System.out.println("Companhia disponível para compra: " + getNome());
            System.out.printf("Valor da companhia: R$ %.2f\n", this.valorCompra);

            // Verifica se o jogador quer comprar a companhia
            String escolha = Jogo.solicitarEntradaValida("Deseja comprar a companhia? (S/N) ", "[sS|nN]", "Opção inválida");

            if (escolha.toLowerCase().equalsIgnoreCase("S")) {
                // Verifica se o jogador tem saldo suficiente para comprar a companhia
                if (jogador.getSaldo() >= valorCompra) {
                    jogador.comprarCompanhia(this);
                    System.out.println("Jogador " + jogador.getNome() + " comprou a companhia " + this.getNome());
                } else {
                    System.out.println("Saldo insuficiente para comprar a companhia");
                }
            } else {
                System.out.println("Jogador " + jogador.getNome() + " não comprou a propriedade " + getNome());
            }
        } else if(this.proprietario == jogador) {
            // Informa ao jogador que caiu em uma propriedade que ele é o dono e que já possui um hotel
            System.out.println("Jogador " + jogador.getNome() + " caiu na companhia: " + getNome() + ".");
            System.out.println("Você já é o proprietário desta companhia.");
            System.out.println("Nenhuma ação adicional é necessária.");

        } else if (this.proprietario != jogador) {
            // Solicita o pagamento de aluguel ao jogador
            solicitarAluguel(jogador, somaDados);
        }
    }
}