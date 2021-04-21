package com.odanielrodrigues.notation.gui;

import com.odanielrodrigues.notation.util.*;
import com.odanielrodrigues.notation.Main;
import com.odanielrodrigues.notation.model.Notation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.HTMLEditor;

public class principalController {

	@FXML
	private TableView<Notation> tabela;
	@FXML
	private TableColumn<Notation, String> colunaNome;
	@FXML
	private TableColumn<Notation, String> colunaTitulo;

	@FXML
	private Label rotuloTitulo;
	@FXML
	private Label rotuloData;
	@FXML
	private Label rotuloNotation;
	
	
	@FXML 
	HTMLEditor htmleditor;
	
	private Main main;

	public principalController() {
	}

	@FXML
	private void initialize() {
		colunaNome.setCellValueFactory(celula -> celula.getValue().criadorProperty());
		colunaTitulo.setCellValueFactory(celula -> celula.getValue().tituloProperty());
		
		
		tabela.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostraDetalhe(newValue));
		mostraDetalhe(null);
		
		tabela.setPlaceholder(new Label(""));
	}
	
	public void setMain(Main main) {
		this.main = main;
		this.tabela.setItems(main.getDados());
		
	}
	
	private void mostraDetalhe(Notation notation) {
		
		
		if (notation != null) {
			
			htmleditor.setDisable(false);
			System.out.println(tabela.getSelectionModel().getSelectedIndex());
			rotuloTitulo.setText(notation.getTitulo());
			htmleditor.setHtmlText(notation.getSalvaHTML().get());
			rotuloData.setText(DateUtil.format(notation.getData()));
			
			String x = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>";
			String y = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>";
			
			if(htmleditor.getHtmlText().toString().equals(x) || htmleditor.getHtmlText().toString().equals(y)) {
				System.out.println("CAMPO EDITOR VAZIO");
			}else {
				
				htmleditor.setHtmlText(notation.getSalvaHTML().get());
			}
								
		} else {
			rotuloTitulo.setText("");
			rotuloData.setText("");
			
		}
	}
	
	@FXML
	private void handleSalvaNota() {
		
		
		int selecionado = tabela.getSelectionModel().getSelectedIndex();
		if(selecionado >=0) {
			StringProperty aux = new SimpleStringProperty(htmleditor.getHtmlText());
			tabela.getItems().get(selecionado).setHTML(aux.get());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("O conteúdo da sua NOTATION foi salvo!");
			alert.showAndWait();
		}else {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Não existem NOTATION selecionada!");
			alert.setHeaderText("Você precisa criar/selecionar uma NOTATION para salvar o conteudo!");
			alert.showAndWait();
		}
		
							
	}
	
	@FXML
	private void handleNovaNota() {
		Notation temp = new Notation();
		boolean onClicked = main.mostarContatoDialog(temp);
		if (onClicked) {
			main.getDados().add(temp);
		}
	}

	@FXML
	private void handleExcluirNota() {
		int selecionado = tabela.getSelectionModel().getSelectedIndex();
		if(selecionado >= 0) {
			tabela.getItems().remove(selecionado);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notation excluida com sucesso!");
			alert.setHeaderText("Sua notation foi excluida!");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhuma Notation foi selecionada");
			alert.setContentText("Por favor, selecione uma notation para poder excluir!");
			alert.showAndWait();
			
		}
		
		if(tabela.getSelectionModel().isEmpty() == true) {
			htmleditor.setHtmlText("");
			htmleditor.setDisable(true);
		}
	}
	
	@FXML
	private void hadleEditarNota() {
		Notation selecionado = tabela.getSelectionModel().getSelectedItem();
		if(selecionado != null) {
			boolean onClicked = main.mostarContatoDialog2(selecionado);
			if(onClicked) this.mostraDetalhe(selecionado);
			
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhuma Notation foi selecionada");
			alert.setContentText("Por favor, selecione uma notation para poder editar!");
			alert.showAndWait();
			
		}
	}
	
	


	
	
	

}
