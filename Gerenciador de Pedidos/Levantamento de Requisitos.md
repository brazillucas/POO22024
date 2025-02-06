# Sistema De Gerenciamento De Pedidos


No dia a dia no trabalho são feitos diversos pedidos compartilhados, como uniformes, epi e almoxarifado.
No entanto, o sistema padrão só permite fazer o pedido e não definir para quem ou qual setor serão direcionados os itens.

Os pedidos de uniformes e epi, por exemplo, são pré-digitados em planilhas, mas isso impede que sejam centralizados para rápida consulta posterior.

Com estes problemas em vista, foi pensado o desenvolvimento de uma ferramenta que permita atribuir os itens de cada pedido às pessoas ou setores que os receberão.


## Funções

O sistema deverá atender, ao final do seu desenvolvimento, os três tipos de pedidos citados anteriormente, pois são os mais urgentes e recorrentes na empresa. Sendo inicialmente atendido o pedido de almoxarifado.

Para o correto funcionamento o sistema deve, primeiramente, permitir os cadastros por parte do administrador.
Esses cadastro são:
- Cadastro de Itens
- Cadastro de Pedidos
- Cadastro de Funcionários

### Cadastro de Itens
O cadastro de itens será feito automaticamente consultado o banco de dados que conterá a informação de qual o tipo de pedido está sendo lida (almoxarifado, epi ou uniformes).
Também será necessário permitir atualizar a lista de itens para o pedido entre a adição de um item e outro.

### Cadastro de Pedidos
Pedidos serão realizados informando:
- o tipo de pedido que será iniciado
- o **item**
- a **quantidade** -
- o **setor** (almoxarifado)/**funcionário** (uniformes e EPI) que receberá aquele item quando ele chegar.
O pedido precisa registrar a **data**, para que possa ser consultado posteriormente os pedidos realizados por dia.

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

#### Listagem Por Funcionário
O sistema poderá exibir itens pedidos para cada funcionário, informando qual a data de realização do pedido daquele item e qual o tipo daquele pedido.

### Cadastro de Atores
O sistema deve ler arquivos com os nomes dos funcionários e dos setores selecionáveis para os pedidos.
#### Setor
Cada setor conterá um nome, funcionários e seus pedidos.

##### Funcionário
Cada funcionário terá um nome, uma matrícula, um setor, sua data de admissão, o tamanho do uniforme, os pedidos que contém itens para ele.
##### Cargo do funcionário
O funcionário deve ser subclasses para cada setor, pois funcionários de setores diferentes possuem características diferentes, como, por exemplo, o tamanho de bota, o tamanho de avental, etc.

## Geração de Planilhas Para Novos Pedidos
Como função final, é esperado que o sistema seja capaz de exportar um pedido em formato de planilha para que seja enviada por e-mail para a devida confirmação daquela solicitação junto ao setor responsável.
