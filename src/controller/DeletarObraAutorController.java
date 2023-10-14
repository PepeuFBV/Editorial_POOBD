package controller;


import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAO.AutorDAO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;
import view.Telas;

public class DeletarObraAutorController {
	
	@FXML
	private Label erroDeletarObra;
	
	@FXML
	private ChoiceBox<String> obra;
	
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
	
	@FXML
	private void deletar(ActionEvent event) {
	    ObservableList<String> obrasList = obra.getItems();
	    if (!obrasList.isEmpty()) {
	        String tituloObra = obrasList.get(0);
	        ObraVO obraExcluir = new ObraVO();
	        obraExcluir.setTitulo(tituloObra);
	        
	        ObraDAO obraDAO = new ObraDAO();
	        obraDAO.excluir(obraExcluir);
	        
	        carregarTitulosDasObrasDoAutor(autorVO);

	        erroDeletarObra.setText("Obra excluída com sucesso.");
	        erroDeletarObra.setVisible(true);
	    }
	}
    
    @FXML
    private void voltar(ActionEvent event) {
	    Stage stage = (Stage) erroDeletarObra.getScene().getWindow();
	    stage.close();
    }
}


