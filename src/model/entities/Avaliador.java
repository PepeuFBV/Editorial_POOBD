package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Avaliador extends Usuario {
    private String endereco;
    private String cpf;
    private List<Obra> obras = new ArrayList<>();
    private List<Avaliador> avaliadores;

    public Avaliador(String nome, String endereco, String cpf, String login, String senha) {
        super(nome, login, senha);
        setEndereco(endereco);
        setCpf(cpf);
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
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

	//alterar metódos depois
	public void cadastrar(Avaliador avaliador) {
		System.out.printf(avaliador.getNome() + " está cadastrado!\n");
	}

	public void alterar(Avaliador avaliador) {
		System.out.printf(avaliador.getNome() + " está alterado!\n");
	}

	public void excluir(Avaliador avaliador) {
		System.out.printf(avaliador.getNome() + " foi excluido!\n");
	}

	public Avaliador buscarPorObra(Obra obra) {
        for (Avaliador avaliador : avaliadores) {
            List<Obra> obrasDoAvaliador = avaliador.getObras();
            for (Obra obraAvaliador : obrasDoAvaliador) {
                if (obraAvaliador != null && obraAvaliador.equals(obra)) {
                    System.out.println("Avaliador encontrado para a obra: " + obra.getTitulo());
                    return avaliador;
                }
            }
        }
        
        System.out.println("Nenhum avaliador encontrado para a obra.");
        return null;
    }

}
