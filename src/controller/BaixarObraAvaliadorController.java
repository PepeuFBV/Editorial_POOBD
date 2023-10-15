package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.BO.AvaliadorBO;
import model.DAO.ObraDAO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;
import view.Telas;

public class BaixarObraAvaliadorController {

    @FXML
    private ChoiceBox<String> titulo;
    
    @FXML
    private Label erroBaixarObra;

    AvaliadorVO avaliadorVO = new AvaliadorVO();
    
	@FXML
	private void initialize() {
		setUsuarioVO(Telas.getUsuarioVOAtual());
	}

    public void setUsuarioVO(UsuarioVO usuarioVO) {
        avaliadorVO = (AvaliadorVO) usuarioVO;
        avaliadorVO.setEmail(Telas.getUsuarioVOAtual().getEmail());
        AvaliadorBO avaliadorBO = new AvaliadorBO();
        List<AvaliadorVO> avaliadores = avaliadorBO.buscarPorEmail(avaliadorVO);

        if (!avaliadores.isEmpty()) {
        	AvaliadorVO primeiroAvaliador = avaliadores.get(0);

            avaliadorVO.setIDUsuario(primeiroAvaliador.getIDUsuario());
            avaliadorVO.setIDAvaliador(primeiroAvaliador.getIDAvaliador());
            avaliadorVO.setTipo("Avaliador");
            avaliadorVO.setNome(primeiroAvaliador.getNome());
            avaliadorVO.setEndereco(primeiroAvaliador.getEndereco());
            avaliadorVO.setCpf(primeiroAvaliador.getCpf());
            avaliadorVO.setSenha(primeiroAvaliador.getSenha());
        }
        titulo.setItems(carregarTitulosDasObrasDoAutor(avaliadorVO));
    }
    
    public ObservableList<String> carregarTitulosDasObrasDoAutor(AvaliadorVO avaliadorVO) {
        ObservableList<String> titulos = FXCollections.observableArrayList();
        try {
            ObraDAO obraDAO = new ObraDAO();
            ArrayList<ObraVO> obrasDoAutor = obraDAO.buscarPorAvaliador(avaliadorVO);
            for (ObraVO obra : obrasDoAutor) {
                titulos.add(obra.getTitulo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titulos;
    }
    
    @FXML
    public void baixar(ActionEvent event) {
        ObservableList<String> obrasList = titulo.getItems();
        if (!obrasList.isEmpty()) {
            String tituloObra = obrasList.get(0);
            ObraVO obra = new ObraVO();
            obra.setTitulo(tituloObra);
            ObraDAO obraDAO = new ObraDAO();
            try {
                List<ObraVO> obras = obraDAO.buscarPorTitulo(obra);
                if (!obras.isEmpty()) {
                    ObraVO primeiraObra = obras.get(0);
                    byte[] pdfObraBytes = primeiraObra.getPdfObra();
                    
                    if (pdfObraBytes != null) {
                        String nomeArquivoObra = "obra_" + primeiraObra.getIDObra() + ".pdf";
                        String diretorioSalvar = "C:\\Obras"; 
                        String caminhoArquivoObra = diretorioSalvar + nomeArquivoObra;

                        File diretorio = new File(diretorioSalvar);
                        if (!diretorio.exists()) {
                            diretorio.mkdirs();
                        }

                        try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivoObra)) {
                            outputStream.write(pdfObraBytes);
                            System.out.println("Obra baixada com sucesso.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("A obra não possui um PDF associado.");
                    }
                } else {
                    System.out.println("Obra não encontrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void voltar(ActionEvent event) {
        Stage stage = (Stage) erroBaixarObra.getScene().getWindow();
        stage.close();
    }

}
