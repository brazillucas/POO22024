import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {

    private String nome;

    private Comanda[] mesas = new Comanda[10];

    private ArrayList<Produto> menu = new ArrayList<>();

    public  Restaurante(String nome) {
        this.nome = nome;
        carregaProdutos();
    }

    private void carregaProdutos() {
        this.menu.add(new Porcao(350.0, 1, 12.0, "Tropeiro"));
        this.menu.add(new Porcao(500.0, 3, 15.0, "Fritas"));
        this.menu.add(new Porcao(500.0, 2, 36.0, "Filé"));
        this.menu.add(new Bebida(380.0, true, 10.0, "Bebida"));
        this.menu.add(new Bebida(500.0, false, 12.0, "Suco de Laranja"));

    }

    public void realCadastraComanda() {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Informe o nome do cliente: ");
        String nomeCli = entrada.nextLine();
        System.out.print("Informe o número de telefone do cliente: ");
        String telCli = entrada.nextLine();

        Cliente novoCli = new Cliente(nomeCli, telCli);

        System.out.print("Informe o número da mesa do cliente: ");
        int numMesa = entrada.nextInt();

        Comanda novaComanda = new Comanda(novoCli, numMesa);

        mesas[numMesa] = novaComanda;
    }

    public boolean realizarPedido() {

        Scanner entrada = new Scanner(System.in);

        System.out.print("Informe o número da mesa: ");
        int numMesa = entrada.nextInt();

        if (numMesa < 0 || numMesa > 9 || mesas[numMesa] == null) {
            return false;
        } else {
            // Imprime o menu
            System.out.println("MENU");
            for(int i = 0; i < this.menu.size(); i++) {
                System.out.println((i+1) + " - " + this.menu.get(i));
                // System.out.println("Entrei no for em " + i);
            }

            System.out.print("Informe o produto do pedido: ");
            int numProduto = entrada.nextInt();
            numProduto--;

            this.mesas[numMesa].anotaPedido(this.menu.get(numProduto));

            return true;
        }
    }

    public void fecharComanda() {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Informe o número da mesa: ");

        int numMesa = entrada.nextInt();

        if (numMesa < 0 || numMesa > 9 || mesas[numMesa] == null) {
            System.out.println("Mesa não encontrada");
        } else {
            mesas[numMesa].imprimirComanda();
            System.out.println("Comanda encerrada!");
            mesas[numMesa] = null;
        }
    }
}