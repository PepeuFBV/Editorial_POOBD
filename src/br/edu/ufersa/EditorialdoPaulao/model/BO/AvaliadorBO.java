package model.BO;

public class AvaliadorBO {

    private String nome;
    private String login;
    private String senha;

    public AvaliadorBO() {
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setLogin(String login) {
        if (login != null && !login.isEmpty()) {
            this.login = login;
        }
    }

    public String getLogin() {
        return this.login;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty()) {
            this.senha = senha;
        }
    }

    public String getSenha() {
        return this.senha;
    }

}
