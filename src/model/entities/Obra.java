package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Obra {
	private String titulo;
	private String genero;
	private LocalDate ano;
	private Autor autor;
	private StatusObra status;
	private Avaliador avaliador;
	private List<Obra> obras = new ArrayList<>();
	
	public Obra(String titulo, String genero, LocalDate ano, Autor autor, StatusObra status, Avaliador avaliador) {
		setTitulo(titulo);
		setGenero(genero);
		setAno(ano);
		setAutor(autor);
		setStatus(status);
		setAvaliador(avaliador);
	}

	public Obra() {}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo != null && titulo != "") {
			this.titulo = titulo;
		}
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		if (genero != null && genero != "") {
			this.genero = genero;
		}
	}

	public LocalDate getAno() {
		return ano;
	}

	public void setAno(LocalDate ano) {
		if (ano != null) {
			this.ano = ano;
		}
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		if (autor != null) {
			this.autor = autor;
		}
	}

	public StatusObra getStatus() {
		return status;
	}

	public void setStatus(StatusObra status) {
		if (status != null) {
			this.status = status;
		}
	}

	public Avaliador getAvaliador() {
		return avaliador;
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
        for (Obra obra : obras) { // Suponha que 'todasAsObras' seja a lista completa de obras
            if (obra.getTitulo().equalsIgnoreCase(titulo)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorGenero(String genero) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obras) {
            if (obra.getGenero().equalsIgnoreCase(genero)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorAno(LocalDate ano) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obras) {
            if (obra.getAno().equals(ano)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorAutor(Autor autor) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obras) {
            if (obra.getAutor().equals(autor)) {
                obrasEncontradas.add(obra);
            }
        }
        return obrasEncontradas;
    }

    public List<Obra> buscarPorStatus(StatusObra status) {
        List<Obra> obrasEncontradas = new ArrayList<>();
        for (Obra obra : obras) {
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
