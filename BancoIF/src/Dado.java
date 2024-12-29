import java.util.Random;

/**
 * Classe Dado:
 * Representa um dado de seis faces usado no jogo.
 * Métodos:
 * rolar(): int (retorna um valor aleatório entre 1 e 6)
 */
public class Dado {
    private Random random;

    // Construtor
    public Dado() {
        this.random = new Random();
    }

    // Método para rolar o dado e obter um valor entre 1 e 6
    public int rolar() {
        return random.nextInt(6) + 1;
    }
}