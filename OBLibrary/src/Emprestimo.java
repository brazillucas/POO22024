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
import java.time.format.DateTimeFormatter;


public class Emprestimo {
    private Usuario usuario;
    private ObraLiteraria obra;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoRealizada;
    private boolean emprestimoAtivo;
    private boolean atrasado;

    // Construtor para empréstimos realizados no sistema
    public Emprestimo(Usuario usuario, ObraLiteraria obra) {
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = LocalDate.now().plusDays(usuario.getTipoUsuario() == 1 ? 7 : 14);
        this.emprestimoAtivo = true;
        this.atrasado = false;
    }
    
    // Construtor para carregamento de empréstimos finalizado a partir de arquivo
    public Emprestimo(Usuario usuario, ObraLiteraria obra, LocalDate dataEmprestimo, LocalDate dataDevolucaoRealizada) {
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(usuario.getTipoUsuario() == 1 ? 7 : 14);
        this.dataDevolucaoRealizada = dataDevolucaoRealizada;
        if (dataDevolucaoRealizada != null) {
            this.atrasado = dataDevolucaoPrevista.isBefore(dataDevolucaoRealizada);
            this.emprestimoAtivo = false;
        } else {
            this.atrasado = LocalDate.now().isAfter(dataDevolucaoPrevista);
            this.emprestimoAtivo = true;
        }
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

    public boolean getAtrasado() {
        return atrasado;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDevolucaoRealizada() {
        this.dataDevolucaoRealizada = LocalDate.now();
        if (verificarAtraso()) {
            this.atrasado = true;
        }
        this.emprestimoAtivo = false;
        this.obra.atualizarQuantidade(1);
    }

    public LocalDate getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }

    public boolean getEmprestimoAtivo() {
        return emprestimoAtivo;
    }

    public int getDiasAtraso() {
        return  (int) (LocalDate.now().toEpochDay() - this.dataDevolucaoPrevista.toEpochDay() );
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario:" + usuario +
                " | obra:" + obra +
                " | dataEmprestimo:" + dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " | dataDevolucaoPrevista:" + dataDevolucaoPrevista.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " | atrasado=" + atrasado +
                '}';
    }

}
