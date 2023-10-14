package model.VO;

import java.time.LocalDate;

public class ObraVO {
    
    private Long IDObra;
    private String titulo;
    private String genero;
    private LocalDate ano;
    private String status;
    private LocalDate data_avaliacao;
    private AutorVO autor;
    private AvaliadorVO avaliador;
    private byte[] pdf_obra;
    private byte[] pdf_avaliacao;
    
    public ObraVO(Long iDObra, String titulo, String genero, LocalDate ano, String status, LocalDate data_avaliacao,
            AutorVO autor, AvaliadorVO avaliador, byte[] pdf_obra, byte[] pdf_avaliacao) {
        setIDObra(iDObra);
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setStatus(status);
        setDataAvaliacao(data_avaliacao);
        setAutor(autor);
        setAvaliador(avaliador);
        setPdfObra(pdf_obra);
        setPdfAvaliacao(pdf_avaliacao);
    }
    
    public ObraVO(String titulo, String genero, LocalDate ano, AutorVO autor, byte[] pdf_obra) { //adição de obra pela primeira vez
        setTitulo(titulo);
        setGenero(genero);
        setAno(ano);
        setStatus("Em espera");
        setAutor(autor);
        setPdfObra(pdf_obra);
    }

    public ObraVO() {
    }
    

    public Long getIDObra() {
        return IDObra;
    }

    public void setIDObra(Long iDObra) {
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
        this.status = status;
    }
    
    public LocalDate getDataAvaliacao() {
        return data_avaliacao;
    }

    public void setDataAvaliacao(LocalDate data_avaliacao) {
        this.data_avaliacao = data_avaliacao;
    }

    public AutorVO getAutor() {
        return autor;
    }

    public void setAutor(AutorVO autor) {
        if (autor != null) {
            this.autor = autor;
        } else {
            throw new IllegalArgumentException("Autor da obra não pode ser nulo.");
        }
    }

    public AvaliadorVO getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(AvaliadorVO avaliador) {
        if (avaliador != null) {
            this.avaliador = avaliador;
        } else {
            throw new IllegalArgumentException("Avaliador da obra não pode ser nulo.");
        }
    }
    
    public byte[] getPdfObra() {
        return pdf_obra;
    }

    public void setPdfObra(byte[] pdf_obra) {
        this.pdf_obra = pdf_obra;
    }

    public byte[] getPdfAvaliacao() {
        return pdf_avaliacao;
    }

    public void setPdfAvaliacao(byte[] pdf_avaliacao) {
        this.pdf_avaliacao = pdf_avaliacao;
    }

}
