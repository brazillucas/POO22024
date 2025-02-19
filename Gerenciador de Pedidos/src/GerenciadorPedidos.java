/*
 * Classe responsável por gerenciar todo o sistema de pedidos.
 * Inicia o sistema, exibe o menu principal e gerencia as opções escolhidas pelo usuário.
 * É chamado pelo método main da classe Principal.
 * 
 * Atributos:
 * administradorLogado (Administrador): O administrador que está atualmente logado no sistema.
 * sistemaDeLogin (SistemaDeLogin): O sistema de login que gerencia os administradores cadastrados.
 * itens (List<Item>): A lista de itens disponíveis para pedidos.
 * setores (List<Setor>): A lista de setores disponíveis para pedidos.
 * funcoes (List<FuncaoFuncionario>): A lista de funções disponíveis para funcionários.
 * funcionarios (List<Funcionario>): A lista de funcionários cadastrados no sistema.
 * pedidos (List<Pedido>): A lista de pedidos registrados no sistema.
 * 
 * Construtores:
 * Gerenciador(): Inicializa o sistema de pedidos.
 * 
 * Getters e Setters:
 * getAdministradorLogado(): Retorna o administrador atualmente logado no sistema.
 * setAdministradorLogado(Administrador administrador): Define o administrador atualmente logado no sistema.
 * getSistemaDeLogin(): Retorna o sistema de login do sistema de pedidos.
 * setSistemaDeLogin(SistemaDeLogin sistemaDeLogin): Define o sistema de login do sistema de pedidos.
 * getItens(): Retorna a lista de itens disponíveis para pedidos.
 * setItens(List<Item> itens): Define a lista de itens disponíveis para pedidos.
 * getSetores(): Retorna a lista de setores disponíveis para pedidos.
 * setSetores(List<Setor> setores): Define a lista de setores disponíveis para pedidos.
 * getFuncoes(): Retorna a lista de funções disponíveis para funcionários.
 * setFuncoes(List<FuncaoFuncionario> funcoes): Define a lista de funções disponíveis para funcionários.
 * getFuncionarios(): Retorna a lista de funcionários cadastrados no sistema.
 * setFuncionarios(List<Funcionario> funcionarios): Define a lista de funcionários cadastrados no sistema.
 * getPedidos(): Retorna a lista de pedidos registrados no sistema.
 * setPedidos(List<Pedido> pedidos): Define a lista de pedidos registrados no sistema.
 * 
 * Métodos:
 * iniciarSistema(): Inicializa o sistema, carregando os dados do banco de dados e exibindo o menu principal.
 * 
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorPedidos {

    // Atributos
    private Administrador administradorLogado;
    private SistemaDeLogin sistemaDeLogin;
    private List<Item> itens;
    private List<Setor> setores;
    private List<Funcoes> funcoes;
    private List<Funcionario> funcionarios;
    private List<Pedido> pedidos;
    private CSVHandler csvHandler;
    private PlanilhaHandler planilhaHandler;
    private BancoDeDados bancoDeDados;
    private Menu menu;
    

    // Construtores
    public GerenciadorPedidos() {
        this.sistemaDeLogin = new SistemaDeLogin();
        this.bancoDeDados = new BancoDeDados();
        this.csvHandler = new CSVHandler();
        this.planilhaHandler = new PlanilhaHandler();
        this.menu = new Menu();
        this.itens = new ArrayList<>();
        this.setores = new ArrayList<>();
        this.funcoes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public GerenciadorPedidos (Administrador admin) {
        this.administradorLogado = admin;
        this.sistemaDeLogin = new SistemaDeLogin();
        this.bancoDeDados = new BancoDeDados();
        this.csvHandler = new CSVHandler();
        this.planilhaHandler = new PlanilhaHandler();
        this.menu = new Menu();
        this.itens = new ArrayList<>();
        this.setores = new ArrayList<>();
        this.funcoes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // Getters e Setters
    public Administrador getAdministradorLogado() {
        return administradorLogado;
    }

    public void setAdministradorLogado(Administrador administrador) {
        this.administradorLogado = administrador;
    }

    public SistemaDeLogin getSistemaDeLogin() {
        return sistemaDeLogin;
    }

    public void setSistemaDeLogin(SistemaDeLogin sistemaDeLogin) {
        this.sistemaDeLogin = sistemaDeLogin;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public List<Funcoes> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Funcoes> funcoes) {
        this.funcoes = funcoes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public CSVHandler getCsvHandler() {
        return csvHandler;
    }

    public void setCsvHandler(CSVHandler csvHandler) {
        this.csvHandler = csvHandler;
    }

    public PlanilhaHandler getPlanilhaHandler() {
        return planilhaHandler;
    }

    public void setPlanilhaHandler(PlanilhaHandler planilhaHandler) {
        this.planilhaHandler = planilhaHandler;
    }

    public BancoDeDados getBancoDeDados() {
        return bancoDeDados;
    }

    public void setBancoDeDados(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    // Métodos
    public void iniciarSistema() {
        
        // Inicializa o sistema
        System.out.println("Iniciando o sistema...");
        
        // Carrega os dados do banco de dados
        System.out.println("Carregando dados do banco de dados...");

        // Carregar dados do banco de dados
        this.itens = this.bancoDeDados.carregarItens();
        this.setores = this.bancoDeDados.carregarSetores();
        this.funcoes = this.bancoDeDados.carregarFuncoes();
        this.funcionarios = this.bancoDeDados.carregarFuncionarios();
        this.pedidos = this.bancoDeDados.listarPedidos();
        
        // Carregar administradores
        this.sistemaDeLogin.addTodosAdministradores(this.bancoDeDados.carregarAdministradores());

        // Carregar dados do CSV
        System.out.println("Carregando dados do CSV...");
        carregarDadosCSV();

        // Realizar login
        realizarLogin();
        if (this.administradorLogado == null) {
            Entrada.aguardarEnter();
            return;
        }

        // Chama o fluxo principal do sistema
        menuPrincipal();

    }

    private void menuPrincipal() {
        // Limpar a tela
        Entrada.limparTela();
        this.menu.exibirMenuPrincipal();
        // Exibe o menu principal
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-6]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Menu Item
                menuItem();
                break;
            case 2:
                menuSetor();
                break;
            case 3:
                menuFuncionario();
                break;
            case 4:
                exibirMenuPedido();
                break;
            case 5:
                alterarSenha();
                break;
            case 6:
                logoutUsuario();
                break;
        }
    }

    private void menuItem() {
        Entrada.limparTela();
        menu.exibirMenuItem();
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-3]+", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Consultar itens
                consultaItens();
                break;
            case 2:
                // Importar itens
                importarItens();
                break;
            case 3:
                menuPrincipal();
                break;
        }

    }

    // Consultar itens
    private void consultaItens() {
        Entrada.limparTela();
        this.menu.exibirConsultaItens();
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[0-5]+", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Consultar todos os itens
                if (this.itens.isEmpty()) {
                    System.out.println("Nenhum item cadastrado.");
                } else {
                    for (Item item : this.itens) {
                        item.exibirDetalhes();
                    }
                }
                Entrada.aguardarEnter();
                menuItem();
                break;
            case 2:
                // Consultar itens por setor
                int idSetor = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor: ", "[0-9]+", "ID inválido"));
                int exibidos = 0;
                boolean setorEncontrado = false;
                for (Setor setorConsulta : this.setores) {
                    if (setorConsulta.getId() == idSetor) {
                        System.out.printf("Setor: %s\n", setorConsulta.getNome());
                        setorEncontrado = true;
                    }
                }
                if (!setorEncontrado) {
                    System.out.println("Setor não encontrado.");
                    Entrada.aguardarEnter();
                    menuItem();
                    return;
                }
                for (Item item : this.itens) {
                    if (item instanceof Uniforme uniforme && uniforme.getSetorDestino() == idSetor) {
                        item.exibirDetalhes();
                        exibidos++;
                    } else if (item instanceof EPI epi && epi.getSetorDestino() == idSetor) {
                        item.exibirDetalhes();
                        exibidos++;
                    }
                }
                if (exibidos == 0) {
                    System.out.println("Nenhum item encontrado para o setor informado.");
                }
                Entrada.aguardarEnter();
                menuItem();
                break;
            case 3:
                // Consultar itens por ID
                int idItem = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do item: ", "[0-9]+", "ID inválido"));
                int itensEncontrados = 0;
                for (Item item : this.itens) {
                    if (item.getCodigo() == idItem) {
                        item.exibirDetalhes();
                        itensEncontrados++;
                    }
                }
                if (itensEncontrados == 0) {
                    System.out.println("Item não encontrado.");
                }
                Entrada.aguardarEnter();
                menuItem();
                break;
            case 4:
                menuItem();
                break;
        }
        Entrada.aguardarEnter();
    }

    // Importar itens
    private void importarItens() {
        System.out.println("Importando itens do Banco de Dados...");
        verificarItensCarregados(this.bancoDeDados.carregarItens());
        System.out.println("Importando itens do CSV...");
        lerItensCSV();
        System.out.println("Itens importados com sucesso!");
        System.out.println("Retornando ao menu anterior!");
        Entrada.aguardarEnter();
        menuItem();
    }

    // Menu Setor
    private void menuSetor() {
        Entrada.limparTela();
        menu.exibirMenuSetor();
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-3]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Cadastrar novo setor
                cadastrarSetor();
                break;
            case 2:
                // Consultar pedidos
                listarSetores();
                break;
            case 3:
                // Voltar
                menuPrincipal();
                break;
        }
    }

    // Cadastrar novo setor
    private void cadastrarSetor() {
        int id = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor: ", "[0-9]+", "ID inválido"));
        String nome = Entrada.solicitarEntradaValida("Digite o nome do setor: ", "^[a-zA-Z\\s]+$", "Nome inválido");

        Setor novoSetor = new Setor(id, nome);
        this.setores.add(novoSetor);
        this.bancoDeDados.cadastrarSetor(novoSetor);
        System.out.println("Setor cadastrado com sucesso!");
        Entrada.aguardarEnter();
        menuSetor();
    }

    // Listar setores
    private void listarSetores() {
        if (this.setores.isEmpty()) {
            System.out.println("Nenhum setor cadastrado.");
        } else {
            for (Setor setor : this.setores) {
                setor.exibirDetalhes();
            }
        }
        Entrada.aguardarEnter();
        menuSetor();
    }

    // Menu Funcionário
    private void menuFuncionario() {
        Entrada.limparTela();
        menu.exibirMenuFuncionario();
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-4]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Cadastrar funcionário
                cadastrarFuncionario();
                break;
            case 2:
                // Consultar pedidos
                consultarPedidos(5);
                break;
            case 3:
                // Atualizar dados do funcionário
                atualizarFuncionario();
            case 4:
                // Voltar
                menuPrincipal();
                break;
        }
    }

    // Cadastrar funcionário
    private void cadastrarFuncionario() {
        int matricula = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário: ", "[0-9]+", "Matrícula inválida"));
        String nome = Entrada.solicitarEntradaValida("Digite o nome do funcionário: ", "^[a-zA-Z\\s]+$", "Nome inválido");
        int funcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID da função do funcionário: ", "[0-9]+", "ID inválido"));

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAdmissao = LocalDate.parse(Entrada.solicitarEntradaValida("Digite a data de admissão do funcionário (DD/MM/AAAA): ", "\\d{2}/\\d{2}/\\d{4}", "Data inválida"), formatador);
        
        int lojaTrabalho = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID da loja de trabalho do funcionário: ", "[0-9]+", "ID inválido"));
        String tamanhoUniforme = Entrada.solicitarEntradaValida("Digite o tamanho do uniforme do funcionário: ", "^[aA-zZ]$", "Tamanho inválido");
        int setorFuncionario = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor do funcionário: ", "[0-9]+", "ID inválido"));

        Funcionario novoFuncionario = new Funcionario(matricula, nome, setorFuncionario, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme);
        this.funcionarios.add(novoFuncionario);
        this.bancoDeDados.cadastrarFuncionario(novoFuncionario);
        
        for (Setor setor : this.setores) {
            if (setor.getId() == novoFuncionario.getSetor()) {
                setor.adicionarFuncionario(novoFuncionario);
            }
        }
        System.out.println("Funcionário cadastrado com sucesso!");
        Entrada.aguardarEnter();
        menuFuncionario();
    }

    // Atualizar dados do funcionário
    private void atualizarFuncionario() {
        int matricula = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário: ", "[0-9]+", "Matrícula inválida"));
        Funcionario funcionario = this.bancoDeDados.buscarFuncionarioPorMatricula(matricula);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            Entrada.aguardarEnter();
            menuFuncionario();
        }

        menu.exibirMenuFuncionario(funcionario);
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-6]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Atualizar nome do funcionário
                String novoNome = Entrada.solicitarEntradaValida("Digite o novo nome do funcionário: ", "^[a-zA-Z\\s]+$", "Nome inválido");
                if (funcionario != null) {
                    funcionario.setNome(novoNome);
                    this.bancoDeDados.atualizarFuncionario(funcionario);
                    System.out.println("Nome atualizado com sucesso!");
                } else {
                    System.out.println("Funcionário não encontrado.");
                }
                break;
            case 2:
                // Atualizar tamanho do uniforme do funcionário
                String novoTamanho = Entrada.solicitarEntradaValida("Digite o novo tamanho do uniforme do funcionário: ", "^[aA-zZ]$", "Tamanho inválido");
                if (funcionario != null) {
                    funcionario.setTamanhoUniforme(novoTamanho);
                    this.bancoDeDados.atualizarFuncionario(funcionario);
                    System.out.println("Tamanho do uniforme atualizado com sucesso!");
                } else {
                    System.out.println("Funcionário não encontrado.");
                }
                break;
            case 3:
                // Atualizar setor do funcionário
                int novoSetor = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o novo ID do setor do funcionário: ", "[0-9]+", "ID inválido"));
                if (funcionario != null) {
                    funcionario.setSetor(novoSetor);
                    this.bancoDeDados.atualizarFuncionario(funcionario);
                    for (Setor setor : this.setores) {
                        if (setor.getId() == funcionario.getSetor()) {
                            setor.adicionarFuncionario(funcionario);
                        }
                    }
                    System.out.println("Setor atualizado com sucesso!");
                } else {
                    System.out.println("Funcionário não encontrado.");
                }
                break;
            case 4:
                // Atualizar função do funcionário
                int novaFuncao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o novo ID da função do funcionário: ", "[0-9]+", "ID inválido"));
                if (funcionario != null) {
                    funcionario.setFuncao(novaFuncao);
                    this.bancoDeDados.atualizarFuncionario(funcionario);
                    for (Setor setor : this.setores) {
                        if (setor.getId() == funcionario.getSetor()) {
                            setor.adicionarFuncionario(funcionario);
                        }
                    }
                    System.out.println("Função atualizada com sucesso!");
                } else {
                    System.out.println("Funcionário não encontrado.");
                }
                break;
            case 5:
                // Excluir funcionário
                int matriculaExclusao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário: ", "[0-9]+", "Matrícula inválida"));
                
                Funcionario funcionarioExcluir = this.bancoDeDados.buscarFuncionarioPorMatricula(matriculaExclusao);

                if (funcionarioExcluir != null) {
                    this.bancoDeDados.excluirFuncionario(matriculaExclusao);
                    System.out.println("Funcionário excluído com sucesso!");
                } else {
                    System.out.println("Funcionário não encontrado.");
                }
                break;
            case 6:
                // Voltar
                menuFuncionario();
                break;
        }
    }

    // Menu Pedidos
    private void exibirMenuPedido() {
        Entrada.limparTela();
        menu.exibirMenuPedido();
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-3]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Realizar pedido
                realizarPedido();
                break;
            case 2:
                // Consultar pedidos
                consultarPedidos(0);
                break;
            case 3:
                // Atualizar pedido
                atualizarPedido();
                break;
            case 4:
                // Voltar
                menuPrincipal();
                break;
        }
    }

    // Realizar pedido
    private void realizarPedido() {
        Entrada.limparTela();
        // Realizar pedido
        menu.exibirRealizarPedido();
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-4]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Realizar pedido de itens
                Pedido novoPedido = new Pedido(this.pedidos.size() + 1, TipoPedido.ALMOXARIFADO);

                int opcaoItem;
                do {
                    opcaoItem = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do item que deseja adicionar ao pedido: ", "[0-9]+", "ID inválido"));
                    Item item = this.bancoDeDados.buscarItemPorId(opcaoItem);

                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    if (item != null) {
                        ItemPedido itemPedido = new ItemPedido(item.getCodigo(), quantidade, novoPedido.getNumeroPedido(), setorDestino, 0); //
                        novoPedido.adicionarItemPedido(itemPedido);
                    } else {
                        System.out.println("Item não encontrado.");
                    }
                } while (opcaoItem != 0);

                this.pedidos.add(novoPedido);
                this.bancoDeDados.salvarPedido(novoPedido);
                System.out.println("Pedido realizado com sucesso!");
                break;
            case 2:
                // Realizar pedido de planilha
                System.out.println("Realizar pedido de planilha");
                break;
            case 3:
                // Voltar
                exibirMenuPedido();
                break;
        }
    }


    // Consultar pedidos
    private void consultarPedidos(int opcao) {
        Entrada.limparTela();
        // Consultar pedidos
        if (opcao == 0) {
            menu.exibirConsultaPedidos();
            opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-5]", "Opção inválida"));
        }

        List<Pedido> listPedidos;

        switch (opcao) {
            case 1:
                // Consultar todos pedidos
                listPedidos = this.bancoDeDados.listarPedidos();

                if (listPedidos.isEmpty()) {
                    System.out.println("Nenhum pedido encontrado.");
                } else {
                    for (Pedido pedido : listPedidos) {
                            pedido.exibirDetalhesPedido();
                    }
                }
                break;
            case 2:
                // Consultar pedido por ID
                int idPedido = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do pedido: ", "[0-9]+", "ID inválido"));

                Pedido consultaPedido = this.bancoDeDados.buscarPedidoPorNumero(idPedido);
                if (consultaPedido != null) {
                    consultaPedido.exibirDetalhesPedido();
                } else {
                    System.out.println("Pedido não encontrado.");
                }

                break;
            case 3:
                // Consultar pedidos por período
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate dataInicio = LocalDate.parse(Entrada.solicitarEntradaValida("Digite a data de início (DD-MM-AAAA): ", "\\d{2}-\\d{2}-\\d{4}", "Data inválida"), formatoData);
                LocalDate dataFim = LocalDate.parse(Entrada.solicitarEntradaValida("Digite a data de fim (DD-MM-AAAA): ", "\\d{2}-\\d{2}-\\d{4}", "Data inválida"), formatoData);

                listPedidos = this.bancoDeDados.listarPedidosPorPeriodo(dataInicio, dataFim);


                if (listPedidos.isEmpty()) {
                    System.out.println("Nenhum pedido encontrado.");
                } else {
                    for (Pedido pedido : this.pedidos) {
                        if (pedido.getDataPedido().isAfter(dataInicio) && pedido.getDataPedido().isBefore(dataFim)) {
                            pedido.exibirDetalhesPedido();
                        }
                    }
                }
                break;
            case 4:
                // Consultar pedidos por setor
                int idSetor = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor: ", "[0-9]+", "ID inválido"));

                listPedidos = this.bancoDeDados.listarPedidosPorSetor(idSetor);

                if(listPedidos.isEmpty()) {
                    System.out.println("Nenhum pedido encontrado.");
                } else {
                    for (Pedido pedido : listPedidos) {
                        for (ItemPedido itemPedido : pedido.getItensPedido()) {
                            if (itemPedido.getSetorDestino() == idSetor) {
                                pedido.exibirDetalhesPedido();
                            }
                        }
                    }
                }
                break;
            case 5:
                // Consultar pedidos por funcionário
                int matriculaFuncionario = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário: ", "[0-9]+", "Matrícula inválida"));

                listPedidos = this.bancoDeDados.listarPedidosPorFuncionario(matriculaFuncionario);

                if (listPedidos.isEmpty()) {
                    System.out.println("Nenhum pedido encontrado.");
                } else {
                    for (Pedido pedido : listPedidos) {
                        for (ItemPedido itemPedido : pedido.getItensPedido()) {
                            if (itemPedido.getFuncionarioDestino() == matriculaFuncionario) {
                                pedido.exibirDetalhesPedido();
                            }
                        }
                    }
                }
                break;
            case 6:
                menuSetor();
                break;
        }

        Entrada.aguardarEnter();
    }

    // Atualizar pedido
    private void atualizarPedido() {
        int idPedido = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do pedido: ", "[0-9]+", "ID inválido"));
        Pedido pedido = this.bancoDeDados.buscarPedidoPorNumero(idPedido);

        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            Entrada.aguardarEnter();
            exibirMenuPedido();
        }

        menu.exibirMenuPedido(pedido);
        int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a opção desejada: ", "[1-4]", "Opção inválida"));

        switch (opcao) {
            case 1:
                // Adicionar item ao pedido
                int novoItemId = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do novo item: ", "[0-9]+", "ID inválido"));
                
                Item novoItem = this.bancoDeDados.buscarItemPorId(novoItemId);
                if (pedido != null && pedido.getTipoPedido() == TipoPedido.ALMOXARIFADO) {
                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(novoItem.getCodigo(), quantidade, pedido.getNumeroPedido(), setorDestino, 0);
                    this.bancoDeDados.cadastrarItemPedido(itemPedido);
                    pedido.adicionarItemPedido(itemPedido);
                    break;

                } else if(pedido != null && pedido.getTipoPedido() == TipoPedido.UNIFORME) {
                    int funcionarioDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(novoItem.getCodigo(), quantidade, pedido.getNumeroPedido(), 0, funcionarioDestino);
                    this.bancoDeDados.cadastrarItemPedido(itemPedido);
                    pedido.adicionarItemPedido(itemPedido);

                    break;
                } else if(pedido != null && pedido.getTipoPedido() == TipoPedido.EPI) {
                    int funcionarioDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário de destino do item: ", "[0-9]+", "ID inválido"));
                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(novoItem.getCodigo(), quantidade, pedido.getNumeroPedido(), setorDestino, funcionarioDestino);
                    
                    this.bancoDeDados.cadastrarItemPedido(itemPedido);
                    // atualizar o ItemPedido do pedido
                    pedido.adicionarItemPedido(itemPedido);

                    break;
                } else {
                    System.out.println("Pedido não encontrado.");
                }
            case 2:
                // Remover item do pedido
                int idItemRemover = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do item a ser removido: ", "[0-9]+", "ID inválido"));

                Item itemRemover = this.bancoDeDados.buscarItemPorId(idItemRemover);
                if (pedido != null && pedido.getTipoPedido() == TipoPedido.ALMOXARIFADO) {
                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(itemRemover.getCodigo(), quantidade, pedido.getNumeroPedido(), setorDestino, 0);

                    this.bancoDeDados.excluirItemPedido(itemPedido);
                    pedido.removerItemPedido(itemPedido);

                    break;

                } else if (pedido != null && pedido.getTipoPedido() == TipoPedido.UNIFORME) {
                    int funcionarioDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(itemRemover.getCodigo(), quantidade, pedido.getNumeroPedido(), 0, funcionarioDestino);

                    this.bancoDeDados.excluirItemPedido(itemPedido);
                    pedido.removerItemPedido(itemPedido);

                    break;
                } else if (pedido != null && pedido.getTipoPedido() == TipoPedido.EPI) {
                    int funcionarioDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário de destino do item: ", "[0-9]+", "ID inválido"));
                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(itemRemover.getCodigo(), quantidade, pedido.getNumeroPedido(), setorDestino, funcionarioDestino);
                    
                    this.bancoDeDados.excluirItemPedido(itemPedido);
                    // Remover o ItemPedido do pedido
                    pedido.removerItemPedido(itemPedido);

                    break;
                } else {
                    System.out.println("Pedido não encontrado.");
                }
                
                break;
            case 3:
                // Atualizar setor de destino do pedido
                int itemAtualizar = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o novo ID do item que deseja atualizar: ", "[0-9]+", "ID inválido"));

                if (pedido != null && pedido.getTipoPedido() == TipoPedido.ALMOXARIFADO) {
                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));

                    ItemPedido itemPedido = new ItemPedido(itemAtualizar, quantidade, pedido.getNumeroPedido(), setorDestino, 0);

                    this.bancoDeDados.atualizarItemPedido(itemPedido);
                    pedido.atualizarItemPedido(itemPedido);

                    break;

                } else if (pedido != null && pedido.getTipoPedido() == TipoPedido.UNIFORME) {
                    int funcionarioDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(itemAtualizar, quantidade, pedido.getNumeroPedido(), 0, funcionarioDestino);

                    this.bancoDeDados.atualizarItemPedido(itemPedido);
                    pedido.atualizarItemPedido(itemPedido);

                    break;
                } else if (pedido != null && pedido.getTipoPedido() == TipoPedido.EPI) {
                    int funcionarioDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula do funcionário de destino do item: ", "[0-9]+", "ID inválido"));
                    int setorDestino = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID do setor de destino do item: ", "[0-9]+", "ID inválido"));
                    int quantidade = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a quantidade do item: ", "[0-9]+", "Quantidade inválida"));
                    ItemPedido itemPedido = new ItemPedido(itemAtualizar, quantidade, pedido.getNumeroPedido(), setorDestino, funcionarioDestino);

                    this.bancoDeDados.atualizarItemPedido(itemPedido);
                    pedido.atualizarItemPedido(itemPedido);

                    break;
                } else {
                    System.out.println("Pedido não encontrado.");
                }
                break;
            case 4:
                // Voltar
                exibirMenuPedido();
                break;
        }

    }

    // Alterar senha
    private void alterarSenha() {
        String novaSenha = Entrada.solicitarSenha();
        this.administradorLogado.setSenha(novaSenha);
        this.bancoDeDados.atualizarSenhaAdministrador(this.administradorLogado);
        System.out.println("Senha alterada com sucesso!");
        Entrada.aguardarEnter();
        menuPrincipal();
    }

    // Fechar o programa
    private void logoutUsuario() {
        Entrada.limparTela();
        menu.exibirMenuSair();
        if (Entrada.solicitarEntradaValida("Digite a opção desejada: ", "^[1-2]$", "Opção inválida").equals("1")) {
            System.out.println("Deslogando...");
            this.administradorLogado = null;
        } else {
            menuPrincipal();
        }
    }
    
    // Realizar login
    public void realizarLogin() {
        // Solicita a matrícula e a senha do administrador
        int matricula = Integer.parseInt(Entrada.solicitarEntradaValida("Digite a matrícula: ", "[0-9]+", "Matrícula inválida"));
        String senha = Entrada.solicitarSenha();

        // Autenticar o administrador
        Administrador admin = this.sistemaDeLogin.autenticar(matricula, senha);

        if (admin != null) {
            // Se o login for bem-sucedido, exibe o menu principal
            this.administradorLogado = admin;
            this.getMenu().exibirMenuPrincipal();
        } else {
            // Se o login falhar, exibe uma mensagem de erro
            System.out.println("Login ou senha inválidos. Tente novamente.");
        }
    }

    // Métodos
    private void carregarDadosCSV() {
        // Carregar dados do CSV

        // Carregar e tratar carregamento de itens
        lerItensCSV();

        // Carregar e tratar carregamento de setores
        lerSetoresCSV();

        // Carregar e tratar carregamento de funções
        lerFuncoesCSV();

        // Carregar e tratar carregamento de funcionários
        lerFuncionariosCSV();

        // Carregar e tratar carregamento de pedidos
        lerPedidosCSV();
    }

    private void lerItensCSV() {
        // Carregar e tratar carregamento de itens
        String caminhoItens = "src/Data/itens.csv";
        List<String> dadosItensStrings = this.csvHandler.lerCSV(caminhoItens);

        if (dadosItensStrings == null) {
            System.out.println("Nenhum novo item adicionado.");
            return;
        }

        List<Item> itensCarregados = new ArrayList<>();

        for (String linha : dadosItensStrings) {
            String[] dados = linha.split(",");
            int id = Integer.parseInt(dados[0]);
            String nome = dados[1];
            if (dados[2].equalsIgnoreCase("Almoxarifado")) {
                Almoxarifado novoAlmoxarifado = new Almoxarifado(id, nome);
                itensCarregados.add(novoAlmoxarifado);
            } else if (dados[2].equalsIgnoreCase("Uniforme")) {
                int setor = Integer.parseInt(dados[3]);
                String tamanho = dados[4];
                Uniforme novoUniforme = new Uniforme(id, nome, setor, tamanho);
                itensCarregados.add(novoUniforme);
            } else if (dados[2].equalsIgnoreCase("EPI") && dados.length == 6) {
                int setor = Integer.parseInt(dados[3]);
                String tamanho = dados[4];
                String ca = dados[5];
                EPI novoEPI = new EPI(id, nome, setor, tamanho, ca);
                itensCarregados.add(novoEPI);
            }
        }

        // Verifica se os itens carregados já existem na lista de itens
        verificarItensCarregados(itensCarregados);
    }

    private void verificarItensCarregados(List<Item> itensCarregados) {
        for (Item novoItem : itensCarregados) {
            // Se não existir, adiciona à lista
            if (!this.itens.contains(novoItem)) {
                this.adicionarItem(novoItem);
            }
        }
    }

    private void lerSetoresCSV() {
        String caminhoSetores = "src/Data/setores.csv";
        List<String> dadosSetoresStrings = this.csvHandler.lerCSV(caminhoSetores);

        if (dadosSetoresStrings == null) {
            System.out.println("Nenhum novo setor adicionado.");
            return;
        }

        List<Setor> setoresCarregados = new ArrayList<>();

        for (String linha : dadosSetoresStrings) {
            String[] dados = linha.split(",");
            int id = Integer.parseInt(dados[0]);
            String nome = dados[1];
            Setor novoSetor = new Setor(id, nome);
            setoresCarregados.add(novoSetor);
        }

        // Verifica se os setores carregados já existem na lista de setores
        for (Setor novoSetor : setoresCarregados) {
            // Se não existir, adiciona à lista
            if (!this.setores.contains(novoSetor)) {
                this.setores.add(novoSetor);
            }
        }
    }

    private void lerFuncoesCSV() {
        String caminhoFuncoes = "src/Data/funcoes.csv";
        List<String> dadosFuncoesStrings = this.csvHandler.lerCSV(caminhoFuncoes);

        if (dadosFuncoesStrings == null) {
            System.out.println("Nenhuma nova função adicionada.");
            return;
        }

        List<Funcoes> funcoesCarregadas = new ArrayList<>();

        for (String linha : dadosFuncoesStrings) {
            String[] dados = linha.split(",");
            int id = Integer.parseInt(dados[0]);
            String nome = dados[1];
            Funcoes novaFuncao = new Funcoes(id, nome);
            funcoesCarregadas.add(novaFuncao);
        }

        // Verifica se as funções carregadas já existem na lista de funções
        for (Funcoes novaFuncao : funcoesCarregadas) {
            // Se não existir, adiciona à lista
            if (!this.funcoes.contains(novaFuncao)) {
                this.funcoes.add(novaFuncao);
            }
        }
    }

    private void lerFuncionariosCSV() {
        String caminhoFuncionarios = "src/Data/funcionarios.csv";
        List<String> dadosFuncionariosStrings = this.csvHandler.lerCSV(caminhoFuncionarios);

        if (dadosFuncionariosStrings == null) {
            System.out.println("Nenhum novo funcionário adicionado.");
            return;
        }

        List<Funcionario> funcionariosCarregados = new ArrayList<>();

        for (String linha : dadosFuncionariosStrings) {
            String[] dados = linha.split(",");
            int matricula = Integer.parseInt(dados[0]);
            String nome = dados[1];
            int funcao = Integer.parseInt(dados[2]);
            String dataAdmissaoString = dados[3];
            LocalDate dataAdmissao = LocalDate.parse(dataAdmissaoString);
            int lojaTrabalho = Integer.parseInt(dados[4]);
            String tamanhoUniforme = dados[5];
            int setor = Integer.parseInt(dados[6]);
            boolean ativo = Boolean.parseBoolean(dados[7]);
            List<Integer> pedidosFuncionario = new ArrayList<>();
            if (dados.length > 8) {
                for (int i = 8; i < dados.length; i++) {
                    pedidosFuncionario.add(Integer.valueOf(dados[i]));
                }
            }
            Funcionario novoFuncionario = new Funcionario(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidosFuncionario, ativo);
            funcionariosCarregados.add(novoFuncionario);
        }

        // Adiciona os funcionários existentes em um mapa
        Map<Integer, Funcionario> funcionariosMap = new HashMap<>();
        for (Funcionario funcionario : this.funcionarios) {
            funcionariosMap.put(funcionario.getMatricula(), funcionario);
        }

        for (Funcionario novoFuncionario : funcionariosCarregados) {
            // Busca no mapa o novo funcionário pelo número de matrícula
            Funcionario funcionarioExistente = funcionariosMap.get(novoFuncionario.getMatricula());
            // Verifica se o funcionário já existe
            if (funcionarioExistente != null) {
                // Se existir, atualiza os pedidos
                for (int pedidoId : novoFuncionario.getPedidos()) {
                    if (!funcionarioExistente.getPedidos().contains(pedidoId)) {
                        funcionarioExistente.getPedidos().add(pedidoId);
                    }
                }
            } else {
                // Se não existir, adiciona à lista
                this.funcionarios.add(novoFuncionario);
            }
        }
    }

    private void lerPedidosCSV() {
        String caminhoPedidos = "src/Data/pedidos.csv";
        List<String> dadosPedidosStrings = this.csvHandler.lerCSV(caminhoPedidos);

        if (dadosPedidosStrings == null) {
            System.out.println("Nenhum novo pedido adicionado.");
            return;
        }

        Map<Integer, Pedido> mapaPedidos = new HashMap<>();

        for (String linha : dadosPedidosStrings) {
            String[] dados = linha.split(",");
            int numeroPedido = Integer.parseInt(dados[0]);
            TipoPedido tipoPedido = TipoPedido.valueOf(dados[1].toUpperCase());
            LocalDate dataPedido = LocalDate.parse(dados[2]);
            int itemId = Integer.parseInt(dados[3]);
            int quantidade = Integer.parseInt(dados[4]);
            int setorDestino = Integer.parseInt(dados[5]);
            int funcionarioDestino = Integer.parseInt(dados[6]);

            // Verifica se o pedido já está no mapa, se não estiver, cria um novo
            Pedido pedido = mapaPedidos.get(numeroPedido);
            if (pedido == null) {
                pedido = new Pedido(numeroPedido, tipoPedido, dataPedido);
                mapaPedidos.put(numeroPedido, pedido);
            }

            // Adiciona o item ao pedido
            ItemPedido itemPedido = new ItemPedido(itemId, quantidade, numeroPedido, setorDestino, funcionarioDestino);
            pedido.adicionarItemPedido(itemPedido);
        }

        // Mapeia os pedidos já existentes
        Map<Integer, Pedido> pedidosExistentes = new HashMap<>();
        for (Pedido pedido : this.pedidos) {
            pedidosExistentes.put(pedido.getNumeroPedido(), pedido);
        }

        // Atualiza os pedidos existentes ou adiciona novos
        for (Pedido novoPedido : mapaPedidos.values()) {
            Pedido pedidoExistente = pedidosExistentes.get(novoPedido.getNumeroPedido());

            if (pedidoExistente != null) {
                for (ItemPedido itemPedido : novoPedido.getItensPedido()) {
                    if (!pedidoExistente.getItensPedido().contains(itemPedido)) {
                        pedidoExistente.adicionarItemPedido(itemPedido);
                        this.bancoDeDados.cadastrarItemPedido(itemPedido);
                    }
                }
            } else {
                this.pedidos.add(novoPedido);
                this.bancoDeDados.salvarPedido(novoPedido);
            }
        }
    }

}
