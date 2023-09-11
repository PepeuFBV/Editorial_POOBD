package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Autor extends Usuario {

    private List<Autor> autores = new ArrayList<>();
    private List<Obra> obras = new ArrayList<>();

    public Autor(Long id, String nome, String endereco, String cpf, List<Obra> obras, String login, String senha) {
        super(id, nome, login, senha, endereco, cpf);
    }

    public List<Obra> getObras() {
        return this.obras;
    }

    public void setObras(List<Obra> obras) {
        if (obras != null) {
            this.obras = obras;
        }
    }

    public List<Autor> getAutores() {
        return this.autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    //alterar metódos depois
    /*
	public void cadastrar(Autor autor) {
		autores.add(autor);
		System.out.printf(autor.getNome() + " está cadastrado!\n");
	}
	
	public void alterar(Autor autor) {
		System.out.printf(autor.getNome() + " foi alterado!\n");
	}
	
	public void excluir(Autor autor) {
		System.out.printf(autor.getNome() + " foi excluido!\n");
	}
	
	public Autor buscarPorNome(String nome) {
	    for (Autor autor : autores) {
	        if (autor.getNome().equalsIgnoreCase(nome)) {//ignorecase para ignorar
	            return autor;						// diferença entre maiusculas e minusculas
	        }
	    }
	    System.out.println("Autor não encontrado");
	    return null;
	}
	public Autor buscarPorObra(Obra obra) {
        for (Autor autor : autores) {
            List<Obra> obrasDoAutor = autor.getObras(); //criação da lista de obras associadas ao autor
            for (Obra obraAutor : obrasDoAutor) {
                if (obraAutor.equals(obra)) {
                    return autor;
                }
            }
        }
        System.out.println("Autor não encontrado para a obra: " + obra.getTitulo());
        return null;
    }
    */

}
