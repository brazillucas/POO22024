import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private int TipoUsuario = 0;
    private List<Emprestimo> emprestimosRealizados;
    private boolean bloqueado;

    public Usuario(String nome, String email, String senha, int TipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.TipoUsuario = TipoUsuario;
        this.emprestimosRealizados = new ArrayList<>();
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

    public boolean validacaoEmprestimo(ObraLiteraria obra) {
        if (this.verificarBloqueio()) {
            System.out.printf("%s está bloqueado por atraso.\n", this.nome);
            return false;
        }

        for (Emprestimo emprestimo : this.emprestimosRealizados) {
            if (emprestimo.getObra().equals(obra) && emprestimo.getEmprestimoAtivo()) {
                System.out.printf("%s já possui a obra %s emprestada.\n", this.nome, obra.getTitulo());
                return false;
            }
            if (emprestimo.getDataDevolucaoPrevista().isBefore(LocalDate.now()) && emprestimo.getEmprestimoAtivo()) {
                System.out.printf("%s possui a obra %s emprestada em atraso.\n", this.nome, obra.getTitulo());
                this.setBloqueado(true);
                return false;
            }
        }

        if (obra.getQuantidadeDisponivel() == 0) {
            System.out.printf("A obra %s não está disponível para empréstimo.\n", obra.getTitulo());
            return false;
        }

        return verificarLimiteEmprestimos();
    }

    public boolean verificarLimiteEmprestimos() {
        int empAtivos = 0;
        for (Emprestimo emprestimo : this.emprestimosRealizados) {
            if (emprestimo.getEmprestimoAtivo()) {
                empAtivos++;
            }
        }
        if (this.TipoUsuario == 1 && empAtivos >= 2) {
            System.out.printf("%s já possui 2 obras emprestadas.\n", this.nome);
            return false;
        }

        if (this.TipoUsuario == 2 && empAtivos >= 10) {
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

    public List<Emprestimo> getEmprestimosRealizados() {
        return this.emprestimosRealizados;
    }

    public void exibirObrasEmprestadas() {
        boolean emprestimosAbertos = false;
        for (Emprestimo emprestimo : this.emprestimosRealizados) {
            if (emprestimo.getEmprestimoAtivo()) {
                emprestimosAbertos = true;
            }
        }

        if (this.emprestimosRealizados.isEmpty() || !emprestimosAbertos) {
            System.out.printf("%s não possui obras emprestadas.\n", this.nome);
            return;
        }
        

        System.out.printf("Obras emprestadas para %s:\n", this.nome);
        for (Emprestimo emprestimo : this.emprestimosRealizados) {
            if (emprestimo.getDataDevolucaoRealizada() == null) {
                System.out.printf("%2d | Título: %s\n", emprestimo.getObra().getId(), emprestimo.getObra().getTitulo());
            }
        }
    }

    public boolean estaBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

}
