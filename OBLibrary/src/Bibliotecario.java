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

    public int getDevolucoesRealizadas() {
        return devolucoesRealizadas;
    }

    public void incrementaDevolucoesRealizadas() {
        this.devolucoesRealizadas++;
    }

    public void cadastrarUsuario(Biblioteca biblioteca) {
        // Código para cadastrar um novo usuário no sistema
        System.out.println("=== CADASTRO DE NOVO USUÁRIO ===");
        String novoNome = Entrada.solicitarEntradaValida("Digite o nome do usuário: ", "^[a-zA-Z ]+$", "Nome Inválido");
        String novoEmail = Entrada.solicitarEmail();
        String novaSenha = Entrada.solicitarSenha();
        int tipoNovoUsuario = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o tipo de usuário \n1 - Aluno\n2 - Professor\n3 - Bibliotecario: ", "^[1-2]$", "Tipo de Usuário Inválido"));

        Usuario usuario = null;

        switch (tipoNovoUsuario) {
            case 1:
                String matricula = Entrada.solicitarEntradaValida("Digite a matrícula do aluno: ", "^[0-9]{6}$", "Matrícula Inválida");
                String curso = Entrada.solicitarEntradaValida("Digite o curso do aluno: ", "^[a-zA-Z ]+$", "Curso Inválido");
                usuario = new Aluno(novoNome, novoEmail, novaSenha, matricula, curso);
                break;
            case 2:
                String departamento = Entrada.solicitarEntradaValida("Digite o departamento do professor: ", "^[a-zA-Z ]+$", "Departamento Inválido");
                usuario = new Professor(novoNome, novoEmail, novaSenha, departamento);
                break;
            case 3:
                String novoTel = Entrada.solicitarEntradaValida("Digite o telefone do bibliotecário: ", "^[0-9]{8,9}$", "Telefone Inválido");
                usuario = new Bibliotecario(novoNome, novoEmail, novaSenha, novoTel);
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
                break;
        }

        biblioteca.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
        biblioteca.salvarUsuariosArquivo();
    }

    public void gerarRelatorios(Biblioteca biblioteca) {
        if (biblioteca.getEmprestimosAtivos()) {
            System.out.println("=== RELATÓRIO DE OBRAS EMPRESTADAS ===");
            biblioteca.gerarRelatorioObrasEmprestadas();
        } else {
            System.out.println("Nenhuma obra emprestada no momento.");
        }

        if (biblioteca.getUsuariosComAtraso()) {
            System.out.println("=== RELATÓRIO DE USUÁRIOS COM ATRASO ===");
            biblioteca.gerarRelatorioUsuariosComAtraso();
        } else {
            System.out.println("Nenhum usuário com atraso no momento.");
        }
    }


}
