package com.odanielrodrigues.notation;

import java.io.IOException;
import java.time.LocalDate;

import com.odanielrodrigues.notation.gui.AdicionaNotaController;
import com.odanielrodrigues.notation.gui.principalController;
import com.odanielrodrigues.notation.model.Notation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane principal;
	
	private ObservableList<Notation> dados = FXCollections.observableArrayList();

	public Main() {
		
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("NotationApp");
		initMainStage();
		carregarTela();
		
	}

	private void carregarTela() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("gui/principal.fxml"));
			AnchorPane notationView = (AnchorPane) loader.load();
			
			this.principal.setCenter(notationView);


			principalController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException ex) {
			ex.printStackTrace();
			
		}
		
	}

	private void initMainStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("gui/container.fxml"));
			this.principal = (BorderPane) loader.load();

			Scene scene = new Scene(this.principal);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			

		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("XXXADAS");
		}

	}
	
	public boolean mostarContatoDialog(Notation notation) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("gui/adicionarNota.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicionar Notation");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.primaryStage);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			AdicionaNotaController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setNotation(notation);
			
			dialogStage.showAndWait();
			
			return controller.isOnCliked();
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
			
			return false;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Notation> getDados() {
		return this.dados;
	}
}
