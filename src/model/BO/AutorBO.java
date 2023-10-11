package model.BO;

import java.util.List;
import model.entities.Obra;

public class AutorBO {

    private int id;
    private String nome;
    private String login;
    private String senha;
    private List<Obra> obras;

    public AutorBO(int id, String nome, String login, String senha) {
        setId(id);
        setNome(nome);
        setSenha(senha);
        setLogin(login);
    }

    public AutorBO() {
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public List<Obra> getObras() {
        return this.obras;
    }

    public void setId(int id) {
        if (id != null && id > 0) {
            this.id = id;
        }
    }

    public int getId() {
        return this.id;
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
