

/**
 * 4. Classe Professor (Herda de Usuario)
    Responsabilidade: Representa um professor que pode realizar empréstimos.

    Atributos Adicionais:
    departamento (String): Departamento ao qual o professor está vinculado.
    Métodos:
    realizarEmprestimo(ObraLiteraria obra): Permite até 10 empréstimos simultâneos.
    realizarDevolucao(ObraLiteraria obra): Registra a devolução de uma obra.
    */
public class Professor extends Usuario{
    private String departamento;

    public Professor(String nome, String email, String senha, String departamento) {
        super(nome, email, senha, 2);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }
}
