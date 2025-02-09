/*
 * Setor
 * Atributos:
 * nome
 * funcionarios (matricula dos funcionarios | lista de funcionários pertencentes ao setor)
 * pedidos (número dos pedidos | lista de pedidos direcionados ao setor)
 * Métodos:
 * adicionarFuncionario(Funcionario funcionario)
 * listarPedidos()
 */

import java.util.List;

public class Setor {
    private String nome;
    private List<Integer> funcionarios;
    private List<Integer> pedidos;

    public Setor(String nome, List<Integer> funcionarios, List<Integer> pedidos) {
        this.nome = nome;
        this.funcionarios = funcionarios;
        this.pedidos = pedidos;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Integer> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Integer> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Integer> pedidos) {
        this.pedidos = pedidos;
    }

    // Métodos
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario.getMatricula());
    }

    public void listarPedidos() {
        for (int pedido : pedidos) {
            System.out.println("Pedido: " + pedido);
        }
    }
}
