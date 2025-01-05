package BancoIF;
public class ImpostoDeRenda extends Posicao {
    private double valorImposto;

    public ImpostoDeRenda(String nome) {
        super(nome, "Imposto");
        this.valorImposto = 200;
    }

    @Override
    public void acao(Jogador jogador, int somaDados) {
        jogador.pagar(this.valorImposto);
        System.out.println("Jogador " + jogador.getNome() + " pagou R$" + valorImposto + " de imposto de renda.");
    }
}