package proj01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proj01.controllers.MainController;

/**
 * 
 * @author Mahmoud
 * 
 *         - create customer using createCustomerMenu()
 * 
 *         - create rentable using createRentableMenu()
 * 
 *         - add customer to the list of customers and set of id's and customers
 *         using addObject() add rentable to list of rentables and set of id's
 *         and rentables using addObject()
 * 
 *         - select customer using selectCustomerMenu() which searches for
 *         customer using searchCustomer() and returns the specific type of
 *         customer using typeOfCustomer()
 * 
 *         - select rentable uses selectRentableMenu() which searches for
 *         rentable using searchRentable() and returns the specific type of
 *         rentable using typeOfRentable()
 * 
 *         - search for customer using searchCustomer()
 * 
 *         - search for rentable using searchRentable()
 * 
 *         - rent/return using makeOperationMenu() which uses
 *         selectCustomerMenu() and selectRentableMenu() to check if they are in
 *         system or null and rentOperation() and returnOperation()
 * 
 *         - rentOperation() checks null, checks availability, checks policy. if
 *         all good, it ads the rentable to customer and set rentable not
 *         available and ads rent operation to operations list
 * 
 *         - returnOperation() checks null, checks availability, searches for
 *         operation. if all good it removes the rentable from the customer and
 *         set available and ads return operation to operations list
 * 
 *         -
 * 
 */
public class InfoSys extends Application {

	public static ArrayList<Customer> customers = new ArrayList<>();
	public static ArrayList<Rentable> rentables = new ArrayList<>();
	public static ArrayList<Operation> operations = new ArrayList<>();

	public static Set<Integer> customersIds = new HashSet<>();
	public static Set<String> rentablesIds = new HashSet<>();

	public static ArrayList<Integer> operationsIDs = new ArrayList<>();
	public static ArrayList<Operation> ongoingOperations = new ArrayList<>();

	public static Scanner in = new Scanner(System.in);

	public static Set<Customer> customersSet;
	public static Set<Rentable> rentablesSet;

	public static int mainSelection = 99;
	public static String[] args2;

	/**
	 * stage is window. scene holds scene graph, a tree of nodes. pane helps you
	 * order/organize the layout of the nodes in the scene.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("\n*************************************************");
		System.out.println(" start gui...");
		System.out.println("*************************************************");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/proj01/fx/Main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("proj01/controllers/stylesheet.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			MainController controller = loader.getController();
			controller.setStage(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws CancelException {
		args2 = args;
		read();
		initializeSets();
//		 Application.launch(InfoSys.class, args);
//		launch(args);
		mainMenu();
	}

	private static void mainMenu() throws CancelException {
		try {
			String select = "";
			if (mainSelection != 0) {
				System.out.println("\npress any key to start: ");
				select = in.nextLine();
			}
			if (select != null) {

				if (mainSelection != 99) {
					mainSelection = 0;
				} else {
					mainSelection = 1;
				}
				while (mainSelection != 0) {
					System.out.println("select one operation: ");
					System.out.println("1)add customer");
					System.out.println("2)add rentable");
					System.out.println("3)make operatio rent/return");
					System.out.println("4)Search available rentables");
					System.out.println("5)Report (rentables by customer/operations per date/all customers)");
					System.out.println("6)save data to the disk");
					System.out.println("7)delete(customer/rentable/operation)");
					System.out.println("8)read data");
					System.out.println("0)exit");
					System.out.println("enter a number: ");
					mainSelection = in.nextInt();
					in.nextLine();
					switch (mainSelection) {
					case 1:
						createCustomerMenu();
						break;
					case 2:
						createRentableMenu();
						break;
					case 3:
						makeOperationMenu();
						break;
					case 4:
						listAvailUnavilRentables(true);
						break;
					case 5:
						reportMenu();
						break;
					case 6:
						save();
						break;
					case 7:
						deleteMenu();
						break;
					case 8:
						read();
						break;
					case 0:
						System.out.println("exiting...\n*************************************************");
						in.close();
						System.exit(0);
						break;
					default:
						System.out.println("*************************************************");
						System.out.print("select a number in the range 0 to 7");
						System.out.println("\n*************************************************");
						break;
					}
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			in.nextLine();
			System.out.println("exiting...");
			main(args2);
		}
	}

	/**
	 * used in main menu. an interface to use other methods like addObject().
	 * 
	 * @return a Citizen or Resident or Company.
	 * @throws CancelException
	 */
	private static Customer createCustomerMenu() throws CancelException {
		try {
			int selection = 1;
			while (selection != 0) {
				System.out.println("\n*************************************************");
				System.out.println(" Add Customer Menu...");
				System.out.println("*************************************************");

				System.out.println("what type of customer?");
				System.out.println("1)resident");
				System.out.println("2)company");
				System.out.println("3)citizen");
				System.out.println("0)go back");
				System.out.println("enter a number: ");
				selection = in.nextInt();

				switch (selection) {
				case 1:
					Resident resident = new Resident();
					addObject(resident);
//					return resident;
					break;
				case 2:
					Company company = new Company();
					addObject(company);
//					return company;
					break;
				case 3:
					Citizen citizen = new Citizen();
					addObject(citizen);
//					return citizen;
					break;
				case 0:
					System.out.println("going back..." + "\n*************************************************");
					break;
				default:
					System.out.println("you entered wrong number");
				}
			}
		} catch (InputMismatchException | CancelException e) {
			if (e instanceof CancelException)
				System.out.println(e.getMessage());
			else
				System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(args2);
		}
		return null;
	}

	/**
	 * an interface to use other methods like addObject().
	 * 
	 * @return a Car or RealEstate.
	 * @throws CancelException
	 */
	private static Rentable createRentableMenu() throws CancelException {
		try {
			int selection = 1;
			while (selection != 0) {
				System.out.println("\n*************************************************");
				System.out.println(" Add Rentable Menu...");
				System.out.println("*************************************************");

				System.out.println("what type of rentable:");
				System.out.println("1)add Car");
				System.out.println("2)add Real Estate");
				System.out.println("0)go back");
				System.out.println("enter a number: ");
				selection = in.nextInt();
				switch (selection) {
				case 1:
					Car car = new Car();
					addObject(car);
					return car;
				case 2:
					RealEstate realEstate = new RealEstate();
					addObject(realEstate);
					return realEstate;
				case 0:
					System.out.println("going back...\n*************************************************");
					break;
				default:
					System.out.println("you entered wrong number");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return null;
	}

	/**
	 * used in rentOperation(), returnOperation(), after searching using other
	 * methods like searchCustomer() after determining it's type using
	 * typeOfCustomer()
	 *
	 * @param outId =-1 run normally, show menu, ask for id. =#id use id directly
	 * @return type Customer.
	 * @throws CancelException
	 */
	private static Customer searchCustomerMenu() throws CancelException {
		try {
			int id;
			Customer customer;
			int selection = 1;
			while (selection != 0) {
				System.out.println("\n*************************************************");
				System.out.println("3 search for a customer...");
				System.out.println("*************************************************");
				System.out.println("1) enter customer id");
				System.out.println("2) list all customers");
				System.out.println("0) go back");
				System.out.println("select an option: ");
				selection = in.nextInt();
				switch (selection) {
				case 1:
					System.out.print("enter customer id: ");
					id = in.nextInt();
					customer = searchCustomerByID(id);
					System.out.println("searchCustomerByID(): " + customer);
					return typeOfCustomer(customer);
				case 2:
					listAllCustomers();
					break;
				case 0:
					System.out.println("going back...");
					return null;
				default:
					System.out.println("you entered wrong number");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return null;
	}

	/**
	 * an interface to other methods other methods like search
	 *
	 * @param outId =-1 run normally, show menu, ask for id. =#id use id directly
	 * @return of type Rentable
	 * @throws CancelException
	 */
	private static Rentable searchRentableMenu() throws CancelException {
		try {
			String id;
			Rentable rentable;
			int selection = 1;
			while (selection != 0) {
				System.out.println("\n*************************************************");
				System.out.println("4 search for a Rentable...");
				System.out.println("*************************************************");
				System.out.println("1) enter rentable id");
				System.out.println("2) list all available rentables");
				System.out.println("0) go back");
				System.out.println("select an option: ");
				selection = in.nextInt();
				switch (selection) {
				case 1:
					System.out.print("enter rentable id: ");
					id = String.valueOf(in.nextInt());
					rentable = searchRentableByID(id);
					return typeOfRentable(rentable, false);
				case 2:
					listAvailUnavilRentables(true);
					break;
				case 0:
					System.out.println("going back...");
					return null;
				default:
					System.out.println("you entered wrong number");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return null;
	}

	/**
	 * @param outId = 0 to run menu, =(+)rent operations, =(-)return operations.
	 * @return
	 * @throws CancelException
	 */
	private static Operation searchOperationMenu() throws CancelException {
		try {
			int id;
			Operation operation;
			int selection = 1;
			while (selection != 0) {
				System.out.println("\n*************************************************");
				System.out.println("3 search for a Operation...");
				System.out.println("*************************************************");
				System.out.println("1) enter operation id, id > 0 for rent operations, id < 0 for return");
				System.out.println("2) list all operations");
				System.out.println("0) go back");
				System.out.println("select an option: ");
				selection = in.nextInt();
				switch (selection) {
				case 1:
					System.out.print("enter operation id: ");
					id = in.nextInt();
					operation = searchOperationById(id);
					System.out.println("select operation: " + operation);
					return operation;
				case 2:
					listAllOperations();
					break;
				case 0:
					System.out.println("going back...");
					return null;
				default:
					System.out.println("you entered wrong number");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return null;
	}

	/**
	 * determines the type of a customer. first it checks if null then uses
	 * instanceof to determine the type and returns the correct type.
	 * 
	 * @param customer
	 * @param print    true= print output, false= don't print
	 * @return
	 */
	public static Customer typeOfCustomer(Customer customer) {
		if (customer == null) {
			System.out.println("customer==null");
			return null;
		}

		if (customer instanceof Resident resident) {
			return resident;
		} else if (customer instanceof Citizen citizen) {
			return citizen;
		} else {
			return customer;
		}
	}

	/**
	 * determines the type of a rentable. first it checks if null then uses
	 * instanceof to determine the type and returns the correct type.
	 * 
	 * @param rentable
	 * @param print    true= print output, false= don't print
	 * @return
	 */
	public static Rentable typeOfRentable(Rentable rentable, boolean print) {
		if (rentable == null) {
			System.out.println("rentable==null");
			return null;
		}

		if (rentable instanceof Car) {
			return rentable;
		} else {
			return rentable;
		}
	}

	/**
	 * returns a customer if found
	 *
	 * @param outId
	 * @param print =true print the customer
	 * @return type Customer
	 */
	public static Customer searchCustomerByID(int outId) {
		for (Customer customer : customers) {
			if (customer.getId() == outId) {
				return customer;
			}
		}
		return null;
	}

	/**
	 * returns a rentable if found. uses typeOfRentable()
	 * 
	 * @param outId
	 * @param print =true print the rentable
	 * @return type Rentable
	 */
	public static Rentable searchRentableByID(String outId) {
		Rentable rentable = null;
		for (Rentable rentableIterator : rentables)
			if (rentableIterator.getNumber().equals(outId))
				rentable = rentableIterator;
		System.out.println("searchRentableByID() :" + rentable);
		return rentable;
	}

	public static Customer searchCustomerByRentable(Rentable rentableOut) {
		String rentableId = rentableOut.getNumber();
		boolean found = false;

		for (Customer customer : customers) {
			if (!customer.getCustomerRentables().isEmpty())
				if (rentableOut instanceof Car car) {
					if (customer.getNoOfCars() > 0)
						for (Rentable rentable : customer.getCustomerRentables())
							if (rentable.getNumber().equals(rentableId)) {
								found = true;
								return customer;
							}
				} else {
					if (customer.getNoOfUnits() > 0)
						for (Rentable rentable : customer.getCustomerRentables())
							if (rentable.getNumber().equals(rentableId)) {
								found = true;
								return customer;
							}
				}
		}

		if (!found) {
			alertWindow("customer not found");
			System.out.println("customer not found");
		}

		return null;
	}

	public static Operation searchOperationByCustomerAndRentable(Customer customer, Rentable rentable, Operation op,
			String type) {
//		System.out.println("\n*************************************************");
//		System.out.println(" search operation by customer and rentable...");
//		System.out.println("*************************************************");
		boolean sameCustomer = false;
		boolean sameRentable = false;
		Operation operationFound = null;
		boolean isTypeProvided = false;
		boolean sameType = false;

		if (type.equals("rent") || type.equals("return"))
			isTypeProvided = true;

		for (Operation operation : operations) {

			if (op != null)
				if (op == operation)
					return operation;
			if (operation.getRentable()instanceof Car car)
				sameRentable = car.equals(rentable);
			else if (operation.getRentable()instanceof RealEstate realEstate)
				sameRentable = realEstate.equals(rentable);

			if (operation.getCustomer()instanceof Citizen citizen)
				sameCustomer = citizen.equals(customer);
			if (operation.getCustomer()instanceof Resident resident)
				sameCustomer = resident.equals(customer);
			if (operation.getCustomer()instanceof Company company)
				sameCustomer = company.equals(customer);

			if (isTypeProvided) {
				if (operation.getOperationType().equals(type))
					sameType = true;

				if (sameCustomer && sameRentable && sameType) {
					operationFound = operation;
					break;
				}
			} else {
				if (sameCustomer && sameRentable) {
					operationFound = operation;
					break;
				}
			}
		}
		if (operationFound == null) {
			alertWindow("search operation by customer and rentable failed to find the operation");
			System.out.println("search operation by customer and rentable failed to find the operation");
		}
		return operationFound;
	}

	public static Operation searchOperationById(int outId) {
//		System.out.println("\n*************************************************");
//		System.out.println(" search operation by id...");
//		System.out.println("*************************************************");
		for (Operation operation : operations)
			if (operation.getId() == outId)
				return operation;
		return null;
	}

	/**
	 * search for the most recent rent operation that has no return operation for
	 * the customer and rentable. if operation found you can use it to create return
	 * operation.
	 * 
	 * @param customer
	 * @param rentable
	 * @return operation that you can use to create return operation
	 */
	public static Operation searchRentOperationAtEndByCustomerAndRentable(Customer customer, Rentable rentable) {
		for (Operation op : operations.reversed()) {// 1 search for operation
			if (op.getCustomer().getId() == customer.getId()
					&& op.getRentable().getNumber().equals(rentable.getNumber())) {
				// 2 that has the same customer and rentable
				if (op.getOperationType().equals("rent")) {// 3 and type rent
					if (searchOperationById(op.getId() * -1) == null)
						// 4 and doesn't have return operation
						return op;
				} // 3
			} // 2
		} // 1
		return null;
	}

	public static Operation searchReturnOperationAtEndByCustomerAndRentable(Customer customer, Rentable rentable) {
		for (Operation op : operations.reversed()) {// 1 search for operation
			if (op.getCustomer().getId() == customer.getId()
					&& op.getRentable().getNumber().equals(rentable.getNumber())) {
				// 2 that has the same customer and rentable
				if (op.getOperationType().equals("return")) {// 3 and type rent
					if (searchOperationById(op.getId() * -1) == null)
						// 4 and doesn't have return operation
						return op;
				} // 3
			} // 2
		} // 1
		return null;
	}

	/**
	 * makes an operation of renting or returning a rentable it selects a customer
	 * and rentable then make operation used in main menu and other controller
	 * classes. it uses selecCustomerMenu(), selectRentableMenu(), selectRentable(),
	 * returnOperation(), rentOperation()
	 * 
	 * @param customerId
	 * @param rentableId
	 * @param selectOption =-1 ask user for options,=#option selects directly any
	 *                     option if customer and rentable not null. =3 return, =4
	 *                     rent.
	 * @return
	 * @throws CancelException
	 */
	private static boolean makeOperationMenu() throws CancelException {
		System.out.println("\n*************************************************");
		System.out.println(" make operation...");
		System.out.println("*************************************************");
		boolean result = false;
		Customer customer = null;
		Rentable rentable = null;
		try {
			customer = searchCustomerMenu();
			rentable = searchRentableMenu();
			int selection = 1;

			while (selection != 0) {
				System.out.println("\n*************************************************");
				System.out.println("5 rent/return a rentable...");
				System.out.println("*************************************************");
				System.out.println("1)select a customer");
				System.out.println("2)select a rentable");
				System.out.println("3)rent");
				System.out.println("4)return");
				System.out.println("0)go back: ");
				System.out.println("selected customer: " + customer);
				System.out.println("selected rentable: " + rentable);
				selection = in.nextInt();

				switch (selection) {

				case 1:// select customer
					customer = searchCustomerMenu();
					break;

				case 2:// select rentable
					rentable = searchRentableMenu();
					break;

				case 3:// rent
					if (rentable != null)
						if (!rentable.isStatus()) {// if not available
							alertWindow("rentable not available");
							System.out.println("rentable not available");
							rentable = null;
							break;
						}
					if (rentOperation(customer, rentable) != -1)
						result = true;
					System.out.println("make rent operation(): " + result);
					return result;

				case 4:// return
					Operation operation = searchOperationByCustomerAndRentable(customer, rentable, null, "return");
					if (returnOperation(customer, rentable) != null)
						result = true;
					System.out.println("make return operation(): " + result);
					return result;
				case 0:
					System.out.println("going back..." + "\n*************************************************");
					break;
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return false;
	}

	/**
	 * 
	 * @param customerId
	 * @param rentableId
	 * @return returns the id number or -1 if rent operation failed.
	 * @throws CancelException
	 */
	public static int rentOperation(int customerId, String rentableId) throws CancelException {
		return rentOperation(searchCustomerByID(customerId), searchRentableByID(rentableId));
	}

	/**
	 * 
	 * @param customer
	 * @param rentable
	 * @return returns the id number or -1 if rent operation failed.
	 * @throws CancelException
	 */
	public static int rentOperation(Customer customer, Rentable rentable) throws CancelException {
		if (customer != null && rentable != null) {
			if (rentable.isStatus()) {
				System.out.println("rentable is available");
				if (policy(customer, rentable)) {
					customer.addRentable(rentable);
					rentable.setStatus(false);
					Operation operation = new Operation(-1, customer, rentable, new Date("auto"), "rent");
					operations.add(operation);
					ongoingOperations.add(operation);
					System.out.println("--------rent operation succeeded--------");
					return operation.getId();
				}
			} else {
				alertWindow("rentable not available");
				System.out.println("rentable not available");
			}
		} else {
			alertWindow("customer/rentable null/not found" + "--------rent operation failed--------");
			System.out.println("customer/rentable null/not found" + "--------rent operation failed--------");
		}
		return -1;
	}

	/**
	 * probably the one that is used
	 * 
	 * @param operation
	 * @param customer
	 * @param rentable
	 * @return return operation id or -1 if failed
	 * @throws CancelException
	 */
	public static Operation returnOperation(int customerId, String rentableId) throws CancelException {
		return returnOperation(searchCustomerByID(customerId), searchRentableByID(rentableId));
	}

	/**
	 * create return operation if there is a rent operation for the same customer
	 * and rentable. return -1 if operation failed otherwise return negative
	 * operation id.
	 * 
	 * @param operation
	 * @param customerOut
	 * @param rentableOut
	 * @return
	 * @throws CancelException
	 */
	private static Operation returnOperation(Customer customerOut, Rentable rentableOut) throws CancelException {
		Operation returnOp = searchRentOperationAtEndByCustomerAndRentable(customerOut, rentableOut);
		// search for operation of type rent that has the same customer and rentable and
		// doesn't have return operation
		if (returnOp != null) {// 1 if found
			Customer customer = returnOp.getCustomer();
			Rentable rentable = returnOp.getRentable();
			if (customer.returnRentable(rentable)) {// 2 then return rentable and create return operation
				int operationId = returnOp.getId();
				rentable.setStatus(true);
				returnOp = new Operation(operationId, customer, rentable, new Date(false), "return");
				return returnOp;
			} // 2
		} // 1
		return null;
	}

	public static <T, E> boolean policy(T customer, E rentable) {
		// citizen conditions
		int minAgeCitizen = 16;

		// resident conditions
		int minAgeResidentCar = 18;
		int minAgeResidentRealEstate = 20;
		int noRealEstatesResident = 1;
		int noCarsResident = 2;

		// company conditions
		int noRealEstatesCompany = 10;
		int noCarsCompany = 10;

		// conditions results
		boolean minAge;
		boolean noCars;
		boolean expiry;
		boolean noRealEstates;

		boolean policyResult = false;

		if (customer instanceof Citizen citizen) {

			minAge = citizen.getAgeInYears() > minAgeCitizen;
			policyResult = minAge;
			if (!policyResult) {
				alertWindow("Citizen age: " + citizen.getAgeInYears() + "\nMinmum age: " + minAgeCitizen
						+ "\nPolicy result: " + policyResult);
				System.out.println("Citizen age: " + citizen.getAgeInYears() + "\nMinmum age: " + minAgeCitizen
						+ "\nPolicy result: " + policyResult);
			}
			return policyResult;

		} else if (customer instanceof Resident resident) {

			if (rentable instanceof Car car) {

				minAge = resident.getAgeInYears() > minAgeResidentCar;
				noCars = resident.getNoOfCars() < noCarsResident;
				policyResult = minAge && noCars;
				if (!policyResult) {
					alertWindow("Resident age: " + resident.getAgeInYears() + "\nMinmum age: " + minAgeResidentCar
							+ "\nNo. of cars owned: " + resident.getNoOfCars() + "\nNo. of cars allowed: "
							+ noCarsResident + "\nPolicy result: " + policyResult);
					System.out.println("Resident age: " + resident.getAgeInYears() + "\nMinmum age: "
							+ minAgeResidentCar + "\nNo. of cars owned: " + resident.getNoOfCars()
							+ "\nNo. of cars allowed: " + noCarsResident + "\nPolicy result: " + policyResult);
				}
				return policyResult;

			} else if (rentable instanceof RealEstate realEstate) {

				minAge = resident.getAgeInYears() > minAgeResidentRealEstate;
				noRealEstates = resident.getNoOfUnits() < noRealEstatesResident;
				policyResult = minAge && noRealEstates;
				if (!policyResult) {
					alertWindow("Resident age: " + resident.getAgeInYears() + "\nMinmum age: "
							+ minAgeResidentRealEstate + "\nNo. of realEstates owned: " + resident.getNoOfUnits()
							+ "\nNo. of realEstates allowed: " + noRealEstatesResident + "\nPolicy result: "
							+ policyResult);
					System.out.println("Resident age: " + resident.getAgeInYears() + "\nMinmum age: "
							+ minAgeResidentRealEstate + "\nNo. of realEstates owned: " + resident.getNoOfUnits()
							+ "\nNo. of realEstates allowed: " + noRealEstatesResident + "\nPolicy result: "
							+ policyResult);
				}
				return policyResult;
			}

		} else if (customer instanceof Company company) {

			expiry = company.isExpired();
			if (rentable instanceof Car car) {
				noCars = company.getNoOfCars() < noCarsCompany;
				policyResult = expiry && noCars;
				if (!policyResult) {
					alertWindow("Company expiry date: " + company.getExpiryDate() + "\nNo. of cars owned: "
							+ company.getNoOfUnits() + "\nNo. of cars allowed: " + noRealEstatesCompany
							+ "\nPolicy result: " + policyResult);
					System.out.println("Company expiry date: " + company.getExpiryDate() + "\nNo. of cars owned: "
							+ company.getNoOfUnits() + "\nNo. of cars allowed: " + noRealEstatesCompany
							+ "\nPolicy result: " + policyResult);
				}
				return policyResult;
			} else if (rentable instanceof RealEstate realEstate) {
				noRealEstates = company.getNoOfUnits() < noRealEstatesCompany;
				policyResult = expiry && noRealEstates;
				if (!policyResult) {
					alertWindow("Company expiry date: " + company.getExpiryDate() + "\nNo. of realEstates owned: "
							+ company.getNoOfUnits() + "\nNo. of realEstates allowed: " + noRealEstatesCompany
							+ "\nPolicy result: " + policyResult);
					System.out.println("Company expiry date: " + company.getExpiryDate()
							+ "\nNo. of realEstates owned: " + company.getNoOfUnits() + "\nNo. of realEstates allowed: "
							+ noRealEstatesCompany + "\nPolicy result: " + policyResult);
				}
				return policyResult;
			}
		}
		return false;
	}

	/**
	 * probably replace with remove object will remove this method
	 * 
	 * @throws CancelException
	 */
	private static boolean deleteMenu() throws CancelException {
		Customer customer;
		Rentable rentable;
		Operation operation;
		int selection = 1;
		boolean result = false;
		try {
			do {
				selection = 1;
				System.out.println("\n*************************************************");
				System.out.println("12 delete Menu...");
				System.out.println("*************************************************");
				System.out.println("select an option: ");
				System.out.println("1)delete customer.");
				System.out.println("2)delete rentable.");
				System.out.println("3)delete operation.");
				System.out.println("0)go back.");
				selection = in.nextInt();
				switch (selection) {
				case 1: {// delete customer
					customer = searchCustomerMenu();
					result = deleteObject(customer);
					System.out.println("deleteMenu() Customer: " + result);
					return result;
				}
				case 2: {// delete rentable
					rentable = searchRentableMenu();
					result = deleteObject(rentable);
					System.out.println("deleteMenu() rentable: " + result);
					return result;
				}
				case 3: { // searchOperations()
					operation = searchOperationMenu();
					result = deleteObject(operation);
					System.out.println("deleteMenu() operation: " + result);

					return result;
				}
				case 0:
					System.out.println("going back...");
					break;
				default:
					System.out.println("you entered wrong number");
				}
			} while (selection != 0);
		} catch (InputMismatchException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
		return false;
	}

	/**
	 * not used anywhere in InfoSys but used in Controller classes. uses
	 * checkCustomerId() and checkRentableId() why? to check if unique or not. if
	 * unique why? it determines the type and adds the object to the appropriate
	 * list.
	 * 
	 * @param object
	 * @return
	 * @throws CancelException
	 */
	public static boolean deleteObject(Object object) throws CancelException {
		boolean result = false;
		if (object == null) {
			alertWindow("-------can't remove null Object-------");
			System.out.println("-------can't remove null Object-------");
			return false;
		}
		boolean isRemoved = false;
		if (object instanceof Operation operation) {
			result = deleteOperation(operation);
			System.out.println("deleteObject() operation: " + result);
			System.out.println(operation);

			return result;
		} else if (object instanceof Customer customer) {
			result = deleteCustomer(customer);
			System.out.println("deleteObject() customer: " + result);
			System.out.println(customer);
			return result;
		} else if (object instanceof Rentable rentable) {// OBJECT INSTANCE OF RENTABLE
			result = deleteRentable(rentable);
			System.out.println("deleteObject() rentable: " + result);
			System.out.println(rentable);
			return result;
		}
		return isRemoved;
	}

	/**
	 * takes care of the logic of returning operations and cleaning lists.
	 * 
	 * @param rentable
	 * @return
	 * @throws CancelException
	 */
	private static boolean deleteRentable(Rentable rentable) throws CancelException {
		boolean result = false;
		// false=(not found/unique)
		// true=(found/not unique)
		if (checkRentableId(rentable.getNumber())) {// 1 check if rentable id is in system
			if (rentable.isStatus()) {// 2 rentable is not taken and you don't need to return then delete directly
				result = cleanRentablesListAndSet(rentable);
				System.out.println(result);
				return result;
			} // 2
			else {// 3 rentable is taken, then return it
				Customer customer = searchCustomerByRentable(rentable);
				if (customer != null) {
					Operation operation = searchOperationByCustomerAndRentable(customer, rentable, null, "");
					if (operation != null)
						if (returnOperation(customer.getId(), rentable.getNumber()) != null) {// if return successful
							if (cleanRentablesListAndSet(rentable)) {// remove rentable
								result = true;
								System.out.println("delete rentable: " + result);
								return result;
							}
						}
				}
			} // 3
		} // 1
		return result;
	}

	/**
	 * takes care of the logic of returning operations and cleaning lists.
	 * 
	 * @param customer
	 * @return
	 * @throws CancelException
	 */
	private static boolean deleteCustomer(Customer customer) throws CancelException {
		boolean result = false;
		// false=(not found/unique)
		// true=(found/not unique)
		if (checkCustomerId(customer.getId())) {
			if (!customer.getCustomerRentables().isEmpty())
				for (Rentable rentable : new ArrayList<>(customer.getCustomerRentables())) {
					Operation operation = searchOperationByCustomerAndRentable(customer, rentable, null, "");
					if (returnOperation(operation.getCustomer(), operation.getRentable()) == null)
						return false;
				}
			result = cleanCustomersListAndSet(customer);
			System.out.println("deleteCustomer() : " + result);
			return result;
		}
		return result;
	}

	/**
	 * delete operation at the end. meaning that this customer and rentable has not
	 * been used in other operations and this is the most recent operation.
	 * 
	 * @param operationOut
	 * @throws CancelException
	 */
	private static boolean deleteOperation(Operation operationOut) throws CancelException {
		Customer cus = operationOut.getCustomer();
		Rentable rent = operationOut.getRentable();
		Operation op;
		if (operationOut != null) {
			if (operationOut.getOperationType().equals("rent")) {
				// if opOut of type rent at the end for the same cus and rentable
				// return items without creating return operation then delete opOut
				op = searchRentOperationAtEndByCustomerAndRentable(cus, rent);
				if (op != null) {
					cus.returnRentable(rent);
					rent.setStatus(true);
					return operations.remove(operationOut);
				}
			} else {
				// if opOut of type return at the end for the same cus and rentable
				// make them taken again as in their last operation but their
				// values must be the same but here i am not going to go check.
				op = searchReturnOperationAtEndByCustomerAndRentable(cus, rent);
				if (op != null) {
					cus.addRentable(rent);
					rent.setStatus(false);
					return operations.remove(operationOut);
				}
			}
		}
		return false;
	}

	/**
	 * takes care of cleaning rentable from all lists and sets. it doesn't return or
	 * do any logic to other objects like operations or customers.
	 * 
	 * @param rentable
	 * @return true or false
	 */
	private static boolean cleanRentablesListAndSet(Rentable rentable) {
		boolean result = false;
		result = rentablesIds.remove(rentable.getNumber());
		if (!result) {
			rentablesIds.add(rentable.getNumber());
			return false;
		}
		result = rentablesSet.remove(rentable);
		if (!result) {
			rentablesSet.add(rentable);
			return false;
		}
		result = rentables.remove(rentable);
		if (!result) {
			rentables.add(rentable);
			return false;
		}
		return true;
	}

	/**
	 * takes care of cleaning rentable from all lists and sets. it doesn't return or
	 * do any logic to other objects like operations or customers.
	 * 
	 * @param customer
	 * @return
	 */
	private static boolean cleanCustomersListAndSet(Customer customer) {
		boolean result = false;
		result = customersIds.remove(customer.getId());
		if (!result) {
			customersIds.add(customer.getId());
			return false;
		}
		result = customersSet.remove(customer);
		if (!result) {
			customersSet.add(customer);
			return false;
		}
		result = customers.remove(customer);
		if (!result) {
			customers.add(customer);
			return false;
		}
		return true;
	}

	/**
	 * the exception happens when a class is modified after it's been serialized and
	 * the id changes when it's modified so if id's are different we get this
	 * exception
	 */
	public static void read() {
		try (FileInputStream fis = new FileInputStream("appdata.ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			// Read the single container object
			SaveApplicationData loadedData = (SaveApplicationData) ois.readObject();

			// Clear existing lists to prevent duplicates
			customers.clear();
			rentables.clear();
			operations.clear();
			try {
				customersSet.clear();
				rentablesSet.clear();
				customersIds.clear();
				rentablesIds.clear();
			} catch (NullPointerException e) {
				System.out.println(e);
				System.out.println("sets are empty you can't clear them");
			}

			// Populate lists from the loaded container
			customers.addAll(loadedData.getCustomers());
			rentables.addAll(loadedData.getRentables());
			operations.addAll(loadedData.getOperations());
			System.out.println("Application data loaded successfully.");

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			System.out.println("No save file found ('appdata.ser'). Starting with empty data.");
		}
	}

	public static void save() {
		// Create an instance of our container with the current data
		SaveApplicationData dataToSave = new SaveApplicationData(customers, rentables, operations);

		// Save the single container object to one file
		try (FileOutputStream fos = new FileOutputStream("appdata.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(dataToSave);
			System.out.println("Application data serialized successfully.");

		} catch (IOException e) {
			e.printStackTrace();
			alertWindow("err: Could not save application data.");
			System.out.println("err: Could not save application data.");
		}
	}

	/**
	 * initialize sets
	 */
	public static void initializeSets() {
		// fill id set
		for (Customer customer : customers)
			customersIds.add(customer.getId());
		for (Rentable rentable : rentables)
			rentablesIds.add(rentable.getNumber());

		printRentablesIDsSet();

		rentablesSet = new HashSet<>(rentables);

		customersSet = customers.stream().collect(Collectors.toSet());
		rentablesSet = new HashSet<>(rentables);

		printCustomersSet();

		operationsIDs = operations.stream().map(Operation::getId)
				.collect(java.util.stream.Collectors.toCollection(ArrayList::new));

		ongoingOperations = operations.stream().filter(op -> op.getId() > 0 && !operationsIDs.contains(op.getId() * -1))
				.collect(java.util.stream.Collectors.toCollection(ArrayList::new));

		printOngoingOperations();
	}

	/**
	 * uses a set that hold customers ids to check for uniqueness. if it returns
	 * true then the id is not unique and it already exist.
	 * 
	 * @param id
	 * @return true=(found/not unique), false=(not found/unique)
	 */
	public static boolean checkCustomerId(int id) {
		return customersIds.contains(id);
	}

	/**
	 * uses a set that hold rentables ids to check for uniqueness.
	 * 
	 * @param id
	 * @return true=(found/not unique), false=(not found/unique)
	 */
	public static boolean checkRentableId(String id) {
		return rentablesIds.contains(id);
	}

	/**
	 * used in createCustomerMenu() and createRentableMenu(). uses checkCustomerId()
	 * and checkRentableId() to check if unique or not. if unique it determines the
	 * type and adds the object to the appropriate list.
	 * 
	 * @param object
	 * @return true= object added successfully, false= couldn't add due to duplicate
	 *         id/null
	 */
	public static boolean addObject(Object object) {
		if (object == null) {
			System.out.println("-------can't add null Object-------");
			return false;
		}
		boolean uniqueId = false;
		if (object instanceof Customer customer) {
			// add object=true=!(false=(not found/unique))
			// don't add object=false=!(true=(found/not unique))
			if (!checkCustomerId(customer.getId()))
				uniqueId = customersSet.add(customer);
			if (uniqueId) {
				customersIds.add(customer.getId());
				customers.add(customer);
				System.out.println("addObject() customer: " + customer);
			} else {
				alertWindow("\n*****************************************"
						+ "\ncouldn't add customer, duplicate id... \ncustomer already exist"
						+ "\n*****************************************");
				System.out.println("\n*****************************************"
						+ "\ncouldn't add customer, duplicate id... \ncustomer already exist"
						+ "\n*****************************************");
			}
			return uniqueId;
		} else if (object instanceof Rentable rentable) {// OBJECT INSTANCE OF RENTABLE
			if (!checkRentableId(rentable.getNumber()))
				uniqueId = rentablesSet.add(rentable);
			if (uniqueId) {
				rentablesIds.add(rentable.getNumber());
				rentables.add(rentable);
				System.out.println("\n*****************************************" + "\nrentable added: \n"
						+ rentables.get(rentables.size() - 1) + "\n*****************************************");
				System.out.println("addObject() rentable: " + rentable);

			} else {
				alertWindow("\n*****************************************"
						+ "\ncouldn't add rentable, duplicate id... \nrentable already exist"
						+ "\n*****************************************");
				System.out.println("\n*****************************************"
						+ "\ncouldn't add customer, duplicate id... \ncustomer already exist"
						+ "\n*****************************************");
			}
			return uniqueId;
		}
		return uniqueId;
	}

	/**
	 * shows a message in a dialog window. used to alert user for important
	 * operations results.
	 * 
	 * @param message
	 */
	public static void alertWindow(String message) {
		System.out.println(" alert window...");
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("An error occurred");
			alert.setContentText(message);
			// Get the Stage of the Alert dialog
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

			// Set the stage to be always on top
			stage.setAlwaysOnTop(true);
			// Optional: Set modality to APPLICATION_MODAL or WINDOW_MODAL
			// This makes the alert block input to other windows, which often helps
			// keep it visually on top and prevents users from interacting with
			// the main application behind it.
			// If you want it to block only its owner window, use Modality.WINDOW_MODAL
			// If you want it to block all windows in the application, use
			// Modality.APPLICATION_MODAL
			alert.initModality(Modality.APPLICATION_MODAL);
			// Or if you have a primary stage and want this alert to be modal relative to
			// it:
			// alert.initOwner(primaryStage); // You would need to pass primaryStage to this
			// method
			// alert.initModality(Modality.WINDOW_MODAL);

			alert.showAndWait();

			// You would typically call this from within your JavaFX application's start
			// method
			// // or from an event handler that's already on the JavaFX Application Thread.
			// // For a standalone test, you might need to launch a minimal JavaFX
			// application first.
			// // For demonstration, let's just call it directly, though in a real app,
			// // alertWindow should be called when a JavaFX context is active.
			// Application.launch(TestApp.class, args); // Launch a dummy app
		});
	}

	public static void printRentablesIDsSet() {
		System.out.println("printRentablesIDsSet(): ");
		rentablesIds.forEach(System.out::println);
	}

	public static void printCustomersSet() {
		System.out.println("printCustomersSet(): ");
		int i = 1;
		for (Customer customer : customersSet)
			System.out.println("" + (i++) + " " + customer);
	}

	public static void printOngoingOperations() {
		System.out.println("ongoingOperations(): ");
		int i = 1;
		for (Operation op : ongoingOperations)
			System.out.println("" + (i++) + " " + op);
	}

	public static void listOperationsByDate() throws CancelException {
		ArrayList<Rentable> specificDateRentables = new ArrayList<>();
		Date date = new Date(true);// user entry
		if (date != null)
			for (Operation operation : operations) {
				Date dd = operation.getOperationDate();
				if (dd.getDay() == date.getDay() && dd.getMonth() == date.getMonth()
						&& dd.getYear() == date.getYear()) {
					specificDateRentables.add(operation.getRentable());
					System.out.println("list of all operations on date " + date.getDay() + "/" + date.getMonth() + "/"
							+ date.getYear() + " " + operation.getOperationType() + " "
							+ operation.getCustomer().getId() + " " + operation.getCustomer().getName() + " "
							+ operation.getRentable().getClass().getSimpleName() + " "
							+ operation.getRentable().getNumber());
				}
			}
	}

	private static void reportMenu() throws CancelException {
		int initialRun = 0;
		try {
			int selection = 1;
			do {
				if (initialRun == 0) {// to run only one time at first loop
					listAllCompanies();
					listAllResidents();
					listAllCitizens();
					listAllCars();
					listAllRealEstates();
					initialRun++;
				}
				System.out.println("\n*************************************************");
				System.out.println("9 reporting... ");
				System.out.println("*************************************************");

				System.out.println("select the report you want:");
				System.out.println("1)list all rented rentables by a customer.");
				System.out.println("2)list all rent operations on a specific date.");
				System.out.println("3)list all customers.");
				System.out.println("4)list all operations.");
				System.out.println("5)list all rentables.");
				System.out.println("6)list available rentables.");
				System.out.println("7)list unavailable rentables.");
				System.out.println("8)list all companies.");
				System.out.println("9)list all residents.");
				System.out.println("10)list all citizens.");
				System.out.println("11)list all cars.");
				System.out.println("12)list all realestates.");
				System.out.println("13)report all options from 8 to 12.");
				System.out.println("0)go back.");
				selection = in.nextInt();
				switch (selection) {
				case 1: {
					Customer customer = searchCustomerMenu();

					if (customer != null) {
						System.out.println("Report 1 list all rentables by customer: \nname: " + customer.getName()
								+ ", id: " + customer.getId());
						for (Rentable customerRentable : customer.getCustomerRentables())
							System.out.println(customerRentable);
					} else
						System.out.println("couldn't report. customer not found/null");
					break;
				}
				case 2: {
					listOperationsByDate();
					break;
				}
				case 3:
					listAllCustomers();
					break;
				case 4:
					listAllOperations();
					break;

				case 5:
					listAllRentables();
					break;
				case 6:
					listAvailUnavilRentables(true);
					break;
				case 7:
					listAvailUnavilRentables(false);
					break;

				case 8:
					listAllCompanies();
					break;
				case 9:
					listAllResidents();
					break;
				case 10:
					listAllCitizens();
					break;
				case 11:
					listAllCars();
					break;
				case 12:
					listAllRealEstates();
					break;
				case 13:
					listAllCompanies();
					listAllResidents();
					listAllCitizens();
					listAllCars();
					listAllRealEstates();
					break;
				case 0:
					System.out.println("going back...");
					break;
				default:
					System.out.println("you entered wrong number");
				}
			} while (selection != 0); // END while
		} catch (InputMismatchException | CancelException e) {
			System.out.println("you enterd a string in place of integers.\npress any key to go back...");
			in.nextLine();
			System.out.println("exiting...");
			main(null);
		}
	} // END report()

	/**
	 * 
	 * @param available true=available, false=unavailable.
	 * @return
	 */
	public static ArrayList<Rentable> listAvailUnavilRentables(boolean available) {
		ArrayList<Rentable> availableRentables = new ArrayList<>();
		ArrayList<Rentable> unavailableRentables = new ArrayList<>();
		for (Rentable rentable : rentables)
			if (rentable.isStatus()) {
				availableRentables.add(rentable);
			} else {
				unavailableRentables.add(rentable);
			}

		if (available) {// 1 print available array
			for (Rentable rentable : availableRentables)
				if (rentable instanceof Car car) {
					System.out.println(car);
				} else {
					RealEstate realestate = (RealEstate) rentable;
					System.out.println(realestate);
				}
			return availableRentables;
		} // 1
		else if (!available) {// 2 print unavailable array
			for (Rentable rentable : unavailableRentables)
				if (rentable instanceof Car car) {
					System.out.println(car);
				} else {
					RealEstate realestate = (RealEstate) rentable;
					System.out.println(realestate);
				}
			return unavailableRentables;
		} // 2
		return null;
	}

	public static void listAllRentables() {
		int i = 1;
		System.out.println("rentables: ");
		for (Rentable rentable : rentables)
			System.out.println("\t" + (i++) + " " + rentable);
	}

	public static void listAllOperations() {
		int i = 1;
		System.out.println("operations: ");
		for (Operation operation : operations)
			System.out.println("\t" + (i++) + " " + operation);
	}

	public static ArrayList<Customer> listAllCustomers() {
		int i = 1;
		System.out.println("customer: ");
		for (Customer customer : customers) {
			if (customer instanceof Resident resident) {
				System.out.println("\t" + (i++) + ") " + resident);
			}
			if (customer instanceof Citizen citizen) {
				System.out.println("\t" + (i++) + ") " + citizen);
			}
			if (customer instanceof Company company) {
				System.out.println("\t" + (i++) + ") " + company);
			}
		} // END FOR LOOP
		return customers;
	}

	public static void listAllCompanies() {
		int i = 1;
		System.out.println("companies: ");
		for (Customer companyIterator : customers) {
			if (companyIterator instanceof Company company) {
				if (company.isExpired())
					System.out.print("expired");
				System.out.print("\t" + (i++) + " " + company);
			}
		}
	}

	public static void listAllCars() {
		int i = 1;
		System.out.println("cars: ");
		for (Rentable carIterator : rentables)
			if (carIterator instanceof Car car)
				System.out.println("\t" + (i++) + " " + car);
	}

	public static void listAllRealEstates() {
		int i = 1;
		System.out.println("realestates: ");
		for (Rentable realestateIterator : rentables)
			if (realestateIterator instanceof RealEstate realestate)
				System.out.println("\t" + (i++) + " " + realestate);
	}

	public static void listAllResidents() {
		int i = 1;
		System.out.println("residents: ");
		for (Customer residentIterator : customers)
			if (residentIterator instanceof Resident resident)
				System.out.println("\t" + (i++) + " " + resident);

	}

	public static void listAllCitizens() {
		int i = 1;
		System.out.println("Citizens: ");
		for (Customer citizienIterator : customers)
			if (citizienIterator instanceof Citizen citizen)
				System.out.println("\t" + (i++) + " " + citizen);
	}

}
