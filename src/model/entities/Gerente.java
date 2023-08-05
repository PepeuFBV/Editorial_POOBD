package model.entities;

public class Gerente {
    private String nome;
    private boolean isGerente;
    private String login;
    private String senha;

    public Gerente() { }

    public Gerente(String nome, boolean isGerente, String login, String senha) {
        this.nome = nome;
        this.isGerente = isGerente;
        this.login = login;
        this.senha = senha;    
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome != "") {
            this.nome = nome;
        }
    }

    public boolean getIsGerente() {
        return isGerente;
    }

    public void setIsGerente(boolean isGerente) {
        this.isGerente = isGerente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null && login != "") {
            this.login = login;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && senha != "") {
            this.senha = senha;
        }
    }

    //alterar metódos depois
    public boolean logar(String login, String senha) {
        return true;
    }
}
