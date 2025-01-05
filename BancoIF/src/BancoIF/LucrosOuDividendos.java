package BancoIF;

/**
 * Representa uma posição de Lucros ou dividendos no tabuleiro do jogo.
 * 
 * <p>
 * Esta classe herda de {@link Posicao} e adiciona funcionalidades específicas
 * para Lucros ou dividendos, como valor do lucro a ser recebido.
 * </p>
 * 
 */
public class LucrosOuDividendos extends Posicao {
    // Atributos
    /**
     * O valor do lucro a ser recebido.
     */
    private double valorLucro;

    // Construtor
    /**
     * Construtor da classe LucrosOuDividendos.
     * <p>
     * Cria uma nova posição de lucros ou dividendos com um nome.
     * </p>
     * 
     * @param nome O nome da posição de lucros ou dividendos.
     */
    public LucrosOuDividendos(String nome) {
        super(nome, "Lucros ou Dividendos");
        this.valorLucro = 150;
    }

    // Métodos
    /**
     * Executa a ação da posição de lucros ou dividendos.
     * <p>
     * O jogador que cai na posição de lucros ou dividendos recebe o valor do lucro.
     * </p>
     * 
     * @param jogador O jogador que caiu na posição de lucros ou dividendos.
     */
    @Override
    public void acao(Jogador jogador) {
        jogador.receber(this.valorLucro);
        System.out.println("Jogador " + jogador.getNome() + " recebeu R$" + valorLucro + " de lucros ou dividendos.");
    }
}