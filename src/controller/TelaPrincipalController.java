package controller;

import javafx.fxml.FXML;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BO.AutorBO;
import model.BO.AvaliadorBO;
import model.BO.ObraBO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import view.Telas;

public class TelaPrincipalController {

    private static String visaoAtual = "obras"; //variável para saber qual visão está sendo mostrada na tabela
    private static String tipoUsuarioAtual; //variável para saber qual tipo de usuário está logado

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
    private Group RelatorioOption;

    @FXML
    private Group SearchBar;

    @FXML
    private Group Tabelas;

    @FXML
    private Group autores;

    @FXML
    private Group avaliadores;

    @FXML
    private ImageView background_logo;

    @FXML
    private Button baixarBtn;

    @FXML
    private ImageView borders;

    @FXML
    private ImageView buscarBtn;

    @FXML
    private Button editarBtn;

    @FXML
    private Button excluirBtn;

    @FXML
    private ImageView fundoAdicionar;

    @FXML
    private ImageView fundoBotaoAutoresA;

    @FXML
    private ImageView fundoBotaoAutoresD;

    @FXML
    private ImageView fundoBotaoAvaliadoresA;

    @FXML
    private ImageView fundoBotaoAvaliadoresD;

    @FXML
    private ImageView fundoBotaoBaixar;

    @FXML
    private ImageView fundoBotaoEditar;

    @FXML
    private ImageView fundoBotaoExcluir;

    @FXML
    private ImageView fundoBotaoObrasA;

    @FXML
    private ImageView fundoBotaoObrasD;

    @FXML
    private ImageView fundoRelatorio;

    @FXML
    private ImageView logo;

    @FXML
    private Label nomeUser;

    @FXML
    private Group obras;

    @FXML
    private Button sair_btn;

    @FXML
    private TextField searchBar;

    @FXML
    private Label tipoUser;

    @FXML
    private Text txtAdicionar;

    @FXML
    private Text txtAutoresA;

    @FXML
    private Text txtAutoresD;

    @FXML
    private Text txtAvaliadoresA;

    @FXML
    private Text txtAvaliadoresD;

    @FXML
    private Text txtObrasA;

    @FXML
    private Text txtObrasD;

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


    @FXML
    private Button paraObrasBtn;

    @FXML
    private Button paraAutoresBtn;

    @FXML
    private Button paraAvaliadoresBtn;

    @FXML
    private Button adicionarBtn;

    @FXML
    private Button relatorioBtn;

    @FXML
    private Button buscarBtnPress;

    @FXML
    private ToggleButton avaliadasBtn;

    @FXML
    private ToggleButton emAvaliacaoBtn;

    @FXML
    private ToggleButton emEsperaBtn;

    @FXML
    private Button definirAvaliadorBtn;

    
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

    public List<ObraVO> getCurrentTable() {
        //pega os dados da tabela obras atual
        ObservableList<ObraVO> dados = mainTableViewObras.getItems();
        List<ObraVO> obras = dados.subList(0, dados.size());
        return obras;
    }

    @FXML
    public void defineObrasTableView(ActionEvent event) {
        initializeTableView();
        defineObrasTableView(getCurrentTable());
    }

    @FXML
    public void defineObrasTableView(List<ObraVO> obras) {

        ObraBO obraBO = new ObraBO();
        if (emEsperaBtn.isSelected()) {
            obras = obraBO.filtrarSemAvaliador(obras);
        }
        if (avaliadasBtn.isSelected()) {
            obras = obraBO.filtrarAvaliadas(obras);
        }
        if (emAvaliacaoBtn.isSelected()) {
            obras = obraBO.filtrarEmEspera(obras);
        }

        ObservableList<ObraVO> dados = FXCollections.observableArrayList();
        dados.addAll(obras);

        id_obra.setCellValueFactory(new PropertyValueFactory<>("IDObra"));
        titulo_obra.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        genero_obra.setCellValueFactory(new PropertyValueFactory<>("genero"));
        status_obra.setCellValueFactory(new PropertyValueFactory<>("status"));
        ano_obra.setCellValueFactory(new PropertyValueFactory<>("ano"));
        autor_obra.setCellValueFactory(new PropertyValueFactory<>("autor"));
        avaliador_obra.setCellValueFactory(new PropertyValueFactory<>("avaliador"));

        mainTableViewObras.setItems(dados);

        System.out.println("Obras encontradas pela busca!");
    }

    @FXML
    public void defineAvaliadoresTableView(List<AvaliadorVO> avaliadores) {

        ObservableList<AvaliadorVO> dados = FXCollections.observableArrayList();
        dados.addAll(avaliadores);

        id_avaliador.setCellValueFactory(new PropertyValueFactory<>("IDAvaliador"));
        nome_avaliador.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpf_avaliador.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        email_avaliador.setCellValueFactory(new PropertyValueFactory<>("email"));
        senha_avaliador.setCellValueFactory(new PropertyValueFactory<>("senha"));
        endereco_avaliador.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        mainTableViewAvaliadores.setItems(dados);

        System.out.println("Avaliadores encontrados pela busca!");
    }

    @FXML
    public void defineAutoresTableView(List<AutorVO> autores) {

        ObservableList<AutorVO> dados = FXCollections.observableArrayList();
        dados.addAll(autores);

        id_autor.setCellValueFactory(new PropertyValueFactory<>("IDAutor"));
        nome_autor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpf_autor.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        email_autor.setCellValueFactory(new PropertyValueFactory<>("email"));
        senha_autor.setCellValueFactory(new PropertyValueFactory<>("senha"));
        endereco_autor.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        mainTableViewAutores.setItems(dados);

        System.out.println("Autores encontrados pela busca!");
    }

    @FXML
    public void initializeButtons() {

        //reseta tudo para inivísivel
        RelatorioOption.setVisible(false);
        relatorioBtn.setVisible(false);

        fundoBotaoAutoresA.setVisible(false);
        fundoBotaoAutoresD.setVisible(false);
        txtAutoresA.setVisible(false);
        txtAutoresD.setVisible(false);
        paraAutoresBtn.setVisible(false);

        fundoBotaoAvaliadoresA.setVisible(false);
        fundoBotaoAvaliadoresD.setVisible(false);
        txtAvaliadoresA.setVisible(false);
        txtAvaliadoresD.setVisible(false);
        paraAvaliadoresBtn.setVisible(false);

        fundoBotaoObrasA.setVisible(false);
        fundoBotaoObrasD.setVisible(false);
        txtObrasA.setVisible(false);
        txtObrasD.setVisible(false);
        paraObrasBtn.setVisible(false);

        emEsperaBtn.setVisible(false);
        emAvaliacaoBtn.setVisible(false);
        avaliadasBtn.setVisible(false);

        if (tipoUsuarioAtual.equals("Gerente")) {

            RelatorioOption.setVisible(true);
            relatorioBtn.setVisible(true);
            if (visaoAtual.equals("obras")) {
                fundoBotaoAutoresD.setVisible(true);
                txtAutoresD.setVisible(true);
                paraAutoresBtn.setVisible(true);

                fundoBotaoAvaliadoresD.setVisible(true);
                txtAvaliadoresD.setVisible(true);
                paraAvaliadoresBtn.setVisible(true);

                fundoBotaoObrasA.setVisible(true);
                txtObrasA.setVisible(true);

                emEsperaBtn.setVisible(true);
                emAvaliacaoBtn.setVisible(true);
                avaliadasBtn.setVisible(true);
            } else if (visaoAtual.equals("autores")) {
                fundoBotaoAutoresA.setVisible(true);
                txtAutoresA.setVisible(true);

                fundoBotaoAvaliadoresD.setVisible(true);
                txtAvaliadoresD.setVisible(true);
                paraAvaliadoresBtn.setVisible(true);

                fundoBotaoObrasD.setVisible(true);
                txtObrasD.setVisible(true);
                paraObrasBtn.setVisible(true);
            } else if (visaoAtual.equals("avaliadores")) {
                fundoBotaoAutoresD.setVisible(true);
                txtAutoresD.setVisible(true);
                paraAutoresBtn.setVisible(true);

                fundoBotaoAvaliadoresA.setVisible(true);
                txtAvaliadoresA.setVisible(true);

                fundoBotaoObrasD.setVisible(true);
                txtObrasD.setVisible(true);
                paraObrasBtn.setVisible(true);
            }

        } else if (tipoUsuarioAtual.equals("Autor")) {

            if (visaoAtual.equals("obras")) {
                fundoBotaoObrasA.setVisible(true);
                txtObrasA.setVisible(true);
                paraObrasBtn.setVisible(true);

                emEsperaBtn.setVisible(true);
                emAvaliacaoBtn.setVisible(true);
                avaliadasBtn.setVisible(true);
            }

        } else if (tipoUsuarioAtual.equals("Avaliador")) {

            if (visaoAtual.equals("obras")) {
                fundoBotaoObrasA.setVisible(true);
                txtObrasA.setVisible(true);
                paraObrasBtn.setVisible(true);

                emEsperaBtn.setVisible(true);
                emAvaliacaoBtn.setVisible(true);
                avaliadasBtn.setVisible(true);

                fundoBotaoEditar.setVisible(false);
                editarBtn.setVisible(false);

            }
        }

    }

    @FXML
    public void setApropriateScreen() {
        
        String nomeUsuario = Telas.getUsuarioVOAtual().getNome();
        tipoUsuarioAtual = Telas.getUsuarioVOAtual().getTipo();

        nomeUser.setText(nomeUsuario);
        tipoUser.setText(TelaPrincipalController.tipoUsuarioAtual);

        initializeButtons();
        initializeTableView();
        
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
            System.out.println("Saiu da conta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buscar(ActionEvent event) { //ao apertar o botão de busca
        String busca = searchBar.getText();

        if (busca == null || busca.isEmpty()) {
            //TODO pop-up de campo vazio
        } else {
            
            if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                if (visaoAtual.equals("obras")) {
                    ObraBO obraBO = new ObraBO();
                    List<ObraVO> lista = obraBO.listarObrasPelaBusca(busca);
                    defineObrasTableView(lista);
                } else if (visaoAtual.equals("autores")) {
                    AutorBO autorBO = new AutorBO();
                    List<AutorVO> lista = autorBO.listarAutoresPelaBusca(busca);
                    defineAutoresTableView(lista);
                } else if (visaoAtual.equals("avaliadores")) {
                    AvaliadorBO avaliadorBO = new AvaliadorBO();
                    List<AvaliadorVO> lista = avaliadorBO.listarAvaliadoresPelaBusca(busca);
                    defineAvaliadoresTableView(lista);
                }

            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
                if (visaoAtual.equals("obras")) {
                    ObraBO obraBO = new ObraBO();
                    List<ObraVO> obras = obraBO.filtrarParaAutorAtual(obraBO.listarObrasPelaBusca(busca));
                    defineObrasTableView(obras);
                }

            } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Avaliador")) {
                if (visaoAtual.equals("obras")) {
                    ObraBO obraBO = new ObraBO();
                    List<ObraVO> obras = obraBO.filtrarParaAvaliadorAtual(obraBO.listarObrasPelaBusca(busca));
                    defineObrasTableView(obras);
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
        } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Avaliador")) {
            try {
                Telas.telaAvaliarObra(Telas.getUsuarioVOAtual());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void excluir(ActionEvent event) {
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
    public void editar(ActionEvent event) {
        try {
            if (TelaPrincipalController.visaoAtual.equals("obras")) {
                if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                    Telas.telaEditarObraGerente();
                } else if (TelaPrincipalController.tipoUsuarioAtual.equals("Autor")) {
                    Telas.telaEditarObraAutor(Telas.getUsuarioVOAtual());
                }
            } else if (TelaPrincipalController.visaoAtual.equals("autores")) {
                if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                    Telas.telaEditarAutor();
                }
            } else if (TelaPrincipalController.visaoAtual.equals("avaliadores")) {
                if (TelaPrincipalController.tipoUsuarioAtual.equals("Gerente")) {
                    Telas.telaEditarAvaliador();
                }
            }
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
            Telas.telaAvaliarObra(Telas.getUsuarioVOAtual());
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void baixar(ActionEvent event) { 
    	try {
			Telas.telaBaixarObra(Telas.getUsuarioVOAtual());;
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void definirAvaliador(ActionEvent event) { 
    	try {
			Telas.telaDefinirAvaliador();;
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
