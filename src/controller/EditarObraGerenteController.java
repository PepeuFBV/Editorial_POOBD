package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

public class EditarObraGerenteController {

    @FXML
    private TextField showFileger;
    
    @FXML
    private TextField showFileger2;
    
    @FXML
    private DatePicker data;
    
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
    private DatePicker ano;

    @FXML
    private Label erroEditarObraGerente;

    @FXML
    private Button btncancelar;
    
    ObraBO obraBO = new ObraBO();
    
    AutorBO autorBO = new AutorBO();
    
    AvaliadorBO avaliadorBO = new AvaliadorBO();

    private Map<String, Long> autorParaIDMap = new HashMap<>();
    private Map<String, Long> avaliadorParaIDMap = new HashMap<>();

    @FXML
    public void handleBtnOpenFile(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção da Obra");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.pdf*"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            showFileger.appendText(file.getAbsolutePath() + "\n");
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }
    
    @FXML
    public void handleBtnOpenFile2(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Seleção da Obra");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.pdf*"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            showFileger2.appendText(file.getAbsolutePath() + "\n");
        } else {
            System.out.println("Você deve selecionar um arquivo");
        }
    }

    @FXML
    private void initialize() {
    	stts.setValue("Avaliador Pendente");
    	stts.setDisable(true);
    	data.setDisable(true);
    	ObservableList<String> status = FXCollections.observableArrayList("Avaliador Pendente", "Em avaliação", "Aceita", "Rejeitada");

    	avaliador.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
    	    if (newValue != null) {
    	        stts.setDisable(false);
    	    } else {
    	    	stts.getItems().remove("Avaliador Pendente");
    	        stts.setDisable(true);
    	        data.setValue(null);
    	        data.setDisable(true);
    	    }
    	});

    	stts.valueProperty().addListener((obs, oldValue, newValue) -> {
    	    if (newValue != null && (newValue.equals("Em avaliação") || newValue.equals("Avaliador Pendente"))) {
    	        data.setValue(null);
    	        data.setDisable(true);
    	    } else {
    	        data.setDisable(false);
    	    }
    	});

        ObservableList<String> autores = FXCollections.observableArrayList();
        ObservableList<String> avaliadores = FXCollections.observableArrayList();
        ObservableList<String> obrasList = FXCollections.observableArrayList();
        
        try {
            List<ObraVO> obrasDoDB = obraBO.listar(); 
            for (ObraVO obraVO : obrasDoDB) {
                String tituloObra = obraVO.getTitulo();
                obrasList.add(tituloObra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        obras.setItems(obrasList);

        try {
            List<AutorVO> autoresDoDB = autorBO.listar();
            for (AutorVO autorVO : autoresDoDB) {
                String nomeAutor = autorVO.getNome();
                Long autorID = autorVO.getIDAutor();
                autores.add(nomeAutor);
                autorParaIDMap.put(nomeAutor, autorID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<AvaliadorVO> avaliadoresDoDB = avaliadorBO.listar();
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

        ObraVO obraEncontrada = buscarObraPorTitulo(tituloSelecionado);

        if (obraEncontrada != null) {
            String tituloText = titulo.getText();
            String generoText = genero.getText();
            LocalDate anoData = ano.getValue();
            String autorSelecionado = autor.getValue();
            String avaliadorSelecionado = avaliador.getValue();
            String statusSelecionado = stts.getValue();
            LocalDate dataSelecionada = data.getValue();
            String obraSelecionada = showFileger.getText();
            String avaliacaoSelecionada = showFileger2.getText();
            
            if ("Avaliador Pendente".equals(statusSelecionado) && avaliadorSelecionado != null) {
                erroEditarObraGerente.setText("Você não pode selecionar 'Avaliador Pendente' quando um avaliador está selecionado.");
                erroEditarObraGerente.setVisible(true);
                return;
            }
            
            if (statusSelecionado == null) {
                statusSelecionado = "Avaliador Pendente";
            }else {
                obraEncontrada.setStatus(statusSelecionado);
                
                if ("Aceita".equals(statusSelecionado) || "Rejeitada".equals(statusSelecionado)) {
                    if (dataSelecionada == null) {
                        erroEditarObraGerente.setText("Você deve inserir uma data.");
                        erroEditarObraGerente.setVisible(true);
                        return;
                    }
                    
                    if (avaliacaoSelecionada.isEmpty()) {
                        erroEditarObraGerente.setText("Você deve selecionar um arquivo de avaliação.");
                        erroEditarObraGerente.setVisible(true);
                        return;
                    }
                    
                } else {
                    obraEncontrada.setDataAvaliacao(null);
                }
            }
            
            if (!obraSelecionada.isEmpty()) {
                try {
                    byte[] pdfConteudo = LerPDF.lerConteudoPDF(obraSelecionada);
                    obraEncontrada.setPdfObra(pdfConteudo);
                } catch (IOException e) {
                    erroEditarObraGerente.setText("Erro ao ler o arquivo PDF.");
                    erroEditarObraGerente.setVisible(true);
                    return;
                }
            } else {
            	obraEncontrada.setPdfObra(obraEncontrada.getPdfObra());
            }
            
            if (!avaliacaoSelecionada.isEmpty()) {
                try {
                    byte[] pdfConteudo = LerPDF.lerConteudoPDF(avaliacaoSelecionada);
                    obraEncontrada.setPdfAvaliacao(pdfConteudo);
                } catch (IOException e) {
                    erroEditarObraGerente.setText("Erro ao ler o arquivo PDF.");
                    erroEditarObraGerente.setVisible(true);
                    return;
                }
            } else {
            	obraEncontrada.setPdfAvaliacao(obraEncontrada.getPdfAvaliacao());
            }

            if(tituloText.isEmpty()) {
            	obraEncontrada.setTitulo(obraEncontrada.getTitulo());
            } else {
            	obraEncontrada.setTitulo(tituloText);
            }
            
            if(generoText.isEmpty()) {
            	obraEncontrada.setGenero(obraEncontrada.getGenero());
            } else {
            	obraEncontrada.setGenero(generoText);
            }

            if(anoData == null) {
            	obraEncontrada.setAno(obraEncontrada.getAno());
            } else {
                obraEncontrada.setAno(anoData);
            }
            
            if (avaliadorSelecionado == null || avaliadorSelecionado.isEmpty()) {
            	obraEncontrada.setAvaliador(obraEncontrada.getAvaliador());
            	obraEncontrada.setStatus(obraEncontrada.getStatus());
            	obraEncontrada.setDataAvaliacao(obraEncontrada.getDataAvaliacao());
            	obraEncontrada.setPdfAvaliacao(obraEncontrada.getPdfAvaliacao());
            } else {
                long idAvaliador = avaliadorParaIDMap.get(avaliadorSelecionado);
                AvaliadorVO avaliadorVO = new AvaliadorVO();
                avaliadorVO.setIDAvaliador(idAvaliador);
                List<AvaliadorVO> avaliadores = avaliadorBO.buscarPorId(avaliadorVO);
                AvaliadorVO primeiroAvaliador = avaliadores.get(0);
                
                avaliadorVO.setIDUsuario(primeiroAvaliador.getIDUsuario());
                avaliadorVO.setCpf(primeiroAvaliador.getCpf());
                avaliadorVO.setEmail(primeiroAvaliador.getEmail());
                avaliadorVO.setEndereco(primeiroAvaliador.getEndereco());
                avaliadorVO.setNome(primeiroAvaliador.getNome());
                avaliadorVO.setSenha(primeiroAvaliador.getSenha());
                avaliadorVO.setTipo("Avaliador");
                
                obraEncontrada.setAvaliador(avaliadorVO);
            }
            
            if(autorSelecionado == null || autorSelecionado.isEmpty()) {
            	obraEncontrada.setAutor(obraEncontrada.getAutor());
            } else {
                long idAutor = autorParaIDMap.get(autorSelecionado);
                AutorVO autorVO = new AutorVO();
                autorVO.setIDAutor(idAutor);
                List<AutorVO> autores = autorBO.buscarPorId(autorVO);
                AutorVO primeiroAutor = autores.get(0);
                
                autorVO.setIDUsuario(primeiroAutor.getIDUsuario());
                autorVO.setCpf(primeiroAutor.getCpf());
                autorVO.setEmail(primeiroAutor.getEmail());
                autorVO.setEndereco(primeiroAutor.getEndereco());
                autorVO.setNome(primeiroAutor.getNome());
                autorVO.setSenha(primeiroAutor.getSenha());
                autorVO.setTipo("Autor");
                
                obraEncontrada.setAutor(autorVO);
            }

            obraBO.atualizar(obraEncontrada);

            System.out.println("Obra atualizada com sucesso.");
            erroEditarObraGerente.setText("Obra atualizada com sucesso.");
            erroEditarObraGerente.setVisible(true);
            btncancelar.setText("Fechar");
        }
    }

    private ObraVO buscarObraPorTitulo(String titulo) {
        ObraVO obra = new ObraVO();
        obra.setTitulo(titulo);

        ArrayList<ObraVO> obrasEncontradas = obraBO.buscarPorTitulo(obra);
        if (!obrasEncontradas.isEmpty()) {
            return obrasEncontradas.get(0);
        }

        return null;
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroEditarObraGerente.getScene().getWindow();
        stage.close();
    }
}
