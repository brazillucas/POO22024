# Sistema de Gerenciamento de Pedidos

Este é um sistema desenvolvido em Java como trabalho final da disciplina de Programação Orientada a Objetos. O objetivo principal é resolver problemas relacionados à gestão de pedidos compartilhados (uniformes, EPI e almoxarifado) em uma empresa, permitindo atribuir itens de pedidos a funcionários ou setores específicos.

## Visão Geral

No dia a dia da empresa, diversos pedidos são feitos, mas o sistema atual não permite definir para qual funcionário ou setor os itens serão direcionados. Além disso, os pedidos são pré-digitados em planilhas, dificultando a centralização e consulta posterior. Este sistema foi projetado para resolver esses problemas, oferecendo funcionalidades como cadastro de itens, pedidos e atores, além de consultas e geração de planilhas.

### Estrutura do Projeto

```
/
├── src/               # Contém todas as classes Java
│   ├── data/          # Banco de dados SQLite
│   └── *.java         # Classes Java do sistema
└── README.md          # Documentação do projeto
```



### Requisitos do Sistema

- **Java**: Certifique-se de ter o Java instalado na sua máquina.
- **SQLite**: O banco de dados utilizado pelo sistema está localizado na pasta `src/data`.

## Funcionalidades

O sistema atende inicialmente ao gerenciamento de pedidos de almoxarifado, com suporte planejado para uniformes e EPIs. As principais funcionalidades incluem:

### 1. Cadastro de Itens
- **Itens de Almoxarifado**, **Uniformes** e **EPIs** podem ser cadastrados automaticamente a partir de arquivos CSV ou manualmente.
- Informações básicas dos itens:
  - Código
  - Descrição
  - Uniformes e EPIs possuem campos adicionais como **setor de destino**, **tamanho** e **C.A.** (Certificado de Aprovação).

### 2. Cadastro de Atores
- **Setores** e **Funcionários** podem ser cadastrados a partir de arquivos CSV.
- Informações dos atores:
  - Setor: Nome, funcionários e pedidos direcionados.
  - Funcionário: Matrícula, nome, setor, função, data de admissão, loja onde trabalha, tamanho de uniforme e pedidos associados.

### 3. Cadastro de Pedidos
- Os pedidos são realizados informando:
  - Número do pedido (gerado automaticamente)
  - Tipo de pedido (almoxarifado, uniformes ou EPIs)
  - Item, quantidade, setor ou funcionário destinatário
  - Data de realização
- Todos os pedidos são salvos em um banco de dados **SQLite** para histórico.

### 4. Consulta de Pedidos
O sistema permite consultar pedidos de diferentes maneiras:
- **Por Setor**: Exibe os itens pedidos por cada setor.
- **Por Período**: Lista os pedidos realizados entre datas específicas.
- **Por Funcionário**: Mostra os itens pedidos para um funcionário específico.
- **Por Número de Pedido**: Exibe detalhes de um pedido específico.

### 5. Geração de Planilhas
O sistema exporta planilhas no formato `.xls` para novos pedidos:
- **Planilha de Uniformes**: Configuração específica para pedidos de uniformes, incluindo matrícula, nome, função, tamanho e motivo de troca.
- **Planilha de EPIs**: Configuração para entrega de EPIs, com informações como tipo, tamanho, C.A., data de recebimento e assinatura.

## Como Executar o Sistema

1. **Pré-requisitos**:
   - Java instalado (versão 8 ou superior).
   - SQLite configurado (o banco de dados já está incluído na pasta `src/data`).

2. **Clonar o Repositório**:
   ```bash
   git clone https://github.com/seu-usuario/sistema-gerenciamento-pedidos.git
   cd sistema-gerenciamento-pedidos

3. **Executar o Sistema** :
    - Compile as classes Java:
    ```
    javac src/*.java
    ```
    - Execute o sistema:
    ```
    java src.NomeDaClassePrincipal
    ```
4. **Arquivos CSV** :
    - Certifique-se de que os arquivos CSV para cadastro de itens e atores estejam disponíveis e corretamente formatados.

## Licença
MIT License. Veja o arquivo LICENSE para mais detalhes: [MIT License](https://opensource.org/licenses/MIT).MIT License .