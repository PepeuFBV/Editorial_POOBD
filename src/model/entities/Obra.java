package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	public void setAno(LocalDate ano) { //checar se o ano é válido (não pode ser maior que o ano atual)
		if (ano != null) {
			this.ano = ano;
		}
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		if (status != null && !status.isEmpty()) {
			this.status = status;
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





	//alterar metódos depois
	public void cadastrar(Obra obra) {
		System.out.printf(obra.getTitulo() + " foi cadastrada!\n");
	}

	public void alterar(Obra obra) {
		System.out.printf(obra.getTitulo() + " foi alterada!\n");
	}

	public void excluir(Obra obra) {
		System.out.printf(obra.getTitulo() + " foi excluida!\n");
	}

	public List<Obra> buscarPorTitulo(String titulo) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obrasEncontradas) {
            if (obra.getTitulo().equalsIgnoreCase(titulo)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorGenero(String genero) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obrasEncontradas) {
            if (obra.getGenero().equalsIgnoreCase(genero)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorAno(LocalDate ano) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obrasEncontradas) {
            if (obra.getAno().equals(ano)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorAutor(Autor autor) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obrasEncontradas) {
            if (obra.getAutor().equals(autor)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorStatus(String status) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obrasEncontradas) {
            if (obra.getStatus().equals(status)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

	public void definirAvaliador(Obra obra, Avaliador avaliador) {
		obra.setAvaliador(avaliador);
	}

	/*public StatusObra avaliarObra(Obra obra, Avaliador avaliador) {
		return; //?
	}
	
	public List<Obra> gerarRelatorio(LocalDate inicio, LocalDate fim) {
		return; //?
	}*/
}
