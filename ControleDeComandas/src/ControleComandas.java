import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// import java.util.*;

public class ControleComandas {

    public static void testeLeitura() {
        // 1 encontrar o arquivo
        File arquivo = new File("ControleDeComandas/src/refeicoes_e_bebidas.csv");

        // 2 marcar como leitura
        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile()) {
            System.out.println("Arquivo encontrado e pode ser lido");
            try {
                FileReader marcarLeitura = new FileReader(arquivo);
                BufferedReader bufLeitura = new BufferedReader(marcarLeitura);

                String linha = bufLeitura.readLine(); // captura uma linha
                System.out.println(linha); // imprimir a linha

                linha = bufLeitura.readLine(); // captura uma linha
                System.out.println(linha); // imprimir a linha

                linha = bufLeitura.readLine(); // captura uma linha
                System.out.println(linha); // imprimir a linha

                linha = bufLeitura.readLine(); // captura uma linha
                System.out.println(linha); // imprimir a linha



            } catch (FileNotFoundException erro) {
                System.out.println(("Caminho do arquivo incorreto."));
            } catch (IOException erroLeitura) {
                System.out.println("Erro ao ler o arquivo.");
            }

        } else {
            System.out.println("Arquivo não encontrado ou não pode ser lido");
            return;
        }

        // 3 ler linhas do arquivo
    }

    public static void main(String[] args) {

        testeLeitura();
        // Scanner entrada = new Scanner(System.in);

        // Restaurante restAvenida = new Restaurante("marisa sunset");

        // while(true) {
        //     System.out.println("MENU\n"
        //                     + "1 - Cadastrar comanda\n"
        //                        + "2 - Realizar pedido\n"
        //                        + "3 - fechar comanda\n"
        //                        + "4 - encerrar dia");
        //     System.out.print("Informe a opção desejada: ");

        //     int opcao = entrada.nextInt();

        //     switch(opcao) {
        //         case 1: restAvenida.realCadastraComanda(); break;
        //         case 2: restAvenida.realizarPedido(); break;
        //         case 3: restAvenida.fecharComanda(); break;
        //         case 4: System.out.println("Encerrando o programa..."); return;
        //     }
        // }
    }
}