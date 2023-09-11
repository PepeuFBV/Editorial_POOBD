package model.entities;

public class Gerente extends Usuario {

    public Gerente(Long id, String nome, String login, String senha, String endereco, String cpf) {
        super(id, nome, login, senha, endereco, cpf);
    }
}
