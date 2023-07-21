package model.entities;

import java.util.Date;

public class Avaliacao {
	private Obra obra;
	private Avaliador avaliador;
	private Date initialDate;
	private Date finalDate;
	private StatusObra statusAvaliacao;
	
	public Avaliacao() {
	}

	public Avaliacao(Obra obra, Avaliador avaliador, Date initialDate, Date finalDate, StatusObra statusAvaliacao) {
		this.obra = obra;
		this.avaliador = avaliador;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.statusAvaliacao = statusAvaliacao;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Avaliador getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Avaliador avaliador) {
		this.avaliador = avaliador;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public StatusObra getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(StatusObra statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}
	
}
