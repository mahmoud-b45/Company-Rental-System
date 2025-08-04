package proj01.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.naming.Binding;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import proj01.Citizen;
import proj01.Company;
import proj01.Customer;
import proj01.Date;
//import proj01.ErrorStreamListener;
import proj01.InfoSys;
import proj01.Resident;

public class AddCustomerController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab residentTab, citizenTab, companyTab;

	@FXML
	private TextField citizenNameField, residentNameField, companyNameField, citizenIdField, residentIdField,
			companyIdField, licenceNoField, locationField, nationalNoField, nationalityField, passportNoField,
			residenceNoField;

	@FXML
	private DatePicker citizenBirthDateField, residentBirthDateField, birthDateField1, expiryDateField;

	@FXML
	private Button addResidentBtn, addCitizenBtn, addCompanyBtn, clearBtn, deleteBtn, addImageCitizenBtn,
			addImageCompanyBtn, addImageResidentBtn;

	@FXML
	private Label birthDateLabel, birthDateLabel1, companyIdLabel, citizenIdLabel, residentIdLabe, expiryDateLabel,
			licenceNoLabel, locationLabel, nameLabel, nationalNoLabel, nationalityLabel, passportNoLabel,
			residenceNoLabel;

	@FXML
	private TableView<Citizen> citizenTable;
	@FXML
	private TableView<Company> companyTable;
	@FXML
	private TableView<Resident> residentTable;

	@FXML
	private TableColumn<Citizen, Integer> citizenIDColumn, citizenNationalNoColumn;
	@FXML
	private TableColumn<Citizen, String> citizenNameColumn;
	@FXML
	private TableColumn<Citizen, Date> citizenBirthDateColumn;
//	@FXML
//	private TableColumn<Citizen, Integer> citizenAgeColumn;
	@FXML
	private TableColumn<Resident, Integer> residentIDColumn, residentPassportNoColumn, residentResidenceNoColumn;
	@FXML
	private TableColumn<Resident, String> residentNameColumn, residentNationalityColumn;
	@FXML
	private TableColumn<Resident, Date> residentBirthDateColumn;
//	@FXML
//	private TableColumn<resident, Integer> residentAgeColumn;
	@FXML
	private TableColumn<Company, Integer> companyIDColumn, companyLicenceNoColumn;
	@FXML
	private TableColumn<Company, String> companyNameColumn, companyLocationColumn;
	@FXML
	private TableColumn<Company, Date> companyExpiryDateColumn;

	// Using an ObservableList so that the TableView gets notified automatically.
	private ObservableList<Citizen> citizensObservableList = null;
	private ObservableList<Resident> residentsObservableList = null;
	private ObservableList<Company> companiesObservableList = null;

	private ArrayList<Citizen> citizensList = new ArrayList<>();
	private ArrayList<Resident> residentsList = new ArrayList<>();
	private ArrayList<Company> companiesList = new ArrayList<>();

	@FXML
	private ImageView citizenImageView, residentImageView, companyImageView;

	private Stage stage;

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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { // this method runs always when the scene loads
		// initilize the error window for when it happen it will be ready to show up
//		System.setErr(new PrintStream(new ErrorStreamListener(), true));
//		System.err.println("controller add customer error.");

//		=;
//		javafx.beans.binding.BooleanExpression
//		computeValue()
//		javafx.beans.value.ObservableBooleanValue.get()
		// Correct: Creating an instance of the implementing class
//		BooleanProperty myProperty = new SimpleBooleanProperty(true);
//		boolean booleanValue=true;
//        Observable<Boolean> booleanObservable = Observable.just(booleanValue);
//        BooleanProperty myBooleanProperty = new SimpleBooleanProperty(booleanValue);
//        addImageCitizenBtn.disableProperty().bind(citizenTable.getSelectionModel().selectedItemProperty().isNull());
		
		// enable addImage btn after selecting a customer
		addImageCitizenBtn.disableProperty().bind(Bindings.isEmpty(citizenTable.getSelectionModel().getSelectedItems()));// method 1
		addImageResidentBtn.disableProperty().bind(residentTable.getSelectionModel().selectedItemProperty().isNull());// method 2
		addImageCompanyBtn.disableProperty().bind(companyTable.getSelectionModel().selectedItemProperty().isNull());

		// listen for double click and open image in new window
		citizenImageView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				openImageInNewWindow(citizenImageView);
			}
		});
		companyImageView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				openImageInNewWindow(companyImageView);
			}
		});
		residentImageView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				openImageInNewWindow(residentImageView);
			}
		});
		
		addCitizenBtn.disableProperty()
				.bind(citizenIdField.textProperty().isEmpty().or(citizenNameField.textProperty().isEmpty())
						.or(citizenNationalNoColumn.textProperty().isEmpty())
						.or(citizenBirthDateField.valueProperty().isNull()) // Check if DatePicker is empty (null)
				);
		addResidentBtn.disableProperty()
				.bind(residentIdField.textProperty().isEmpty().or(residentNameField.textProperty().isEmpty())
						.or(nationalityField.textProperty().isEmpty()).or(passportNoField.textProperty().isEmpty())
						.or(residenceNoField.textProperty().isEmpty())
						.or(residentBirthDateField.valueProperty().isNull()));
		addCompanyBtn.disableProperty()
				.bind(companyIdField.textProperty().isEmpty().or(companyNameField.textProperty().isEmpty())
						.or(licenceNoField.textProperty().isEmpty()).or(locationField.textProperty().isEmpty())
						.or(expiryDateField.valueProperty().isNull()));

		citizenTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		residentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		companyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		ArrayList<Customer> arr = InfoSys.customers;
		for (Customer customer : arr) {
			if (customer instanceof Citizen) {
				citizensList.add((Citizen) customer);
			} else if (customer instanceof Resident) {
				residentsList.add((Resident) customer);
			} else {
				companiesList.add((Company) customer);
			}
		}
		
		// change arraylist to observable list
		citizensObservableList = FXCollections.observableArrayList(citizensList);
		residentsObservableList = FXCollections.observableArrayList(residentsList);
		companiesObservableList = FXCollections.observableArrayList(companiesList);

		// bind instance variables/properties to columns
		// **Numeric sorting fix**:
		citizenIDColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));
		citizenNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		citizenNationalNoColumn
				.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getNationalNo()));
		citizenBirthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		residentIDColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));
		residentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		residentResidenceNoColumn.setCellValueFactory(new PropertyValueFactory<>("residence"));
		residentPassportNoColumn.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
		residentNationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
		residentBirthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		companyIDColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));
		companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		companyLicenceNoColumn.setCellValueFactory(new PropertyValueFactory<>("licenceNo"));
		companyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		companyExpiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

		citizenTable.setItems(citizensObservableList);// fill the table
		residentTable.setItems(residentsObservableList);
		companyTable.setItems(companiesObservableList);

		citizenTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					citizenImageView.setImage(image);
				} else {
					citizenImageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				citizenImageView.setImage(null);
			}
		});
		residentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					residentImageView.setImage(image);
				} else {
					residentImageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				residentImageView.setImage(null);
			}
		});
		companyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					companyImageView.setImage(image);
				} else {
					companyImageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				companyImageView.setImage(null);
			}
		});
	}

	@FXML
	void onAddCitizen(ActionEvent event) {
//		if (nationalNoField.getText().equals("") && citizenIdField.getText().equals("")
//				&& citizenNameField.getText().equals("") && citizenBirthDateField.getValue().equals("")) {
		int day = citizenBirthDateField.getValue().getDayOfMonth();
		int month = citizenBirthDateField.getValue().getMonthValue();
		int year = citizenBirthDateField.getValue().getYear();
		Date date = new Date(day, month, year);
		int id = Integer.parseInt(citizenIdField.getText());
		String name = citizenNameField.getText();
		int nationalNo = Integer.parseInt(nationalNoField.getText());
		Citizen citizen = new Citizen(id, name, nationalNo, date);
		if (InfoSys.addObject(citizen)) {
			citizensObservableList.add(citizen);
			onClear(null);
			citizenImageView.setImage(citizen.getImage());
		}
//		} else
//			System.out.println("fill all fields");
	}

	@FXML
	void onAddResident(ActionEvent event) {
//		if (passportNoField.getText().equals("") && nationalityField.getText().equals("")	&& residenceNoField.getText().equals("") && residentIdField.getText().equals("")
//				&& residentNameField.getText().equals("") && residentBirthDateField.getValue().equals(""))
//		{
		int id = Integer.parseInt(residentIdField.getText());
		String name = residentNameField.getText();
		int residenceNo = Integer.parseInt(residenceNoField.getText());
		String passportNo = passportNoField.getText();
		String nationality = nationalityField.getText();
		int day = residentBirthDateField.getValue().getDayOfMonth();
		int month = residentBirthDateField.getValue().getMonthValue();
		int year = residentBirthDateField.getValue().getYear();
		Date date = new Date(day, month, year);
		Resident resident = new Resident(id, name, residenceNo, passportNo, nationality, date);
		if (InfoSys.addObject(resident)) {
			residentsObservableList.add(resident);
			onClear(null);
			residentImageView.setImage(resident.getImage());
		}
//		} else
//			System.out.println("fill all fields");
	}

	@FXML
	void onAddCompany(ActionEvent event) {
//		if (locationField.getText().equals("") && licenceNoField.getText().equals("")
//				&& companyIdField.getText().equals("") && companyNameField.getText().equals("")
//				&& expiryDateField.getValue().equals("")) {
		int id = Integer.parseInt(companyIdField.getText());
		String name = companyNameField.getText();
		String licenceNo = licenceNoField.getText();
		String location = locationField.getText();
		int day = expiryDateField.getValue().getDayOfMonth();
		int month = expiryDateField.getValue().getMonthValue();
		int year = expiryDateField.getValue().getYear();
		Date date = new Date(day, month, year);
		Company company = new Company(id, name, licenceNo, location, date);
		if (InfoSys.addObject(company)) {
			companiesObservableList.add(company);
			onClear(null);
			companyImageView.setImage(company.getImage());
		}
//		} else
//			System.out.println("fill all fields");
	}

//	@FXML
//	void onGoBack(ActionEvent event) throws IOException {
//		MainController controller = master("/proj01/fx/designMain.fxml").getController();
//		controller.setStage(stage);
//	}

	private FXMLLoader master(String str) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return loader;
	}

	@FXML
	void onClear(ActionEvent event) {
		if (citizenTab.isSelected()) {
			citizenIdField.clear();
			citizenBirthDateField.setValue(null);
			nationalNoField.clear();
			citizenNameField.clear();
		} else if (residentTab.isSelected()) {
			residenceNoField.clear();
			residentIdField.clear();
			passportNoField.clear();
			nationalityField.clear();
			residentBirthDateField.setValue(null);
			residentNameField.clear();
		} else if (companyTab.isSelected()) {
			companyIdField.clear();
			licenceNoField.clear();
			expiryDateField.setValue(null);
			locationField.clear();
			companyNameField.clear();
		}
	}

	@FXML
	void onDelete(ActionEvent event) {
		int row;
		if (citizenTab.isSelected()) {
			row = citizenTable.getSelectionModel().getSelectedIndex();
			if (row >= 0) {
				Citizen removedCitizen = citizenTable.getItems().get(row);
				if (InfoSys.deleteObject(removedCitizen)) {
					removedCitizen = citizenTable.getItems().remove(row);
					citizenTable.getSelectionModel().clearSelection();
					citizensObservableList.remove(removedCitizen);
				}
			}
		} else if (residentTab.isSelected()) {
			row = residentTable.getSelectionModel().getSelectedIndex();
			if (row >= 0) {
				Resident removedResident = residentTable.getItems().remove(row);
				residentTable.getSelectionModel().clearSelection();
				if (InfoSys.deleteObject(removedResident)) {
					residentsObservableList.remove(removedResident);
				}
			}
		} else {
			row = companyTable.getSelectionModel().getSelectedIndex();
			if (row >= 0) {
				Company removedCompany = companyTable.getItems().remove(row);
				companyTable.getSelectionModel().clearSelection();
				if (InfoSys.deleteObject(removedCompany)) {
					companiesObservableList.remove(removedCompany);
				}
			}
		}
	}

	@FXML
	void onAddImageCitizen() {
		int row = citizenTable.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			Citizen citizen = citizenTable.getItems().get(row);
			citizen.setImagePath();
			citizenTable.getSelectionModel().clearSelection();//to update the image view immediately
			citizenTable.getSelectionModel().select(row);
		}
	}

	@FXML
	void onAddImageResident() {
		int row = residentTable.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			Resident resident = residentTable.getItems().get(row);
			resident.setImagePath();
			residentTable.getSelectionModel().clearSelection();
			residentTable.getSelectionModel().select(row);
		}
	}

	@FXML
	void onAddImageCompany() {
		int row = companyTable.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			Company company = companyTable.getItems().get(row);
			company.setImagePath();
			companyTable.getSelectionModel().clearSelection();
			companyTable.getSelectionModel().select(row);
		}
	}

}
