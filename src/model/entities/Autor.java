package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Autor extends User {

    private String endereco;
    private String cpf;
    private List<Obra> obras = new ArrayList<>();

    public Autor(String nome, String login, String senha, String endereco, String cpf) {
        super(nome, login, senha);
        setEndereco(endereco);
        setCpf(cpf);
    }

    public Autor() {
        super();
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco != null && !endereco.isEmpty()) {
            this.endereco = endereco;
        }
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            this.cpf = cpf;
        }
    }

    public List<Obra> getObras() {
        return this.obras;
    }

    public void setObras(List<Obra> obras) {
        if (obras != null) {
            this.obras = obras;
        }
    }

}
