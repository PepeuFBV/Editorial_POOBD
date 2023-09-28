package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

// TODO:
// checar se o método visibleProperty().set() apenas tira da visão ou também desabilita o botão

public class TelaPrincipalController {

    private String tipoUsuario;

    public void inicializar(String tipoUsuario, String nomeUsuario) { //checar se está correto os argumentos
        Scene scene = null;
        try {
            scene = HelloFX.telaPrincipal();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
        
        Text nomeDoUsuario = (Text) scene.lookup("#nomeUser");
        nomeDoUsuario.setText(nomeUsuario);

        Text tipoDoUsuario = (Text) scene.lookup("#tipoUser");
        tipoDoUsuario.setText(tipoUsuario);

        if (tipoUsuario.equals("gerente")) { //habilitar ações de gerente e visões de tabela de gerente
            
            //mudar a tabela para a de todas as obras (será recebido um ResultSet)

        } else if (tipoUsuario.equals("autor")) { //habilitar ações de autor e visões de tabela de autor

            Button botao = (Button) scene.lookup("#botaoRelatorio");
            botao.visibleProperty().set(false);
            ImageView fundoBotao = (ImageView) scene.lookup("#fundoRelatorio");
            fundoBotao.visibleProperty().set(false);
            Text textoBotao = (Text) scene.lookup("#txtRelatorio");
            textoBotao.visibleProperty().set(false);

            botao = (Button) scene.lookup("#botaoAvaliadores");
            botao.visibleProperty().set(false);
            fundoBotao = (ImageView) scene.lookup("#fundoAvaliadores");
            fundoBotao.visibleProperty().set(false);
            textoBotao = (Text) scene.lookup("#txtAvaliadores");

            botao = (Button) scene.lookup("#botaoAutores");
            botao.visibleProperty().set(false);
            fundoBotao = (ImageView) scene.lookup("#fundoAutores");
            fundoBotao.visibleProperty().set(false);
            textoBotao = (Text) scene.lookup("#txtAutores");
            textoBotao.visibleProperty().set(false);

            //mudar a tabela para a das obras do próprio autor (será recebido um ResultSet)

        } else { //habilitar ações de avaliador e visões de tabela de avaliador
            
            Button botao = (Button) scene.lookup("#botaoAutores");
            botao.visibleProperty().set(false);
            ImageView fundoBotao = (ImageView) scene.lookup("#fundoAutores");
            fundoBotao.visibleProperty().set(false);
            Text textoBotao = (Text) scene.lookup("#txtAutores");
            textoBotao.visibleProperty().set(false);

            botao = (Button) scene.lookup("#botaoAvaliadores");
            botao.visibleProperty().set(false);
            fundoBotao = (ImageView) scene.lookup("#fundoAvaliadores");
            fundoBotao.visibleProperty().set(false);
            textoBotao = (Text) scene.lookup("#txtAvaliadores");
            textoBotao.visibleProperty().set(false);

            //mudar a tabela para a das obras que o avaliador deve avaliar (será recebido um ResultSet)

        }
    }

    @FXML
    public void paraAvaliadores(ActionEvent event) throws Exception { //botão de avaliador é apertado por gerentes
        
        

    }

    @FXML
    public void paraAutores(ActionEvent event) throws Exception { //botão de autor é apertado por gerentes
        //muda o conteúdo das tabelas e botões para o de autor
    }

    @FXML
    public void sair(ActionEvent event) throws Exception { //checar se método funciona
        HelloFX.telaLogin();
    }

    @FXML
    public void adicionarNovaObra(ActionEvent event) {
        try {
            if (tipoUsuario.equals("gerente")) {
                HelloFX.telaNovaObraGerente();
            } else if (tipoUsuario.equals("autor")){
                HelloFX.telaNovaObraAutor();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void deletarObra(ActionEvent event) {
        try {
            if (tipoUsuario.equals("gerente")) {
                HelloFX.telaDeletarObra();
            } else if (tipoUsuario.equals("autor")){
                HelloFX.telaDeletarObra();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarObra(ActionEvent event) {
        try {
            if (tipoUsuario.equals("gerente")) {
                HelloFX.telaEditarObraGerente();
            } else if (tipoUsuario.equals("autor")) {
                HelloFX.telaEditarObraAutor();
            }
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void adicionarNovoAutor(ActionEvent event) {
        try {
            HelloFX.telaEditarAutor();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarAutor(ActionEvent event) {
        try {
            HelloFX.telaEditarAutor();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void excluirAutor(ActionEvent event) {
        try {
            HelloFX.telaDeletarAutor();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void adicionarNovoAvaliador(ActionEvent event) {
        try {
            HelloFX.telaEditarAvaliador();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void editarAvaliador(ActionEvent event) {
        try {
            HelloFX.telaEditarAvaliador();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void excluirAvaliador(ActionEvent event) {
        try {
            HelloFX.telaDeletarAvaliador();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

    @FXML
    public void gerarRelatorio(ActionEvent event) { //botão só está disponível para gerente
        try {
            HelloFX.telaGerarRelatorio();
        } catch (Exception e) {
            e.printStackTrace(); //criar nova Exception para lidar com isso
        }
    }

}
