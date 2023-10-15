package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
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
import model.BO.AvaliadorBO;
import model.BO.ObraBO;
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
        AvaliadorBO avaliadorBO = new AvaliadorBO();
        avaliadorVO = avaliadorBO.convertToAvaliadorVO(usuarioVO);
        titulo.setItems(carregarTitulosDasObrasDoAvaliador(avaliadorVO));
    }

    @FXML
    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção do relatório");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            if (file.getName().toLowerCase().endsWith(".pdf")) {
                showFile.setText(file.getAbsolutePath());
            } else {
                System.out.println("Selecione um arquivo PDF válido.");
            }
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }

    public ObservableList<String> carregarTitulosDasObrasDoAvaliador(AvaliadorVO avaliadorVO) {
        ObservableList<String> titulos = FXCollections.observableArrayList();
        AvaliadorBO avaliadorBO = new AvaliadorBO();
        List<ObraVO> obras = avaliadorBO.listarObrasAvaliadas(avaliadorVO);
        
        titulos.addAll(obras.stream().map(ObraVO::getTitulo).collect(Collectors.toList()));
        
        return titulos;
    }


    public void enviar(ActionEvent event) {
        try {
            String caminhoArquivo = showFile.getText();
            String tituloSelecionado = titulo.getValue();
            ObraVO obraSelecionada = new ObraVO();
            obraSelecionada.setTitulo(tituloSelecionado);
            ObraDAO obraDAO = new ObraDAO();
            try {
                List<ObraVO> obrasEncontradas = obraDAO.buscarPorTitulo(obraSelecionada);
                
                if (!obrasEncontradas.isEmpty()) {
                    ObraVO obraEncontrada = obrasEncontradas.get(0);

                    if (!caminhoArquivo.isEmpty()) {
                        byte[] pdfAvaliacao = LerPDF.lerConteudoPDF(caminhoArquivo);
                        
                        obra.setPdfAvaliacao(pdfAvaliacao);
                        obra.setAno(obraEncontrada.getAno());
                        obra.setAutor(obraEncontrada.getAutor());
                        obra.setAvaliador(obraEncontrada.getAvaliador());
                        obra.setDataAvaliacao(obraEncontrada.getDataAvaliacao());
                        obra.setGenero(obraEncontrada.getGenero());
                        obra.setIDObra(obraEncontrada.getIDObra());
                        obra.setPdfObra(obraEncontrada.getPdfObra());
                        obra.setStatus(obraEncontrada.getStatus());
                        obra.setTitulo(obraEncontrada.getTitulo());
                        
                        ObraBO obraBO = new ObraBO();
                        obraBO.atualizar(obra);
                        System.out.println("Caminho do arquivo salvo no banco de dados com sucesso.");
                    } else {
                        erroAut.setText("Você deve selecionar um arquivo antes de enviar.");
                        erroAut.setVisible(true);
                    }
                    
                } else {
                    System.out.println("Nenhuma obra encontrada com o título: " + tituloSelecionado);
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
