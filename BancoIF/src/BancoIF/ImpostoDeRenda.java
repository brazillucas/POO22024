package BancoIF;

/**
 * Representa a posição de Imposto de Renda no tabuleiro do jogo.
 * 
 * <p>
 * Esta classe herda de {@link Posicao} e adiciona funcionalidades específicas
 * para o Imposto de Renda, como valor do imposto a ser pago.
 * </p>
 */
public class ImpostoDeRenda extends Posicao {
    // Atributos
    /**
     * O valor do imposto a ser pago.
     */
    private final double valorImposto;

    // Construtor
    /**
     * Construtor da classe ImpostoDeRenda.
     * <p>
     * Cria uma nova posição de imposto de renda com um nome.
     * </p>
     * 
     * @param nome O nome da posição de imposto de renda.
     */
    public ImpostoDeRenda(String nome) {
        super(nome, "Imposto");
        this.valorImposto = 200;
    }

    // Métodos
    /**
     * Executa a ação da posição de imposto de renda.
     * <p>
     * O jogador que cai na posição de imposto de renda paga o valor do imposto.
     * </p>
     * 
     * @param jogador O jogador que caiu na posição de imposto de renda.
     */
    @Override
    public void acao(Jogador jogador) {
        jogador.pagar(this.valorImposto);
        System.out.printf("Jogador %S pagou R$ %.2f de imposto de renda.\n", jogador.getNome(), valorImposto);
    }
}