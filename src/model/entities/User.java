package model.entities;

public class User {

    private int id;
    private String nome;
    private String login;
    private String senha;

    public User(String nome, String login, String senha) {
        setNome(nome);
        setLogin(login);
        setSenha(senha);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        if (login != null && !login.isEmpty()) {
            this.login = login;
        }
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty() && senha.length() >= 6) { //talvez fazer requesitos de senha em exception
            this.senha = senha;
        }
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
    
}
