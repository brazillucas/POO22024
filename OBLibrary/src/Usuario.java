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
    private List<ObraLiteraria> emprestimosAtivos;
    private boolean bloqueado;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public abstract void realizarEmprestimo(ObraLiteraria obra);

    public abstract void realizarDevolucao(ObraLiteraria obra);

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<ObraLiteraria> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }


}
