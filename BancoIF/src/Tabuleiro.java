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
        posicoes[1] = new Propriedade("Bangu", 100, 10, 50, 100);
        posicoes[2] = new CartaDeSorte("Sorte/Reves");
        posicoes[3] = new Propriedade("Botafogo", 200, 20, 100, 200);
        posicoes[4] = new ImpostoDeRenda("Imposto de Renda");
        posicoes[5] = new Companhia("Companhia de Navegação", 150, 4);
        posicoes[6] = new Propriedade("Avenida Brasil", 300, 30, 150, 300);
        posicoes[7] = new CartaDeSorte("Sorte/Reves");
        posicoes[8] = new Propriedade("Avenida Paulista", 400, 40, 200, 400);
        posicoes[9] = new Propriedade("Jardim Europa", 500, 50, 250, 500);
        posicoes[10] = new Cadeia("Vá para a Prisão");
        posicoes[11] = new Propriedade("Copacabana", 600, 60, 300, 600);
        posicoes[12] = new Companhia("Companhia de Aviação", 150, 4);
        posicoes[13] = new Propriedade("Avenida Vieira Souto", 700, 70, 350, 700);
        posicoes[14] = new Propriedade("Avenida Atlântica", 800, 80, 400, 800);
        posicoes[15] = new Companhia("Companhia Táxi Aéreo", 150, 4);
        posicoes[16] = new Propriedade("Ipanema", 900, 90, 450, 900);
        posicoes[17] = new CartaDeSorte("Sorte/Reves");
        posicoes[18] = new Propriedade("Jardim Paulista", 1000, 100, 500, 1000);
        posicoes[19] = new Propriedade("Brooklin", 1100, 110, 550, 1100);
        posicoes[20] = new Cadeia("Cadeia");
        posicoes[21] = new Propriedade("Avenida 9 de Julho", 1200, 120, 600, 1200);
        posicoes[22] = new Propriedade("Avenida Rebouças", 1300, 130, 650, 1300);
        posicoes[23] = new Companhia("Companhia de Viação", 150, 4);
        posicoes[24] = new Propriedade("Avenida Brigadeiro Faria Lima", 1400, 140, 700, 1400);
        posicoes[25] = new Companhia("Companhia Ferroviária", 150, 4);
        posicoes[26] = new Propriedade("Avenida Nossa Senhora de Copacabana", 1500, 150, 750, 1500);
        posicoes[27] = new Propriedade("Avenida Presidente Vargas", 1600, 160, 800, 1600);
        posicoes[28] = new CartaDeSorte("Sorte/Reves");
        posicoes[29] = new Propriedade("Leblon", 1700, 170, 850, 1700);
        posicoes[30] = new Cadeia("Vá para a Prisão");
        posicoes[31] = new Propriedade("Morumbi", 1800, 180, 900, 1800);
        posicoes[32] = new LucrosOuDividendos("Lucros ou Dividendos");
        posicoes[33] = new Propriedade("Interlagos", 1900, 190, 950, 1900);
        posicoes[34] = new CartaDeSorte("Sorte/Reves");
        posicoes[35] = new Companhia("Companhia de Táxi", 150, 4);
        posicoes[36] = new Propriedade("Avenida Pacaembu", 2000, 200, 1000, 2000);
        posicoes[37] = new Propriedade("Rua Augusta", 2100, 210, 1050, 2100);
        posicoes[38] = new CartaDeSorte("Sorte/Reves");
        posicoes[39] = new Propriedade("Avenida Europa", 2200, 220, 1100, 2200);
    }

    public Posicao getPosicao(int index) {
        if (index >= 0 && index < posicoes.length) {
            return posicoes[index];
        } else {
            throw new IndexOutOfBoundsException("Posição inválida no tabuleiro.");
        }
    }

    public void executarAcao(Jogador jogador, int indice) {
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
        posicao.acao(jogador);
    }

    public void exibirTabuleiro() {
        for (int i = 0; i < posicoes.length; i++) {
            System.out.println("Posição " + i + ": " + posicoes[i]);
        }
    }

}