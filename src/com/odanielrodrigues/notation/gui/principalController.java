package com.odanielrodrigues.notation.gui;

import com.odanielrodrigues.notation.Main;
import com.odanielrodrigues.notation.model.Notation;
import com.odanielrodrigues.notation.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class principalController {

	@FXML
	private TableView<Notation> tabela;
	@FXML
	private TableColumn<Notation, String> colunaNome;
	@FXML
	private TableColumn<Notation, String> colunaSobrenome;

	@FXML
	private Label rotuloNome;
	@FXML
	private Label rotuloSobrenome;
	@FXML
	private Label rotuloData;

	private Main main;

	public principalController() {
	}

	@FXML
	private void initialize() {
		colunaNome.setCellValueFactory(celula -> celula.getValue().criadorProperty());
		colunaSobrenome.setCellValueFactory(celula -> celula.getValue().tituloProperty());

		mostraDetalhe(null);

		tabela.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostraDetalhe(newValue));

	}

	public void setMain(Main main) {
		this.main = main;
		this.tabela.setItems(main.getDados());
	}

	private void mostraDetalhe(Notation notation) {
		if (notation != null) {
			rotuloNome.setText(notation.getCriador());
			rotuloSobrenome.setText(notation.getTitulo());

		} else {
			rotuloNome.setText("");
			rotuloSobrenome.setText("");
			rotuloData.setText("");
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
	}
	
	@FXML
	private void hadleEditarNota() {
		Notation selecionado = tabela.getSelectionModel().getSelectedItem();
		if(selecionado != null) {
			boolean onClicked = main.mostarContatoDialog(selecionado);
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
