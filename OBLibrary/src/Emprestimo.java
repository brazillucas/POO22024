/**
 * 6. Classe Emprestimo
    Responsabilidade: Representa um empréstimo realizado por um usuário.

    Atributos:
    usuario (Usuario): Usuário que realizou o empréstimo.
    obra (ObraLiteraria): Obra emprestada.
    dataEmprestimo (LocalDate): Data do empréstimo.
    dataDevolucaoPrevista (LocalDate): Data prevista para devolução.
    dataDevolucaoRealizada (LocalDate): Data real da devolução (opcional).
    Métodos:
    verificarAtraso(): Verifica se o empréstimo está em atraso.
 */

import java.time.LocalDate;


public class Emprestimo {
    private Usuario usuario;
    private ObraLiteraria obra;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoRealizada;
    private boolean atrasado;

    // Construtor para empréstimos realizados no sistema
    public Emprestimo(Usuario usuario, ObraLiteraria obra) {
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = LocalDate.now().plusDays(usuario.getTipoUsuario() == 1 ? 7 : 14);
    }
    // Construtor para carregamento de empréstimos abertos a partir de arquivo
    public Emprestimo(Usuario usuario, ObraLiteraria obra, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = LocalDate.now().plusDays(usuario.getTipoUsuario() == 1 ? 7 : 14);
    }

    // Construtor para carregamento de empréstimos finalizado a partir de arquivo
    public Emprestimo(Usuario usuario, ObraLiteraria obra, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    // Construtor para carregamento de empréstimos finalizado a partir de arquivo
    public Emprestimo(Usuario usuario, ObraLiteraria obra, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoRealizada) {
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoRealizada = dataDevolucaoRealizada;
    }

    public boolean verificarAtraso() {
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(dataDevolucaoPrevista);
    }

    public ObraLiteraria getObra() {
        return obra;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDevolucaoRealizada() {
        this.dataDevolucaoRealizada = LocalDate.now();
        if (verificarAtraso()) {
            this.atrasado = true;
        }
    }

    public LocalDate getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }

}
