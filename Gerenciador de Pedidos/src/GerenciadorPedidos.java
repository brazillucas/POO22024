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
    }

    public GerenciadorPedidos (Administrador admin) {
        this.administradorLogado = admin;
        this.sistemaDeLogin = new SistemaDeLogin();
        this.bancoDeDados = new BancoDeDados();
        this.csvHandler = new CSVHandler();
        this.planilhaHandler = new PlanilhaHandler();
        this.menu = new Menu();
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

        // Carregar dados do CSV
        System.out.println("Carregando dados do CSV...");
        carregarDadosCSV();

        // Exibe o menu principal
        System.out.println("Exibindo o menu principal...");
        menu.exibirMenuIncial();

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
            List<Integer> pedidosFuncionario = new ArrayList<>();
            if (dados.length > 7) {
                for (int i = 7; i < dados.length; i++) {
                    pedidosFuncionario.add(Integer.valueOf(dados[i]));
                }
            }
            Funcionario novoFuncionario = new Funcionario(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidosFuncionario);
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
                    }
                }
            } else {
                this.pedidos.add(novoPedido);
            }
        }
    }


}
