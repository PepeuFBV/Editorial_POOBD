package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.BO.AutorBO;
import model.BO.AvaliadorBO;
import model.BO.ObraBO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import util.LerPDF;

public class NovaObraGerenteController {
    
    @FXML
    private TextField showFileger;

    @FXML
    private ChoiceBox<String> autor;

    @FXML
    private ChoiceBox<String> avaliador;

    @FXML
    private ChoiceBox<String> stts;

    @FXML
    private TextField titulo;

    @FXML
    private TextField genero;

    @FXML
    private DatePicker ano;

    @FXML
    private Label erroNovaObraGerente;

    @FXML
    private Button btncancelar;

    private Map<String, Integer> autorParaIDMap = new HashMap<>();
    private Map<String, Integer> avaliadorParaIDMap = new HashMap<>();

    @FXML
    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção da Obra");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            showFileger.appendText(file.getAbsolutePath() + "\n");
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }

    @FXML
    public void initialize() {
        ObservableList<String> status = FXCollections.observableArrayList("Aceita", "Rejeitada", "Em avaliação", "Avaliador pendente");
        ObservableList<String> autores = FXCollections.observableArrayList();
        ObservableList<String> avaliadores = FXCollections.observableArrayList();

        try {
            AutorBO autorBO = new AutorBO();
            List<AutorVO> autoresDoDB = autorBO.listar();

            if (!autoresDoDB.isEmpty()) {
                for (AutorVO autor : autoresDoDB) {
                    String nomeAutor = autor.getNome();
                    Long autorID = autor.getIDAutor();
                    int autorIDInt = autorID.intValue();
                    autores.add(nomeAutor);
                    autorParaIDMap.put(nomeAutor, autorIDInt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            AvaliadorBO avaliadorBO = new AvaliadorBO();            
            List<AvaliadorVO> avaliadoresDoDB = avaliadorBO.listar();

            if (!avaliadoresDoDB.isEmpty()) {
                for (AvaliadorVO avaliador : avaliadoresDoDB) {
                    String nomeAvaliador = avaliador.getNome();
                    Long avaliadorID = avaliador.getIDAvaliador();
                    int avaliadorIDInt = avaliadorID.intValue();
                    avaliadores.add(nomeAvaliador);
                    avaliadorParaIDMap.put(nomeAvaliador, avaliadorIDInt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        autor.setItems(autores);
        avaliador.setItems(avaliadores);
        stts.setItems(status);
    }

    public void adicionar(ActionEvent event) {
        String tituloText = titulo.getText();
        String generoText = genero.getText();
        LocalDate anoText = ano.getValue();
        String autorSelecionado = autor.getValue();
        String avaliadorSelecionado = avaliador.getValue();
        String statusSelecionado = stts.getValue();
        String obraSelecionada = showFileger.getText();

        if (obraSelecionada.isEmpty() || tituloText.isEmpty() || generoText.isEmpty() || autorSelecionado == null || avaliadorSelecionado == null || statusSelecionado == null) {
            erroNovaObraGerente.setText("Por favor, preencha todos os campos.");
            erroNovaObraGerente.setVisible(true);
            throw new IllegalArgumentException("Por favor, preencha todos os campos.");
        }
        
	    if (ano == null) {
	        throw new IllegalArgumentException("O campo de data não pode estar vazio.");
	    }

        byte[] pdfObraBytes = null;

        try {
            pdfObraBytes = LerPDF.lerConteudoPDF(obraSelecionada);
        } catch (IOException e) {
            e.printStackTrace();
            erroNovaObraGerente.setText("Erro ao ler o arquivo PDF.");
            erroNovaObraGerente.setVisible(true);
        }

        if (pdfObraBytes != null) {
            ObraVO novaObra = new ObraVO();
            novaObra.setTitulo(tituloText);
            novaObra.setGenero(generoText);
            novaObra.setAno(anoText);

            int idAutor = autorParaIDMap.get(autorSelecionado);
            AutorVO autorVO = new AutorVO();
            autorVO.setIDAutor((long) idAutor);

            int idAvaliador = avaliadorParaIDMap.get(avaliadorSelecionado);
            AvaliadorVO avaliadorVO = new AvaliadorVO();
            avaliadorVO.setIDAvaliador((long) idAvaliador);

            novaObra.setStatus(statusSelecionado);
            novaObra.setPdfObra(pdfObraBytes);
            novaObra.setAutor(autorVO);
            novaObra.setAvaliador(avaliadorVO);

            ObraBO obraBO = new ObraBO();
            obraBO.cadastrar(novaObra);

            System.out.println("Obra adicionada com sucesso.");
            erroNovaObraGerente.setText("Obra adicionada com sucesso.");
            erroNovaObraGerente.setVisible(true);
            btncancelar.setText("Fechar");
        } else {
        	erroNovaObraGerente.setText("Obra vazia. Não foi possível adicionar");
            erroNovaObraGerente.setVisible(true);
        }
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroNovaObraGerente.getScene().getWindow();
        stage.close();
    }

}
