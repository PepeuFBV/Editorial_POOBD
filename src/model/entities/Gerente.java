package model.entities;

public class Gerente extends Usuario {

    public Gerente(String id, String nome, String login, String senha) {
        setId(id);
        setNome(nome);
        setLogin(login);
        setSenha(senha);
    }
}
