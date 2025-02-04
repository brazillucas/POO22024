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
