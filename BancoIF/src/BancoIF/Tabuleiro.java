package BancoIF;
/*
 * Tabuleiro: O tabuleiro é composto por posições que representam propriedades (pontos turísticos brasileiros), companhias de serviço e funcionalidades específicas (Sorte/Revés, imposto de renda, bonificações).
 * 
 * Métodos:
 * inicializarTabuleiro(): void (cria e inicializa o tabuleiro com todas as posições)
 * getPosicao(int indice): Posicao (retorna a posição do tabuleiro com base no índice)
 * getTipoPosicao(int indice): String (retorna o tipo da posição no tabuleiro com base no índice)
 * executarAcao(Jogador jogador, int indice): void (executa a ação correspondente à posição no tabuleiro)
 * exibirTabuleiro(): void (exibe o estado atual do tabuleiro)
 
* O tabuleiro em uma tabela markdown:
| Parada Livre      | Bangu           | Sorte/Reves      | Botafogo            | Imposto de Renda       | Companhia de navegação | Avenida Brasil                        | Sorte/Reves          | Avenida Paulista  | Jardim Europa | Vá para a Prisão         |
|-------------------|-----------------|------------------|---------------------|------------------------|------------------------|---------------------------------------|----------------------|-------------------|---------------|--------------------------|
| Morumbi           |                 |                  |                     |                        |                        |                                       |                      |                   |               | Copacabana               |
| Lucros ou Dividendos |              |                  |                     |                        |                        |                                       |                      |                   |               | Companhia de Aviação     |
| Interlagos        |                 |                  |                     |                        |                        |                                       |                      |                   |               | Avenida Vieira Souto     |
| Sorte/Reves       |                 |                  |                     |                        |                        |                                       |                      |                   |               | Avenida Atlântica        |
| Companhia de Táxi |                 |                  |                     |                        |                        |                                       |                      |                   |               | Companhia Táxi Aéreo     |
| Sorte/Reves       |                 |                  |                     |                        |                        |                                       |                      |                   |               | Ipanema                  |
| Avenida Pacaembu  |                 |                  |                     |                        |                        |                                       |                      |                   |               | Sorte/Reves              |
| Rua Augusta       |                 |                  |                     |                        |                        |                                       |                      |                   |               | Jardim Paulista          |
| Avenida Europa    |                 |                  |                     |                        |                        |                                       |                      |                   |               | Brooklin                 |
| Cadeia            | Avenida 9 Julho | Avenida Rebouças | Companhia de Viação | Avenida Brigadeiro Faria Lima | Companhia Ferroviária  | Avenida Nossa Senhora de Copacabana | Avenida Presidente Vargas | Sorte/Reves   | Leblon        | Partida - Receba (+200)   |
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tabuleiro {
    // Atributos e métodos para gerenciar o tabuleiro
    private Posicao[] posicoes;

    public Tabuleiro() {
        posicoes = new Posicao[40]; // Número total de posições no tabuleiro.
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/android/devFolder/POO22024/BancoIF/src/tabuleiro.csv"))) {
            String linha;
            int index = 0;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipo = dados[0];
                switch (tipo) {
                    case "Propriedade":
                        posicoes[index] = new Propriedade(dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3]), Integer.parseInt(dados[4]), Integer.parseInt(dados[5]));
                        break;
                    case "CartaDeSorte":
                        posicoes[index] = new CartaDeSorte(dados[1]);
                        break;
                    case "ImpostoDeRenda":
                        posicoes[index] = new ImpostoDeRenda(dados[1]);
                        break;
                    case "Companhia":
                        posicoes[index] = new Companhia(dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3]));
                        break;
                    case "Cadeia":
                        posicoes[index] = new Cadeia(dados[1]);
                        break;
                    case "LucrosOuDividendos":
                        posicoes[index] = new LucrosOuDividendos(dados[1]);
                        break;
                    default:
                        posicoes[index] = new Posicao(dados[1], dados[2]);
                        break;
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Posicao getPosicao(int index) {
        if (index >= 0 && index < posicoes.length) {
            return posicoes[index];
        } else {
            throw new IndexOutOfBoundsException("Posição inválida no tabuleiro.");
        }
    }

    public void executarAcao(Jogador jogador, int indice, int somaDados) {
        Posicao posicao = getPosicao(indice);
        // Se o jogador cair na prisão, ele deve ser movido para a posição da cadeia.
        if (posicao.getNome().equals("Vá para a Prisão") || posicao.getNome().equals("Cadeia")) {
            for (int i = 0; i < posicoes.length; i++) {
                if (posicoes[i].getNome().equals("Cadeia")) {
                    jogador.setPosicao(i);
                    break;
                }
            }
        }
        posicao.acao(jogador, somaDados);
    }

    public void exibirTabuleiro() {
        for (int i = 0; i < posicoes.length; i++) {
            System.out.println("Posição " + i + ": " + posicoes[i]);
        }
    }

}