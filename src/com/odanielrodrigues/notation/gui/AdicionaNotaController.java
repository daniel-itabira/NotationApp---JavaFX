package com.odanielrodrigues.notation.gui;

import com.odanielrodrigues.notation.model.Notation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionaNotaController {
	
	@FXML
	private TextField txtCriador;
	@FXML
	private TextField txtTitulo;
	
	private Stage dialogoStage;
	private Notation notation;
	private boolean onCliked = false;
	

	@FXML
	private void initialize(){
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogoStage = dialogStage;
	}
	
	public void setNotation(Notation notation) {
		this.notation = notation;
		txtCriador.setText(notation.getCriador());
		txtTitulo.setText(notation.getTitulo());
		
		
		//DateUtil.format(notation.getData())
	}
	
	public boolean isOnCliked() {
		return this.onCliked;
	}
	
	@FXML
	public void handleCancelar( ) {
		this.dialogoStage.close();
	}
	
	@FXML
	public void handleSalvar() {
		if(ehValidaEntrada()) {
			notation.setCriador(txtCriador.getText());
			notation.setTitulo(txtTitulo.getText());
			
			//falta colocar data.. 
			this.onCliked = true;
			dialogoStage.close();
		}
		
	}


	private boolean ehValidaEntrada() {
		String mensagem = "";
		
		if(txtCriador.getText() == null || txtCriador.getText().isEmpty() || txtCriador.getText().length() <=3) {
			mensagem +="Nome Invalido                \n";
		}
		
		if(txtTitulo.getText() == null || txtTitulo.getText().isEmpty() || txtTitulo.getText().length() <=3) {
			mensagem +="Titulo Invalido                 \n";
		}
		
		if(mensagem.length() == 0) {return true;}
		else {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Campos Inválidos");
			alert.setHeaderText("Por favor, corrija as informações");
			alert.setContentText(mensagem);
			alert.showAndWait();
			return false;
		}
		
	}

}
