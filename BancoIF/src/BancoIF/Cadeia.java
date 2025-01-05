package BancoIF;

/**
 * Representa uma posição no tabuleiro do jogo.
 * <p>
 * Uma posição pode ser de diferentes tipos, como "Propriedade", "Companhia",
 * "Sorte/Reves", entre outros. Cada posição possui um nome e um tipo.
 * </p>
 */
public class Cadeia extends Posicao {
    // Construtor
    /**
     * Construtor da classe Cadeia.
     * <p>
     * Cria uma nova posição de cadeia com um nome.
     * </p>
     * 
     * @param nome O nome da posição de cadeia.
     */
    public Cadeia(String nome) {
        super(nome, "Cadeia");
    }

    // Métodos
    /**
     * Executa a ação da posição de cadeia.
     * <p>
     * O jogador que cai na posição de cadeia perde a vez.
     * </p>
     * 
     * @param jogador O jogador que caiu na posição de cadeia.
     */
    @Override
    public void acao(Jogador jogador) {
        System.out.println("Jogador " + jogador.getNome() + " está na cadeia.");
        System.out.println("Jogador " + jogador.getNome() + " perdeu a vez.");
    }
}
