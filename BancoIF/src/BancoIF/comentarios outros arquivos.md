**Comentarios dos outros arquivos**
## 1 - Jogo.java

### Início do Jogo:
 * O sistema deve solicitar o número de jogadores (entre 2 e 6).
 * O sistema deve solicitar o nome de cada jogador.
 * Todos os jogadores devem iniciar com um saldo de R$1.500.
 * 
### Rodadas do Jogo:
 * Os jogadores devem lançar dois dados, e a soma determinará a quantidade de casas a serem avançadas.
 * A ordem dos turnos deve seguir a ordem de cadastro dos jogadores.
 * O sistema deve permitir que mais de um jogador ocupe a mesma posição no tabuleiro.
 * 
### Ações nas Posições:
 * **Compra de Propriedade/Companhia**: O jogador deve poder comprar uma propriedade/companhia se ela estiver disponível.
 * **Pagamento de Aluguel**: O jogador deve pagar aluguel se parar em uma propriedade/companhia de outro jogador.
 * **Sorte/Revés**: O jogador deve ganhar ou perder dinheiro com base em um evento aleatório.
 * **Imposto de Renda**: O jogador deve pagar uma penalidade de R$200.
 * **Melhorias em Propriedades**: O jogador deve poder construir pousadas ou hotéis em suas propriedades.
 * **Lucros ou Dividendos**: O jogador recebe uma bonificação de R$200.
 * Volta Completa: O jogador deve receber R$200 ao completar uma volta no tabuleiro.
 * Prisão: O jogador deve ser movido para a prisão e ficar uma rodada sem jogar.
 * 
### Fim do Jogo:
 * O jogo deve terminar quando restar apenas um jogador com saldo positivo.
 * Jogadores que ficarem sem saldo devem ser eliminados do jogo, e suas propriedades e companhias voltam a ficar disponíveis.
 * 
### Interface:
 * O jogo deve ser implementado para interação via terminal.
 * O sistema deve registrar e exibir o estado atual de cada jogador, incluindo saldo, propriedades adquiridas e posição no tabuleiro.
 * O sistema deve exibir as ações realizadas por cada jogador, como movimentação, compra de propriedades/companhias, pagamento de aluguel, etc.

 ---

 ## 2. Classe posicao

 * Classe Posicao:
### Atributos:
 * nome: String (nome da posição)
 * tipo: String (tipo da posição: "Propriedade", "Companhia", "Sorte/Reves", etc.)
### Métodos:
 * **getNome()**: String (retorna o nome da posição)
 * **getTipo()**: String (retorna o tipo da posição)
 * **toString()**: String (retorna uma representação da posição)

---

## 3. Classe Propriedade

 * Classe Propriedade (Herda de Posicao):
### Atributos:
 * valorCompra: double (valor de compra da propriedade)
 * aluguel: double (valor base do aluguel)
 * aluguelPousada: double (valor do aluguel com pousada)
 * aluguelHotel: double (valor do aluguel com hotel)
 * proprietario: Jogador (jogador proprietário da propriedade)
 * nivelMelhoria: int (nível de melhoria: 0 = sem melhoria, 1 = pousada, 2 = hotel)
### Métodos:
 * **calcularAluguel()**: double (calcula o valor do aluguel com base nas melhorias)
 * **getProprietario()**: Jogador (retorna o proprietário da propriedade)
 * **setProprietario(Jogador jogador)**: void (define o proprietário da propriedade)
 * **getValorCompra()**: double (retorna o valor de compra da propriedade)
 * **getNivelMelhoria()**: int (retorna o nível de melhoria da propriedade)
 * **setNivelMelhoria(int nivel)**: void (define o nível de melhoria da propriedade)

---

## 4. Classe Companhia

 * Classe Companhia (Herda de Posicao):
### Atributos:
 * valorCompra: double (valor de compra da companhia)
 * fatorAluguel: int (fator multiplicador do aluguel)
 * proprietario: Jogador (jogador proprietário da companhia)
### Métodos:
 * **calcularAluguel(int somaDados)**: double (calcula o aluguel com base no valor do dado)
 * **getProprietario()**: Jogador (retorna o proprietário da companhia)
 * **setProprietario(Jogador jogador)**: void (define o proprietário da companhia)
 * **getValorCompra()**: double (retorna o valor de compra da companhia)

 ---

## 5. Classe Jogador


 * Classe Jogador:
### Atributos:
 * nome: String (nome do jogador)
 * saldo: double (saldo do jogador)
 * posicao: int (posição atual no tabuleiro)
 * propriedades: List<Propriedade> (lista de propriedades que o jogador possui)
 * companhias: List<Companhia> (lista de companhias que o jogador possui)
### Métodos:
 * **mover(int casas)**: void (move o jogador um número de casas no tabuleiro)
 * **comprarCompanhia(Companhia companhia)**: void (compra uma companhia)
 * **comprarPropriedade(Propriedade propriedade)**: void (compra uma propriedade)
 * **pagarAluguel(double valor)**: void (paga um aluguel para outro jogador)
 * **receber(double valor)**: void (recebe um valor)
 * **construirPousada(Propriedade propriedade)**: void (constrói uma pousada em uma propriedade)
 * **construirHotel(Propriedade propriedade)**: void (constrói um hotel em uma propriedade)
 * **falido()**: boolean (verifica se o jogador está falido)
 * **getPosicao()**: int (retorna a posição atual do jogador)
 * **getEstado()**: String (retorna o estado atual do jogador: saldo, propriedades e posição)
 * **toString()**: String (retorna uma representação do jogador e suas propriedas/companhias)

 ---

## 5. Classe Tabuleiro


Tabuleiro: O tabuleiro é composto por posições que representam propriedades (pontos turísticos brasileiros), companhias de serviço e funcionalidades específicas (Sorte/Revés, imposto de renda, bonificações).

### Métodos:
 * **inicializarTabuleiro()**: void (cria e inicializa o tabuleiro com todas as posições)
 * **getPosicao(int indice)**: Posicao (retorna a posição do tabuleiro com base no índice)
 * **getTipoPosicao(int indice)**: String (retorna o tipo da posição no tabuleiro com base no índice)
 * **executarAcao(Jogador jogador, int indice)**: void (executa a ação correspondente à posição no tabuleiro)
 * **exibirTabuleiro()**: void (exibe o estado atual do tabuleiro)
 
### O tabuleiro em uma tabela markdown:

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
 
 --- Comentarios dos outros arquivos





 -----------------
## 1 - Jogo.java

### Início do Jogo:
 * O sistema deve solicitar o número de jogadores (entre 2 e 6).
 * O sistema deve solicitar o nome de cada jogador.
 * Todos os jogadores devem iniciar com um saldo de R$1.500.
 * 
### Rodadas do Jogo:
 * Os jogadores devem lançar dois dados, e a soma determinará a quantidade de casas a serem avançadas.
 * A ordem dos turnos deve seguir a ordem de cadastro dos jogadores.
 * O sistema deve permitir que mais de um jogador ocupe a mesma posição no tabuleiro.
 * 
### Ações nas Posições:
 * Compra de Propriedade/Companhia: O jogador deve poder comprar uma propriedade/companhia se ela estiver disponível.
 * Pagamento de Aluguel: O jogador deve pagar aluguel se parar em uma propriedade/companhia de outro jogador.
 * Sorte/Revés: O jogador deve ganhar ou perder dinheiro com base em um evento aleatório.
 * Imposto de Renda: O jogador deve pagar uma penalidade de R$200.
 * Melhorias em Propriedades: O jogador deve poder construir pousadas ou hotéis em suas propriedades.
 * Lucros ou Dividendos: O jogador recebe uma bonificação de R$200.
 * Volta Completa: O jogador deve receber R$200 ao completar uma volta no tabuleiro.
 * Prisão: O jogador deve ser movido para a prisão e ficar uma rodada sem jogar.
 * 
### Fim do Jogo:
 * O jogo deve terminar quando restar apenas um jogador com saldo positivo.
 * Jogadores que ficarem sem saldo devem ser eliminados do jogo, e suas propriedades e companhias voltam a ficar disponíveis.
 * 
### Interface:
 * O jogo deve ser implementado para interação via terminal.
 * O sistema deve registrar e exibir o estado atual de cada jogador, incluindo saldo, propriedades adquiridas e posição no tabuleiro.
 * O sistema deve exibir as ações realizadas por cada jogador, como movimentação, compra de propriedades/companhias, pagamento de aluguel, etc.

 ---

 ## 2. Classe posicao

 * Classe Posicao:
### Atributos:
 * nome: String (nome da posição)
 * tipo: String (tipo da posição: "Propriedade", "Companhia", "Sorte/Reves", etc.)
### Métodos:
 * getNome(): String (retorna o nome da posição)
 * getTipo(): String (retorna o tipo da posição)
 * toString(): String (retorna uma representação da posição)

---

## 3. Classe Propriedade

 * Classe Propriedade (Herda de Posicao):
### Atributos:
 * valorCompra: double (valor de compra da propriedade)
 * aluguel: double (valor base do aluguel)
 * aluguelPousada: double (valor do aluguel com pousada)
 * aluguelHotel: double (valor do aluguel com hotel)
 * proprietario: Jogador (jogador proprietário da propriedade)
 * nivelMelhoria: int (nível de melhoria: 0 = sem melhoria, 1 = pousada, 2 = hotel)
### Métodos:
 * calcularAluguel(): double (calcula o valor do aluguel com base nas melhorias)
 * getProprietario(): Jogador (retorna o proprietário da propriedade)
 * setProprietario(Jogador jogador): void (define o proprietário da propriedade)
 * getValorCompra(): double (retorna o valor de compra da propriedade)
 * getNivelMelhoria(): int (retorna o nível de melhoria da propriedade)
 * setNivelMelhoria(int nivel): void (define o nível de melhoria da propriedade)

---

## 4. Classe Companhia

 * Classe Companhia (Herda de Posicao):
### Atributos:
 * valorCompra: double (valor de compra da companhia)
 * fatorAluguel: int (fator multiplicador do aluguel)
 * proprietario: Jogador (jogador proprietário da companhia)
### Métodos:
 * calcularAluguel(int somaDados): double (calcula o aluguel com base no valor do dado)
 * getProprietario(): Jogador (retorna o proprietário da companhia)
 * setProprietario(Jogador jogador): void (define o proprietário da companhia)
 * getValorCompra(): double (retorna o valor de compra da companhia)

 ---

## 5. Classe Jogador


 * Classe Jogador:
### Atributos:
 * nome: String (nome do jogador)
 * saldo: double (saldo do jogador)
 * posicao: int (posição atual no tabuleiro)
 * propriedades: List<Propriedade> (lista de propriedades que o jogador possui)
 * companhias: List<Companhia> (lista de companhias que o jogador possui)
### Métodos:
 * mover(int casas): void (move o jogador um número de casas no tabuleiro)
 * comprarCompanhia(Companhia companhia): void (compra uma companhia)
 * comprarPropriedade(Propriedade propriedade): void (compra uma propriedade)
 * pagarAluguel(double valor): void (paga um aluguel para outro jogador)
 * receber(double valor): void (recebe um valor)
 * construirPousada(Propriedade propriedade): void (constrói uma pousada em uma propriedade)
 * construirHotel(Propriedade propriedade): void (constrói um hotel em uma propriedade)
 * falido(): boolean (verifica se o jogador está falido)
 * getPosicao(): int (retorna a posição atual do jogador)
 * getEstado(): String (retorna o estado atual do jogador: saldo, propriedades e posição)
 * toString(): String (retorna uma representação do jogador e suas propriedas/companhias)

 ---

## 5. Classe Tabuleiro


Tabuleiro: O tabuleiro é composto por posições que representam propriedades (pontos turísticos brasileiros), companhias de serviço e funcionalidades específicas (Sorte/Revés, imposto de renda, bonificações).

### Métodos:
 * inicializarTabuleiro(): void (cria e inicializa o tabuleiro com todas as posições)
 * getPosicao(int indice): Posicao (retorna a posição do tabuleiro com base no índice)
 * getTipoPosicao(int indice): String (retorna o tipo da posição no tabuleiro com base no índice)
 * executarAcao(Jogador jogador, int indice): void (executa a ação correspondente à posição no tabuleiro)
 * exibirTabuleiro(): void (exibe o estado atual do tabuleiro)
 
### O tabuleiro em uma tabela markdown:

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
 

 ---
