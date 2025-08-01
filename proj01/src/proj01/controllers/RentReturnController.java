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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
//	@FXML
//	private TableColumn<Customer, Integer> customerIdCol = new TableColumn<Customer, Integer>("id");
	@FXML
	private TableColumn<Customer, String> customerBirthDateCol, customerIdCol, customerNameCol, citizenNationalNoCol,
			companyExpiryDateCol, companyLicenceNoCol, companyLocationCol, residentNationalityCol,
			residentPassportNoCol, residentResidenceNoCol, customerTypeCol, NoOfUnitsCol, NoOfCarsCol;

	@FXML
	private TableColumn<Operation, String> operationCustomerTypeCol, operationCustomerNameCol, operationCustomerIdCol,
			operationRentableTypeCol, operationTypeCol, operationRentableIdCol, operationDateCol, operationAmountCol;

	@FXML
	private Button clearBtn, deleteOperationBtn, goBackBtn, rentOperationBtn, returnOperationBtn;

	@FXML
	private TextField customerIdField, rentableIdField;

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

	@FXML
	private Label errorLabel, noOfOperations, noOfProperties, noOfCars, noOfCitizens, noOfResidents, noOfCompanies;
	@FXML
	private ImageView imageView;

	Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

//	private void setColCustomersTable() throws IOException {
//		// first we fill the table with objects in the intilization phase
//		// then we read the values
//		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//		customerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		customerNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
//			@Override
//			public void handle(CellEditEvent<Customer, String> event) {
//				Customer person = event.getRowValue();
//				person.setName(event.getNewValue());
//			}
//		});
//
//		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//		customerIdCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//		customerIdCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			person.setId(e.getNewValue());
//		});
//		nationalNoCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Citizen) {
//				return new SimpleIntegerProperty(((Citizen) cust).getNationalNo()).asObject();
//			}
//			return null;
//		});
//		nationalNoCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//		nationalNoCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			((Citizen) person).setNationalNo(e.getNewValue());
//
//		});
//		residenceNoCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Resident) {
//				return new SimpleIntegerProperty(((Resident) cust).getResidence()).asObject();
//			}
//			return null;
//		});
//		residenceNoCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//		residenceNoCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			((Resident) person).setResidence(e.getNewValue());
//		});
//		passportNoCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Company) {
//				return new SimpleStringProperty(((Company) cust).getLocation());
//			}
//			return new SimpleStringProperty("-");
//		});
//		passportNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		passportNoCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			((Resident) person).setPassportNo(e.getNewValue());
//
//		});
//		licenceNoCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Company) {
//				return new SimpleStringProperty(((Company) cust).getLicenceNo());
//			}
//			return new SimpleStringProperty("-");
//		});
//		licenceNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		licenceNoCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			((Company) person).setLicenceNo(e.getNewValue());
//		});
//		nationalityCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Resident) {
//				return new SimpleStringProperty(((Resident) cust).getNationality());
//			}
//			return new SimpleStringProperty("-");
//		});
//		nationalityCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		nationalityCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			((Resident) person).setNationality(e.getNewValue());
//		});
//		birthDateCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Resident) {
//				return new SimpleStringProperty(String.valueOf(((Resident) cust).getBirthDate()));
//			} else if (cust instanceof Citizen)
//				return new SimpleStringProperty(String.valueOf(((Citizen) cust).getBirthDate()));
//			else
//				return new SimpleStringProperty("-");
//		});
//		expiryDateCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Company) {
//				return new SimpleStringProperty(String.valueOf(((Company) cust).getExpiryDate()));
//			}
//			return new SimpleStringProperty("-");
//		});
//		companyLocationCol.setCellValueFactory(e -> {
//			Customer cust = e.getValue();
//			if (cust instanceof Company) {
//				return new SimpleStringProperty(((Company) cust).getLocation());
//			}
//			return new SimpleStringProperty("-");
//		});
//		companyLocationCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		companyLocationCol.setOnEditCommit(e -> {
//			Customer person = e.getRowValue();
//			((Company) person).setLocation(e.getNewValue());
//		});
//
//		customersTable.setEditable(true);
//		customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
////		StackPane pane = new StackPane(customersTable);
////		Scene scene = new Scene(pane, 600, 500);
////		newWindow.setScene(scene);
////		newWindow.show();
//	}

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

		if (!rentablesObservableList.isEmpty() && !InfoSys.rentables.isEmpty()) {
//		System.out.println((rentablesObservableList.get(2) == InfoSys.rentables.get(2)) ? "true" : "false");
//		System.out.println("rentablesObservableList.get(2): "+rentablesObservableList.get(2));
//		System.out.println("InfoSys.rentables.get(2): " +InfoSys.rentables.get(2));
		}

		// bind instance variables/properties to columns
		operationCustomerTypeCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getCustomer().getClass().getSimpleName()));
		operationCustomerNameCol
				.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getCustomer().getName()));
		operationCustomerIdCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(String.valueOf(cell.getValue().getOperationSnapshot().getCustomer().getId())));
		operationRentableTypeCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getRentable().getClass().getSimpleName()));
		operationRentableIdCol
				.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getRentable().getNumber()));
		operationTypeCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getOperationSnapshot().getOperationType()));
		operationDateCol.setCellValueFactory(
				cell -> new ReadOnlyObjectWrapper<>(String.valueOf(cell.getValue().getOperationSnapshot().getOperationDate())));
		operationAmountCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		customerTypeCol.setCellValueFactory(e -> {
			return new SimpleStringProperty(e.getValue().getClass().getSimpleName());
		});
		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		NoOfCarsCol.setCellValueFactory(new PropertyValueFactory<>("noOfCars"));
		NoOfUnitsCol.setCellValueFactory(new PropertyValueFactory<>("noOfUnits"));
		citizenNationalNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Citizen)
				return new SimpleStringProperty(String.valueOf(((Citizen) e.getValue()).getNationalNo()));
			return new SimpleStringProperty("-");
		});
		customerBirthDateCol.setCellValueFactory(e -> {
			Customer cust = e.getValue();
			if (cust instanceof Resident) {
				return new SimpleStringProperty(String.valueOf(((Resident) cust).getBirthDate()));
			} else if (cust instanceof Citizen)
				return new SimpleStringProperty(String.valueOf(((Citizen) cust).getBirthDate()));
			else
				return new SimpleStringProperty("-");
		});

		residentResidenceNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Resident)
				return new SimpleStringProperty(String.valueOf(((Resident) e.getValue()).getResidence()));
			return new SimpleStringProperty("-");
		});
		residentPassportNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Resident)
				return new SimpleStringProperty(((Resident) e.getValue()).getPassportNo());
			return new SimpleStringProperty("-");
		});
		residentNationalityCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Resident)
				return new SimpleStringProperty(((Resident) e.getValue()).getNationality());
			return new SimpleStringProperty("-");
		});

		companyLicenceNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Company)
				return new SimpleStringProperty(((Company) e.getValue()).getLicenceNo());
			return new SimpleStringProperty("-");
		});
		companyLocationCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Company)
				return new SimpleStringProperty(((Company) e.getValue()).getLocation());
			return new SimpleStringProperty("-");
		});
		companyExpiryDateCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Company)
				return new SimpleStringProperty(String.valueOf(((Company) e.getValue()).getExpiryDate()));
			return new SimpleStringProperty("-");
		});

		rentableTypeCol.setCellValueFactory(e -> {
			return new SimpleStringProperty(e.getValue().getClass().getSimpleName());
		});
		rentableIdCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		carPlateNoCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car)
				return new SimpleStringProperty(String.valueOf(((Car) e.getValue()).getPlateNo()));
			return new SimpleStringProperty("-");
		});
		carBrandCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car)
				return new SimpleStringProperty(((Car) e.getValue()).getBrand());
			return new SimpleStringProperty("-");
		});
		carModelCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car)
				return new SimpleStringProperty(String.valueOf(((Car) e.getValue()).getModel()));
			return new SimpleStringProperty("-");
		});
		rentableStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		rentableMonthlyPriceCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof Car)
				return new SimpleStringProperty(String.valueOf(((Car) e.getValue()).getMonthlyPrice()));
			else
				return new SimpleStringProperty(String.valueOf(((RealEstate) e.getValue()).getMonthlyPrice()));
		});

		realEstateLocationCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof RealEstate)
				return new SimpleStringProperty(((RealEstate) e.getValue()).getLocation());
			return new SimpleStringProperty("-");
		});
		realEstateTypeCol.setCellValueFactory(e -> {
			if (e.getValue() instanceof RealEstate)
				return new SimpleStringProperty(((RealEstate) e.getValue()).getType());
			return new SimpleStringProperty("-");
		});

		// search
		FilteredList<Rentable> rentableFilteredData = new FilteredList<>(rentablesObservableList, p -> true);
		rentableIdField.textProperty().addListener((observable, oldValue, newValue) -> {
			rentableFilteredData.setPredicate(rentable -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare car details with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if (rentable instanceof Car car) {
					if (rentable.getNumber().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					} else if (String.valueOf(car.getPlateNo()).contains(lowerCaseFilter)) {
						return true;
					} else if (car.getBrand().contains(lowerCaseFilter))
						return true;
					else if (String.valueOf(car.getMonthlyPrice()).contains(lowerCaseFilter))
						return true;
					else if (String.valueOf(car.isStatus()).contains(lowerCaseFilter))
						return true;
					// compare realEstate details
				} else if (rentable instanceof RealEstate realEstate) {
					if (realEstate.getType().contains(lowerCaseFilter))
						return true;
					else if (String.valueOf(realEstate.getMonthlyPrice()).contains(lowerCaseFilter))
						return true;
					else if (String.valueOf(realEstate.isStatus()).contains(lowerCaseFilter))
						return true;
				}
				return false; // Does not match.
			});
		});

		FilteredList<Customer> customersFilteredData = new FilteredList<>(customersObservableList, p -> true);

		// 6. Add a listener to the text property of the search field
		customerIdField.textProperty().addListener((observable, oldValue, newValue) -> {
			customersFilteredData.setPredicate(customer -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare person's details with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches name.
				} else if (String.valueOf(customer.getNoOfCars()).contains(lowerCaseFilter)) {
					return true; // Filter matches email.
				} else if (String.valueOf(customer.getId()).contains(lowerCaseFilter)) {
					return true; // Filter matches id.
				}
				return false; // Does not match.
			});
		});
//		rentablesObservableList.addListener((ListChangeListener.Change<? extends Rentable> change)-> {
//    		noOfCars.setText(String.valueOf(rentablesObservableList.stream().filter(Car.class::isInstance).count()));
//
//		});

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

		// column resize policy to get ride of extra space
		customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		rentablesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		operationsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

//		customersTable.setItems(customersObservableList);
		customersTable.setItems(customersFilteredData);
		rentablesTable.setItems(rentableFilteredData);
//		rentablesTable.setItems(rentablesObservableList);

		operationsTable.setItems(operationsObservableList);

//		String str = rentablesTable.getSelectionModel().getSelectedItem().getImage();
//		Image image= new Image(str);
//		imageView.setImage(image);

		// get image of customer
		customersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					imageView.setImage(image);
				} else {
					imageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				imageView.setImage(null);
			}
		});

		rentablesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// 'newValue' is the Rentable object that was just selected
			if (newValue != null) {
				Image image = newValue.getImage(); // Get the Image from the selected Rentable
				if (image != null) {
					imageView.setImage(image);
				} else {
					imageView.setImage(null); // Clear image if loading failed
				}
			} else {
				// No item is selected (e.g., if selection is cleared)
				imageView.setImage(null);
			}
		});
//		operationsTable.getSelectionModel().select(0);
//		int row = operationsTable.getSelectionModel().getSelectedIndex();
//		if (row >= 0) {
//			Operation removedOperation = operationsTable.getItems().get(row);
//			if(!rentablesObservableList.isEmpty() && !InfoSys.rentables.isEmpty())
//			System.out.println((rentablesObservableList.get(2) == InfoSys.rentables.get(2)) ? "true" : "false");//true
//			if( !InfoSys.rentables.isEmpty())
//			System.out.println((removedOperation.getRentable() == InfoSys.rentables.get(2)) ? "true" : "false");//false
//			if(!rentablesObservableList.isEmpty())
//			System.out.println((removedOperation.getRentable() == rentablesObservableList.get(2)) ? "true" : "false");//false
//			System.out.println("removedOperation.getRentable(): "+removedOperation.getRentable());
//			if(!rentablesObservableList.isEmpty())
//			System.out.println("rentablesObservableList.get(2): "+rentablesObservableList.get(2));
//			if( !InfoSys.rentables.isEmpty())
//			System.out.println((removedOperation.getRentable() == InfoSys.operations.get(0).getRentable()) ? "true" : "false");//true
//		}

	}

	@FXML
	void onGoBack(ActionEvent event) throws Exception {

		MainController controller = master("/proj01/fx/designMain.fxml").getController();
		controller.setStage(stage);
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
			if (operationId != -1)
				operationsObservableList.add(InfoSys.searchOperationById(operationId));
//			Customer customer = InfoSys.selectCustomerMenu(customerId);
//			Rentable rentable = InfoSys.selectRentableMenu(Integer.valueOf(rentableId));
//			Date date = new Date("auto");
//			System.out.println(customer + "\n" + rentable);
//			if (customer != null && rentable != null) {
//				Operation operation = new Operation(customer, rentable, date, "rent");
//			if (InfoSys.makeOperationMenu(customerId, rentableId, 4)) {
//				operationsObservableList.add(InfoSys.searchOperationByCustomerAndRentable(
//						InfoSys.selectCustomerMenu(customerId), InfoSys.selectRentableMenu(rentableId), null));
//			}
//			} // else System.out.println("customer/rentable not found");

//			InfoSys.report(4);
		} else
			showAlertError("rent operation failed, fill all the fields");
		operationsTable.refresh();
		customersTable.refresh();
		rentablesTable.refresh();
		System.out.println("*************************");
	}

	@FXML
	void onClear(ActionEvent event) {
		customerIdField.clear();
		rentableIdField.clear();
	}

	@FXML
	void onReturnOperation(ActionEvent event) throws CancelException {
		System.out.println("\n*************************");
		System.out.println("return operation");

		if (!customerIdField.getText().equals("") && !rentableIdField.getText().equals("")) {
			int customerId = Integer.parseInt(customerIdField.getText());
			String rentableId = rentableIdField.getText();
			// get operaions id
			// list only current operations
			int operationId = InfoSys.returnOperation(null, customerId, rentableId);
			if (operationId != -1)
				operationsObservableList.add(InfoSys.searchOperationById(operationId * -1));
//			Customer customer = InfoSys.selectCustomerMenu(customerId);
//			Rentable rentable = InfoSys.selectRentableMenu(Integer.valueOf(rentableId));
//			Date date = new Date("flag");
//			System.out.println(customer + "\n" + rentable);
//			if (customer != null && rentable != null) {
//				Operation operation = new Operation(customer, rentable, date, "return");
//			if (InfoSys.makeOperationMenu(customerId, rentableId, 3)) {
//				operationsObservableList.add(InfoSys.searchOperationByCustomerAndRentable(
//						InfoSys.selectCustomerMenu(customerId), InfoSys.selectRentableMenu(rentableId), null));
//			} else {
//				InfoSys.alertWindow("return operation controller failed to make a return operation");
//				return;
//			}
//				InfoSys.report(4);
//			} // else System.out.println("customer/rentable not found");

		} else
			showAlertError("return operation failed, fill all the fields");
		operationsTable.refresh();
		customersTable.refresh();
		rentablesTable.refresh();
		System.out.println("*************************");
	}

	@FXML
	void onDeleteOperation(ActionEvent event) {

		int row = operationsTable.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			Operation removedOperation = operationsTable.getItems().get(row);
			String rentableId = removedOperation.getRentable().getNumber();
			int customerId = removedOperation.getCustomer().getId();
			operationsTable.getSelectionModel().selectLast();
			if (row == operationsTable.getSelectionModel().getSelectedIndex()) {//operation at the end
				InfoSys.deleteOperation(removedOperation, removedOperation);
				operationsObservableList.remove(removedOperation);
				operationsTable.getSelectionModel().clearSelection();
			}else {
				showAlertError("if you want to update an operation double click on the cell other wise you can't delete an old operation");
//				customerIdField.clear();
//				rentableIdField.clear();
				
			}
		}
//			System.out.println("should be true " + InfoSys.rentables.get(2).isStatus());// should be true

		// **
//				customersObservableList.remove(customer);
//				customersObservableList.add(customer);
//				rentablesObservableList.remove(rentable);
//			System.out.println("2");
//			if (InfoSys.removeObject(rentable)) {
////					rentable = rentablesTable.getItems().remove(row);
//				rentablesTable.getSelectionModel().clearSelection();
//				rentablesObservableList.remove(rentable);
//			}
//			rentablesObservableList.add(rentable);
		// **

//			}
//		}
//		reInitilize();
		operationsTable.refresh();
		customersTable.refresh();
		rentablesTable.refresh();
//		System.out.println("should be true " + InfoSys.rentables.get(2).isStatus());// should be true

//		System.out.println("***************************end delete op");
//		InfoSys.operations.forEach(System.out::println);
//		System.out.println("***************************");
////		System.out.println("should be true " + InfoSys.rentables.get(2).isStatus());// should be true
//		InfoSys.rentables.forEach(System.out::println);
//		System.out.println("***************************start delete op");

	}
}

//removedOperation operationsTable.getSelectionModel().getSelectedItem();
//operationsObservableList.remove(operation);
//InfoSys.returnRentable(removedOperation.getCustomer(),removedOperation.getRentable(),removedOperation);

//int row = operationsTable.getSelectionModel().getSelectedIndex();
//if (row >= 0) {
//	Operation removedOperation = operationsTable.getItems().get(row);
//	Rentable rentable = removedOperation.getRentable();
//	Customer customer = removedOperation.getCustomer();
//	if (InfoSys.removeObject(removedOperation)) {
//		removedOperation = operationsTable.getItems().remove(row);
//		operationsTable.getSelectionModel().clearSelection();
//		operationsObservableList.remove(removedOperation);
//	}
//}
//operationsTable.refresh();
//customersTable.refresh();
//rentablesTable.refresh();
