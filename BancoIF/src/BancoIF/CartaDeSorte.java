package BancoIF;
/**
 * Representa uma carta Sorte/Reves no tabuleiro do jogo.
 * 
 * <p>
 * Esta classe herda de {@link Posicao} e adiciona funcionalidades específicas
 * para cartas Sorte/Reves, como descrição e valores de ganho e perda.
 * </p>
 */

public class CartaDeSorte extends Posicao {
    // Atributos
    /** 
     * A descrição da carta de sorte.
     */
    private String descricaoSorte;
    /** 
     * A descrição da carta de revés.
     */
    private String descricaoReves;
    /** 
     * O valor mínimo que o jogador pode ganhar.
     */
    private int ganhoMinimo;
    /** 
     * O valor máximo que o jogador pode ganhar.
     */
    private int ganhoMaximo;
    /** 
     * O valor mínimo que o jogador pode perder.
     */
    private int perdaMinima;
    /** 
     * O valor máximo que o jogador pode perder.
     */
    private int perdaMaxima;

    // Construtor
    /**
     * Construtor da classe CartaDeSorte.
     * <p>
     * Cria uma nova carta de sorte com um nome.
     * </p>
     * 
     * @param nome O nome da carta de sorte.
     */
    public CartaDeSorte(String nome) {
        super(nome, "Carta");
        this.descricaoSorte = "Essa carta gera um valor aleatório, determinando um ganho de dinheiro para o jogador.";
        this.descricaoReves = "Essa carta gera um valor aleatório, determinando uma perda de dinheiro para o jogador.";
        this.ganhoMinimo = 1;
        this.ganhoMaximo = 150;
        this.perdaMinima = 1;
        this.perdaMaxima = 80;
    }

    // Métodos

    /**
     * Retorna o valor que o jogador vai ganhar.
     * 
     * @return O valor que o jogador vai ganhar.
     */
    private double sortearGanho() {
        // Sorteia um valor aleatório entre ganhoMinimo e ganhoMaximo.
        int valor = (int) (Math.random() * (this.ganhoMaximo - this.ganhoMinimo + 1) + this.ganhoMinimo);
        return valor;
    }

    /**
     * Retorna o valor que o jogador vai perder.
     * 
     * @return O valor que o jogador vai perder.
     */
    private double sortearPerda() {
        // Sorteia um valor aleatório entre perdaMinima e perdaMaxima.
        int valor = (int) (Math.random() * (this.perdaMaxima - this.perdaMinima + 1) + this.perdaMinima);
        return valor;
    }

    /**
     * Executa uma ação específica baseada na carta de sorte.
     * <p>
     * Este método sorteia um valor aleatório e determina se o jogador vai ganhar ou perder dinheiro.
     * </p>
     * 
     * @param jogador   O jogador que está na posição.
     */
    @Override
    public void acao(Jogador jogador) {
        // Sorteia um valor aleatório entre 0 e 1.
        double sorteio = Math.random();
        double valor = 0;
        // Se o valor sorteado for menor que 0.5, o jogador ganha, senão, perde.
        if (sorteio < 0.5) {
            valor = this.sortearGanho();
            System.out.println("Sorte: " + this.descricaoSorte);
            System.out.printf("Você ganhou: R$ %.2f\n", valor);
            jogador.receber(valor);
        } else {
            valor = this.sortearPerda();
            System.out.println("Revés: " + this.descricaoReves);
            System.out.printf("Você perdeu: R$ %.2f\n", valor);
            jogador.pagar(valor);
        }
    }
}