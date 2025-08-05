package proj01.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import proj01.Car;
import proj01.InfoSys;
import proj01.RealEstate;
import proj01.Rentable;

public class AddRentableController implements Initializable {

	@FXML
	private Button addCarBtn, addRealEstateBtn, clearBtn, deleteBtn, /* goBackBtn, */ addImageCarBtn,
			addImageRealEstateBtn;

	@FXML
	private Label carBrandLabel, carIDLabel, carModelLabel, carPlateNoLabel, monthlyPriceLabel, monthlyPriceLabel1,
			realEstateIDLabel, realEstateLocationLabel, realEstateTypeLabel;

	@FXML
	private TextField carBrandField, carIDField, carModelField, carMonthlyPriceField, carPlateNoField,
			realEstateIDField, realEstateLocationField, realEstateMonthlyPriceField, realEstateTypeField;

	@FXML
	private Tab carTab, realEstateTab;

	@FXML
	private TableView<Car> carTable;
	@FXML
	private TableView<RealEstate> realEstateTable;

	@FXML
	private TableColumn<Car, Integer> carIDColumn, carPlateNoColumn, carModelColumn, carMonthlyPriceColumn;
	@FXML
	private TableColumn<Car, String> carBrandColumn;
	@FXML
	private TableColumn<Car, Boolean> carStatusColumn;

	@FXML
	private TableColumn<RealEstate, Integer> realEstateIDColumn, realEstateMonthlyPriceColumn;
	@FXML
	private TableColumn<RealEstate, String> realEstateTypeColumn, realEstateLocationColumn;
	@FXML
	private TableColumn<RealEstate, Boolean> realEstateStatusColumn;

	private ArrayList<Car> carsList = new ArrayList<>();
	private ArrayList<RealEstate> realEstatesList = new ArrayList<>();

	private ObservableList<Car> carsObservableList;
	private ObservableList<RealEstate> realEstatesObservableList;

	private Stage stage;
	private ArrayList<Rentable> arr = InfoSys.rentables;

	@FXML
	private ImageView carImageView, realEstateImageView;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	Stage newWindow = new Stage();

	void openImageInNewWindow(ImageView imageView) {
		ImageView imgV = new ImageView();// create new view because after closing
		// the window/stage the view gets lost
		imgV.setImage(imageView.getImage());
		imgV.setPreserveRatio(true);
		StackPane pane = new StackPane(imgV);

		if (imgV.getImage().getWidth() > imgV.getImage().getHeight()) {
			imageView.fitWidthProperty().bind(pane.widthProperty());
		} else {
			imageView.fitHeightProperty().bind(pane.heightProperty());
		}
		Scene scene = new Scene(pane, 600, 500);
		newWindow.setScene(scene);
		newWindow.show();
	}

	/**
	 * update sets or lists
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// enable addImage btn after selecting a car/realestate
		addImageCarBtn.disableProperty().bind(Bindings.isEmpty(carTable.getSelectionModel().getSelectedItems()));// method 1
		addImageRealEstateBtn.disableProperty().bind(carTable.getSelectionModel().selectedItemProperty().isNull());// method 2

		// listen for double click and view image in a new window
		carImageView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				openImageInNewWindow(carImageView);
			}
		});

		addCarBtn.disableProperty()
				.bind(carIDField.textProperty().isEmpty().or(carPlateNoField.textProperty().isEmpty())
						.or(carModelField.textProperty().isEmpty()).or(carBrandField.textProperty().isEmpty())
						.or(carMonthlyPriceField.textProperty().isEmpty()) // Check if DatePicker is empty (null)
				);

		addRealEstateBtn.disableProperty()
				.bind(realEstateIDField.textProperty().isEmpty().or(realEstateTypeField.textProperty().isEmpty())
						.or(realEstateLocationField.textProperty().isEmpty())
						.or(realEstateMonthlyPriceField.textProperty().isEmpty()) // Check if DatePicker is empty (null)
				);

		carTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		realEstateTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		for (Rentable rentable : arr) {
			if (rentable instanceof Car) {
				carsList.add((Car) rentable);
			} else {
				realEstatesList.add((RealEstate) rentable);
			}
		}
		carsObservableList = FXCollections.observableArrayList(carsList);
		realEstatesObservableList = FXCollections.observableArrayList(realEstatesList);

		carIDColumn
				.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(Integer.valueOf(cell.getValue().getNumber())));
		carPlateNoColumn.setCellValueFactory(new PropertyValueFactory<>("plateNo"));
		carBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
		carModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
		carStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		carMonthlyPriceColumn.setCellValueFactory(new PropertyValueFactory<>("monthlyPrice"));

		realEstateIDColumn
				.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(Integer.valueOf(cell.getValue().getNumber())));
		realEstateLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		realEstateStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		realEstateTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		realEstateMonthlyPriceColumn.setCellValueFactory(new PropertyValueFactory<>("monthlyPrice"));
//		carTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);// column resize policy to get ride of extra
//		realEstateTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		carTable.setItems(carsObservableList);
		realEstateTable.setItems(realEstatesObservableList);

		carTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					carImageView.setImage(image);
				} else {
					carImageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				carImageView.setImage(null);
			}
		});
		realEstateTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					realEstateImageView.setImage(image);
				} else {
					realEstateImageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				realEstateImageView.setImage(null);
			}
		});
	}

	@FXML
	void onAddCar(ActionEvent event) {
//		if (carIDField.getText().equals("") && carBrandField.getText().equals("") && carModelField.getText().equals("")
//				&& carPlateNoField.getText().equals("") && carMonthlyPriceField.getText().equals("")) {
		String id = carIDField.getText();
		String brand = carBrandField.getText();
		int model = Integer.parseInt(carModelField.getText());
		int plateNo = Integer.parseInt(carPlateNoField.getText());
		int price = Integer.parseInt(carMonthlyPriceField.getText());

		Car car = new Car(id, plateNo, brand, model, price);
		if (InfoSys.addObject(car)) {
			carsObservableList.add(car);
			onClear(null);
			carImageView.setImage(car.getImage());
		}
//		} else
//			System.out.println("fill all fields");

	}

	@FXML
	void onAddRealEstate(ActionEvent event) {
//		if (realEstateIDField.getText().equals("") && realEstateLocationField.getText().equals("")
//				&& realEstateTypeField.getText().equals("") && realEstateMonthlyPriceField.getText().equals("")) {
		String id = realEstateIDField.getText();
		String location = realEstateLocationField.getText();
		int price = Integer.parseInt(realEstateMonthlyPriceField.getText());
		String type = realEstateTypeField.getText();
		RealEstate realEstate = new RealEstate(id, type, location, price);
		if (InfoSys.addObject(realEstate)) {
			realEstatesObservableList.add(realEstate);
			onClear(null);
			realEstateImageView.setImage(realEstate.getImage());

		}

//		} else
//			System.out.println("fill all fields");

	}

	@FXML
	void onClear(ActionEvent event) {
		if (carTab.isSelected()) {
			carIDField.clear();
			carPlateNoField.clear();
			carModelField.clear();
			carBrandField.clear();
			carMonthlyPriceField.clear();
		} else {
			realEstateIDField.clear();
			realEstateMonthlyPriceField.clear();
			realEstateLocationField.clear();
			realEstateTypeField.clear();
		}
	}

	@FXML
	void onDelete(ActionEvent event) {
		int row;
		if (carTab.isSelected()) {
			row = carTable.getSelectionModel().getSelectedIndex();
			if (row >= 0) {
				Car removedCar = carTable.getItems().get(row);
				if (InfoSys.deleteObject(removedCar)) {
					removedCar = carTable.getItems().remove(row);
					carTable.getSelectionModel().clearSelection();
					carsObservableList.remove(removedCar);
					carImageView.setImage(null);

				}
			}
		} else {
			if (realEstateTab.isSelected()) {
				row = realEstateTable.getSelectionModel().getSelectedIndex();
				if (row >= 0) {
					RealEstate removedRealEstate = realEstateTable.getItems().get(row);
					if (InfoSys.deleteObject(removedRealEstate)) {
						removedRealEstate = realEstateTable.getItems().remove(row);
						carTable.getSelectionModel().clearSelection();
						carsObservableList.remove(removedRealEstate);
						realEstateImageView.setImage(null);

					}
				}
			}
		}
	}

//	@FXML
//	private void onGoBack(ActionEvent event) throws Exception {
//		MainController controller = master("/proj01/fx/designMain.fxml").getController();
//		controller.setStage(stage);
//	}

	// helper method to load new stage
//	private FXMLLoader master(String str) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
//		Parent root = loader.load();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//		return loader;
//	}

	@FXML
	void onAddImageCar() {
		int row = carTable.getSelectionModel().getSelectedIndex();
		System.out.println(row);
		if (row >= 0) {
			Car car = carTable.getItems().get(row);
			car.setImagePath();
			carTable.getSelectionModel().clearSelection();
			carTable.getSelectionModel().select(row);
		}
	}

	@FXML
	void onAddImageRealEstate() {
		int row = realEstateTable.getSelectionModel().getSelectedIndex();
		System.out.println(row);
		if (row >= 0) {
			RealEstate realEstate = realEstateTable.getItems().get(row);
			realEstate.setImagePath();
			realEstateTable.getSelectionModel().clearSelection();
			realEstateTable.getSelectionModel().select(row);
		}
	}
}

/*
 * create elements id implement initialize method bind rows with properties
 * create observable lists set tables with observable lists hide all elements
 * show only elements for the selected rentable type
 *
 *
 */
