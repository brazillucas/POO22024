import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante {

    private String nome;

    private Comanda[] mesas = new Comanda[10];

    private ArrayList<Produto> menu = new ArrayList<>();

    public  Restaurante(String nome) {
        this.nome = nome;
        carregaProdutosMenu();
    }

    public String getNome(){
        return this.nome;
    }

    private void carregaProdutosMenu() {
        // 1 - encontra o arquivo com os produtos
        File arquivo = new File("ControleDeComandas/src/refeicoes_e_bebidas.csv");

        // 2 - marca como leitura
        if(arquivo.exists() && arquivo.canRead() && arquivo.isFile()) {
            try {
                // marca o arquivo ref, como leitura
                FileReader marcarLeitura = new FileReader(arquivo);

                try (BufferedReader bufLeitura = new BufferedReader(marcarLeitura)) {
                    // Primeira linha - cabeçalho
                    String linha = bufLeitura.readLine();

                    while(linha != null) {
                        linha = bufLeitura.readLine();

                        // Verifica se foram esgotadas todas as linhas do arquivo
                        if(linha != null) {
                            String pedacosLinhas[] = linha.split(";");

                            // Transformar Produto
                            Produto novoProduto = new Produto(
                                Double.parseDouble(pedacosLinhas[1]),
                                pedacosLinhas[0]);

                            // Produto do arquivo disponivel para vender
                            this.menu.add(novoProduto);
                        }
                    }
                }
            }  catch (FileNotFoundException erro) {
                System.out.println("Caminho do arquivo incorreto");
            } catch (IOException erroLeitura) {
                System.out.println("Erro na leitura dos dados.");
            }
        }
    }

    // procesando arquivo ... LEITURA

    public void cadastraComanda() {
        String nomeCliente = ControleComandas.solicitarEntradaValida("Informe o nome do cliente (apenas caracteres simples, sem acentos ou pontuações): ",
        "^[a-zA-Z\\s]{3,}$",
        "Nome inválido!");
        String telCliente = ControleComandas.solicitarEntradaValida(
            "Informe o número de telefone do cliente: ",
            "^[0-9]{8,9}$",
            "Telefone inválido!"
        );

        Cliente novoCliente = new Cliente(nomeCliente, telCliente);

        int numMesa = Integer.parseInt(ControleComandas.solicitarEntradaValida(
            "Informe o número da mesa do cliente (1-10): ",
            "^(10|[1-9])$",
            "Mesa inválida!"
        ));

        Comanda novaComanda = new Comanda(novoCliente, numMesa--);

        mesas[numMesa] = novaComanda;
    }

    public void realizarPedido() {

        int numMesa = Integer.parseInt(ControleComandas.solicitarEntradaValida(
            "Informe o número da mesa (1 a 10): ",
            "^(10|[1-9])$",
            "Mesa inválida!"));

        numMesa--;

        if (mesas[numMesa] == null) {
            System.out.println("Mesa vazia!");
            System.out.println("Retornando ao menu...\n");
            return;
        } else {
            // Imprime o menu
            ControleComandas.limparTela();
            imprimirMenu();
            ControleComandas.imprimirSeparador();

            int numProduto = -1;
            do{
                numProduto = Integer.parseInt(ControleComandas.solicitarEntradaValida(
                    "Informe o produto do pedido: (1 - " + this.menu.size() + ") ",
                    "^[1-9]+$",
                    "Produto inválido!"
                ));
            } while(numProduto > this.menu.size());
            numProduto--;

            this.mesas[numMesa].anotaPedido(this.menu.get(numProduto));

            return;
        }
    }

    private void imprimirMenu() {
        System.out.println("MENU");
        for(int i = 0; i < this.menu.size(); i++) {
            System.out.printf("%02d - %25s | R$ %.2f\n",
                                    (i+1),
                                    this.menu.get(i).getNome(),
                                    this.menu.get(i).getValorUnit());
        }
    }

    public void fecharComanda() {

        int numMesa = Integer.parseInt(ControleComandas.solicitarEntradaValida(
            "Informe o número da mesa: ",
            "^(10|[1-9])$",
            "Mesa inválida!"
        ));

        numMesa--;
        ControleComandas.imprimirSeparador();
        if (mesas[numMesa] == null) {
            System.out.println("Mesa não está ocupada!");
        } else {
            mesas[numMesa].imprimirComanda();
            mesas[numMesa].encerrarComanda();
            System.out.println("Comanda encerrada!");
            mesas[numMesa] = null;
        }
        ControleComandas.imprimirSeparador();
    }
}