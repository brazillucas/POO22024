// Comentarios dos outros arquivos
// 1 - Jogo.java

 * Início do Jogo:
 * O sistema deve solicitar o número de jogadores (entre 2 e 6).
 * O sistema deve solicitar o nome de cada jogador.
 * Todos os jogadores devem iniciar com um saldo de R$1.500.
 * 
 * Rodadas do Jogo:
 * Os jogadores devem lançar dois dados, e a soma determinará a quantidade de casas a serem avançadas.
 * A ordem dos turnos deve seguir a ordem de cadastro dos jogadores.
 * O sistema deve permitir que mais de um jogador ocupe a mesma posição no tabuleiro.
 * 
 * Ações nas Posições:
 * Compra de Propriedade/Companhia: O jogador deve poder comprar uma propriedade/companhia se ela estiver disponível.
 * Pagamento de Aluguel: O jogador deve pagar aluguel se parar em uma propriedade/companhia de outro jogador.
 * Sorte/Revés: O jogador deve ganhar ou perder dinheiro com base em um evento aleatório.
 * Imposto de Renda: O jogador deve pagar uma penalidade de R$200.
 * Melhorias em Propriedades: O jogador deve poder construir pousadas ou hotéis em suas propriedades.
 * Lucros ou Dividendos: O jogador recebe uma bonificação de R$200.
 * Volta Completa: O jogador deve receber R$200 ao completar uma volta no tabuleiro.
 * Prisão: O jogador deve ser movido para a prisão e ficar uma rodada sem jogar.
 * 
 * Fim do Jogo:
 * O jogo deve terminar quando restar apenas um jogador com saldo positivo.
 * Jogadores que ficarem sem saldo devem ser eliminados do jogo, e suas propriedades e companhias voltam a ficar disponíveis.
 * 
 * Interface:
 * O jogo deve ser implementado para interação via terminal.
 * O sistema deve registrar e exibir o estado atual de cada jogador, incluindo saldo, propriedades adquiridas e posição no tabuleiro.
 * O sistema deve exibir as ações realizadas por cada jogador, como movimentação, compra de propriedades/companhias, pagamento de aluguel, etc.

 -----------------------------

 2. Classe posicao

 * Classe Posicao:
 * Atributos:
 * nome: String (nome da posição)
 * tipo: String (tipo da posição: "Propriedade", "Companhia", "Sorte/Reves", etc.)
 * Métodos:
 * getNome(): String (retorna o nome da posição)
 * getTipo(): String (retorna o tipo da posição)
 * toString(): String (retorna uma representação da posição)

--------------------------------