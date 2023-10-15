package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BO.AutorBO;
import model.BO.AvaliadorBO;
import model.BO.ObraBO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import model.entities.Avaliador;
import view.Telas;

public class TelaPrincipalController {

    private static String visaoAtual = "obras"; //variável para saber qual visão está sendo mostrada na tabela
    private static String tipoUsuarioAtual; //variável para saber qual tipo de usuário está logado

    @FXML
    private Group AutoresOption;

    @FXML
    private Group AvaliadoresOption;

    @FXML
    private Group BaixarOption;

    @FXML
    private Group BotoesAcimadaTabela;

    @FXML
    private Group EditarOption;

    @FXML
    private Group ExcluirOption;

    @FXML
    private Group ExtraButtons;

    @FXML
    private Group LeftArea;

    @FXML
    private Group NovoOption;

    @FXML
    private Group ObrasOption;

    @FXML
    private Group RelatorioOption;

    @FXML
    private Group SearchBar;

    @FXML
    private Group Tabelas;

    @FXML
    private Group TabelasButtons;
    
    @FXML
    private ImageView background_logo;
    
    @FXML
    private ImageView borders;
    
    @FXML
    private Button botaoAdicionar;
    
    @FXML
    private Button botaoAutores;
    
    @FXML
    private Button botaoAvaliadores;
    
    @FXML
    private Button botaoBaixar;
    
    @FXML
    private Button botaoEditar;

    @FXML
    private Button botaoExcluir;
    
    @FXML
    private Button botaoRelatorio;
    
    @FXML
    private ImageView buscarBtn;
    
    @FXML
    private ImageView fundoAdicionar;
    
    @FXML
    private ImageView fundoBotaoAutores;
    
    @FXML
    private ImageView fundoBotaoAvaliadores;

    @FXML
    private ImageView fundoBotaoBaixar;
    
    @FXML
    private ImageView fundoBotaoEditar;
    
    @FXML
    private ImageView fundoBotaoExcluir;
    
    @FXML
    private ImageView fundoBotaoObras;
    
    @FXML
    private ImageView fundoBotaoRelatorio;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private Label nomeUser;
    
    @FXML
    private Button sair_btn;

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
    private Text txtBaixar;
    
    @FXML
    private Text txtEditar;
    
    @FXML
    private Text txtExcluir;
    
    @FXML
    private Text txtObras;
    
    @FXML
    private Text txtRelatorio;
    

    @FXML
    private TableView<AutorVO> mainTableViewAutores;
    
    @FXML
    private TableColumn<AutorVO, Long> id_autor;

    @FXML
    private TableColumn<AutorVO, String> nome_autor;
    
    @FXML
    private TableColumn<AutorVO, String> cpf_autor;
    
    @FXML
    private TableColumn<AutorVO, String> email_autor;

    @FXML
    private TableColumn<AutorVO, String> senha_autor;
    
    @FXML
    private TableColumn<AutorVO, String> endereco_autor;


    @FXML
    private TableView<AvaliadorVO> mainTableViewAvaliadores;
    
    @FXML
    private TableColumn<AvaliadorVO, Long> id_avaliador;
    
    @FXML
    private TableColumn<AvaliadorVO, String> nome_avaliador;
    
    @FXML
    private TableColumn<AvaliadorVO, String> cpf_avaliador;
    
    @FXML
    private TableColumn<AvaliadorVO, String> email_avaliador;
    
    @FXML
    private TableColumn<AvaliadorVO, String> senha_avaliador;
    
    @FXML
    private TableColumn<AvaliadorVO, String> endereco_avaliador;

    
    @FXML
    private TableView<ObraVO> mainTableViewObras;
    
    @FXML
    private TableColumn<ObraVO, Long> id_obra;
    
    @FXML
    private TableColumn<ObraVO, String> titulo_obra;

    @FXML
    private TableColumn<ObraVO, String> genero_obra;
    
    @FXML
    private TableColumn<ObraVO, String> status_obra;
    
    @FXML
    private TableColumn<ObraVO, String> ano_obra;
    
    @FXML
    private TableColumn<ObraVO, String> autor_obra;
    
    @FXML
    private TableColumn<ObraVO, String> avaliador_obra;

    
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
    public void initializeTableView() {

        //reseta para tudo invisível
        mainTableViewAutores.setVisible(false);
        mainTableViewAvaliadores.setVisible(false);
        mainTableViewObras.setVisible(false);

        if (tipoUsuarioAtual.equals("Gerente")) {

            if (visaoAtual.equals("obras")) { //lista todas as obras
                
                ObraBO obraBO = new ObraBO();
                ObservableList<ObraVO> dados = FXCollections.observableArrayList();
                dados.addAll(obraBO.listar());

                id_obra.setCellValueFactory(new PropertyValueFactory<>("IDObra"));
                titulo_obra.setCellValueFactory(new PropertyValueFactory<>("titulo"));
                genero_obra.setCellValueFactory(new PropertyValueFactory<>("genero"));
                status_obra.setCellValueFactory(new PropertyValueFactory<>("status"));
                ano_obra.setCellValueFactory(new PropertyValueFactory<>("ano"));
                autor_obra.setCellValueFactory(new PropertyValueFactory<>("autor"));
                avaliador_obra.setCellValueFactory(new PropertyValueFactory<>("avaliador"));

                mainTableViewObras.setItems(dados);
                mainTableViewObras.setVisible(true);

                System.out.println("Tabela de Obras");

            } else if (visaoAtual.equals("autores")) { //lista todos os autores
                
                AutorBO autorBO = new AutorBO();
                ObservableList<AutorVO> dados = FXCollections.observableArrayList();
                dados.addAll(autorBO.listar());

                id_autor.setCellValueFactory(new PropertyValueFactory<>("IDAutor"));
                nome_autor.setCellValueFactory(new PropertyValueFactory<>("nome"));
                cpf_autor.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                email_autor.setCellValueFactory(new PropertyValueFactory<>("email"));
                senha_autor.setCellValueFactory(new PropertyValueFactory<>("senha"));
                endereco_autor.setCellValueFactory(new PropertyValueFactory<>("endereco"));

                mainTableViewAutores.setItems(dados);
                mainTableViewAutores.setVisible(true);

                System.out.println("Tabela de Autores");
                
            } else if (visaoAtual.equals("avaliadores")) { //lista todos os avaliadores

                AvaliadorBO avaliadorBO = new AvaliadorBO();
                ObservableList<AvaliadorVO> dados = FXCollections.observableArrayList();
                dados.addAll(avaliadorBO.listar());

                id_avaliador.setCellValueFactory(new PropertyValueFactory<>("IDAvaliador"));
                nome_avaliador.setCellValueFactory(new PropertyValueFactory<>("nome"));
                cpf_avaliador.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                email_avaliador.setCellValueFactory(new PropertyValueFactory<>("email"));
                senha_avaliador.setCellValueFactory(new PropertyValueFactory<>("senha"));
                endereco_avaliador.setCellValueFactory(new PropertyValueFactory<>("endereco"));

                mainTableViewAvaliadores.setItems(dados);
                mainTableViewAvaliadores.setVisible(true);

                System.out.println("Tabela de Avaliadores");

            }
            
        } else if (tipoUsuarioAtual.equals("Autor")) { //lista todas as obras do autor logado
            
            AutorBO autorBO = new AutorBO();
            AutorVO autorVO = autorBO.convertToAutorVO(Telas.getUsuarioVOAtual());
            ObservableList<ObraVO> dados = FXCollections.observableArrayList();
            dados.addAll(autorBO.listarObrasProprias(autorVO));

            id_obra.setCellValueFactory(new PropertyValueFactory<>("IDObra"));
            titulo_obra.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            genero_obra.setCellValueFactory(new PropertyValueFactory<>("genero"));
            status_obra.setCellValueFactory(new PropertyValueFactory<>("status"));
            ano_obra.setCellValueFactory(new PropertyValueFactory<>("ano"));
            autor_obra.setCellValueFactory(new PropertyValueFactory<>("autor"));
            avaliador_obra.setCellValueFactory(new PropertyValueFactory<>("avaliador"));

            mainTableViewObras.setItems(dados);
            mainTableViewObras.setVisible(true);
            
        } else if (tipoUsuarioAtual.equals("Avaliador")) { //lista todas as obras avaliadas pelo avaliador logado
            
            AvaliadorBO avaliadorBO = new AvaliadorBO();
            AvaliadorVO avaliadorVO = avaliadorBO.convertToAvaliadorVO(Telas.getUsuarioVOAtual());
            ObservableList<ObraVO> dados = FXCollections.observableArrayList();
            dados.addAll(avaliadorBO.listarObrasAvaliadas(avaliadorVO));

            id_obra.setCellValueFactory(new PropertyValueFactory<>("IDObra"));
            titulo_obra.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            genero_obra.setCellValueFactory(new PropertyValueFactory<>("genero"));
            status_obra.setCellValueFactory(new PropertyValueFactory<>("status"));
            ano_obra.setCellValueFactory(new PropertyValueFactory<>("ano"));
            autor_obra.setCellValueFactory(new PropertyValueFactory<>("autor"));
            avaliador_obra.setCellValueFactory(new PropertyValueFactory<>("avaliador"));

            mainTableViewObras.setItems(dados);
            mainTableViewObras.setVisible(true);

        }
          
    }

    @FXML
    public void setApropriateScreen() {
        
        String nomeUsuario = Telas.getUsuarioVOAtual().getNome();
        tipoUsuarioAtual = Telas.getUsuarioVOAtual().getTipo();

        nomeUser.setText(nomeUsuario);
        tipoUser.setText(TelaPrincipalController.tipoUsuarioAtual);
        
        if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) { //habilita as ações de gerente e visões de tabela de gerente

            initializeTableView();
            
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
            
            initializeTableView();
            
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

            initializeTableView();

        }
    }
    
    @FXML
    public void paraObras(ActionEvent event) throws Exception {
        visaoAtual = "obras";
        setApropriateScreen();
    }
    

    @FXML
    public void paraAvaliadores(ActionEvent event) throws Exception { //botão de avaliador é apertado por gerentes
        //muda o conteúdo das tabelas e subir o botão de avaliadores
        visaoAtual = "avaliadores";
        setApropriateScreen();
    }

    @FXML
    public void paraAutores(ActionEvent event) throws Exception { //botão de autor é apertado por gerentes
        //muda o conteúdo das tabelas e subir o botão de autores
        visaoAtual = "autores";
        setApropriateScreen();
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
    private void buscar(ActionEvent event) { //ao apertar o botão de busca
        String busca = searchBar.getText();

        if (busca == null || busca.isEmpty()) {
            //pop-up de campo vazio
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
                Telas.telaNovaObraAutor(Telas.getUsuarioVOAtual());
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
                	Telas.telaDeletarObraGerente();
                } else if (visaoAtual.equals("autores")) {
                	Telas.telaDeletarAutor();
                } else if (visaoAtual.equals("avaliadores")) {
                	Telas.telaDeletarAvaliador();
                }
            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
                Telas.telaDeletarObraAutor(Telas.getUsuarioVOAtual());
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
                Telas.telaEditarObraAutor(Telas.getUsuarioVOAtual());
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarAutor(ActionEvent event) { //apenas gerentes podem editar autores
        try {
            Telas.telaEditarAutor();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void adicionarNovoAvaliador(ActionEvent event) { //apenas gerentes podem adicionar avaliadores
        try {
            Telas.telaEditarAvaliador();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarAvaliador(ActionEvent event) { //apenas gerentes podem editar avaliadores
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
    
    @FXML
    public void avaliarObra(ActionEvent event) { 
    	try {
			Telas.telaAvaliarObra(Telas.getUsuarioVOAtual());;
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
