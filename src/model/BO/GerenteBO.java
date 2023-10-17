package model.BO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import exceptions.InsertException;
import model.DAO.GerenteDAO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.GerenteVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;


public class GerenteBO {

    private GerenteDAO gerenteDAO = new GerenteDAO();

    public void adicionarAvaliador(String cpf, String nome, String endereco, String senha, String email) throws InsertException {
        if (cpf.isEmpty() || senha.isEmpty() || email.isEmpty() || nome.isEmpty() || endereco.isEmpty()) {
            throw new InsertException("Por favor, preencha todos os campos.");
        }
        UsuarioVO autorVO = new AutorVO();
        autorVO.setCpf(cpf);
        autorVO.setNome(nome);
        autorVO.setEndereco(endereco);
        autorVO.setEmail(email);
        autorVO.setSenha(senha);
        autorVO.setTipo("Avaliador");

        UsuarioBO<AutorVO> usuarioBO = new UsuarioBO<AutorVO>();
        try {
            usuarioBO.cadastrar(autorVO);
        } catch (InsertException e) {
            throw e;
        }
    }
    
    public void adicionarAutor(String cpf, String nome, String endereco, String senha, String email) throws InsertException {
        if (cpf.isEmpty() || senha.isEmpty() || email.isEmpty() || nome.isEmpty() || endereco.isEmpty()) {
            throw new InsertException("Por favor, preencha todos os campos.");
        }

        UsuarioVO autorVO = new AutorVO();
        autorVO.setCpf(cpf);
        autorVO.setNome(nome);
        autorVO.setEndereco(endereco);
        autorVO.setEmail(email);
        autorVO.setSenha(senha);
        autorVO.setTipo("Autor");

        UsuarioBO<AutorVO> userBO = new UsuarioBO<AutorVO>();
        try {
            userBO.cadastrar(autorVO);
        } catch (InsertException e) {
            throw e;
        }
    }
    
    public void baixarRelatorios(Date anoInicio, Date anoFinal) throws IOException, SQLException {
    	System.out.println("entrada");
        LocalDate dataInicio = anoInicio.toLocalDate();
        LocalDate dataFinal = anoFinal.toLocalDate();
        if (dataInicio.isAfter(dataFinal)) {
            throw new IllegalArgumentException("O ano inicial não pode ser maior que o ano final.");
        }

        ArrayList<ObraVO> obras = new ArrayList<>();
        ObraDAO obraDAO = new ObraDAO();
        obras = obraDAO.buscarPorAno(anoInicio, anoFinal);

        if (!obras.isEmpty()) {

            String diretorioSalvar = "C:\\Avaliações";
            File diretorio = new File(diretorioSalvar);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            for (ObraVO obra : obras) {

                if (obra.getPdfAvaliacao() != null) {

                    byte[] pdfAvaliacaoBytes = obra.getPdfAvaliacao();
                    String nomeArquivoAvaliacao = "avaliacao_" + obra.getIDObra() + ".pdf";
                    String caminhoArquivoAvaliacao = diretorioSalvar + nomeArquivoAvaliacao;

                    try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivoAvaliacao)) {
                        outputStream.write(pdfAvaliacaoBytes);
                        System.out.println("Relatório(s) baixado(s) com sucesso.");
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                } else {
                	System.out.println("PDF(s) correspondente(s) vazio(s).");
                }
            }
        } else {
        	System.out.println("Não há obras avaliadas entre as datas correspondentes.");
        }
    }

    public List<GerenteVO> listar() {
        List<GerenteVO> gerentes = gerenteDAO.listar();
        return gerentes;
    }

}
