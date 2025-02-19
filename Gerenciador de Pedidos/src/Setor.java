/*
 * Setor
 * Atributos:
 * id
 * nome
 * funcionarios (matricula dos funcionarios | lista de funcionários pertencentes ao setor)
 * pedidos (número dos pedidos | lista de pedidos direcionados ao setor)
 * Métodos:
 * adicionarFuncionario(Funcionario funcionario)
 * listarPedidos()
 */

import java.util.List;

public class Setor {
    // Atributos
    private int id;
    private String nome;
    private List<Integer> funcionarios;
    private List<Integer> pedidos;

    // Construtores
    public Setor(int id, String nome, List<Integer> funcionarios, List<Integer> pedidos) {
        this.id = id;
        this.nome = nome;
        this.funcionarios = funcionarios;
        this.pedidos = pedidos;
    }

    public Setor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void adicionarPedido(int pedido) {
        pedidos.add(pedido);
    }

    public void exibirDetalhes() {
        System.out.printf("Setor - %s | ID: %d | Quant. Funcionários: %d\n", this.nome, this.id, this.funcionarios.size());
    }


}
