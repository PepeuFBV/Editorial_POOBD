package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.BO.AutorBO;
import model.DAO.AutorDAO;
import model.VO.AutorVO;
import model.VO.UsuarioVO;
import view.Telas;

public class NovaObraAutorController {
	
	@FXML
	private TextField showFileautor;
	
	@FXML
	private TextField titulo;
	
	@FXML
	private TextField genero;
	
	@FXML
	private DatePicker ano;
	
	@FXML
	private Label erroNovaObraAutor;
	
	@FXML
	private Button btncancelar;
	
	private AutorVO autorVO;
	
    @FXML
    public void initialize() {
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
    }
	
	@FXML
	public void handleBtnOpenFile(ActionEvent event) {
		final FileChooser fc = new FileChooser();
		fc.setTitle("Seleção da Obra");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.*"));
		File file = fc.showOpenDialog(null);
		if (file != null) {
			showFileautor.appendText(file.getAbsolutePath() + "\n");
		} else {
			System.out.println("Você deve selecionar um pdf");
		}
	}
	
	public void adicionar(ActionEvent event) {
	    String obraSelecionada = showFileautor.getText();
	    String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    LocalDate anoData = ano.getValue();

	    try {
	        AutorBO autorBO = new AutorBO();
	        autorBO.adicionarObra(obraSelecionada, tituloText, generoText, anoData, autorVO);

	        System.out.println("Obra adicionada com sucesso.");
	        erroNovaObraAutor.setText("Obra adicionada com sucesso.");
	        erroNovaObraAutor.setVisible(true);
	        btncancelar.setText("Fechar");
	    } catch (IOException e) {
	        e.printStackTrace();
	        erroNovaObraAutor.setText("Erro ao ler o arquivo PDF.");
	        erroNovaObraAutor.setVisible(true);
	    } catch (IllegalArgumentException e) {
	        erroNovaObraAutor.setText(e.getMessage());
	        erroNovaObraAutor.setVisible(true);
	    }
	}

	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroNovaObraAutor.getScene().getWindow();
	    stage.close();
	}
}


