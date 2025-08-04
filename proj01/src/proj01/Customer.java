package proj01;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.image.Image;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L; // Good practice for serialization

	private int id;
	private String name;
	private ArrayList<Rentable> customerRentables;
	private int noOfUnits;
	private int noOfCars;
	private transient Scanner in = new Scanner(System.in);
	private String imagePath;
	private JFileChooser fileChooser = new JFileChooser();

	public Customer() throws CancelException {
		setId();
		setName();
		this.customerRentables = new ArrayList<>();
		this.noOfUnits = 0;
		this.noOfCars = 0;
		setImagePath();
	}

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
		this.customerRentables = new ArrayList<>();
		this.noOfCars = 0;
		this.noOfUnits = 0;
		setImagePath();
	}

	/**
	 * copy constructor
	 *
	 * @param customer
	 */
	public Customer(Customer customer) {// copy
		this.id = customer.getId();
		this.name = customer.getName();
		this.noOfUnits = customer.getNoOfUnits();
		this.noOfCars = customer.getNoOfCars();
		this.imagePath = customer.getImagePath();
		this.customerRentables = deepCopy(customer.getCustomerRentables());
	}

	private ArrayList<Rentable> deepCopy(ArrayList<Rentable> originalArray) {
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

	/**
	 * this method is used to compare customers with other customer objects to
	 * determine if they are the same or not.
	 */
	@Override
	public boolean equals(Object customer) {
		if (this == customer) {
			System.out.println("Resident found by refrence");
			return true;
		}
//		if (customer == null || !(customer instanceof Customer))
		if (customer == null || getClass() != customer.getClass()) {
			return false;
		}

		Customer c = (Customer) customer;
		boolean sameId = this.id == c.getId();
		boolean sameName = this.name == c.getName();

		return sameId && sameName; // Uniqueness based solely on ID
	}

	/**
	 * this method is used by sets in addition to equals to to determine which
	 * properties it will use to compare customers and tell if they are the same or
	 * not and to store them
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, name); // Use 'id' and 'name' for hash code
	}

	/**
	 * this method create snap shots/track record that stores initial values after
	 * rent/return operation. to prevent changes for old values/records if some item
	 * in the system changes it's attributes values for example the price. if all
	 * operations get the new price that will produce wrong information for example
	 * if we want to calculate the actual revenue for the company. it stores the id
	 * and name of customer. map class to create 1 to 1 collection. keys are unique
	 * and it can't be ordered and dosen't allow duplicate keys. and fast for
	 * lookups, insertion and deletion.
	 *
	 * @return
	 */
	public Map<String, Object> createSnapshot() {
		Map<String, Object> snapshot = new HashMap<>();
		snapshot.put("id", this.id);
		snapshot.put("name", this.name);
		snapshot.put("customerRentables", this.customerRentables);
		snapshot.put("noOfCars", this.noOfCars);
		snapshot.put("noOfUnits", this.noOfUnits);
		return snapshot;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * this is setter is used by the console app. if user enters 0 it cancels the
	 * process and customer creation.
	 *
	 * @return
	 */
	public boolean setName() {
		this.name = enterDetails(getClass().getSimpleName() + " name: (" + this.name + ")");
		if (name.equals("0")) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	// we don't need setters because we update them from code only
	public int getNoOfUnits() {
		return noOfUnits;
	}

	public int getNoOfCars() {
		return noOfCars;
	}

	public ArrayList<Rentable> getCustomerRentables() {
		return customerRentables;
	}

	public String printRentables() {
		String str = "";
		if (!customerRentables.isEmpty()) {
			for (Rentable ren : customerRentables) {
				str += ("\t" + ren.toString("\t") + ",\n ");
			}
			return str.substring(0, str.length() - 3);
		}
		return str;
	}

	/**
	 * this method checks id if it's duplicate or unique before setting it to the
	 * customer. if user enters 0 it cancels the process and customer creation.
	 *
	 * @return
	 * @throws CancelException
	 */
	public void setId() throws CancelException {
		boolean isDuplicate = false;// , result = false;
		try {
			do {
				id = 0;
				this.id = Integer.parseInt(enterDetails(getClass().getSimpleName() + " ID: (" + this.id + ")"));
				if (id == 0) {
					throw new CancelException("you entered 0 to exit");
				}
				isDuplicate = InfoSys.checkCustomerId(this.id);
				if (isDuplicate) {// true=duplicate/not unique
					System.out.println("\nduplicate id...\nenter a different id.\n");
				}
			} while (isDuplicate);
//			result = true;
//			System.out.println("unique id...\n");
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setId();
		}
//		return result;
	}

	/**
	 * Correctly loads the image from the stored path. It creates a File object and
	 * then converts it to a valid URI for JavaFX.
	 */
	public Image getImage() {
		if (imagePath != null && !imagePath.isEmpty()) {
			try {
				File imageFile = new File(imagePath);
				if (imageFile.exists()) {
					// imageFile.toURI().toString() correctly creates the URI string
					// e.g., "file:/C:/Users/user/image.png"
					return new Image(imageFile.toURI().toString());
				} else {
					System.err.println("Image file not found: " + imagePath);
					return null;
				}
			} catch (Exception e) {
				System.err.println("Error loading image from path " + imagePath + ": " + e.getMessage());
				return null;
			}
		}
		return null;
	}

	/**
	 * this method creates popup window to select an image file from the system
	 * files to use for the customer in the gui. it also filters files and show only
	 * image files. Correctly saves the absolute path from the JFileChooser without
	 * any prefixes.
	 */
	public void setImagePath(/*String path*/) { // The 'path' parameter is not used here
		// this frame is not used/not shown, it's just to make the file chooser appear
		// on the top.
		JFrame frame = new JFrame("My Application");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // Center the frame

		JFileChooser fileChooser = new JFileChooser();
		frame.setAlwaysOnTop(true);
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files (JPG, PNG, GIF, BMP)", "jpg",
				"jpeg", "png", "gif", "bmp");
		fileChooser.setFileFilter(imageFilter);
//		frame.setVisible(true);
		int result = fileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Store the clean, absolute path. Do NOT add "file:" here.
			this.imagePath = selectedFile.getAbsolutePath();
			System.out.println("Selected file path stored: " + this.imagePath);
		} else {
			System.out.println("File selection cancelled.");
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	public void addRentable(Rentable rentable) {
		this.customerRentables.add(rentable);
		if (rentable.getClass().getSimpleName().equals("Car")) {
			noOfCars++;
			System.out.println(
					getClass().getSimpleName() + " added rentable successfully. cars count is: " + noOfCars);
		} else {
			noOfUnits++;
			System.out.println(
					getClass().getSimpleName() + " added rentable successfully. realestate count is: " + noOfUnits);
		}
	}

	public boolean returnRentable(Rentable rentable) {
		if (customerRentables.size() > 0) {
			this.customerRentables.remove(rentable);
			if (rentable.getClass().getSimpleName().equals("Car")) {
				noOfCars--;
				System.out.println(
						getClass().getSimpleName() + " removed rentable successfully. cars count is: " + noOfCars);
				return true;
			} else {
				noOfUnits--;
				System.out.println(
						getClass().getSimpleName() + " removed rentable successfully. realestate count is: " + noOfUnits);
				return true;
			}
		} else {
			InfoSys.alertWindow("customer rentables list already empty");
		}
		return false;
	}

	/**
	 * the purpose of this method is to not repeat code of user input
	 *
	 * @param str
	 * @return
	 */
	public String enterDetails(String str) {
		System.out.println("enter " + str);
		String x = in.next();
		return x;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", noOfUnits=" + noOfUnits + ", noOfCars=" + noOfCars
				+ ", customerRentables=" + /*customerRentables*/ "]\n"+printRentables()+"\n";
	}

	public String toString(String str) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("Customer [\n");
//		sb.append("  id=").append(id).append(",\n");
//		sb.append("  name=").append(name).append(",\n");
//
//		sb.append("  customerRentables=").append(customerRentables.isEmpty() ? "[]" : "[\n");
//		for (Rentable r : customerRentables) {
//			sb.append("    ").append(r).append("\n");
//		}
//		if (!customerRentables.isEmpty())
//			sb.append("  ]\n");
//
//		sb.append("  noOfUnits=").append(noOfUnits).append(",\n");
//		sb.append("  noOfCars=").append(noOfCars).append("\n");
//		sb.append("]");
//		return sb.toString();
//	}

//	public String toString(String str) {
//		return "Customer " + str + " [id=" + id + ", name=" + name + ", noOfUnits=" + noOfUnits + ", noOfCars="
//				+ noOfCars + customerRentables + "]";
//	}

}
