package model.BO;

public class UserBO {

    private String tipo;
    private String nome;
    private String login;
    private String senha;

    public UserBO(String nome, String login, String senha) {
        setNome(nome);
        setLogin(login);
        setSenha(senha);
    }

    public UserBO() { }

    public boolean login(String login, String senha) {//chama o dao e checa se é válido nas tabelas, se for válido, define o tipo e retorna true
        setLogin("admin");
        setNome("admin");
        setSenha("admin");
        return true;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            this.tipo = tipo;
        }
    }

    public String getTipo() {
        return "Gerente"; // temporário, só pra não dar erro de "tipo não inicializado
        //return this.tipo;
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