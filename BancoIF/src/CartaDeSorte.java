public class CartaDeSorte extends Posicao {
    private String descricao;
    private boolean sorte;
    private int valorMinimoGanho;
    private int valorMaximoGanho;
    private int valorMinimoPerda;
    private int valorMaximoPerda;

    public CartaDeSorte(String nome) {
        super(nome, "Carta");
        this.descricao = "Essa carta gera um evento aleatório, determinando um ganho ou perda de dinheiro para o jogador";
        this.sorte = false;
        this.valorMinimoGanho = 1;
        this.valorMaximoGanho = 150;
        this.valorMinimoPerda = 1;
        this.valorMaximoPerda = 80;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isSorte() {
        return sorte;
    }

    private double sortearGanho() {
        int valor = (int) (Math.random() * (valorMaximoGanho - valorMinimoGanho + 1) + valorMinimoGanho);
        return valor;
    }

    private double sortearPerda() {
        int valor = (int) (Math.random() * (valorMaximoPerda - valorMinimoPerda + 1) + valorMinimoPerda);
        return valor * -1;
    }

    @Override
    public void acao(Jogador jogador) {
        double sorteio = Math.random();
        double valor = 0;
        if (sorteio < 0.5) {
            valor = this.sortearGanho();
            sorte = true;
        } else {
            valor = this.sortearPerda();
        }

        if (sorte) {
            System.out.println("Sorte: " + descricao);
            jogador.receber(valor);
        } else {
            System.out.println("Revés: " + descricao);
            jogador.receber(valor);
        }
    }
}