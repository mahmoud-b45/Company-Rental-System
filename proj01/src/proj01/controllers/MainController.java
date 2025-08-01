package proj01.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import proj01.InfoSys;

public class MainController {

	@FXML
	private Button homeBtn, addCustomer, addRentableBtn, exitBtn, rentBtn, saveBtn;

//	@FXML
//	private AnchorPane contentAnchorPane;
	@FXML
	private StackPane contentStackPane;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void initialize() {
		try {
			HomeController controller = master("/proj01/fx/Home.fxml").getController();
			controller.setStage(stage);
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
	void rentReturn(ActionEvent event) {
		try {
			RentReturnController controller = master("/proj01/fx/RentReturn.fxml").getController();
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
		InfoSys.mainSelection = 0;
		System.exit(0);
	}

}
