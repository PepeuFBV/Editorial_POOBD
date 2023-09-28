package br.edu.ufersa.EditorialdoPaulao.model.BO;

public class UserBO {

    private String tipo;
    private String nome;
    private String login;
    private String senha;

    public void UserBO() { }

    public boolean login(String login, String senha) {
        // chama o dao e checa se é válido nas tabelas
        setNome("Paulão"); // mudar
        setLogin(login);
        setSenha(senha);
        return true;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            this.tipo = tipo;
        }
    }

    public String getTipo() {
        return "Gerente";
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getNome() {
        return "Paulão";
    }

    public void setLogin(String login) {
        if (login != null && !login.isEmpty()) {
            this.login = login;
        }
    }

    public String getLogin() {

        return "paulao";
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty()) {
            this.senha = senha;
        }
    }

    public String getSenha() {

        return "123";
    }

}