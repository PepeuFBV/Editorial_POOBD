package model.BO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import exceptions.InsertException;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;


public class GerenteBO {

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
    
    public void baixarRelatorios(int anoInicio, int anoFinal) throws IOException, SQLException {
        if (anoInicio > anoFinal) {
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
                if (obra.getPdfObra() != null) {
                    byte[] pdfObraBytes = obra.getPdfObra();
                    String nomeArquivoObra = "obra_" + obra.getIDObra() + ".pdf";
                    String caminhoArquivoObra = diretorioSalvar + nomeArquivoObra;

                    try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivoObra)) {
                        outputStream.write(pdfObraBytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                }

                if (obra.getPdfAvaliacao() != null) {
                    byte[] pdfAvaliacaoBytes = obra.getPdfAvaliacao();
                    String nomeArquivoAvaliacao = "avaliacao_" + obra.getIDObra() + ".pdf";
                    String caminhoArquivoAvaliacao = diretorioSalvar + nomeArquivoAvaliacao;

                    try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivoAvaliacao)) {
                        outputStream.write(pdfAvaliacaoBytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
        }
    }
    
}

