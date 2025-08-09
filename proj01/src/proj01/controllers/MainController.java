package proj01.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import proj01.InfoSys;

public class MainController {

	@FXML
	private Button homeBtn, addCustomer, addRentableBtn, exitBtn, rentBtn, saveBtn, consoleBtn;

	@FXML
	private StackPane contentStackPane=new StackPane();

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void initialize() {
		try {
			HomeController controller = master("/proj01/fx/Home.fxml").getController();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FXMLLoader master(String str) throws IOException {
		contentStackPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
		Parent root = loader.load();
		contentStackPane.getChildren().add(root);
		return loader;
	}

	@FXML
	private TextField consoleInputTextField;
	@FXML
	private TextArea consoleOutputTextArea;
	
	@FXML
	void onConsole() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/proj01/fx/console.fxml"));
			Parent parent=	loader.load();
			Scene newScene = new Scene(parent);
			Stage consoleWindow = new Stage();
			consoleWindow.setScene(newScene);
			consoleWindow.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onHome() {
		try {
			HomeController controller = master("/proj01/fx/Home.fxml").getController();
			controller.setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addCustomer(ActionEvent event) throws IOException {
		try {
			AddCustomerController controller = master("/proj01/fx/AddCustomers.fxml").getController();
			controller.setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addRentable(ActionEvent event) {
		try {
			AddRentableController controller = master("/proj01/fx/AddRentable.fxml").getController();
			controller.setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void rentReturnOperation(ActionEvent event) {
		try {
			RentReturnController controller = master("/proj01/fx/RentReturnOperation.fxml").getController();
			controller.setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void save(ActionEvent event) {
		InfoSys.save();
	}

	@FXML
	void exit(ActionEvent event) {
		stage.close();
		System.exit(0);
	}

}
