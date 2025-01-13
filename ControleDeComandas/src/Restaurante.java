import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante {

    private String nome;
    private double caixa = 0.0;
    private int mesasAtendidas;

    private Comanda[] mesas = new Comanda[10];

    private ArrayList<Produto> menu = new ArrayList<>();

    public  Restaurante(String nome) {
        this.nome = nome;
        this.caixa = 0.0;
        this.mesasAtendidas = 0;
        carregaProdutosMenu();
    }

    public String getNome(){
        return this.nome;
    }

    private void setSaldoCaixa(double valor) {
        this.caixa += valor;
    }

    public double getSaldoCaixa() {
        return this.caixa;
    }

    public void incrementaMesasAtendidas() {
        this.mesasAtendidas++;
    }

    public int getMesasAtendidas() {
        return this.mesasAtendidas;
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
        String nomeCliente = ControleComandas.solicitarEntradaValida("Informe o nome do cliente (apenas caracteres simples, sem acentos ou pontuações | '-1' para cancelar): ",
        "^([a-zA-Z\\s]{3,}|-1)$",
        "Nome inválido!");
        if (nomeCliente.equals("-1")) {
            System.out.println("Nenhum cliente cadastrado, retornando ao menu...");
            return;
        } else {
            for (int i = 0; i < mesas.length; i++) {
                if (mesas[i] != null && mesas[i].getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                    System.out.println("Cliente já possui comanda aberta!");
                    System.out.printf("Gentileza, atendê-lo na mesa %d\n", (i+1));
                    System.out.println("Retornando ao menu...\n");
                    return;
                }
            }
        }

        String telCliente = ControleComandas.solicitarEntradaValida(
            "Informe o número de telefone do cliente (8 a 9 dígitos | '-1' para cancelar): ",
            "(^[0-9]{8,9}|-1)$",
            "Telefone inválido!"
        );

        if (telCliente.equals("-1")) {
            System.out.println("Nenhum cliente cadastrado, retornando ao menu...");
            return;
        }

        Cliente novoCliente = new Cliente(nomeCliente, telCliente);

        int numMesa = Integer.parseInt(ControleComandas.solicitarEntradaValida(
            "Informe o número da mesa do cliente (1-10): ",
            "^[1-9]\\d*$",
            "Mesa inválida!"
        ));

        numMesa--;

        if(mesas[numMesa] != null) {
            System.out.println("Mesa já ocupada!");
            boolean mudouMesa = false;
            for(int i= 0; i < mesas.length; i++) {
                if (mesas[i] == null) {
                    numMesa = i;
                    System.out.println("Alocando cliente em mesa disponível...");
                    System.out.println("Cliente alocado para a mesa " + (numMesa + 1));
                    mudouMesa = true;
                    break;
                }
            }
            if (!mudouMesa) {
                System.out.println("Nenhuma mesa disponível!");
                System.out.println("Retornando ao menu...\n");
                return;
            }
        }

        Comanda novaComanda = new Comanda(novoCliente, numMesa);

        mesas[numMesa] = novaComanda;
    }

    public void realizarPedido() {

        // Exibir mesas ativas
        ControleComandas.imprimirSeparador();
        this.exibirMesasAtivas();
        ControleComandas.imprimirSeparador();

        int numMesa = Integer.parseInt(ControleComandas.solicitarEntradaValida(
            "Informe o número da mesa (1 a 10 | -1 para cancelar): ",
            "^([1-9]\\d*|-1)$",
            "Mesa inválida!"));

        if (numMesa == -1) {
            System.out.println("Nenhuma mesa selecionada, retornando ao menu...");
            return;
        }

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
                    "Informe o produto do pedido (1 - " + this.menu.size() + "): ",
                    "^[1-9]\\d*$",
                    "Produto inválido!"
                ));
            } while(numProduto > this.menu.size());
            numProduto--;

            this.mesas[numMesa].anotaPedido(this.menu.get(numProduto));

            return;
        }
    }

    private void exibirMesasAtivas() {
        System.out.println("Atendimentos em andamento:");
        for(int i = 0; i < mesas.length; i++) {
            if (mesas[i] != null) {
                System.out.printf("Mesa %d - %s\n", (i+1), mesas[i].getCliente().getNome());
            }
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
        this.exibirMesasAtivas();
        ControleComandas.imprimirSeparador();

        int numMesa = Integer.parseInt(ControleComandas.solicitarEntradaValida(
            "Informe o número da mesa ('-1' para cancelar): ",
            "^([1-9]\\d*|-1)$",
            "Mesa inválida!"
        ));

        if (numMesa == -1) {
            System.out.println("Nenhuma mesa selecionada, retornando ao menu...");
            return;
        }

        numMesa--;
        ControleComandas.imprimirSeparador();
        if (mesas[numMesa] == null) {
            System.out.println("Mesa não está ocupada!");
        } else {
            mesas[numMesa].imprimirComanda();
            this.setSaldoCaixa(mesas[numMesa].getValorTotal());
            this.incrementaMesasAtendidas();
            mesas[numMesa].encerrarComanda();
            System.out.println("Comanda encerrada!");
            mesas[numMesa] = null;
        }
        ControleComandas.imprimirSeparador();
    }

    public void fecharTodasComandas() {
        for (Comanda mesa : mesas) {
            if (mesa != null) {
                this.setSaldoCaixa(mesa.getValorTotal());
                this.incrementaMesasAtendidas();
                mesa.encerrarComanda();
                mesa = null;
            }
        }
    }
}