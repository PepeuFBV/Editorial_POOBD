package controller;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DAO.AutorDAO;
import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;

public class EditarObraGerenteController {

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
    private TextField ano;

    @FXML
    private Label erroEditarObraGerente;

    @FXML
    private Button btncancelar;

    private Map<String, Long> autorParaIDMap = new HashMap<>();
    private Map<String, Long> avaliadorParaIDMap = new HashMap<>();

    @FXML
    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção da Obra");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.*"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            showFileger.appendText(file.getAbsolutePath() + "\n");
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }

    @FXML
    private void initialize() {

        ObservableList<String> status = FXCollections.observableArrayList("Aceita", "Rejeitada", "Em avaliação", "Avaliador pendente");
        ObservableList<String> autores = FXCollections.observableArrayList();
        ObservableList<String> avaliadores = FXCollections.observableArrayList();

        AutorDAO autorDAO = new AutorDAO();
        try {
            ArrayList<AutorVO> autoresDoDB = autorDAO.listar();
            for (AutorVO autorVO : autoresDoDB) {
                String nomeAutor = autorVO.getNome();
                Long autorID = autorVO.getIDAutor();
                autores.add(nomeAutor);
                autorParaIDMap.put(nomeAutor, autorID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
        try {
            ArrayList<AvaliadorVO> avaliadoresDoDB = avaliadorDAO.listar();
            for (AvaliadorVO avaliadorVO : avaliadoresDoDB) {
                String nomeAvaliador = avaliadorVO.getNome();
                Long avaliadorID = avaliadorVO.getIDAvaliador();
                avaliadores.add(nomeAvaliador);
                avaliadorParaIDMap.put(nomeAvaliador, avaliadorID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        autor.setItems(autores);
        avaliador.setItems(avaliadores);
        stts.setItems(status);
    }

    public void concluir(ActionEvent event) {

        String tituloText = titulo.getText();
        String generoText = genero.getText();
        String anoText = ano.getText();
        String autorSelecionado = autor.getValue();
        String avaliadorSelecionado = avaliador.getValue();
        String statusSelecionado = stts.getValue();
        String obraSelecionada = showFileger.getText();

        if (tituloText.isEmpty() || generoText.isEmpty() || anoText.isEmpty() || autorSelecionado == null || avaliadorSelecionado == null || statusSelecionado == null || obraSelecionada.isEmpty()) {
            erroEditarObraGerente.setText("Por favor, preencha todos os campos.");
            erroEditarObraGerente.setVisible(true);
            return;
        } else {
        	
            obra.setTitulo(tituloText);
            obra.setGenero(generoText);
            obra.setAno(LocalDate.parse(anoText));
            obra.setStatus(statusSelecionado);

            long idAutor = autorParaIDMap.get(autorSelecionado);
            long idAvaliador = avaliadorParaIDMap.get(avaliadorSelecionado);
            
            AutorVO autorVO = new AutorVO();
            autorVO.setIDAutor(idAutor);
            obra.setAutor(autorVO);

            AvaliadorVO avaliadorVO = new AvaliadorVO();
            avaliadorVO.setIDAvaliador(idAvaliador);
            obra.setAvaliador(avaliadorVO);

            LocalDate dataAvaliacao = obra.getDataAvaliacao();
            obra.setDataAvaliacao(dataAvaliacao);

            obraDAO.atualizar(obra);

            System.out.println("Obra atualizada com sucesso.");
            erroEditarObraGerente.setText("Obra atualizada com sucesso.");
            erroEditarObraGerente.setVisible(true);
            btncancelar.setText("Fechar");
        }
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroEditarObraGerente.getScene().getWindow();
        stage.close();
    }
}
