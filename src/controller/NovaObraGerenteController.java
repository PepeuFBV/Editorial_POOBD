package controller;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import model.DAO.UsersDAO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;

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
	private TextField ano;
	
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
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.*"));
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
	        AutorDAO autorDAO = new AutorDAO();
	        ResultSet autoresDoDB = autorDAO.listar();

	        while (autoresDoDB.next()) {
	            String nomeAutor = autoresDoDB.getString("nome");
	            int autorID = autoresDoDB.getInt("id_autor");
	            autores.add(nomeAutor);
	            autorParaIDMap.put(nomeAutor, autorID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    try {
	        AvaliadorDAO avaDAO = new AvaliadorDAO();
	        ResultSet avaliadoresDoDB = avaDAO.listar();

	        while (avaliadoresDoDB.next()) {
	            String nomeAvaliador = avaliadoresDoDB.getString("nome");
	            int avaliadorID = avaliadoresDoDB.getInt("id_avaliador");
	            avaliadores.add(nomeAvaliador);
	            avaliadorParaIDMap.put(nomeAvaliador, avaliadorID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    autor.setItems(autores);
	    avaliador.setItems(avaliadores);
	    stts.setItems(status);
	}


	public void adicionar(ActionEvent event) {
	    String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    String anoText = ano.getText();
	    String autorSelecionado = autor.getValue();
	    String avaliadorSelecionado = avaliador.getValue();
	    String statusSelecionado = stts.getValue();
	    String obraSelecionada = showFileger.getText();

	    if (obraSelecionada.isEmpty() ||tituloText.isEmpty() || generoText.isEmpty() || anoText.isEmpty() || autorSelecionado == null || avaliadorSelecionado == null || statusSelecionado == null) {
	        erroNovaObraGerente.setText("Por favor, preencha todos os campos.");
	        erroNovaObraGerente.setVisible(true);
	        return;
	    }

	    ObraVO novaObra = new ObraVO();
	    novaObra.setTitulo(tituloText);
	    novaObra.setGenero(generoText);
	    LocalDate anoLocalDate = LocalDate.parse(anoText);
	    novaObra.setAno(anoLocalDate);


	    int idAutor = autorParaIDMap.get(autorSelecionado);
	    UsuarioVO autorUsuario = new UsuarioVO();
	    autorUsuario.setIDUsuario((long) idAutor);
	    autorUsuario.setTipo("Autor");
	    UsersDAO<AutorVO> autorDAO = new UsersDAO<AutorVO>();
	    ResultSet autorRS = autorDAO.buscarPorId(autorUsuario);

	    AutorVO autorVO = new AutorVO();

	    try {
	        if (autorRS.next()) {
	            autorVO.setIDUsuario(autorRS.getLong("id_usuario"));
	            autorVO.setNome(autorRS.getString("nome"));
	            autorVO.setCpf(autorRS.getString("cpf"));
	            autorVO.setEmail(autorRS.getString("email"));
	            autorVO.setEndereco(autorRS.getString("endereco"));
	            autorVO.setSenha(autorRS.getString("senha"));
	            autorVO.setTipo(autorRS.getString("tipo"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    int idAvaliador = avaliadorParaIDMap.get(avaliadorSelecionado);
	    novaObra.setAvaliador(null);
	    UsuarioVO avaliadorUsuario = new UsuarioVO();
	    avaliadorUsuario.setIDUsuario((long) idAvaliador);
	    avaliadorUsuario.setTipo("Avaliador");
	    UsersDAO<AvaliadorVO> avaliadorDAO = new UsersDAO<AvaliadorVO>();
	    ResultSet avaliadorRS = avaliadorDAO.buscarPorId(autorUsuario);

	    AvaliadorVO avaliadorVO = new AvaliadorVO();

	    try {
	        if (avaliadorRS.next()) {
	        	avaliadorVO.setIDUsuario(autorRS.getLong("id_usuario"));
	        	avaliadorVO.setNome(autorRS.getString("nome"));
	        	avaliadorVO.setCpf(autorRS.getString("cpf"));
	        	avaliadorVO.setEmail(autorRS.getString("email"));
	        	avaliadorVO.setEndereco(autorRS.getString("endereco"));
	        	avaliadorVO.setSenha(autorRS.getString("senha"));
	        	avaliadorVO.setTipo(autorRS.getString("tipo"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    novaObra.setStatus(statusSelecionado);
	    novaObra.setPdfObra(obraSelecionada);

	    ObraDAO obraDAO = new ObraDAO();
	    obraDAO.inserir(novaObra);

	    System.out.println("Obra adicionada com sucesso.");
	    erroNovaObraGerente.setText("Obra adicionada com sucesso.");
	    erroNovaObraGerente.setVisible(true);
	    btncancelar.setText("Fechar");
	}

	
	public void cancelar(ActionEvent event) {
	    Stage stage = (Stage) erroNovaObraGerente.getScene().getWindow();
	    stage.close();
	}
}
