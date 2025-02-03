/**
 * 5. Classe Bibliotecario (Herda de Usuario)
    Responsabilidade: Representa um bibliotecário responsável por operações administrativas.

    Atributos Adicionais:
    telefone (String): Telefone do bibliotecário.
    devolucoesRealizadas (int): Número total de devoluções realizadas pelo bibliotecário.
    Métodos:
    cadastrarUsuario(Usuario usuario): Cadastra novos usuários no sistema.
    registrarDevolucao(Usuario usuario, ObraLiteraria obra): Registra a devolução de uma obra e desbloqueia o usuário, se necessário.
    gerarRelatorios(): Gera relatórios de obras emprestadas e usuários com atrasos.
 */

public class Bibliotecario extends Usuario {
    private String telefone;
    private int devolucoesRealizadas;

    public Bibliotecario(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, 3);
        this.telefone = telefone;
        this.devolucoesRealizadas = 0;
    }

    public Bibliotecario(String nome, String email, String senha, String telefone, int devolucoesRealizadas) {
        super(nome, email, senha, 3);
        this.telefone = telefone;
        this.devolucoesRealizadas = devolucoesRealizadas;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getDevolucoesRealizadas() {
        return devolucoesRealizadas;
    }

    public void cadastrarUsuario(Usuario usuario, Biblioteca biblioteca) {
        // Código para cadastrar um novo usuário no sistema
        System.out.println("=== CADASTRO DE NOVO USUÁRIO ===");
        String novoNome = Entrada.solicitarEntradaValida("Digite o nome do usuário: ", "^[aAzZ]", "Nome Inválido");
        String novoEmail = Entrada.solicitarEmail();
        String novaSenha = Entrada.solicitarSenha();
        int tipoNovoUsuario = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o tipo de usuário \n1 - Aluno\n2 - Professor\n3 - Bibliotecario): ", "^[1-2]$", "Tipo de Usuário Inválido"));

        if (tipoNovoUsuario == 1) {
            String matricula = Entrada.solicitarEntradaValida("Digite a matrícula do aluno: ", "^[0-9]{6}$", "Matrícula Inválida");

            String curso = Entrada.solicitarEntradaValida("Digite o curso do aluno: ", "^[a-zA-Z ]+$", "Curso Inválido");

            usuario = new Aluno(novoNome, novoEmail, novaSenha, matricula, curso);
        } else if (tipoNovoUsuario == 2) {
            String departamento = Entrada.solicitarEntradaValida("Digite o departamento do professor: ", "^[a-zA-Z ]+$", "Departamento Inválido");

            usuario = new Professor(novoNome, novoEmail, novaSenha, departamento);
        } else if(tipoNovoUsuario == 3) {
            String novoTel = Entrada.solicitarEntradaValida("Digite o telefone do bibliotecário: ", "^[0-9]{8,9}$", "Telefone Inválido");

            usuario = new Bibliotecario(novoNome, novoEmail, novaSenha, novoTel);
        } else {
            System.out.println("Tipo de usuário inválido.");
        }

        biblioteca.cadastrarUsuario(usuario);
    }

    public void registrarDevolucao(Usuario usuario, ObraLiteraria obra) {
        usuario.realizarDevolucao(obra);
        if (usuario.verificarBloqueio()) {
            usuario.setBloqueado(true);
        }
        devolucoesRealizadas++;
    }

    public void gerarRelatorios(Biblioteca biblioteca) {
        System.out.println("=== RELATÓRIO DE OBRAS EMPRESTADAS ===");
        biblioteca.gerarRelatorioObrasEmprestadas();
        System.out.println("=== RELATÓRIO DE USUÁRIOS COM ATRASO ===");
        biblioteca.gerarRelatorioUsuariosComAtraso();
    }

}
