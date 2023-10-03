package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.BO.UserBO;
import view.Telas;

public class TelaPrincipalController {

    private String nomeUsuario;

    private String tipoUsuario;

    private String visaoAtual = "obras"; //variável para saber qual visão está sendo mostrada na tabela

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
    private TextField searchBar;

    @FXML
    private TableColumn<?, ?> status_obra;

    @FXML
    private Label tipoUser;

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

    public void initialize(UserBO userBO) { //checar se está correto os argumentos

        String nomeUsuario = userBO.getNome();
        String tipoUsuario = "autor";
        //tipoUsuario = userBO.getTipo();

        /*
        nomeUser.setText(nomeUsuario); //fix
        tipoUser.setText(tipoUsuario);
        */

        if (tipoUsuario.equals("gerente")) { //habilita as ações de gerente e visões de tabela de gerente

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
            if (tipoUsuario.equals("Gerente")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar todas as obras
                    } else if (visaoAtual.equals("autores")) {
                        //mostrar todos os autores
                    } else if (visaoAtual.equals("avaliadores")) {
                        //mostrar todos os avaliadores
                    }

            } else if (tipoUsuario.equals("autor")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar obras do próprio autor 
                    }

            } else if (tipoUsuario.equals("avaliador")) {
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
        if (tipoUsuario.equals("gerente")) {
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
        } else if (tipoUsuario.equals("autor")) {
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
            if (tipoUsuario.equals("gerente")) {
                if (visaoAtual.equals("obras")) {
                	Telas.telaDeletarObra();
                } else if (visaoAtual.equals("autores")) {
                	Telas.telaDeletarAutor();
                } else if (visaoAtual.equals("avaliadores")) {
                	Telas.telaDeletarAvaliador();
                }
            } else if (tipoUsuario.equals("autor")) {
                Telas.telaDeletarObra();
            } else if (tipoUsuario.equals("avaliador")) {
                Telas.telaDeletarAvaliacao(); //fazer telaDeletarAvaliacao
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
