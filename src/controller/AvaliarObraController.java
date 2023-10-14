package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;
import util.LerPDF;
import view.Telas;

public class AvaliarObraController {

    @FXML
    private TextField showFile;

    @FXML
    private ChoiceBox<String> titulo;
    
    @FXML
    private Button buttonarquivo;

    @FXML
    private Label erroAut;

    @FXML
    private RadioButton buttonaceita;

    @FXML
    private RadioButton buttonrejeitada;

    @FXML
    private ToggleGroup radioavaliar;

    private AvaliadorVO avaliadorVO;
    
    private ObraVO obra = new ObraVO(); 
    
    public void initialize() {	
        setUsuarioVO(Telas.getUsuarioVOAtual());
    }
	
    public void setUsuarioVO(UsuarioVO usuarioVO) {
    	avaliadorVO = (AvaliadorVO) usuarioVO;
    	avaliadorVO.setEmail(Telas.getUsuarioVOAtual().getEmail());
        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
        ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorEmail(avaliadorVO);

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
        titulo.setItems(carregarTitulosDasObrasDoAvaliador(avaliadorVO));
    }
    
    @FXML
    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção do relatório");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            showFile.setText(file.getAbsolutePath()); 
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }
    
    public ObservableList<String> carregarTitulosDasObrasDoAvaliador(AvaliadorVO avaliadorVO) {
        ObservableList<String> titulos = FXCollections.observableArrayList();
        try {
            ObraDAO obraDAO = new ObraDAO();
            ArrayList<ObraVO> obrasDoAvaliador = obraDAO.buscarPorAvaliador(avaliadorVO);
            for (ObraVO obra : obrasDoAvaliador) {
                titulos.add(obra.getTitulo());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titulos;
    }

    public void enviar(ActionEvent event) {
        try {
            String caminhoArquivo = showFile.getText();

            if (!caminhoArquivo.isEmpty()) {
                byte[] pdfAvaliacao = LerPDF.lerConteudoPDF(caminhoArquivo);
                obra.setPdfAvaliacao(pdfAvaliacao);

                ObraDAO obraDAO = new ObraDAO();
                obraDAO.atualizar(obra);

                System.out.println("Caminho do arquivo salvo no banco de dados com sucesso.");
            } else {
                erroAut.setText("Você deve selecionar um arquivo antes de enviar.");
                erroAut.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            erroAut.setText("Erro ao ler o arquivo PDF.");
            erroAut.setVisible(true);
        }
    }

    public void voltarTelaOBrasAvaliador(ActionEvent event) {
        Stage stage = (Stage) showFile.getScene().getWindow();
        stage.close();
    }
}
