package controller;

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
import model.BO.ObraBO;
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
	
	@FXML
	private void deletar(ActionEvent event) {
	    String tituloText = obra.getValue();
	    if (!tituloText.isEmpty()) {
	        String tituloObra = tituloText;
	        ObraVO obra = new ObraVO();
	        obra.setTitulo(tituloObra);
	        ObraBO obraBO = new ObraBO();

            List<ObraVO> obras = obraBO.buscarPorTitulo(obra);
            ObraVO primeiraObra = obras.get(0);
            ObraVO obraExcluir = new ObraVO();
            obraExcluir.setIDObra(primeiraObra.getIDObra());
            obraBO.excluir(obraExcluir);      
	        
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


