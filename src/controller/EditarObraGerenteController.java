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
import model.BO.ObraBO;
import model.DAO.AutorDAO;
import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;

public class EditarObraGerenteController {

    @FXML
    private TextField showFileger;
    
    @FXML
    private ChoiceBox<String> obras;

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
        ObservableList<String> obrasList = FXCollections.observableArrayList();
        
        ObraDAO obraDAO = new ObraDAO();
        try {
            ArrayList<ObraVO> obrasDoDB = obraDAO.listar(); 
            for (ObraVO obraVO : obrasDoDB) {
                String tituloObra = obraVO.getTitulo();
                obrasList.add(tituloObra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        obras.setItems(obrasList);

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
        String tituloSelecionado = obras.getValue();

        if (tituloSelecionado == null || tituloSelecionado.isEmpty()) {
            erroEditarObraGerente.setText("Por favor, selecione uma obra para editar.");
            erroEditarObraGerente.setVisible(true);
            return;
        }

        ObraBO obraBO = new ObraBO();
        ObraVO obraEncontrada = buscarObraPorTitulo(tituloSelecionado);

        if (obraEncontrada != null) {
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
            }

            obraEncontrada.setTitulo(tituloText);
            obraEncontrada.setGenero(generoText);
            obraEncontrada.setAno(LocalDate.parse(anoText));
            obraEncontrada.setStatus(statusSelecionado);

            long idAutor = autorParaIDMap.get(autorSelecionado);
            long idAvaliador = avaliadorParaIDMap.get(avaliadorSelecionado);

            AutorVO autorVO = new AutorVO();
            autorVO.setIDAutor(idAutor);
            AutorDAO autorDAO = new AutorDAO();
            ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO);
            AutorVO primeiroAutor = autores.get(0);
            
            autorVO.setIDUsuario(primeiroAutor.getIDUsuario());
            autorVO.setCpf(primeiroAutor.getCpf());
            autorVO.setEmail(primeiroAutor.getEmail());
            autorVO.setEndereco(primeiroAutor.getEndereco());
            autorVO.setNome(primeiroAutor.getNome());
            autorVO.setSenha(primeiroAutor.getSenha());
            autorVO.setTipo("Autor");
            
            obraEncontrada.setAutor(autorVO);

            AvaliadorVO avaliadorVO = new AvaliadorVO();
            avaliadorVO.setIDAvaliador(idAvaliador);
            AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
            ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
            AvaliadorVO primeiroAvaliador = avaliadores.get(0);
            
            avaliadorVO.setIDUsuario(primeiroAvaliador.getIDUsuario());
            avaliadorVO.setCpf(primeiroAvaliador.getCpf());
            avaliadorVO.setEmail(primeiroAvaliador.getEmail());
            avaliadorVO.setEndereco(primeiroAvaliador.getEndereco());
            avaliadorVO.setNome(primeiroAvaliador.getNome());
            avaliadorVO.setSenha(primeiroAvaliador.getSenha());
            avaliadorVO.setTipo("Avaliador");
            
            obraEncontrada.setAvaliador(avaliadorVO);

            obraBO.atualizar(obraEncontrada);

            System.out.println("Obra atualizada com sucesso.");
            erroEditarObraGerente.setText("Obra atualizada com sucesso.");
            erroEditarObraGerente.setVisible(true);
            btncancelar.setText("Fechar");
        }
    }

    private ObraVO buscarObraPorTitulo(String titulo) {
        ObraVO obra = new ObraVO();
        ObraDAO obraDAO = new ObraDAO();
        obra.setTitulo(titulo);

        try {

            ArrayList<ObraVO> obrasEncontradas = obraDAO.buscarPorTitulo(obra);
            if (!obrasEncontradas.isEmpty()) {
                return obrasEncontradas.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroEditarObraGerente.getScene().getWindow();
        stage.close();
    }
}
