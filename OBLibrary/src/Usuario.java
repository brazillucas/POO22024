import java.util.ArrayList;
import java.util.List;

/**
 * 2. Classe Usuario (Classe Abstrata)
    Responsabilidade: Representa um usuário genérico do sistema. Será usada como base para as subclasses específicas.

    Atributos:
    nome (String): Nome do usuário.
    email (String): E-mail do usuário.
    senha (String): Senha do usuário.
    emprestimosAtivos (List<ObraLiteraria>): Lista de obras atualmente emprestadas ao usuário.
    bloqueado (boolean): Indica se o usuário está bloqueado por atraso.
    
    Métodos:
    login(String email, String senha): Verifica se o login é válido.
    logout(): Realiza o logout do usuário.
    verificarBloqueio(): Verifica se o usuário está bloqueado.
    realizarEmprestimo(ObraLiteraria obra): Método abstrato para realizar empréstimos.
    realizarDevolucao(ObraLiteraria obra): Método abstrato para realizar devoluções.
 */

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private int TipoUsuario = 0;
    private List<Emprestimo> emprestimosAtivos;
    private boolean bloqueado;

    public Usuario(String nome, String email, String senha, int TipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.TipoUsuario = TipoUsuario;
        this.emprestimosAtivos = new ArrayList<>();
        this.bloqueado = false;
    }

    public boolean login(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void logout() {
    
    }

    public boolean verificarBloqueio() {
        return this.bloqueado;
    }

    public void realizarDevolucao(ObraLiteraria obra) {
        for (Emprestimo emprestimo : this.emprestimosAtivos) {
            if (emprestimo.getObra().equals(obra)) {
                emprestimo.setDevolucaoRealizada();
                obra.atualizarQuantidade(1);
                System.out.printf("A obra %s não está mais emprestada para %s.\n", emprestimo.getUsuario().getNome(), obra.getNome(), this.nome);
                return;
            }
        }

        System.out.printf("%s não possui a obra %s emprestada.\n", this.nome, obra.getTitulo());
    }

    public void realizarEmprestimo(ObraLiteraria obra) {
        if (!validacaoEmprestimo(obra)) {
            return;
        }
        Emprestimo novoEmprestimo = new Emprestimo(this, obra);
        this.emprestimosAtivos.add(novoEmprestimo);
        System.out.println("Empréstimo realizado com sucesso.");
        System.out.printf("Novo livro emprestado: %s\n", obra.getTitulo());
        obra.atualizarQuantidade(-1);
    }

    public boolean validacaoEmprestimo(ObraLiteraria obra) {
        if (this.bloqueado) {
            System.out.printf("%s está bloqueado por atraso.\n", this.nome);
            return false;
        }

        for (Emprestimo emprestimo : this.emprestimosAtivos) {
            if (emprestimo.getObra().equals(obra)) {
                System.out.printf("%s já possui a obra %s emprestada.\n", this.nome, obra.getTitulo());
                return false;
            }
        }

        if (obra.getQuantidadeDisponivel() == 0) {
            System.out.printf("A obra %s não está disponível para empréstimo.\n", obra.getTitulo());
            return false;
        }

        if (this.TipoUsuario == 1 && this.emprestimosAtivos.size() >= 2) {
            System.out.printf("%s já possui 2 obras emprestadas.\n", this.nome);
            return false;
        }

        if (this.TipoUsuario == 2 && this.emprestimosAtivos.size() >= 10) {
            System.out.printf("%s já possui 10 obras emprestadas.\n", this.nome);
            return false;
        }

        return true;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public int getTipoUsuario() {
        return this.TipoUsuario;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return this.emprestimosAtivos;
    }

    public void exibirObrasEmprestadas() {
        boolean emprestimosAbertos = false;
        for (Emprestimo emprestimo : this.emprestimosAtivos) {
            if (emprestimo.getDataDevolucaoRealizada() == null) {
                emprestimosAbertos = true;
            }
        }

        if (this.emprestimosAtivos.isEmpty() || !emprestimosAbertos) {
            System.out.printf("%s não possui obras emprestadas.\n", this.nome);
            return;
        }
        

        System.out.printf("Obras emprestadas para %s:\n", this.nome);
        for (Emprestimo emprestimo : this.emprestimosAtivos) {
            if (emprestimo.getDataDevolucaoRealizada() == null) {
                System.out.printf("%d | Título: %s\n", emprestimo.getObra().getId(), emprestimo.getObra().getTitulo());
            }
        }
    }

    public boolean isBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }


}
