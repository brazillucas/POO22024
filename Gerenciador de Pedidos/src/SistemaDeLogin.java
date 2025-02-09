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

import java.util.HashMap;
import java.util.Map;

public class SistemaDeLogin {
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, Administrador> usuariosCadastrados;
    
    public SistemaDeLogin() {
        usuariosCadastrados = new HashMap<>();
    }
    
    public Administrador autenticar(String matricula, String senha) {
        // Criptografar a senha
        Criptografia criptografia = new Criptografia(senha, Criptografia.SHA256);
        senha = criptografia.criptografar();
        
        Administrador admin = usuariosCadastrados.get(matricula);
        if (admin != null && admin.getSenha().equals(senha)) {
            return admin;
        }
        return null;
    }
    
    public void adicionarAdministrador(Administrador admin) {
        usuariosCadastrados.put(String.valueOf(admin.getMatricula()), admin);
    }
    
    public void removerAdministrador(String matricula) {
        usuariosCadastrados.remove(matricula);
    }

}

