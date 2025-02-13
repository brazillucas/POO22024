/*
 * SistemaDeLogin
 * Para gerenciar o acesso de múltiplos administradores, criaremos uma classe separada chamada SistemaDeLogin. Essa classe será responsável por autenticar os usuários e verificar suas permissões.
 * 
 * Atributos:
 * usuariosCadastrados (Map<String, Administrador>): Um mapa que associa a matrícula de cada administrador ao seu objeto Administrador.
 * Métodos:
 * autenticar(String matricula, String senha)
 * Verifica se a matrícula e a senha correspondem a um administrador cadastrado.
 * Retorna o objeto Administrador caso a autenticação seja bem-sucedida.
 * adicionarAdministrador(Administrador admin)
 * Adiciona um novo administrador ao sistema.
 * removerAdministrador(String matricula)
 * Remove um administrador do sistema com base na matrícula.
 *  */

import java.util.ArrayList;
import java.util.List;

public class SistemaDeLogin {
    @SuppressWarnings("FieldMayBeFinal")
    private List<Administrador> usuariosCadastrados;

    public SistemaDeLogin() {
        usuariosCadastrados = new ArrayList<>();
    }

    public Administrador autenticar(int matricula, String senha) {
        // Criptografar a senha
        Criptografia criptografia = new Criptografia(senha, Criptografia.SHA256);
        senha = criptografia.criptografar();

        for (Administrador admin : usuariosCadastrados) {
            if (admin.getMatricula() == matricula && admin.getSenha().equals(senha)) {
                return admin;
            }
        }
        return null;
    }

    public void adicionarAdministrador(Administrador admin) {
        usuariosCadastrados.add(admin);
    }

    public void removerAdministrador(int matricula) {
        usuariosCadastrados.removeIf(admin -> admin.getMatricula() == matricula);
    }
}

