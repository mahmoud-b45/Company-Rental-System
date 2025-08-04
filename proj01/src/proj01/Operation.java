package proj01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Operation implements Serializable {
	private static final long serialVersionUID = 1L; // if i add it manually
	//i need to change it when ever i change something in the class to
	//prevent errors. if it's added automatically it will change when
	//ever something changes and you would need to do something to confirm
	//it's the same so you can be able to serialize and read/write the object.

	private int operationId;
	private Customer customer;
	private Rentable rentable;
	private Date operationDate;
	private String operationType; // rent or return
	private transient Scanner in = new Scanner(System.in);

	OperationSnapshot operationSnapshot=new OperationSnapshot();
	private int price;
//	private int[][] arr=new int[6][1];
//	ArrayList<Object> arr1 = new ArrayList<>();
//	ArrayList<ArrayList<Integer>> arr2 = new ArrayList<>();

	// The snapshot of all item details, both common and specific
//	private Map<String, Object> rentableSnapshot;
//	private Map<String, Object> customerSnapshot;
	private Random random = new Random();

	/**
	 * rent operation positive id, return operation same id of the rent but
	 * negative. if a rent operation id is automatically generated, a return
	 * operation you need to provide the same id used for the rent operation.
	 *
	 * @param id
	 * @param customer
	 * @param rentable
	 * @param date
	 * @param operationType
	 * @throws CancelException
	 */
	public Operation(int id, Customer customer, Rentable rentable, Date date, String operationType) throws CancelException {
		this.customer = customer;
		this.rentable = rentable;
		this.operationType = operationType;
		this.operationDate = date;
		if (operationType.equals("rent")) {
			this.operationId = random.nextInt(1000);
		} else {
			this.operationId = id * -1;
		}
		// Polymorphism in action!
		// This single line works for Books, Cars, or any other Rentable.
//		this.rentableSnapshot = rentable.createSnapshot();
//		this.customerSnapshot = customer.createSnapshot();
		setOperationSnapshot();
	}

	/**
	 * rent operation positive id, return operation same id of the rent but
	 * negative. if a rent operation id is automatically generated, a return
	 * operation you need to provide the same id used for the rent operation.
	 *
	 * @param id
	 * @param customer
	 * @param rentable
	 * @param type
	 * @throws CancelException
	 */
	public Operation(int id, Customer customer, Rentable rentable, String type) throws CancelException {
		setCustomer(customer);
		setRentable(rentable);
		setOperationDate();
		setOperationType(type);

		if (operationType.equals("rent")) {
			this.operationId = random.nextInt(1000);
		} else {
			this.operationId = id * -1;
		}
		setOperationSnapshot();

		// if (type.equals("rent")) {
//			if (rentable instanceof RealEstate realEstate)
//				this.price = realEstate.getMonthlyPrice();
//			else
//				this.price = ((Car) rentable).getMonthlyPrice();
//
//		} else {
//			if (rentable instanceof RealEstate realEstate)
//				this.price = realEstate.getMonthlyPrice() * -1;
//			else
//				this.price = ((Car) rentable).getMonthlyPrice() * -1;
//		}
	}

	/**
	 * copy constructor this helps you copy from other objects
	 *
	 * @param operation
	 */
	public Operation(Operation operation) {
//		arr[0][1]= 1;
//		arr1.add(operation.getCustomer())

		if (operation.getRentable() != null) {
			Rentable originalRentable = operation.getRentable();
			if (originalRentable instanceof Car car) {
				this.rentable = new Car(car);
			} else if (originalRentable instanceof RealEstate realEstate) {
				this.rentable = new RealEstate(realEstate);
			} else {
				// Fallback for base Rentable or other types you might add
				this.rentable = new Rentable(originalRentable);
			}
		} else {
			this.rentable = null;
		}
		if (operation.getCustomer() != null) {
			Customer originalCustomer = operation.getCustomer();
			if (originalCustomer instanceof Citizen citizen) {
				this.customer = new Citizen(citizen);
			} else if (originalCustomer instanceof Resident resident) {
				this.customer = new Resident(resident);
			} else if (originalCustomer instanceof Company company) {
				this.customer = new Company(company);

			} else {
				// Fallback for base Rentable or other types you might add
				this.customer = new Customer(originalCustomer);
			}
		} else {
			this.customer = null;
		}
//		this.customer = (operation.getCustomer() != null) ? new Customer(operation.getCustomer()) : null;
//		this.rentable = (operation.getRentable() != null) ? new Rentable(operation.getRentable()) : null;
		this.operationDate = (operation.getOperationDate() != null) ? new Date(operation.getOperationDate()) : null;
		this.operationType = operation.getOperationType();
		this.operationId = operation.getId();
//		this.rentableSnapshot = deepCopyMap(operation.getRentableSnapshot());
//		this.customerSnapshot = deepCopyMap(operation.getCustomerSnapshot());
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public OperationSnapshot getOperationSnapshot() {
		return operationSnapshot;
	}

	public void setOperationSnapshot(OperationSnapshot operationSnapshot) {
		this.operationSnapshot = operationSnapshot;
	}

	public void setOperationSnapshot() throws CancelException {
		operationSnapshot.setCustomer(customer);
		operationSnapshot.setRentable(rentable);
		operationSnapshot.setOperationDate(operationDate);
		operationSnapshot.setOperationType(operationType);
		operationSnapshot.setOperationId(operationId);
	}

//	public void setRentableSnapshot(Map<String, Object> rentableSnapshot) {
//		this.rentableSnapshot = rentableSnapshot;
//	}

//	public void setCustomerSnapshot(Map<String, Object> customerSnapshot) {
//		this.customerSnapshot = customerSnapshot;
//	}

	private Map<String, Object> deepCopyMap(Map<String, Object> originalMap) {
		if (originalMap == null) {
			return null;
		}
		Map<String, Object> newMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value instanceof Car car) {
				newMap.put(key, new Car(car));
			} else if (value instanceof RealEstate realEstate) {
				newMap.put(key, new RealEstate(realEstate));
			} else if (value instanceof Citizen citizen) {
				newMap.put(key, new Citizen(citizen));
			} else if (value instanceof Resident resident) {
				newMap.put(key, new Resident(resident));
			} else if (value instanceof Company company) {
				newMap.put(key, new Company(company));
			} else if (value instanceof ArrayList) {
				// Be careful: This is a shallow copy of the ArrayList, but if the elements
				// inside the ArrayList are mutable, you'd need to iterate and deep copy them
				// too.
//                 newMap.put(key, new ArrayList<>((ArrayList) value));
				newMap.put(key, deepcopyArray((ArrayList<Rentable>) value));
			}
			// Add more if you have custom List/Map types as values
			else {
				// For immutable types (String, Integer, Boolean, primitives wrapped in Objects,
				// etc.)
				// and any other types you deem safe for shallow copy (or don't have deep copy
				// logic for),
				// simply copy the reference.
				newMap.put(key, value);
			}
		}
		return newMap;
	}

	private ArrayList<Rentable> deepcopyArray(ArrayList<Rentable> originalArray) {
		if (originalArray == null) {
			return null;
		}
		ArrayList<Rentable> newArray = new ArrayList<>();
		for (Rentable rentable : originalArray) {
			if (rentable instanceof Car car) {
				newArray.add(new Car(car));
			} else if (rentable instanceof RealEstate realEstate) {
				newArray.add(new RealEstate(realEstate));
			}
		}
		return newArray;
	}

	@Override
	public boolean equals(Object operation) {
		if (this == operation) {
			System.out.println("Operation found by refrence");
			return true;
		}
		if (operation == null || getClass() != operation.getClass()) {
			return false;
		}
		Operation o = (Operation) operation;
		boolean sameCustomer = Objects.equals(this.customer, o.getCustomer());
		boolean sameRentable = Objects.equals(this.rentable, o.getRentable());
		boolean sameId = this.operationId == o.getId();
		if (sameCustomer && sameRentable && sameId) {
			System.out.println("Operation equals found by value not refrence");
		}
		return sameCustomer && sameRentable && sameId;
	}

	public int getId() {
		return operationId;
	}

	public void setId(int id) {
		this.operationId = id;
	}

//	public int getPriceFromSnapshot() {
//		// Safely cast the object back to its type
//		return (int) this.rentableSnapshot.get("monthlyPrice");
//	}

//	public Map<String, Object> getCustomerSnapshot() {
//		return customerSnapshot;
//	}
//
//	public Map<String, Object> getRentableSnapshot() {
//		return rentableSnapshot;
//	}

	public int getPrice() {
		return getOperationSnapshot().getRentable().getMonthlyPrice(getOperationSnapshot().getRentable());
	}

//
	/**
	 * only used to update operation details in case of errors.
	 * otherwise operation class is immutable.
	 *
	 * @param price
	 */
	public void setPrice(int price) {
//		rentableSnapshot.put("monthlyPrice", price);
		operationSnapshot.setPrice(price);
	}

//	public Operation(Customer customer, Rentable rentable, Date operationDate, String type) {
//		super();
//		this.customer = customer;
//		this.rentable = rentable;
//		this.operationDate = operationDate;
//		this.operationType = type;
//		if(type.equals("rent")) {
//			if(rentable instanceof RealEstate realEstate)
//			this.price=realEstate.getMonthlyPrice();
//			else this.price=((Car)rentable).getMonthlyPrice();
//
//		}else {
//			if(rentable instanceof RealEstate realEstate)
//				this.price=realEstate.getMonthlyPrice()*-1;
//				else this.price=((Car)rentable).getMonthlyPrice()*-1;
//		}
//
//	}

	public Rentable getRentable() {
		return rentable;
	}

	public void setRentable(Rentable rentable) {
		this.rentable = rentable;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate() throws CancelException {
		System.out.println(getClass().getSimpleName() + " enter date manualy? (y/n/x)");
		String str = in.next();
		while (!str.equalsIgnoreCase("x")) {
			if (str.equalsIgnoreCase("y")) {
				this.operationDate = new Date(true);
				str = "x";
			} else if (str.equalsIgnoreCase("n")) {
				this.operationDate = new Date(false);
				str = "x";
			} else {
				System.out.println("invalid input. try again or enter x to exit.");
				str = in.next();
			}
		}
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String type) {
		this.operationType = type;
	}

//	@Override
//	public String toString() {
//		return "Operation [id=" + id + ", customer=" + customer + ", rentable=" + rentable + ", operationDate="
//				+ operationDate + ", operationType=" + operationType + ", rentableSnapshot=" + rentableSnapshot
//				+ ", customerSnapshot=" + customerSnapshot + ", random=" + random + "]";
//	}

//	@Override
//	public String toString() {
//
//		return "Operation [id=" + id + ", " + customer.getClass().getSimpleName() + " [id="
//				+ customer.getId() +", "+customer.getName()  + "]" + ", "
//				+ rentable.getClass().getSimpleName() + "[id=" + rentable.getNumber() + ", status="
//				+ rentable.isStatus() /* + ", monthlyPrice=" + rentable.getMonthlyPrice() */ + "], operationDate="
//				+ operationDate + ", operationType=" + operationType + ", rentableSnapshot="
//				+ rentableSnapshot.get("Class") + ", customerSnapshot=" + customerSnapshot.get("Class") + ", noOfCars="
//				+ customerSnapshot.get("noOfCars") + ", noOfUnits=" + customerSnapshot.get("noOfUnits") + "]";
//	}
	@Override
	public String toString() {

		return "Operation " + operationType + " [id=" + operationId + ", " + customer.getClass().getSimpleName()
				+ " [id=" + customer.getId() + ", " + customer.getName() + "]" + ", "
				+ rentable.getClass().getSimpleName() + "[id=" + rentable.getNumber() + ", status="
				+ rentable.isStatus() + ", monthlyPrice=$" + /*rentable.getMonthlyPrice(rentable)*/operationSnapshot.getPrice() + "], operationDate="
				+ operationDate +/* ", rentableSnapshot=" + rentableSnapshot.get("Class") + ", customerSnapshot="
				+ customerSnapshot.get("Class") + */", noOfCars=" + /*customerSnapshot.get("noOfCars")*/operationSnapshot.getNoOfCars() + ", noOfUnits="
				+/* customerSnapshot.get("noOfUnits")*/operationSnapshot.getNoOfUnits() + "]";
	}
//	@Override
//	public String toString() {
//		String str ="Operation [id=" + id + ", operationDate="
//				+ operationDate + ", operationType=" + operationType + ", rentableSnapshot=";
//		System.out.println(str);
//		for(Entry<String, Object> item:rentableSnapshot.entrySet())
//			System.out.println(item);
//
//				System.out.println(", customerSnapshot=");
//				for(Entry<String, Object> item:customerSnapshot.entrySet())
//					System.out.println(item);
//
//		return "";
//
//	}

}
