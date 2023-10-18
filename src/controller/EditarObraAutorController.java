package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import model.BO.ObraBO;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;
import view.Telas;

public class EditarObraAutorController {
	
    @FXML
    private TextField showFileAutor;
    
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
    
    AutorBO autorBO = new AutorBO();
    
    @FXML
    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção da Obra");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.pdf*"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            showFileAutor.appendText(file.getAbsolutePath() + "\n");
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }

    @FXML
    private void initialize() {
        setUsuarioVO(Telas.getUsuarioVOAtual());
    }
    
    public void setUsuarioVO(UsuarioVO usuarioVO) {
        autorVO = (AutorVO) usuarioVO;
        autorVO.setEmail(Telas.getUsuarioVOAtual().getEmail());
        List<AutorVO> autores = autorBO.buscarPorEmail(autorVO);

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
            ObraBO obraBO = new ObraBO();
            ArrayList<ObraVO> obrasDoAutor = obraBO.buscarPorAutor(autorVO);
            for (ObraVO obra : obrasDoAutor) {
                titulos.add(obra.getTitulo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titulos;
    }

    public void concluir(ActionEvent event) throws Exception {
    	String obraSelecionada = showFileAutor.getText();
        String tituloText = titulo.getText();
        String generoText = genero.getText();
        LocalDate anoData = ano.getValue();
        String obraChoiceBox = obra.getValue();

        if (obraChoiceBox == null || obraChoiceBox.isEmpty()) {
            erroEditarObraAutor.setText("Por favor, selecione uma obra para editar.");
            erroEditarObraAutor.setVisible(true);
            
        }

        try {
            autorBO.editarObra(obraChoiceBox, tituloText, generoText, anoData, autorVO, obraSelecionada);

            System.out.println("Edição bem-sucedida.");
            erroEditarObraAutor.setText("Edição bem-sucedida.");
            erroEditarObraAutor.setVisible(true);
            btncancelar.setText("Fechar");
        } catch (IOException e) {
            e.printStackTrace();
            erroEditarObraAutor.setText("Erro ao editar a obra.");
            erroEditarObraAutor.setVisible(true);
        }
		catch (Exception ex) {
		    ex.printStackTrace();
		    erroEditarObraAutor.setText("Erro ao editar a obra: " + ex.getMessage());
		    erroEditarObraAutor.setVisible(true);
		}
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroEditarObraAutor.getScene().getWindow();
        stage.close();
    }
}
