# Sistema De Gerenciamento De Pedidos


No dia a dia no trabalho são feitos diversos pedidos compartilhados, como uniformes, EPI e almoxarifado.
No entanto, o sistema padrão só permite fazer o pedido e não permite definir para qual funcionário ou setor serão direcionados os itens.

Os pedidos de uniformes e EPI, por exemplo, são pré-digitados em planilhas, mas isso impede que sejam centralizados para rápida consulta posterior.

Com estes problemas em vista, foi pensado o desenvolvimento de uma ferramenta que permita atribuir os itens de cada pedido às pessoas ou setores que os receberão como trabalho final da disciplina de programação orientada a objetos utilizando java.


## Funções

O sistema deverá atender, ao final do seu desenvolvimento, os três tipos de pedidos citados anteriormente, pois são os mais urgentes e recorrentes na empresa. Sendo inicialmente atendido o pedido de almoxarifado.

O sistema trabalhará apenas com o usuário administrador inicialmente. Ou seja, apesar de haver tela de login, só haverá um usuário no sistema, que terá acesso a todos os recursos.

Para o correto funcionamento o sistema deve, primeiramente, permitir os cadastros por parte do administrador.
Esses cadastro são:
- Cadastro de Itens (Consultando arquivo CSV)
  - Cadastro de Uniformes
  - Cadastro de EPI
  - Cadastro de Itens de Almoxarifado
- Cadastro de Atores (Consultando arquivo CSV)
	- Cadastro de Setores
	- Cadastro de Funcionários
- Cadastro de Pedidos (Consultando arquivo CSV ou manualmente)
  - Cadastro de Pedidos de Uniformes
  - Cadastro de Pedidos de EPI
  - Cadastro de Pedidos de Almoxarifado
- Consulta de Pedidos (Consultando banco de dados)
	- Listagem por Setor
	- Listagem por Período
	- Listagem por Funcionário
- Geração de Planilhas para Novos Pedidos (Exportando para arquivo .xls)
	- Planilha de Uniformes
	- Planilha de EPI
    - Planilha de Almoxarifado

### Cadastro de Itens
O cadastro de itens será feito automaticamente consultado o banco de dados que conterá a informação de qual o tipo de pedido está sendo lida (almoxarifado, EPI ou uniformes).
Também será necessário permitir atualizar a lista de itens para o pedido.

Itens possuem as seguintes informações
- codigo
- descricao
- Uniformes:
	- setor de destino
	- tamanho
- EPI:
	- setor de destino
	- tamanho
	- C.A.

### Cadastro de Pedidos
Pedidos serão realizados informando:
 - **número do pedido** (gerado automaticamente)
- tipo de pedido que será iniciado (ao iniciar o pedido, o sistema deve perguntar qual o tipo de pedido será realizado)
- **item**
- **quantidade**
- **setor** (almoxarifado) que receberá aquele item quando ele chegar.
- **funcionário** (uniformes e EPI) que receberá aquele item quando ele chegar.
- O pedido precisa registrar a **data**, para poder ser consultado posteriormente os pedidos realizados por dia.

Cada pedido deve ser salvo em um banco de dados **SQLite** para ser consultado  a cada execução do programa, gerando um histórico sólido.

### Consulta de Pedidos
#### Listagem Por Setor
O sistema deve permitir que o usuário consulte os pedidos realizados anteriormente, informando por ordem de setor/funcionário os itens.
Por exemplo, se no pedido a
- **tesouraria** pediu
    - _2 grampeadores_,
    - _3 pacotes de elástico_
    - _1 tesoura_
- o **escritório** pediu: 
    - _1 suporte de durex_
    - _5 fitas durex_
    
O sistema irá mostrar algo como:

### Tesouraria

| Quantidade | Item           |
|------------|----------------|
| 02         | Grampeador     |
| 03         | Pct. Elástico  |
| 01         | Tesoura        |

### Escritório

| Item             | Quantidade |
|------------------|------------|
| Suporte de durex | 01         |
| Durex            | 05         |


#### Listagem Por Período
O sistema deve permitir listar os pedidos realizados por períodos.
Ou seja, o usuário pode selecionar uma data inicial e uma data  final para visualizar os pedidos realizados entre essas datas.
- Serão exibidos os números dos pedidos, a data de realização e o tipo do pedido.
- Ao digitar o número do pedido, o sistema deve exibir os itens pedidos naquele pedido.

#### Listagem Por Funcionário
O sistema poderá exibir itens pedidos para cada funcionário, informando qual a data de realização do pedido daquele item e qual o tipo daquele pedido.
- Ao digitar o número de matrícula do funcionário, o sistema deve exibir os itens pedidos para aquele funcionário e o número do pedido.

#### Consulta por Número de Pedido
O sistema deve permitir que o usuário consulte um pedido específico, informando o número do pedido.
- O sistema deve exibir os itens pedidos naquele pedido, a data de realização e o tipo do pedido (substituindo o número salvo no banco de dados pelo tipo por extenso correspondente).

### Cadastro de Atores
O sistema deve ler arquivos com os nomes dos funcionários e dos setores selecionáveis para os pedidos a partir de um arquivo CSV e salvar no banco de dados.

#### Setor
Cada setor conterá:
- um nome
- funcionários
- os pedidos direcionados àquele setor

##### Funcionário
Cada funcionário terá:
- matrícula
- nome
- setor
- função
- data de admissão
- loja que trabalha
- tamanho do uniforme

- os pedidos que contém itens para ele

##### Função do funcionário
O funcionário deve conter uma característica **cargo**, pois funcionários de setores diferentes possuem características diferentes, como, por exemplo:
- tamanho de EPI:
	- AÇOUGUEIRO(A), BALCONISTA DE AÇOUGUE, AUXILIAR DE PADARIA, ATENDENTE DE FRIOS, OPERADOR DE CAMARA, ENCARREGADO(A) DE FRIOS:
		- Avental
		- calçado
	- REPOSITOR(A), AUXILIAR DE DEPÓSITO, FISCAL DE LOJA, ENCARREGADO DE DEPÓSITO, CONFERENTE, ENCARREGADO(A) DE HORTIFRUTI, ENCARREGADO(A) DE LOJA, EMBALADOR(A), AUX. DE REPOS. DE MERCADORIAS, APRENDIZ DE VENDEDOR DE COMERCIO VAREJISTA, AUXILIAR DE LOJA, AGENTE PREVENÇÃO DE PERDAS, ORIENTADOR DE TRAF. P/ ESTACIONAMENTO:
		- calçado
	- ORIENTADOR DE TRAF. P/ ESTACIONAMENTO:
		- calçado
		- capa de chuva (será representado como avental, pois os tamanhos são os mesmos)

## Geração de Planilhas Para Novos Pedidos
Como função final, é esperado que o sistema seja capaz de exportar um pedido em formato de planilha para que seja enviada por e-mail para a devida confirmação daquela solicitação junto ao setor responsável.

### Planilha de Uniformes
A planilha de saída para uniformes deve conter a seguinte configuração:
- A primeira linha tem as colunas de A a K mescladas com o texto "NOME DA EMPRESA" como conteúdo
- A2:B3 mesclados com o conteúdo "NUMERO DA LOJA: <<nº da loja do administrador>>"
- C2:G2 mesclados com o conteúdo "PEDIDO DE UNIFORMES PARA:"
- C3:G3 mesclados com o conteúdo "[ ] Novas admissões  [ ] Reposições/Trocas" (que será marcado com "x" dependendo do tipo de pedido)
- H2:H3 mesclados com o conteúdo "DATA:"
- I2:K3 mesclados preenchido com <<data atual>> no formato dd/mm/aaaa
- A4:K4 mesclados com conteúdo uma declaração de recebimento do uniforme
- Nome do funcionário
- Matrícula do funcionário
- Funcao
- Tamanho
- Data de  Admissão
- Motivo de Troca
- G5:H5 mesclados com conteúdo "Tipo de uniforme" (a ser preenchido apenas com "X")
	- Uniforme (coluna G)
	- Moletom (coluna H)
- Campo de Assinatura

As próximas linhas terão apenas bordas finas configuradas e o conteúdo resultando da query realizada

### Planilha de EPI
A planilha de saída para EPI deve conter a seguinte configuração:
- A1:J2 mesclados com conteúdo "PEDIDO / RECIDO DE ENTREGA DE EQUIPAMENTO DE PROTEÇÃO INDIVIDUAL - EPI"
- A linha três tem plano de fundo "ccc" até a coluna J
- A3 com conteúdo "Loja:"
- B3 com conteúdo <<número da loja do administrador>>
- E3 com conteúdo <<data atual>> no formato dd/mm/aaaa
- A4:J6 mescladas com conteúdo "Recebi de <<nome da empresa>>, gratuitamente, os Equipamentos de Proteção Individual - EPIs, abaixo relacionados, bem como as orientações de uso e conservação. Me comprometo a usá-los unicamente para os fins que se destinam e solicitar a troca quando desgastados. Fico ciente da obrigatoriedade do seu uso bem como da devolução deles no término do contrato de trabalho ou indenização no caso de dano ou extravio."
- A7 a J7:
	- Matricula
	- Nome
	- Função
	- EPI (Tipo)
	- Tamanho
	- C.A. do EPI
	- Data de Recebimento
	- Assinatura
	
As próximas linhas terão apenas bordas finas configuradas e o conteúdo resultando da query realizada