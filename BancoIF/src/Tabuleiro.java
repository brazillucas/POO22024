/*
 * Tabuleiro: O tabuleiro é composto por posições que representam propriedades (pontos turísticos brasileiros), companhias de serviço e funcionalidades específicas (Sorte/Revés, imposto de renda, bonificações).
 * 
 * Métodos:
inicializarTabuleiro(): void (cria e inicializa o tabuleiro com todas as posições)
getPosicao(int indice): Posicao (retorna a posição do tabuleiro com base no índice)
getTipoPosicao(int indice): String (retorna o tipo da posição no tabuleiro com base no índice)
executarAcao(Jogador jogador, int indice): void (executa a ação correspondente à posição no tabuleiro)
exibirTabuleiro(): void (exibe o estado atual do tabuleiro)
 
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

public class Tabuleiro {
    // Atributos e métodos para gerenciar o tabuleiro
    private Posicao[] posicoes;

    public Tabuleiro() {
        posicoes = new Posicao[40]; // Número total de posições no tabuleiro.
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        // Preenchendo as posições do tabuleiro.
        posicoes[0] = new Posicao("Partida", "Inicial");
        posicoes[1] = new Posicao("Bangu", "Propriedade");
        posicoes[2] = new Posicao("Sorte/Reves", "Sorte/Reves");
        posicoes[3] = new Posicao("Botafogo", "Propriedade");
        posicoes[4] = new Posicao("Imposto de Renda", "Imposto");
        posicoes[5] = new Posicao("Companhia de Navegação", "Companhia");
        posicoes[6] = new Posicao("Avenida Brasil", "Propriedade");
        posicoes[7] = new Posicao("Sorte/Reves", "Sorte/Reves");
        posicoes[8] = new Posicao("Avenida Paulista", "Propriedade");
        posicoes[9] = new Posicao("Jardim Europa", "Propriedade");
        posicoes[10] = new Posicao("Vá para a Prisão", "Especial");
        posicoes[11] = new Posicao("Copacabana", "Propriedade");
        posicoes[12] = new Posicao("Companhia de Aviação", "Companhia");
        posicoes[13] = new Posicao("Avenida Vieira Souto", "Propriedade");
        posicoes[14] = new Posicao("Avenida Atlântica", "Propriedade");
        posicoes[15] = new Posicao("Companhia Táxi Aéreo", "Companhia");
        posicoes[16] = new Posicao("Ipanema", "Propriedade");
        posicoes[17] = new Posicao("Sorte/Reves", "Sorte/Reves");
        posicoes[18] = new Posicao("Jardim Paulista", "Propriedade");
        posicoes[19] = new Posicao("Brooklin", "Propriedade");
        posicoes[20] = new Posicao("Cadeia", "Especial");
        posicoes[21] = new Posicao("Avenida 9 de Julho", "Propriedade");
        posicoes[22] = new Posicao("Avenida Rebouças", "Propriedade");
        posicoes[23] = new Posicao("Companhia de Viação", "Companhia");
        posicoes[24] = new Posicao("Avenida Brigadeiro Faria Lima", "Propriedade");
        posicoes[25] = new Posicao("Companhia Ferroviária", "Companhia");
        posicoes[26] = new Posicao("Avenida Nossa Senhora de Copacabana", "Propriedade");
        posicoes[27] = new Posicao("Avenida Presidente Vargas", "Propriedade");
        posicoes[28] = new Posicao("Sorte/Reves", "Sorte/Reves");
        posicoes[29] = new Posicao("Leblon", "Propriedade");
        posicoes[30] = new Posicao("Vá para a Prisão", "Especial");
        posicoes[31] = new Posicao("Morumbi", "Propriedade");
        posicoes[32] = new Posicao("Lucros ou Dividendos", "Especial");
        posicoes[33] = new Posicao("Interlagos", "Propriedade");
        posicoes[34] = new Posicao("Sorte/Reves", "Sorte/Reves");
        posicoes[35] = new Posicao("Companhia de Táxi", "Companhia");
        posicoes[36] = new Posicao("Avenida Pacaembu", "Propriedade");
        posicoes[37] = new Posicao("Rua Augusta", "Propriedade");
        posicoes[38] = new Posicao("Sorte/Reves", "Sorte/Reves");
        posicoes[39] = new Posicao("Avenida Europa", "Propriedade");
    }

    public Posicao getPosicao(int index) {
        if (index >= 0 && index < posicoes.length) {
            return posicoes[index];
        } else {
            throw new IndexOutOfBoundsException("Posição inválida no tabuleiro.");
        }
    }

    public void imprimirTabuleiro() {
        for (int i = 0; i < posicoes.length; i++) {
            System.out.printf("Posição %d: %s%n", i, posicoes[i]);
        }
    }

    public String getTipoPosicao(int indice) {
        return posicoes[indice].getTipo();
    }

    public void executarAcao(Jogador jogador, int indice) {
        // Executa a ação correspondente à posição no tabuleiro
    }

    public void exibirTabuleiro() {
        // Exibe o estado atual do tabuleiro
    }

}