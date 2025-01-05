package BancoIF;
/**
 * Representa uma posição no tabuleiro do jogo.
 * <p>
 * Uma posição pode ser de diferentes tipos, como "Propriedade", "Companhia",
 * "Sorte/Reves", entre outros. Cada posição possui um nome e um tipo.
 * </p>
 */

public class Posicao {
    /**
     * O nome da posição.
     * <p>
     * Exemplo: <i>"Avenida Paulista"</i>.
     * </p>
     */
    private String nome;

    /**
     * O tipo da posição.
     * <p>
     * Exemplos: <i>"Propriedade"</i>, <i>"Companhia"</i>, <i>"Sorte/Reves"</i>.
     * </p>
     */
    private String tipo;

    /**
     * Construtor da classe Posicao.
     * <p>
     * Inicializa a posição com um nome e um tipo.
     * </p>
     *
     * @param nome O nome da posição.
     * @param tipo O tipo da posição.
     */
    public Posicao(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    /**
     * Retorna o nome da posição.
     * 
     * @return O nome da posição.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o tipo da posição.
     * 
     * @return O tipo da posição.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna a representação da posição em forma de texto.
     * 
     * @return Uma string com o nome e o tipo da posição.
     */
    @Override
    public String toString() {
        return nome + " (" + tipo + ")";
    }

     /**
     * Executa uma ação específica baseada na posição.
     * <p>
     * Este método deve ser implementado de acordo com as regras do jogo.
     * </p>
     *
     * @param jogador   O jogador que está na posição.
     * @param somaDados O resultado da soma dos dados, que pode influenciar a ação.
     */
    public void acao(Jogador jogador, int somaDados) {
        // Implementar ação da posição
        System.out.println("Jogador " + jogador.getNome() + " está na posição " + getNome());
    }
}
