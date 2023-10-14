package model.BO;

import java.io.IOException;
import java.time.LocalDate;

import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.ObraVO;
import util.LerPDF;

public class AutorBO {
	
	public void adicionarObra(String obraSelecionada, String titulo, String genero, LocalDate ano, AutorVO autorVO) throws IOException {
	    if (obraSelecionada.isEmpty() || titulo.isEmpty() || genero.isEmpty()) {
	        throw new IllegalArgumentException("Por favor, preencha todos os campos.");
	    }

	    if (ano == null) {
	        throw new IllegalArgumentException("O campo de data não pode estar vazio.");
	    }

	    byte[] pdfObraBytes = LerPDF.lerConteudoPDF(obraSelecionada);

	    if (pdfObraBytes == null) {
	        throw new IOException("Erro ao ler o arquivo PDF.");
	    }

	    ObraVO novaObra = new ObraVO();
	    novaObra.setTitulo(titulo);
	    novaObra.setGenero(genero);
	    novaObra.setAno(ano);
	    novaObra.setPdfObra(pdfObraBytes);
        novaObra.setAutor(autorVO);
        novaObra.setStatus("Avaliador Pendente");

	    ObraDAO obraDAO = new ObraDAO();
	    obraDAO.inserir(novaObra);
	}

	public void editarObra(String obraSelecionada, String titulo, String genero, LocalDate ano, AutorVO autorVO) throws IOException {
	    if (obraSelecionada.isEmpty() || titulo.isEmpty() || genero.isEmpty()) {
	        throw new IllegalArgumentException("Por favor, preencha todos os campos.");
	    }

	    if (ano == null) {
	        throw new IllegalArgumentException("O campo de data não pode estar vazio.");
	    }

	    byte[] pdfObraBytes = LerPDF.lerConteudoPDF(obraSelecionada);

	    if (pdfObraBytes == null) {
	        throw new IOException("Erro ao ler o arquivo PDF.");
	    }

	    ObraVO novaObra = new ObraVO();
	    novaObra.setTitulo(titulo);
	    novaObra.setGenero(genero);
	    novaObra.setAno(ano);
	    novaObra.setPdfObra(pdfObraBytes);
        novaObra.setAutor(autorVO);
        novaObra.setStatus("Avaliador Pendente");

	    ObraDAO obraDAO = new ObraDAO();
	    obraDAO.atualizar(novaObra);
	}
    
}
