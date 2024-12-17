import java.time.LocalDate;
import  java.util.ArrayList;

public class Comanda {

    private LocalDate data;
    private int numMesa;
    private double valorTotal;
    private Cliente respCompanda;

    private ArrayList<Produto> produtos = new ArrayList<>();

    public Comanda(Cliente respCompanda, int numMesa) {
        this.respCompanda = respCompanda;
        this.numMesa = numMesa;
        this.data = LocalDate.now();
        this.valorTotal = 0.0;
    }

    public void anotaPedido(Produto novoPedido) {
        this.produtos.add(novoPedido);
        this.valorTotal += novoPedido.getValorUnit();
    }

    public void imprimirComanda() {
        System.out.println("Comanda da mesa " + this.numMesa);
        System.out.println("Data: " + this.data);
        System.out.println("Cliente: " + this.respCompanda.getNome());
        System.out.println("Produtos: ");
        for(Produto produto : produtos) {
            System.out.println(produto);
        }
        System.out.println("Total: " + this.valorTotal);
    }

}
