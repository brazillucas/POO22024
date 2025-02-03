/**
 * 3. Classe Aluno (Herda de Usuario)
    Responsabilidade: Representa um aluno que pode realizar empréstimos.

    Atributos Adicionais:
    matricula (String): Matrícula do aluno.
    curso (String): Curso ao qual o aluno está vinculado.
    Métodos:
    realizarEmprestimo(ObraLiteraria obra): Permite até 2 empréstimos simultâneos.
    realizarDevolucao(ObraLiteraria obra): Registra a devolução de uma obra.
 */

public class Aluno extends Usuario {
    private String matricula;
    private String curso;

    public Aluno(String nome, String email, String senha, String matricula, String curso) {
        super(nome, email, senha, 1);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }
}
