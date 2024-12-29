public class Cadeia extends Posicao {
    
    public Cadeia(String nome) {
        super(nome, "Cadeia");
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println("Jogador " + jogador.getNome() + " está na cadeia.");
        System.out.println("Jogador " + jogador.getNome() + " perdeu a vez.");
    }
}
