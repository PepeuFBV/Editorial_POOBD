package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BO.UserBO;
import view.Telas;

public class TelaPrincipalController {

    private String visaoAtual = "obras"; //inicializa com a visão de obras

    private String tipoUsuario;

    private String nomeUsuario;

    @FXML
    private TableColumn<?, ?> ano_obra;

    @FXML
    private TableColumn<?, ?> autor_obra;

    @FXML
    private TableColumn<?, ?> avaliador_obra;

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
    private TableColumn<?, ?> genero_obra;

    @FXML
    private TableColumn<?, ?> id_obra;

    @FXML
    private Label nomeUser;
    
    @FXML
    private Label tipoUser;

    @FXML
    private TableColumn<?, ?> status_obra;

    @FXML
    private TableColumn<?, ?> titulo_obra;

    @FXML
    private Text txtAdicionar;

    @FXML
    private Text txtAutores;

    @FXML
    private Text txtAvaliadores;

    @FXML
    private Text txtRelatorio;

    @FXML
    private TextField searchBar;

    public void start() { }

    public void start(UserBO userBO) { //checar se está correto os argumentos

        nomeUsuario = userBO.getNome();
        tipoUsuario = userBO.getTipo();

        nomeUser.setText(nomeUsuario); //nomeUser é null pointer ?????? (consertar) TODO
        tipoUser.setText(tipoUsuario);

        if (tipoUsuario.equals("Gerente")) { //habilitar ações de gerente e visões de tabela de gerente
            
          //mudar a tabela para a das obras (será recebido um ResultSet)

        } else if (tipoUsuario.equals("autor")) { //habilitar ações de autor e visões de tabela de autor

            botaoRelatorio.visibleProperty().set(false);
            fundoBotaoRelatorio.visibleProperty().set(false);
            txtRelatorio.visibleProperty().set(false);

            botaoAvaliadores.visibleProperty().set(false);
            fundoBotaoAvaliadores.visibleProperty().set(false);
            txtAvaliadores.visibleProperty().set(false);

            botaoAutores.visibleProperty().set(false);
            fundoBotaoAutores.visibleProperty().set(false);
            txtAutores.visibleProperty().set(false);

            //lembrar de no botão adicionar obra checar o tipo de user para mostrar a tela correta

            //mudar a tabela para a das obras do próprio autor (será recebido um ResultSet)

        } else if (tipoUsuario.equals("Avaliador")) { //habilitar ações de avaliador e visões de tabela de avaliador
            
            botaoRelatorio.visibleProperty().set(false);
            fundoBotaoRelatorio.visibleProperty().set(false);
            txtRelatorio.visibleProperty().set(false);

            botaoAutores.visibleProperty().set(false);
            fundoBotaoAutores.visibleProperty().set(false);
            txtAutores.visibleProperty().set(false);

            botaoAvaliadores.visibleProperty().set(false);
            fundoBotaoAvaliadores.visibleProperty().set(false);
            txtAvaliadores.visibleProperty().set(false);

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
            if (visaoAtual == "obras") {
                
            } else if (visaoAtual == "autores") {
                
            } else if (visaoAtual == "avaliadores") {
                
            }
            
        }

    }
    

    @FXML
    public void paraAvaliadores(ActionEvent event) throws Exception { //botão de avaliador é apertado por gerentes
        //muda o conteúdo das tabelas e botões para o de avaliadores
        visaoAtual = "avaliadores";

    }

    @FXML
    public void paraAutores(ActionEvent event) throws Exception { //botão de autor é apertado por gerentes
        //muda o conteúdo das tabelas e botões para o de autor
        visaoAtual = "autores";
    }

    @FXML
    public void sair(ActionEvent event) throws Exception { //checar se método funciona
        Telas.telaLogin();
    }

    @FXML
    public void adicionarNovaObra(ActionEvent event) {
    	try {
			Telas.telaNovaObraGerente();
		} catch (Exception e) {
			e.printStackTrace();
		}
        /*try {
            if (tipoUsuario.equals("gerente")) {
                Telas.telaNovaObraGerente();
            } else if (tipoUsuario.equals("autor")){
                Telas.telaNovaObraAutor();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }*/
    }

    @FXML
    public void deletarObra(ActionEvent event) {
        try {
            if (tipoUsuario.equals("gerente")) {
                Telas.telaDeletarObra();
            } else if (tipoUsuario.equals("autor")){
                Telas.telaDeletarObra();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarObra(ActionEvent event) {
        try {
            if (tipoUsuario.equals("gerente")) {
                Telas.telaEditarObraGerente();
            } else if (tipoUsuario.equals("autor")) {
                Telas.telaEditarObraAutor();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void adicionarNovoAutor(ActionEvent event) {
        try {
            Telas.telaEditarAutor();
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
    public void excluirAutor(ActionEvent event) {
        try {
            Telas.telaDeletarAutor();
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
    public void excluirAvaliador(ActionEvent event) {
        try {
            Telas.telaDeletarAvaliador();
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
