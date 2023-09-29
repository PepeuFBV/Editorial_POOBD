package br.edu.ufersa.EditorialdoPaulao.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeletarObraController {

    @FXML
    private Button buttonSim;

    @FXML
    private Button buttonNao;

    @FXML
    private void handleSimButtonAction(ActionEvent event) {

    	//lógica para deletar a obra
    	
    	//if ("Gerente".equals(UsuarioBO.getTipoDeUsuario)) {
    		//o usuário é um gerente
    		//abrir a tela de obras para gerente
    	//} else {
	    	//o usuário é um autor
	    	//abrir a tela de obras para autor
		//} 
    	System.out.println("Obra excluída com sucesso.");
    }

    @FXML
    private void handleNaoButtonAction(ActionEvent event) {
    	//if ("Gerente".equals(UsuarioBO.getTipoDeUsuario)) {
    	    //o usuário é um gerente
    		//abrir a tela de obras para gerente

    	//} else {
    	    //o usuário é um autor
    	    //abrir a tela de obras para autor
    	//}
    	} 
}


