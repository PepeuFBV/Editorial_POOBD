package model.BO;

import java.io.IOException;
import java.time.LocalDate;

import model.DAO.ObraDAO;
import model.VO.ObraVO;
import util.LerPDF;

public class AutorBO {

    public void adicionarObra(String obraSelecionada, String titulo, String genero, String ano) throws IOException {
        if (obraSelecionada.isEmpty() || titulo.isEmpty() || genero.isEmpty() || ano.isEmpty()) {
            throw new IllegalArgumentException("Por favor, preencha todos os campos.");
        }

        byte[] pdfObraBytes = LerPDF.lerConteudoPDF(obraSelecionada);

        if (pdfObraBytes == null) {
            throw new IOException("Erro ao ler o arquivo PDF.");
        }

        ObraVO novaObra = new ObraVO();
        novaObra.setTitulo(titulo);
        novaObra.setGenero(genero);
        LocalDate anoLocalDate = LocalDate.parse(ano);
        novaObra.setAno(anoLocalDate);
        novaObra.setPdfObra(pdfObraBytes);

        ObraDAO obraDAO = new ObraDAO();
        obraDAO.inserir(novaObra);
    }
}
