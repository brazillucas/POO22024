/**
 * Classe Posicao:
 * Atributos:
 * nome: String (nome da posição)
 * tipo: String (tipo da posição: "Propriedade", "Companhia", "Sorte/Reves", etc.)
 * Métodos:
 * getNome(): String (retorna o nome da posição)
 * getTipo(): String (retorna o tipo da posição)
 * toString(): String (retorna uma representação da posição)
 */

public class Posicao {
    private String nome;
    private String tipo;

    public Posicao(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ")";
    }

    public void acao(Jogador jogador, int somaDados) {
        // Implementar ação da posição
        System.out.println("Jogador " + jogador.getNome() + " está na posição " + getNome());
    }
}
