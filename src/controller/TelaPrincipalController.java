package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.DAO.ObraDAO;
import model.VO.ObraVO;
import view.Telas;

public class TelaPrincipalController {

    private static String visaoAtual = "obras"; //variável para saber qual visão está sendo mostrada na tabela
    private static String tipoUsuarioAtual; //variável para saber qual tipo de usuário está logado

    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAutores;

    @FXML
    private Button botaoAvaliadores;

    @FXML
    private Button botaoRelatorio;

    @FXML
    private ImageView fundoAdicionar;

    @FXML
    private ImageView fundoBotaoAutores;

    @FXML
    private ImageView fundoBotaoAvaliadores;

    @FXML
    private ImageView fundoBotaoRelatorio;

    @FXML
    private Label nomeUser;

    @FXML
    private TextField searchBar;

    @FXML
    private Label tipoUser;

    @FXML
    private Text txtAdicionar;

    @FXML
    private Text txtAutores;

    @FXML
    private Text txtAvaliadores;

    @FXML
    private Text txtRelatorio;

    public static void setVisaoAtual(String visaoAtual) {
        if (visaoAtual != null) {
            TelaPrincipalController.visaoAtual = visaoAtual;
        }
    }

    public static String getVisaoAtual() {
        return visaoAtual;
    }

    public static String getTipoUsuario() {
        return TelaPrincipalController.tipoUsuarioAtual;
    }

    public static void setTipoUsuario(String tipoUsuario) {
        if (TelaPrincipalController.tipoUsuarioAtual != null) {
            TelaPrincipalController.tipoUsuarioAtual = Telas.getUsuarioVOAtual().getTipo();
        } else {
            throw new NullPointerException("O tipo de usuário não pode ser nulo na tela principal");
        }
    }

    @FXML
    public void setApropriateScreen() {
        
        String nomeUsuario = Telas.getUsuarioVOAtual().getNome();
        tipoUsuarioAtual = Telas.getUsuarioVOAtual().getTipo();

        nomeUser.setText(nomeUsuario);
        tipoUser.setText(TelaPrincipalController.tipoUsuarioAtual);
        

        if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) { //habilita as ações de gerente e visões de tabela de gerente

            ObraDAO obrasDAO = new ObraDAO();
            ArrayList<ObraVO> obras = obrasDAO.listar();

            
            //cria a tableView principal e a estiliza
            TableView<ObraVO> mainTableView = new TableView<>();
            mainTableView.setLayoutX(38.0);
            mainTableView.setLayoutY(111.0);
            mainTableView.setPrefHeight(263.0);
            mainTableView.setPrefWidth(535.0);
            mainTableView.setStyle("-fx-border-color: #01FFB3; -fx-border-radius: 10; -fx-border-width: 6; -fx-padding: 35 0 0 0;");

            //cria as colunas da tabela
            TableColumn<ObraVO, Long> idColumn = new TableColumn<>("ID");
            idColumn.setPrefWidth(28.7999267578125);
            idColumn.setEditable(false);
            ObservableList<Long> obrasNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                Long id = (obra.getIDObra());
                obrasNaTabela.add(id);
            }

            
            TableColumn<ObraVO, String> tituloColumn = new TableColumn<>("Título");
            tituloColumn.setPrefWidth(96.79998779296875);
            tituloColumn.setEditable(false);
            ObservableList<String> titulosNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                String titulo = (obra.getTitulo());
                titulosNaTabela.add(titulo);
            }


            TableColumn<ObraVO, String> generoColumn = new TableColumn<>("Gênero");
            generoColumn.setPrefWidth(72.0);
            generoColumn.setEditable(false);
            ObservableList<String> generosNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                String genero = (obra.getGenero());
                generosNaTabela.add(genero);
            }


            TableColumn<ObraVO, LocalDate> anoColumn = new TableColumn<>("Ano");
            anoColumn.setPrefWidth(34.4000244140625);
            anoColumn.setEditable(false);
            ObservableList<LocalDate> anosNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                LocalDate ano = (obra.getAno());
                anosNaTabela.add(ano);
            }

            TableColumn<ObraVO, String> statusColumn = new TableColumn<>("Status");
            statusColumn.setPrefWidth(84.79998779296875);
            statusColumn.setEditable(false);
            ObservableList<String> statusNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                String status = (obra.getStatus());
                statusNaTabela.add(status);
            }

            TableColumn<ObraVO, String> autorColumn = new TableColumn<>("Autor");
            autorColumn.setPrefWidth(87.20001220703125);
            autorColumn.setEditable(false);
            ObservableList<String> autoresNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                String autor = (obra.getAutor().getNome());
                autoresNaTabela.add(autor);
            }

            TableColumn<ObraVO, String> avaliadorColumn = new TableColumn<>("Avaliador");
            avaliadorColumn.setPrefWidth(67.20001220703125);
            avaliadorColumn.setEditable(false);
            ObservableList<String> avaliadoresNaTabela = FXCollections.observableArrayList();
            for (ObraVO obra : obras) {
                String avaliador = (obra.getAvaliador().getNome());
                avaliadoresNaTabela.add(avaliador);
            }

            //fazer os botões de opções
            TableColumn<ObraVO, String> opcoesColumn = new TableColumn<>("Opções");
            opcoesColumn.setPrefWidth(54.39996337890625);
            opcoesColumn.setEditable(false);

            //adiciona todas as colunas a tabela
            List<TableColumn<ObraVO, ?>> columns = new ArrayList<>();
            columns.addAll(Arrays.asList(idColumn, tituloColumn, generoColumn, anoColumn, statusColumn, autorColumn, avaliadorColumn, opcoesColumn));
            mainTableView.getColumns().addAll(columns);

        } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) { //habilitar ações de autor e visões de tabela de autor

            botaoRelatorio.setVisible(false);
            fundoBotaoRelatorio.visibleProperty().set(false);
            txtRelatorio.visibleProperty().set(false);

            botaoAvaliadores.visibleProperty().set(false);
            fundoBotaoAvaliadores.visibleProperty().set(false);
            txtAvaliadores.visibleProperty().set(false);

            botaoAutores.visibleProperty().set(false);
            fundoBotaoAutores.visibleProperty().set(false);
            txtAutores.visibleProperty().set(false);


        } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Avaliador")) { //habilitar ações de avaliador e visões de tabela de avaliador

            botaoRelatorio.visibleProperty().set(false);
            fundoBotaoRelatorio.visibleProperty().set(false);
            txtRelatorio.visibleProperty().set(false);

            botaoAutores.visibleProperty().set(false);
            fundoBotaoAutores.visibleProperty().set(false);
            txtAutores.visibleProperty().set(false);

            botaoAvaliadores.visibleProperty().set(false);
            fundoBotaoAvaliadores.visibleProperty().set(false);
            txtAvaliadores.visibleProperty().set(false);

            botaoAdicionar.visibleProperty().set(false);
            fundoAdicionar.visibleProperty().set(false);
            txtAdicionar.visibleProperty().set(false);

            //mudar a tabela para a das obras que o avaliador deve avaliar (será recebido um ResultSet)

        }
    }
    
    @FXML
    private void buscar(ActionEvent event) { //ao apertar o botão de busca
        String busca = searchBar.getText();

        if (busca == null || busca.isEmpty()) {
            //pop-up de campo vázio
        } else {
        //mudar a tabela para a das obras/autores/avaliadores que contém a string de busca (será recebido um ResultSet)
            if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar todas as obras
                    } else if (visaoAtual.equals("autores")) {
                        //mostrar todos os autores
                    } else if (visaoAtual.equals("avaliadores")) {
                        //mostrar todos os avaliadores
                    }

            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar obras do próprio autor 
                    }

            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Avaliador")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar obras que o avaliador deve avaliar
                    }
            }
            
        }

    }
    

    @FXML
    public void paraAvaliadores(ActionEvent event) throws Exception { //botão de avaliador é apertado por gerentes
        //muda o conteúdo das tabelas e subir o botão de avaliadores

        visaoAtual = "avaliadores";
    }

    @FXML
    public void paraAutores(ActionEvent event) throws Exception { //botão de autor é apertado por gerentes
        //muda o conteúdo das tabelas e subir o botão de autores

        visaoAtual = "autores";
    }

    @FXML
    public void sair(ActionEvent event) throws Exception { //checar se método funciona
        try {
            Telas.telaLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void adicionarNovo(ActionEvent event) {
        if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
            if (visaoAtual.equals("obras")) {
                try {
                    Telas.telaNovaObraGerente();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (visaoAtual.equals("autores")) {
                try {
                    Telas.telaNovoAutorGerente();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (visaoAtual.equals("avaliadores")) {
                try {
                    Telas.telaNovoAvaliadorGerente();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
            try {
                Telas.telaNovaObraAutor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void deletar(ActionEvent event) {
        try {
            if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                if (visaoAtual.equals("obras")) {
                	Telas.telaDeletarObra();
                } else if (visaoAtual.equals("autores")) {
                	Telas.telaDeletarAutor();
                } else if (visaoAtual.equals("avaliadores")) {
                	Telas.telaDeletarAvaliador();
                }
            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
                Telas.telaDeletarObra();
            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Avaliador")) {
                Telas.telaDeletarAvaliacao(); //fazer telaDeletarAvaliacao
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarObra(ActionEvent event) {
        try {
            if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                Telas.telaEditarObraGerente();
            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
                Telas.telaEditarObraAutor();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarAutor(ActionEvent event) {
        try {
            Telas.telaEditarAutor();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void adicionarNovoAvaliador(ActionEvent event) {
        try {
            Telas.telaEditarAvaliador();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarAvaliador(ActionEvent event) {
        try {
            Telas.telaEditarAvaliador();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void gerarRelatorio(ActionEvent event) { //botão só está disponível para gerente
    	try {
			Telas.telaGerarRelatorio();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
