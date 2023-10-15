package controller;

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
import model.BO.AutorBO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;
import view.Telas;

public class DeletarObraAutorController {
	
	@FXML
	private Label erroDeletarObraAutor;
	
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
        AutorBO autorBO = new AutorBO();
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
            ObraDAO obraDAO = new ObraDAO();
            ArrayList<ObraVO> obrasDoAutor = obraDAO.buscarPorAutor(autorVO);
            for (ObraVO obra : obrasDoAutor) {
                titulos.add(obra.getTitulo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titulos;
    }
	
	@FXML
	private void deletar(ActionEvent event) {
	    ObservableList<String> obrasList = obra.getItems();
	    if (!obrasList.isEmpty()) {
	        String tituloObra = obrasList.get(0);
	        ObraVO obra = new ObraVO();
	        obra.setTitulo(tituloObra);
	        ObraDAO obraDAO = new ObraDAO();
	        try {
				List<ObraVO> obras = obraDAO.buscarPorTitulo(obra);
				ObraVO primeiraObra = obras.get(0);
				ObraVO obraExcluir = new ObraVO();
				obraExcluir.setIDObra(primeiraObra.getIDObra());
		        obraDAO.excluir(obraExcluir);
			} catch (SQLException e) {
				e.printStackTrace();
			}       
	        
	        carregarTitulosDasObrasDoAutor(autorVO);

	        erroDeletarObraAutor.setText("Obra excluída com sucesso.");
	        erroDeletarObraAutor.setVisible(true);
	    }
	}
    
    @FXML
    private void voltar(ActionEvent event) {
	    Stage stage = (Stage) erroDeletarObraAutor.getScene().getWindow();
	    stage.close();
    }
}


