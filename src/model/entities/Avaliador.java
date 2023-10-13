package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Avaliador extends Usuario {

    private String endereco;
    private String cpf;
    private List<Obra> obras = new ArrayList<>();

    public Avaliador(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha);
        setEndereco(endereco);
        setCpf(cpf);
    }

    public Avaliador() {
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
        this.obras = obras;
    }

}
