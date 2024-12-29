public class Jogo {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();

        // Exemplo: imprimir todas as posições do tabuleiro
        tabuleiro.imprimirTabuleiro();

        // Exemplo: acessar uma posição específica
        System.out.println("\nDetalhes da posição 3:");
        System.out.println(tabuleiro.getPosicao(3));
    }
}
