package controller;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BO.AutorBO;
import model.BO.UserBO;
import model.entities.Obra;
import view.Telas;

public class TelaPrincipalController {

    private UserBO userBO;

    private String visaoAtual = "obras"; //variável para saber qual visão está sendo mostrada na tabela

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
    private TableColumn<?, Integer> idTabela; //como faz isso?

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

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    public UserBO getUserBO() {
        return userBO;
    }

    public void setVisaoAtual(String visaoAtual) {
        this.visaoAtual = visaoAtual;
    }

    public String getVisaoAtual() {
        return visaoAtual;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void setApropriateScreen() {
        
        String nomeUsuario = userBO.getNome();
        String tipoUsuario = userBO.getTipo();
        
        nomeUser.setText(nomeUsuario);
        tipoUser.setText(tipoUsuario);
        

        if (userBO.getTipo().equals("gerente")) { //habilita as ações de gerente e visões de tabela de gerente

            //mudar a tabela para a das obras (será recebido um ResultSet)

        } else if (userBO.getTipo().equals("autor")) { //habilitar ações de autor e visões de tabela de autor

            botaoRelatorio.setVisible(false);
            fundoBotaoRelatorio.visibleProperty().set(false);
            txtRelatorio.visibleProperty().set(false);

            botaoAvaliadores.visibleProperty().set(false);
            fundoBotaoAvaliadores.visibleProperty().set(false);
            txtAvaliadores.visibleProperty().set(false);

            botaoAutores.visibleProperty().set(false);
            fundoBotaoAutores.visibleProperty().set(false);
            txtAutores.visibleProperty().set(false);

            //lembrar de no botão adicionar obra checar o tipo de user para mostrar a tela correta

            AutorBO autorBO = new AutorBO(userBO.getId(), userBO.getNome(), userBO.getLogin(), userBO.getSenha());
            List<Obra> obras = autorBO.getObras(); //mudar para o método que retorna as obras do autor
             //define o título da coluna da tabela
            for (Obra obra : obras) {
                //adiciona as obras na tabela, fazer isso para cada obra
                obra.getId();
                obra.getTitulo();
                obra.getGenero();
                obra.getAno();
                obra.getStatus();
                obra.getAutor();
                obra.getAvaliador();

            }

        } else if (userBO.getTipo().equals("Avaliador")) { //habilitar ações de avaliador e visões de tabela de avaliador

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
            if (userBO.getTipo().equals("Gerente")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar todas as obras
                    } else if (visaoAtual.equals("autores")) {
                        //mostrar todos os autores
                    } else if (visaoAtual.equals("avaliadores")) {
                        //mostrar todos os avaliadores
                    }

            } else if (userBO.getTipo().equals("autor")) {
                    if (visaoAtual.equals("obras")) {
                        //mostrar obras do próprio autor 
                    }

            } else if (userBO.getTipo().equals("avaliador")) {
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
        if (userBO.getTipo().equals("gerente")) {
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
        } else if (userBO.getTipo().equals("autor")) {
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
            if (userBO.getTipo().equals("gerente")) {
                if (visaoAtual.equals("obras")) {
                	Telas.telaDeletarObra();
                } else if (visaoAtual.equals("autores")) {
                	Telas.telaDeletarAutor();
                } else if (visaoAtual.equals("avaliadores")) {
                	Telas.telaDeletarAvaliador();
                }
            } else if (userBO.getTipo().equals("autor")) {
                Telas.telaDeletarObra();
            } else if (userBO.getTipo().equals("avaliador")) {
                Telas.telaDeletarAvaliacao(); //fazer telaDeletarAvaliacao
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarObra(ActionEvent event) {
        try {
            if (userBO.getTipo().equals("gerente")) {
                Telas.telaEditarObraGerente();
            } else if (userBO.getTipo().equals("autor")) {
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
