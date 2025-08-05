package proj01.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import javafx.util.converter.IntegerStringConverter;
import proj01.CancelException;
import proj01.Car;
import proj01.Citizen;
import proj01.Company;
import proj01.Customer;
import proj01.Date;
import proj01.InfoSys;
import proj01.Operation;
import proj01.RealEstate;
import proj01.Rentable;
import proj01.Resident;

public class RentReturnController implements Initializable {

	@FXML
	private TableColumn<Rentable, String> carBrandCol, rentableIdCol, carModelCol, rentableMonthlyPriceCol,
			carPlateNoCol, rentableStatusCol, realEstateLocationCol, realEstateTypeCol, rentableTypeCol;

	@FXML
	private TableColumn<?, ?> citizenAgeCol;

	@FXML
	private TableColumn<Customer, String> customerBirthDateCol, customerIdCol, customerNameCol, citizenNationalNoCol,
			companyExpiryDateCol, companyLicenceNoCol, companyLocationCol, residentNationalityCol,
			residentPassportNoCol, residentResidenceNoCol, customerTypeCol, NoOfUnitsCol, NoOfCarsCol;

	@FXML
	private TableColumn<Operation, String> operationCustomerTypeCol, operationIDCol, operationCustomerNameCol,
			operationCustomerIdCol, operationRentableTypeCol, operationTypeCol, operationRentableIdCol,
			operationDateCol, operationAmountCol;

	@FXML
	private Button clearBtn, deleteOperationBtn, goBackBtn, rentOperationBtn, returnOperationBtn;

	@FXML
	private TextField customerIdField, rentableIdField, operationsSearchField, rentablesSearchField,
			customersSearchField;

	@FXML
	private ComboBox<String> operationsFilterComboBox, rentablesFilterComboBox, customersFilterComboBox;

	@FXML
	private TableView<Customer> customersTable;
	@FXML
	private TableView<Rentable> rentablesTable;
	@FXML
	private TableView<Operation> operationsTable;

	private ArrayList<Customer> customers;
	private ArrayList<Rentable> rentables;
	private ArrayList<Operation> operations;

	private ObservableList<Customer> customersObservableList;
	private ObservableList<Rentable> rentablesObservableList;
	private ObservableList<Operation> operationsObservableList;
	private FilteredList<Operation> operationsFilteredData;

	@FXML
	private Label errorLabel, noOfOperations, noOfProperties, noOfCars, noOfCitizens, noOfResidents, noOfCompanies;
	@FXML
	private StackPane imageStackPane;

	@FXML
	private ImageView imageView;

	Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		rentOperationBtn.disableProperty().bind(customerIdField.textProperty().isEmpty());
		rentOperationBtn.disableProperty().bind(rentableIdField.textProperty().isEmpty());

		returnOperationBtn.disableProperty().bind(customerIdField.textProperty().isEmpty());
		returnOperationBtn.disableProperty().bind(rentableIdField.textProperty().isEmpty());

		deleteOperationBtn.disableProperty().bind(operationsTable.getSelectionModel().selectedItemProperty().isNull());
		operationsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// Bind the disable property of the button
		deleteOperationBtn.disableProperty()
				.bind(Bindings.isEmpty(operationsTable.getSelectionModel().getSelectedItems()));

		customers = InfoSys.customers;
		rentables = InfoSys.rentables;
		operations = InfoSys.operations;
		customersObservableList = FXCollections.observableArrayList(customers);
		rentablesObservableList = FXCollections.observableArrayList(rentables);
		operationsObservableList = FXCollections.observableArrayList(operations);

		imageView.setPreserveRatio(true);
		imageView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				openImageInNewWindow(imageView);
			}
		});

		try {
			setColCustomersTable();
			setColRentablesTable();
			setColOperationsTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setColCustomersTable() throws IOException {
		// first we fill the table with objects in the intilization phase
		// then we read the values

		customerTypeCol.setCellValueFactory(e -> {
			return new SimpleStringProperty(e.getValue().getClass().getSimpleName());
		});

		NoOfCarsCol.setCellValueFactory(new PropertyValueFactory<>("noOfCars"));

		NoOfUnitsCol.setCellValueFactory(new PropertyValueFactory<>("noOfUnits"));

		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		customerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		customerNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> event) {
				event.getRowValue().setName(event.getNewValue());
			}
		});

		customerIdCol.setCellValueFactory(e -> {
			return new SimpleStringProperty(String.valueOf(e.getValue().getId()));
		});
		customerIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
		customerIdCol.setOnEditCommit(e -> {
			e.getRowValue().setId(Integer.valueOf(e.getNewValue()));
		});

		citizenNationalNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Citizen cit) {
				return new SimpleStringProperty(String.valueOf(cit.getNationalNo()));
			}
			return new SimpleStringProperty("-");
		});
		citizenNationalNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		citizenNationalNoCol.setOnEditCommit(e -> {
			((Citizen) e.getRowValue()).setNationalNo(Integer.valueOf(e.getNewValue()));
		});

		residentResidenceNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Resident res) {
				return new SimpleStringProperty(String.valueOf(res.getResidence()));
			}
			return new SimpleStringProperty("-");
		});
		residentResidenceNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		residentResidenceNoCol.setOnEditCommit(e -> {
			((Resident) e.getRowValue()).setResidence(Integer.valueOf(e.getNewValue()));
		});

		residentPassportNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Resident res) {
				return new SimpleStringProperty(res.getPassportNo());
			}
			return new SimpleStringProperty("-");
		});
		residentPassportNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		residentPassportNoCol.setOnEditCommit(e -> {
			((Resident) e.getRowValue()).setPassportNo(e.getNewValue());
		});

		companyLicenceNoCol.setCellValueFactory(e -> {
			Customer cust = e.getValue();
			if (cust instanceof Company) {
				return new SimpleStringProperty(((Company) cust).getLicenceNo());
			}
			return new SimpleStringProperty("-");
		});
		companyLicenceNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		companyLicenceNoCol.setOnEditCommit(e -> {
			Customer person = e.getRowValue();
			((Company) person).setLicenceNo(e.getNewValue());
		});

		residentNationalityCol.setCellValueFactory(e -> {
			Customer cust = e.getValue();
			if (cust instanceof Resident) {
				return new SimpleStringProperty(((Resident) cust).getNationality());
			}
			return new SimpleStringProperty("-");
		});
		residentNationalityCol.setCellFactory(TextFieldTableCell.forTableColumn());
		residentNationalityCol.setOnEditCommit(e -> {
			((Resident) e.getRowValue()).setNationality(e.getNewValue());
		});

		customerBirthDateCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Resident res) {
				return new SimpleStringProperty(String.valueOf(res.getBirthDate()));
			} else if (e.getValue() instanceof Citizen cit) {
				return new SimpleStringProperty(String.valueOf(cit.getBirthDate()));
			} else {
				return new SimpleStringProperty("-");
			}
		});
		customerBirthDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		customerBirthDateCol.setOnEditCommit(e -> {
			if (e.getRowValue() instanceof Resident res) {
				res.setBirthDate(new Date(e.getNewValue()));
			} else if (e.getRowValue() instanceof Citizen cit) {
				cit.setBirthDate(new Date(e.getNewValue()));
			}
		});

		companyExpiryDateCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Company comp) {
				return new SimpleStringProperty(String.valueOf(comp.getExpiryDate()));
			}
			return new SimpleStringProperty("-");
		});
		companyExpiryDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		companyExpiryDateCol.setOnEditCommit(e -> {
			((Company) e.getRowValue()).setExpiryDate(new Date(e.getNewValue()));
		});

		companyLocationCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Company comp) {
				return new SimpleStringProperty(comp.getLocation());
			}
			return new SimpleStringProperty("-");
		});
		companyLocationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		companyLocationCol.setOnEditCommit(e -> {
			((Company) e.getRowValue()).setLocation(e.getNewValue());
		});

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("ID", "Name", "National No", "Passport No", "Licence No");
		customersFilterComboBox.setItems(list);
		customersFilterComboBox.setValue("ID");
		FilteredList<Customer> customersFilteredData = new FilteredList<>(customersObservableList, p -> true);
		// 6. Add a listener to the text property of the search field
//		customerIdField.textProperty().addListener((observable, oldValue, newValue) -> {
		customersSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			customersFilteredData.setPredicate(customer -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare person's details with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (customersFilterComboBox.getValue().equals("ID")) {
					if (String.valueOf(customer.getId()).contains(lowerCaseFilter)) {
						return true; // Filter matches id.
					}
				}

				if (customersFilterComboBox.getValue().equals("Name")) {
					if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches name.
					}
				}
				if (customer instanceof Citizen citizen) {
					if (customersFilterComboBox.getValue().equals("National No")) {
						if (String.valueOf(citizen.getNationalNo()).toLowerCase().contains(lowerCaseFilter)) {
							return true; // Filter matches name.
						}
					}
				} else if (customer instanceof Resident resident) {
					if (customersFilterComboBox.getValue().equals("Passport No")) {
						if (String.valueOf(resident.getPassportNo()).toLowerCase().contains(lowerCaseFilter)) {
							return true; // Filter matches name.
						}
					}
				} else if (customer instanceof Company company) {
					if (customersFilterComboBox.getValue().equals("Licence No")) {
						if (String.valueOf(company.getLicenceNo()).toLowerCase().contains(lowerCaseFilter)) {
							return true; // Filter matches name.
						}
					}
				}
				return false; // Does not match.
			});
		});

		// get image of customer
		customersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, rentable) -> {
			// 'newValue' is the Rentable object that was just selected
			if (rentable != null) {
				Image image = rentable.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					if (image.getWidth() > image.getHeight()) {
						imageView.fitWidthProperty().bind(imageStackPane.widthProperty());
					} else {
						imageView.fitHeightProperty().bind(imageStackPane.heightProperty());
					}

					imageView.setImage(image);
				} else {
					imageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				imageView.setImage(null);
			}
		});

		customersTable.setItems(customersFilteredData);

		customersTable.setEditable(true);
		customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void setColOperationsTable() {

		// bind instance variables/properties to columns
		operationIDCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(
				String.valueOf(cell.getValue().getOperationSnapshot().getOperationId())));
		operationCustomerTypeCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(
				cell.getValue().getOperationSnapshot().getCustomer().getClass().getSimpleName()));
		operationCustomerNameCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getCustomer().getName()));
		operationCustomerIdCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(
				String.valueOf(cell.getValue().getOperationSnapshot().getCustomer().getId())));
		operationRentableTypeCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(
				cell.getValue().getOperationSnapshot().getRentable().getClass().getSimpleName()));
		operationRentableIdCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getRentable().getNumber()));
		operationTypeCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getOperationType()));
		operationDateCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(
				String.valueOf(cell.getValue().getOperationSnapshot().getOperationDate())));
		operationAmountCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		// setup values first
		noOfOperations.setText(String.valueOf(operationsObservableList.size()));
		noOfCars.setText(String.valueOf(rentablesObservableList.stream().filter(Car.class::isInstance).count()));
		noOfProperties
				.setText(String.valueOf(rentablesObservableList.stream().filter(RealEstate.class::isInstance).count()));
		noOfCitizens
				.setText(String.valueOf(customersObservableList.stream().filter(Citizen.class::isInstance).count()));
		noOfResidents
				.setText(String.valueOf(customersObservableList.stream().filter(Resident.class::isInstance).count()));
		noOfCompanies
				.setText(String.valueOf(customersObservableList.stream().filter(Company.class::isInstance).count()));

		// then watch for changes
		operationsObservableList.addListener((Observable observable) -> {
			noOfOperations.setText(String.valueOf(operationsObservableList.stream().count()));
		});
		rentablesObservableList.addListener((Observable observable) -> {
			noOfCars.setText(String.valueOf(rentablesObservableList.stream().filter(Car.class::isInstance).count()));
		});
		rentablesObservableList.addListener((Observable observable) -> {
			noOfProperties.setText(
					String.valueOf(rentablesObservableList.stream().filter(RealEstate.class::isInstance).count()));
		});
		customersObservableList.addListener((Observable observable) -> {
			noOfCitizens.setText(
					String.valueOf(customersObservableList.stream().filter(Citizen.class::isInstance).count()));
		});
		customersObservableList.addListener((Observable observable) -> {
			noOfCompanies.setText(
					String.valueOf(customersObservableList.stream().filter(Company.class::isInstance).count()));
		});
		customersObservableList.addListener((Observable observable) -> {
			noOfResidents.setText(
					String.valueOf(customersObservableList.stream().filter(Resident.class::isInstance).count()));
		});

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(/*"Ongoing Operations",*/ "ID", "Price");
		operationsFilterComboBox.setItems(list);
		operationsFilterComboBox.setValue("ID");
		operationsFilteredData=new FilteredList<>(operationsObservableList, p -> true);

		// 6. Add a listener to the text property of the search field
		operationsSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			operationsFilteredData.setPredicate(operation -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare person's details with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (operationsFilterComboBox.getValue().equals("ID")) {
					if (String.valueOf(operation.getOperationSnapshot().getOperationId()).contains(lowerCaseFilter)) {
						return true; // Filter matches id.
					}
				}

				if (operationsFilterComboBox.getValue().equals("Price")) {
					if (String.valueOf(operation.getOperationSnapshot().getPrice()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches name.
					}
				}

//				if (operationsFilterComboBox.getValue().equals("Ongoing Operations"))
//					if (String.valueOf(operation.getOperationSnapshot().getOperationId()).contains(lowerCaseFilter))
//						return true; // Filter matches id.

				return false; // Does not match.
			});
		});

		operationsTable.setItems(operationsFilteredData);
		operationsTable.setEditable(true);
		operationsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void setColRentablesTable() throws IOException {

		rentableTypeCol.setCellValueFactory(e -> {
			return new SimpleStringProperty(e.getValue().getClass().getSimpleName());
		});

		rentableIdCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		rentableIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
		rentableIdCol.setOnEditCommit(e -> {
			e.getRowValue().setNumber(e.getNewValue());
		});

		carPlateNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car) {
				return new SimpleStringProperty(String.valueOf(((Car) e.getValue()).getPlateNo()));
			}
			return new SimpleStringProperty("-");
		});
		carPlateNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		carPlateNoCol.setOnEditCommit(e -> {
			((Car) e.getRowValue()).setPlateNo(Integer.valueOf(e.getNewValue()));
		});

		carBrandCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car) {
				return new SimpleStringProperty(((Car) e.getValue()).getBrand());
			}
			return new SimpleStringProperty("-");
		});
		carBrandCol.setCellFactory(TextFieldTableCell.forTableColumn());
		carBrandCol.setOnEditCommit(e -> {
			((Car) e.getRowValue()).setBrand(e.getNewValue());
		});

		carModelCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car) {
				return new SimpleStringProperty(String.valueOf(((Car) e.getValue()).getModel()));
			}
			return new SimpleStringProperty("-");
		});
		carModelCol.setCellFactory(TextFieldTableCell.forTableColumn());
		carModelCol.setOnEditCommit(e -> {
			((Car) e.getRowValue()).setModel(Integer.valueOf(e.getNewValue()));
		});

		rentableStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

		rentableMonthlyPriceCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car) {
				return new SimpleStringProperty(String.valueOf(((Car) e.getValue()).getMonthlyPrice()));
			} else {
				return new SimpleStringProperty(String.valueOf(((RealEstate) e.getValue()).getMonthlyPrice()));
			}
		});
		rentableMonthlyPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
		rentableMonthlyPriceCol.setOnEditCommit(e -> {
			if (e.getRowValue() instanceof Car car) {
				((Car) e.getRowValue()).setMonthlyPrice(Integer.valueOf(e.getNewValue()));
			} else {
				((RealEstate) e.getRowValue()).setMonthlyPrice(Integer.valueOf(e.getNewValue()));
			}
		});

		realEstateLocationCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof RealEstate) {
				return new SimpleStringProperty(((RealEstate) e.getValue()).getLocation());
			}
			return new SimpleStringProperty("-");
		});
		realEstateLocationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		realEstateLocationCol.setOnEditCommit(e -> {
			((RealEstate) e.getRowValue()).setLocation(e.getNewValue());
		});

		realEstateTypeCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof RealEstate) {
				return new SimpleStringProperty(((RealEstate) e.getValue()).getType());
			}
			return new SimpleStringProperty("-");
		});
		realEstateTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		realEstateTypeCol.setOnEditCommit(e -> {
			((RealEstate) e.getRowValue()).setLocation(e.getNewValue());
		});

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("ID", "Plate No", "Brand", "Price", "RealEstate Type");
		rentablesFilterComboBox.setItems(list);
		rentablesFilterComboBox.setValue("ID");

		// search
		FilteredList<Rentable> rentableFilteredData = new FilteredList<>(rentablesObservableList, p -> true);
		rentablesSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			rentableFilteredData.setPredicate(rentable -> {

				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				// shared details between car and realestate
				if (rentablesFilterComboBox.getValue().equals("ID")) {
					if (rentable.getNumber().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}
				}
				if (rentablesFilterComboBox.getValue().equals("Price")) {
					if (String.valueOf(rentable.getMonthlyPrice(rentable)).contains(lowerCaseFilter)) {
						return true;
					}
				}

				if (rentable instanceof Car car) {
					// Compare car details with filter text.
					if (rentablesFilterComboBox.getValue().equals("Plate No")) {
						if (String.valueOf(car.getPlateNo()).contains(lowerCaseFilter)) {
							return true;
						}
					}
					if (rentablesFilterComboBox.getValue().equals("Brand")) {
						if (car.getBrand().contains(lowerCaseFilter)) {
							return true;
						}
					}

				} else if (rentable instanceof RealEstate realestate) {
					// compare realEstate details
					if (rentablesFilterComboBox.getValue().equals("RealEstate Type")) {
						if (realestate.getType().contains(lowerCaseFilter)) {
							return true;
						}
					}
				}
				return false; // Does not match.
			});
		});

		rentablesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {

				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					if (image.getWidth() > image.getHeight()) {
						imageView.fitWidthProperty().bind(imageStackPane.widthProperty());
					} else {
						imageView.fitHeightProperty().bind(imageStackPane.heightProperty());
					}
					imageView.setImage(image);
				} else {
					imageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				imageView.setImage(null);
			}
		});

		rentablesTable.setItems(rentableFilteredData);

		rentablesTable.setEditable(true);
		rentablesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	void reInitilize() {
		rentables = null;
		rentablesObservableList = null;
		rentables = InfoSys.rentables;
		rentablesObservableList = FXCollections.observableArrayList(rentables);
		rentableStatusCol = null;
		rentableStatusCol = new TableColumn<>("status");
		rentableStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		rentablesTable.setItems(rentablesObservableList);
	}

	Stage newWindow = new Stage();

	void openImageInNewWindow(ImageView originalImageView) {

		ImageView imgV = new ImageView(originalImageView.getImage());

		if (imgV.getImage() != null) {
			imgV.setPreserveRatio(true);
			StackPane anchor = new StackPane(imgV);
			if (imgV.getImage().getWidth() > imgV.getImage().getHeight()) {
				imgV.fitWidthProperty().bind(anchor.widthProperty());
			} else {
				imgV.fitHeightProperty().bind(anchor.heightProperty());
			}

			Scene scene = new Scene(anchor, 600, 500);
			newWindow.setScene(scene);
			newWindow.show();
		}
	}

	// helper method to load new stage
	private FXMLLoader master(String str) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return loader;
	}

	private void showAlertError(String reason) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Validation Error");
		alert.setHeaderText("Input does not meet requirements");
		alert.setContentText(reason);
		alert.showAndWait();
	}

	@FXML
	void onRentOperation(ActionEvent event) throws CancelException {
		System.out.println("\n*************************");
		System.out.println("rent operation");

		if (!customerIdField.getText().equals("") && !rentableIdField.getText().equals("")) {
			int customerId = Integer.parseInt(customerIdField.getText());
			String rentableId = rentableIdField.getText();
			int operationId = InfoSys.rentOperation(customerId, rentableId);
			if (operationId != -1) {
				operationsObservableList.add(InfoSys.searchOperationById(operationId));
			}

		} else {
			showAlertError("rent operation failed, fill all the fields");
		}
		operationsTable.refresh();
		customersTable.refresh();
		rentablesTable.refresh();
		System.out.println("*************************");
	}

	@FXML
	void onClear(ActionEvent event) {
		customerIdField.clear();
		rentableIdField.clear();
		customersSearchField.clear();
		rentablesSearchField.clear();
		operationsSearchField.clear();
	}

	@FXML
	void onReturnOperation(ActionEvent event) throws CancelException {

		if (!customerIdField.getText().equals("") && !rentableIdField.getText().equals("")) {
			int customerId = Integer.parseInt(customerIdField.getText());
			String rentableId = rentableIdField.getText();
			// get operaions id
			// list only current operations
			Operation operation = InfoSys.returnOperation(customerId, rentableId);
			if (operation != null) {
				operationsObservableList.add(InfoSys.searchOperationById(operation.getId() * -1));
			}

		} else {
			showAlertError("return operation failed, fill all the fields");
		}
		operationsTable.refresh();
		customersTable.refresh();
		rentablesTable.refresh();
		System.out.println("*************************");
	}

	@FXML
	void onDeleteOperation(ActionEvent event) throws CancelException {

		int row = operationsTable.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			Operation removedOperation = operationsTable.getItems().get(row);
			String rentableId = removedOperation.getRentable().getNumber();
			int customerId = removedOperation.getCustomer().getId();
			operationsTable.getSelectionModel().selectLast();
			if (row == operationsTable.getSelectionModel().getSelectedIndex()) {// operation at the end
				if (InfoSys.deleteObject(removedOperation)) {
					operationsObservableList.remove(removedOperation);
					operationsTable.getSelectionModel().clearSelection();
				}
			} else {
				showAlertError(
						"if you want to update an operation double click on the cell other wise you can't delete an old operation");
			}
		}
		operationsTable.refresh();
		customersTable.refresh();
		rentablesTable.refresh();
	}
}
