import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        System.out.printf("Data: %02d/%02d/%d\n",
                    this.data.getDayOfMonth(),
                    this.data.getMonthValue(),
                    this.data.getYear());
        System.out.println("Cliente: " + this.respCompanda.getNome());
        System.out.println("Produtos consumidos: ");
        for(Produto produto : produtos) {
            System.out.printf("%-25s - R$ %2.2f\n", produto.getNome(), produto.getValorUnit());
        }
        System.out.printf("Total: R$ %2.2f\n", this.valorTotal);
    }

    public void encerrarComanda() {

        LocalDateTime horaFechamento = LocalDateTime.now();

        File arquivo = new File(String.format("ControleDeComandas/src/mesa%02d_%02d_%02d_%d_%02d_%02d.txt", 
            this.numMesa, 
            horaFechamento.getDayOfMonth(),
            horaFechamento.getMonthValue(),
            horaFechamento.getYear(),
            horaFechamento.getHour(), 
            horaFechamento.getMinute()));

        try {
            FileWriter marcaEscrita = new FileWriter(arquivo);

            BufferedWriter bufEscrita = new BufferedWriter(marcaEscrita);

            // Escreve o cabeçalho com nome do cliente
            bufEscrita.write("Cliente: " + this.respCompanda.getNome() + "\n");
            bufEscrita.write(String.format("Data: %02d/%02d/%d\n",
                                this.data.getDayOfMonth(),
                                this.data.getMonthValue(),
                                this.data.getYear()));
            bufEscrita.write("Produtos consumidos: \n");
            
            for (Produto produto : produtos) {
                bufEscrita.write(String.format("%-25s | R$ %2.2f\n",
                                    produto.getNome(),
                                    produto.getValorUnit()));
            }

            bufEscrita.write(String.format("Total: R$ %2.2f", this.valorTotal));

            bufEscrita.flush();
            bufEscrita.close();
            
        } catch (Exception e) {
            System.err.println("Arquivo corrompido.");
        }
    }
}
