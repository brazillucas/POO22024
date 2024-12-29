# Trabalho Prático 2

# POO e Estruturas de armazenamento

Valor: 10 pontos Entrega: 06/01/2025

Objetivos: Este trabalho tem como objetivo principal proporcionar aos alunos a oportunidade de praticar os conceitos fundamentais de Programação Orientada a Objetos (POO), como encapsulamento,herança e uso de estruturas de dados. Além disso, busca incentivar o planejamento e o desenvolvimento de sistemas interativos baseados em regras, por meio da criação de uma versão digital do jogo Banco Imobiliário, chamada "Banco IFMG". O jogo será implementado exclusivamente para interação via terminal, promovendo a integração entre lógica de programação e a aplicação de conceitos de POO.

## Descrição:

Seu José, um ilustre morador da cidade de Ouro Branco, é um grande entusiasta de jogos de tabuleiro. No entanto, com o passar dos anos, os jogos que ele guarda com tanto carinho frequentemente acabam ficando incompletos, com componentes como cartões e dados sendo perdidos.Reconhecendo esse problema e sabendo que você, aluno do curso de Sistemas de Informação, jádomina os fundamentos da programação (afinal, está se tornando um verdadeiro "NINJA" na área!),ele decidiu pedir sua ajuda para criar uma solução inovadora.

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

Tabela 1: Disposição dos pontos e companhias pelo mapa

Seu José sempre sonhou em ter uma versão digital do famoso jogo Banco Imobiliário, que aqui seráadaptado e chamado de "Banco IFMG". Essa versão digital apresentará pontos turísticos brasileiros selecionados por internautas em uma ação promovida pela fabricante Estrela em 2008, além de incluircompanhias de serviços que fazem parte do universo do jogo. A disposição das propriedades, companhias e funcionalidades segue o modelo ilustrado na Figura 1. 

Cada jogador será representado por uma classe específica. No início do jogo, o sistema deve solicitar o número de participantes (de 2 a 6) e os respectivos nomes. Todos os jogadores iniciam com um saldo fixo e se movem pelo tabuleiro conforme os resultados de dois dados simulados pelo sistema. As regras do jogo incluem a compra de propriedades e companhias disponíveis, pagamento de aluguéis a outros jogadores, realização de melhorias em propriedades adquiridas (como a construção de pousadas e hotéis, que aumentam os valores de aluguel) e a eliminação de jogadores que entram em falência. 

O jogo será encerrado quando restar apenas um jogador em condições financeiras viáveis. Durante as rodadas, o sistema deve registrar e exibir o estado atual de cada jogador, incluindo saldo, propriedades adquiridas e posição no tabuleiro. É imprescindível validar todas as entradas do usuário para garantir a robustez do programa. 

## Especificações sobre o Funcionamento do Jogo 

Você deverá implementar o tabuleiro do jogo, seguindo rigorosamente os componentes apresentados na Figura 1. O tabuleiro será composto por posições que podem conter propriedades, companhias e funcionalidades específicas, que serão detalhadas nos tópicos a seguir. 

No início do jogo, todos os jogadores recebem um saldo inicial de 1.500 reais. O ponto de partida, onde todos começam, corresponde à posição localizada no canto inferior direito do tabuleiro (última linha, última coluna da matriz). Sempre que um jogador completa uma volta no tabuleiro, passando por esse ponto de partida, ele recebe um prêmio de 200 reais. 

Em cada rodada, os jogadores lançam dois dados, cuja soma determina a quantidade de casas a serem avançadas no tabuleiro. A ordem dos turnos segue a sequência de cadastro dos jogadores no início do jogo. Vale destacar que uma mesma posição no tabuleiro pode ser ocupada por mais de um jogador simultaneamente. Ao chegar em uma posição correspondente à soma dos valores dos dados, um dos seguintes cenários pode ocorrer: 

- Compra de uma propriedade ou companhia: O jogador pode adquirir o local caso ele ainda não pertença a outro jogador. 

- Pagamento de aluguel: Caso a propriedade ou companhia pertença a outro jogador, será necessário pagar uma taxa ao proprietário. 

- Rodada de Sorte/Revés: Eventos aleatórios que podem gerar ganhos ou perdas para o jogador.

- Pagamento de imposto de renda: Uma penalidade de 200 reais será deduzida do saldo do jogador. 

- Melhoramentos em uma propriedade: Permite ao jogador proprietário realizar melhorias em suas propriedades, como construir pousadas ou hotéis. 

- Lucros ou dividendos: O jogador recebe uma bonificação de 200 reais.

Cada ponto turístico do tabuleiro possui taxas de locação específicas, que podem ser incrementadas pelos proprietários ao realizarem melhorias, como a construção de pousadas ou hotéis. Para facilitar a compreensão dos valores envolvidos em cada cenário, a relação a seguir apresenta os custos de aquisição, valores básicos de aluguel, e as taxas atualizadas para propriedades com pousadas ou hotéis:

| Propriedade                | Compra | Aluguel | Pousada | Hotel   |
|----------------------------|--------|---------|---------|---------|
| Leblon                     | R$ 80  | R$ 8    | R$ 24   | R$ 240  |
| Avenida Presidente Vargas  | R$ 80  | R$ 6    | R$ 18   | R$ 180  |
| Avenida Nossa Senhora de Copacabana | R$ 80  | R$ 7    | R$ 21   | R$ 210  |
| Avenida Brigadeiro Faria Lima | R$ 110 | R$ 13   | R$ 39   | R$ 390  |
| Avenida Rebouças           | R$ 110 | R$ 11   | R$ 33   | R$ 330  |
| Avenida 9 de Julho         | R$ 110 | R$ 12   | R$ 36   | R$ 360  |
| Avenida Europa             | R$ 100 | R$ 10   | R$ 30   | R$ 300  |
| Rua Augusta                | R$ 100 | R$ 9    | R$ 27   | R$ 270  |
| Avenida Pacaembu           | R$ 100 | R$ 10   | R$ 30   | R$ 300  |
| Interlagos                 | R$ 250 | R$ 35   | R$ 105  | R$ 1.050 |
| Morumbi                    | R$ 250 | R$ 50   | R$ 150  | R$ 1.500 |
| Bangu                      | R$ 100 | R$ 11   | R$ 33   | R$ 330  |
| Botafogo                   | R$ 100 | R$ 10   | R$ 30   | R$ 300  |
| Avenida Brasil             | R$ 110 | R$ 13   | R$ 39   | R$ 390  |
| Avenida Paulista           | R$ 110 | R$ 15   | R$ 45   | R$ 450  |
| Jardim Europa              | R$ 110 | R$ 12   | R$ 36   | R$ 360  |
| Copacabana                 | R$ 170 | R$ 21   | R$ 63   | R$ 630  |
| Avenida Vieira Souto       | R$ 170 | R$ 23   | R$ 69   | R$ 690  |
| Avenida Atlântica          | R$ 170 | R$ 23   | R$ 69   | R$ 690  |
| Ipanema                    | R$ 170 | R$ 22   | R$ 66   | R$ 660  |
| Jardim Paulista            | R$ 190 | R$ 28   | R$ 84   | R$ 840  |
| Brooklin                   | R$ 190 | R$ 22   | R$ 66   | R$ 660  |

Tabela 2: Descrição de valores das propriedades

O valor do aluguel das companhias depende do resultado do dado multiplicado por uma taxa específica. A tabela a seguir detalha os valores:

| Companhia                   | Compra | Aluguel (multiplicador do dado) |
|-----------------------------|--------|----------------------------------|
| Companhia Ferroviária       | R$ 200 | 50 × valor do dado              |
| Companhia de Viação         | R$ 200 | 40 × valor do dado              |
| Companhia de Táxi           | R$ 200 | 40 × valor do dado              |
| Companhia de Navegação      | R$ 200 | 40 × valor do dado              |
| Companhia de Aviação        | R$ 200 | 50 × valor do dado              |
| Companhia de Táxi Aéreo     | R$ 200 | 50 × valor do dado              |

## A - Compra de uma propriedade ou de uma companhia 

Quando um jogador se posiciona em uma propriedade ou companhia que ainda não pertence a outro jogador, ele tem a opção de adquiri-la. Para isso, é necessário pagar o valor de compra especificado, indicado em negrito ao lado do nome da propriedade ou companhia. Cada propriedade ou companhia pode ter apenas um proprietário por vez. 

## B - Pagamento de aluguel pelo uso de propriedades ou companhias

Se um jogador parar em uma propriedade ou companhia que já pertence a outro jogador, será necessário pagar pelo uso. Para as propriedades, o valor a ser pago depende de sua condição: o aluguel padrão é indicado na tabela de valores, mas se houver melhorias (como pousadas ou hotéis), o valor correspondente às melhorias será cobrado. No caso das companhias, o aluguel é calculado multiplicando-se a soma dos valores dos dados pelo fator indicado para a companhia. Por exemplo, se um jogador parar na Companhia de Aviação de um adversário e a soma dos dados for 10, ele deverá pagar 10×50=500ao proprietário. 

## C - Rodada de Sorte/Revés 

As posições de Sorte/Revés no tabuleiro desencadeiam eventos aleatórios que podem beneficiar ou prejudicar o jogador. Ao parar em uma dessas casas, o jogador pode ganhar entre R$ 1 e R$ 150, ou perder entre R$ 1 e R$ 80, com base em um sorteio aleatório. 

## D - Melhorias em propriedades 

Quando um jogador para em uma propriedade de sua posse, ele pode optar por realizar melhorias. Se a propriedade ainda não tiver melhorias, o jogador pode construir uma pousada, pagando o valor correspondente ao aluguel atual. Caso já exista uma pousada, o jogador pode aprimorá-la para um hotel, pagando novamente o valor do aluguel. Essas melhorias aumentam o valor do aluguel cobrado de outros jogadores. 

## Fim da Linha e Encerramento do Jogo 

Quando um jogador não tiver saldo suficiente para pagar suas dívidas a outro jogador, ele será eliminado do jogo. Nesta versão, não serão consideradas opções como a venda de hotéis, pousadas ou a aplicação de hipotecas. Ao ser eliminado, todas as propriedades e companhias anteriormente pertencentes a esse jogador voltam a ficar disponíveis no tabuleiro, podendo ser adquiridas pelos demais participantes. 

O jogo "Banco IFMG" será concluído quando restar apenas um jogador em condições financeiras viáveis, ou seja, que não tenha entrado em falência. Esse jogador será declarado o vencedor. 

## Entrada: 

 O seu algoritmo deve pedir que o usuário informe qual a quantidade inicial de jogadores (máximo de 6) e ainda o nome de cada um dos jogadores. 

## Saída: 

 Como saída o seu algoritmo deve simular cada uma das rodadas do jogo em questão. Soluções que apresentem o estado atual dos jogadores, assim como os seus posicionamentos no tabuleiro serão bem avaliadas. 

## O que deve ser entregue: 

1. Código fonte do programa em Java (bem identada e comentada).

2. Documentação do trabalho. Entre outras coisas, a documentação deve conter:

2.1. Introdução: descrição do problema a ser resolvido e visão geral sobre o funcionamento do programa. 

2.2. Implementação: descrição sobre a implementação do programa. Deve ser detalhada a estrutura de dados utilizada (de preferência com diagramas ilustrativos), o funcionamento das principais funções e procedimentos utilizados, o formato de entrada e saída de dados, bem como decisões tomadas relativas aos casos e detalhes de especificação que porventura estejam omissos no enunciado. 

2.3. Conclusão: comentários gerais sobre o trabalho e as principais dificuldades encontradas em sua implementação. 

2.4. Bibliografia: bibliografia utilizada para o desenvolvimento do trabalho, incluindo sites da Internet se for o caso 

3. Formato: mandatoriamente em PDF (http://www.pdf995.com/). 

Obs1: Apesar desse trabalho ser bem simples, a documentação pedida segue o formato da documentação que deverá ser entregue nos próximos trabalhos. 

Obs2: Consulte as dicas do Prof. Nívio Ziviani de como deve ser feita uma boa implementação e documentação de um trabalho prático: https://saulocabral.pagekite.me/roteirotp.pdf 

## Como deve ser feita a entrega: 

A entrega DEVE ser feita através da plataforma moodle na forma de um único arquivo zipado, contendo o código, os arquivos, o executável e a documentação. 

Comentários Gerais: 

  Comece a fazer este trabalho logo, enquanto o problema está fresco na memória e o prazo para terminá-lo está tão longe quanto jamais poderá estar; 

  Clareza, indentação e comentários no programa também vão valer pontos; 

  O trabalho pode ser feito em trios (grupos de MÁXIMO 3 alunos); 

- Trabalhos copiados (e FONTE) terão nota ZERO; 

- Trabalhos entregue em atraso serão aceitos, todavia a nota atribuída ao trabalho será zero

- Evite discussões inócuas com o professor em tentar postergar a data de entrega do referido trabalho.