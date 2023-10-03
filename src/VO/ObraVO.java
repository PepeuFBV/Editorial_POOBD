package VO;

import java.time.LocalDate;

public class ObraVO {
    
    private int IDObra;
    private String titulo;
    private String genero;
    private LocalDate ano;
    private String status;
    
    public ObraVO(int iDObra, String titulo, String genero, LocalDate ano, String status) {
        setIDObra(iDObra);
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setStatus(status);
    }
    
    public ObraVO() {
    }
    
    public int getIDObra() {
        return IDObra;
    }

    public void setIDObra(int iDObra) {
        if (iDObra > 0) {
            IDObra = iDObra;
        } else {
            throw new IllegalArgumentException("ID da obra deve ser maior que zero.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isEmpty()) {
            this.titulo = titulo;
        } else {
            throw new IllegalArgumentException("Título da obra não pode ser nulo ou vazio.");
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero != null && !genero.isEmpty()) {
            this.genero = genero;
        } else {
            throw new IllegalArgumentException("Gênero da obra não pode ser nulo ou vazio.");
        }
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        if (ano != null) {
            this.ano = ano;
        } else {
            throw new IllegalArgumentException("Ano da obra não pode ser nulo.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status da obra não pode ser nulo ou vazio.");
        }
    }
}
