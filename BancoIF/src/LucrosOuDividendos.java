public class LucrosOuDividendos extends Posicao {
    private double valorLucro;

    public LucrosOuDividendos(String nome) {
        super(nome, "Lucros ou Dividendos");
        this.valorLucro = 150;
    }

    @Override
    public void acao(Jogador jogador) {
        jogador.receber(valorLucro);
        System.out.println("Jogador " + jogador.getNome() + " recebeu R$" + valorLucro + " de lucros ou dividendos.");
    }
}