# Requisitos do Sistema de Venda de Passagens Aéreas - CLI

## 1. Introdução

Este documento descreve os requisitos para o desenvolvimento de um sistema de venda de passagens aéreas em CLI para a companhia aérea **NoisTropicaMaisNaoCai**. O sistema visa gerenciar a venda de passagens para uma aeronave de pequeno porte, realizando viagens diárias com destino e origem fixos. O público-alvo deste documento são os desenvolvedores responsáveis pela implementação do sistema.

---

## 2. Requisitos Funcionais

### 2.1 Gerenciamento de Assentos

- A aeronave possui um número de assentos a ser definido, dispostos em fileiras e colunas (o modelo da aeronave será fornecido em imagem).
- O sistema deve representar os assentos no CLI de forma clara e intuitiva, utilizando caracteres para indicar:
  - **Assentos disponíveis**
  - **Reservados**
  - **Indisponíveis**
- O sistema deve permitir as seguintes operações:
  - Verificar a disponibilidade de assentos para uma data específica.
  - Reservar um assento para um passageiro.
  - Cancelar a reserva de um assento.
- Os preços das poltronas são definidos como:
  - **Janela lado direito:** R$ 720,00
  - **Janela lado esquerdo:** R$ 850,00
  - **Corredor:** R$ 550,00

---

### 2.2 Cadastro de Passageiros

- O sistema deve permitir o cadastro de passageiros com os seguintes dados:
  - Nome completo (texto)
  - E-mail (texto)
  - Endereço (texto)
  - Data de nascimento (data)
  - Comorbidades (sim/não)
- Funcionalidades do cadastro:
  - Inserir dados via CLI com prompts claros e intuitivos.
  - Validar os dados inseridos, garantindo:
    - Preenchimento obrigatório.
    - Formatos corretos para data e e-mail.
  - Armazenar os dados de forma persistente, permitindo consulta e edição.

---

### 2.3 Fila de Embarque/Desembarque

- O sistema deve ordenar os passageiros em uma fila de prioridade, exibindo os nomes com os critérios:
  1. Passageiros com comorbidades.
  2. Passageiros idosos (idade ≥ 60 anos).
  3. Passageiros aniversariantes no dia.
  4. Ordem de compra da passagem.
- Exibir a fila de prioridade no CLI de forma organizada.
- Utilizar um algoritmo de ordenação eficiente.

---

### 2.4 Venda de Passagens

- O sistema deve permitir a venda de passagens para um período de **30 dias consecutivos**.
- Detalhes da funcionalidade:
  - Solicitar a data da viagem, o assento desejado e os dados do passageiro (caso ainda não esteja cadastrado).
  - Antes de concluir a venda:
    - Verificar disponibilidade de assentos.
    - Impedir a venda de múltiplos assentos para o mesmo passageiro no mesmo voo.
    - Calcular o preço da passagem, aplicando descontos (detalhados na seção 2.5).
  - Após a venda:
    - Registrar a compra, associando passageiro, assento e data.
    - Atualizar a disponibilidade de assentos.
    - Gerar um comprovante de compra com informações detalhadas.
- Restrições:
  - Impedir vendas para o dia atual e dias anteriores após o registro da partida.
  - Caso não haja passageiros confirmados para o dia, informar que a viagem não será iniciada.

---

### 2.5 Descontos

- O sistema deve aplicar os seguintes descontos (cumulativos):
  - **5% progressivo:** para cada dia viajado no mês corrente (até 50%).
  - **15%:** para passageiros com comorbidades.
  - **5%:** para passageiros idosos.
  - **5%:** para passageiros aniversariantes no dia.
- Detalhes:
  - Calcular o desconto progressivo automaticamente.
  - Exibir os descontos e preço final de forma clara no CLI.

---

### 2.6 Gerenciamento de Partidas

- O sistema deve permitir o registro de partidas, validando a data como o dia atual.
- Após o registro da partida:
  - Contabilizar o dia.
  - Listar os passageiros confirmados.
  - Impedir a venda de passagens para o dia registrado e dias anteriores.

---

## 3. Requisitos Não Funcionais

### 3.1 Interface

- O sistema deve utilizar interface de linha de comando (CLI).
- A interface deve ser intuitiva, com menus e comandos claros.

---

### 3.2 Usabilidade

- Utilizar mensagens claras e comandos intuitivos.
- Mensagens de erro devem ser descritivas e indicar ações corretivas.

---

### 3.3 Performance

- O tempo de resposta deve ser inferior a **5 segundos** para operações básicas.
- Suporte para até **33 passageiros** e **30 dias** de voos simultaneamente.

---

## 4. Considerações Adicionais

- Utilize uma linguagem clara e objetiva na descrição dos requisitos.
- Inclua exemplos de comandos e saída do sistema no CLI para ilustrar o funcionamento.
- Revise cuidadosamente o documento antes da implementação.

---

Este documento visa fornecer uma especificação detalhada dos requisitos para o desenvolvimento do sistema de venda de passagens aéreas em CLI. Com essas informações, os desenvolvedores podem iniciar o processo de implementação, garantindo que o sistema atenda às necessidades da companhia aérea **NoisTropicaMaisNaoCai**.