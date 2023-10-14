package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BO.AutorBO;
import model.DAO.AutorDAO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;
import view.Telas;

public class EditarObraAutorController {
    
    @FXML
    private ChoiceBox<String> obra; // títulos

    @FXML
    private TextField titulo;

    @FXML
    private TextField genero;

    @FXML
    private DatePicker ano;

    @FXML
    private Label erroEditarObraAutor;

    @FXML
    private Button btncancelar;

    private AutorVO autorVO;

    @FXML
    private void initialize() {
        setUsuarioVO(Telas.getUsuarioVOAtual());
    }
    
    public void setUsuarioVO(UsuarioVO usuarioVO) {
        autorVO = (AutorVO) usuarioVO;
        autorVO.setEmail(Telas.getUsuarioVOAtual().getEmail());
        AutorDAO autorDAO = new AutorDAO();
        ArrayList<AutorVO> autores = autorDAO.buscarPorEmail(autorVO);

        if (!autores.isEmpty()) {
            AutorVO primeiroAutor = autores.get(0);

            autorVO.setIDUsuario(primeiroAutor.getIDUsuario());
            autorVO.setIDAutor(primeiroAutor.getIDAutor());
            autorVO.setTipo("Autor");
            autorVO.setNome(primeiroAutor.getNome());
            autorVO.setEndereco(primeiroAutor.getEndereco());
            autorVO.setCpf(primeiroAutor.getCpf());
            autorVO.setSenha(primeiroAutor.getSenha());
        }
        obra.setItems(carregarTitulosDasObrasDoAutor(autorVO));
    }

    public ObservableList<String> carregarTitulosDasObrasDoAutor(AutorVO autorVO) {
        ObservableList<String> titulos = FXCollections.observableArrayList();
        try {
            ObraDAO obraDAO = new ObraDAO();
            ArrayList<ObraVO> obrasDoAutor = obraDAO.buscarPorAutor(autorVO);
            for (ObraVO obra : obrasDoAutor) {
                titulos.add(obra.getTitulo());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titulos;
    }

    public void concluir(ActionEvent event) throws Exception {
        String tituloText = titulo.getText();
        String generoText = genero.getText();
        LocalDate anoData = ano.getValue();

        if (obra.getValue() == null || obra.getValue().isEmpty()) {
            erroEditarObraAutor.setText("Por favor, selecione uma obra para editar.");
            erroEditarObraAutor.setVisible(true);
            
        } else {
        	throw new Exception();
        }

        try {
            AutorBO autorBO = new AutorBO();
            autorBO.editarObra(obra.getValue(), tituloText, generoText, anoData, autorVO);

            System.out.println("Edição bem-sucedida.");
            erroEditarObraAutor.setText("Edição bem-sucedida.");
            erroEditarObraAutor.setVisible(true);
            btncancelar.setText("Fechar");
        } catch (IOException e) {
            e.printStackTrace();
            erroEditarObraAutor.setText("Erro ao editar a obra.");
            erroEditarObraAutor.setVisible(true);
        }
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroEditarObraAutor.getScene().getWindow();
        stage.close();
    }
}
