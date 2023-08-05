package model.entities;

import java.time.LocalDate;

public class Obra {
	private String titulo;
	private String genero;
	private LocalDate ano;
	private Autor autor;
	private StatusObra status;
	private Avaliador avaliador;
	
	public Obra() { }
	
	public Obra(String titulo, String genero, LocalDate ano, Autor autor, StatusObra status, Avaliador avaliador) {
		this.titulo = titulo;
		this.genero = genero;
		this.ano = ano;
		this.autor = autor;
		this.status = status;
		this.avaliador = avaliador;
	}

	public Obra(String titulo, String genero, LocalDate ano, StatusObra status) {
		this.titulo = titulo;
		this.genero = genero;
		this.ano = ano;
		this.status = status;
	}

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
		this.ano = ano;
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public StatusObra getStatus() {
		return status;
	}

	public void setStatus(StatusObra status) {
		this.status = status;
	}

	public Avaliador getAvaliador() {
		return avaliador;
	}
	
	public void setAvaliador(Avaliador avaliador) {
		this.avaliador = avaliador;
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

	public Obra[] buscarPorTitulo(Obra obra) {
		System.out.printf("Obra encontrada!\n");
		return;
	}

	public Obra[] buscarPorGenero(Obra obra) {
		System.out.printf("Obra encontrada!\n");
		return;
	}

	public Obra[] buscarPorAno(Obra obra) {
		System.out.printf("Obra encontrada!\n");
		return;
	}

	public Obra[] buscarPorAutor(Obra obra) {
		System.out.printf("Obra encontrada!\n");
		return;
	}

	public Obra[] buscarPorStatus(Obra obra) {
		System.out.printf("Obra encontrada!\n");
		return;
	}

	public void definirAvaliador(Obra obra, Avaliador avaliador) {
		obra.setAvaliador(avaliador);
	}

	public StatusObra avaliarObra(Obra obra, Avaliador avaliador) {
		return; //?
	}
	
	public Obra[] gerarRelatorio(LocalDate inicio, LocalDate fim) {
		return; //?
	}
}
