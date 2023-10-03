package model.entities;

import java.time.LocalDate;

//TODO
//Checar no setStatus se o status é válido (uma das 4 opções) (era um enum)

public class Obra {

	private int id;
	private String titulo;
	private String genero;
	private LocalDate ano;
	private String status;
	private Autor autor; //servirá para pegar o id do autor
	private Avaliador avaliador; //servirá para pegar o id do avaliador

    public Obra(String titulo, String genero, LocalDate ano, String status, Autor autor) {
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setStatus(status);
        setAutor(autor);
    }
    
    public Obra() { }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo != null && titulo != "") {
			this.titulo = titulo;
		}
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		if (genero != null && genero != "") {
			this.genero = genero;
		}
	}

	public LocalDate getAno() {
		return this.ano;
	}

    public void setAno(LocalDate ano) {
        LocalDate hoje = LocalDate.now();
        if (ano != null && ano.isBefore(hoje)) {
            this.ano = ano;
        }
	}

	public String getStatus() {
		return this.status;
	}

    public void setStatus(String status) {
        String opcoes[] = { "Em avaliação", "Aprovado", "Reprovado", "Em revisão" };
        for (String opcao : opcoes) {
            if (status == opcao) {
                this.status = status;
            }
        }
	}

	public Autor getAutor() {
		return this.autor;
	}

	public void setAutor(Autor autor) {
		if (autor != null) {
			this.autor = autor;
		}
	}

	public Avaliador getAvaliador() {
		return this.avaliador;
	}
	
	public void setAvaliador(Avaliador avaliador) {
		if (avaliador != null) {
			this.avaliador = avaliador;
		}
	}

}
