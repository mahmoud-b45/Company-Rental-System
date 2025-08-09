package proj01;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.image.Image;

public class Rentable implements Serializable {
	private static final long serialVersionUID = 1L; // Good practice for serialization

	private String number;// just a number in system or building number or car number or just an id number
	private boolean status;
	private transient Scanner in = new Scanner(System.in);
	private String imagePath; // Stores the path to the image file
	JFileChooser fileChooser = new JFileChooser();

	/**
	 * this constructor is used for programmatic/direct object creation
	 * and assigning a number directly and used in subclasses constructors.
	 * @param number
	 */
	public Rentable(String number) {
		this.number = number;
		this.status = true;
		setImagePath();
	}

	/**
	 * this constructor used for manual entry of details by the user using console.
	 */
	public Rentable() {
		setNumber();
		this.status = true;
		setImagePath();
	}

	/**
	 * copy constructor
	 *
	 * @param rentable
	 */
	public Rentable(Rentable rentable) {
		this.number = rentable.getNumber();
		this.status = rentable.isStatus();
		this.imagePath = rentable.getImagePath();
	}

	@Override
	public boolean equals(Object rentable) {
		if (this == rentable) {
			System.out.println("Resident found by refrence");
			return true;
		}
//		if (rentable == null || !(rentable instanceof Rentable))
		if (rentable == null || getClass() != rentable.getClass()) {
			return false;
		}

		Rentable r = (Rentable) rentable;
		boolean sameNumber = Objects.equals(this.getNumber(), r.getNumber());

		return sameNumber; // Uniqueness based solely on ID
	}

	@Override
	public int hashCode() {
		return Objects.hash(number); // Use 'id' and 'name' for hash code
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
	 * Correctly saves the absolute path from the JFileChooser without any prefixes.
	 */
	public void setImagePath() { // The 'path' parameter is unused here
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files (JPG, PNG, GIF, BMP)", "jpg",
				"jpeg", "png", "gif", "bmp");
		fileChooser.setFileFilter(imageFilter);

		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Store the clean, absolute path. Do NOT add "file:" here.
			this.imagePath = selectedFile.getAbsolutePath();
			System.out.println("Selected file path stored: " + this.imagePath);
		} else {
			System.out.println("File selection cancelled.");
		}
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber() {
		do {
			this.number = null;
			this.number = enterDetails(
					getClass().getSimpleName() + " number \"id of rentable in system\": (" + this.number + ")");
			if (InfoSys.checkRentableId(number)) {
				System.out.println("\nduplicate id...\nenter a different id.\n");
			}
		} while (InfoSys.checkRentableId(number));
		System.out.println("unique id...\n");

	}

	public int getMonthlyPrice(Rentable rentable) {
		if (rentable instanceof Car car) {
			return car.getMonthlyPrice();
		} else if (rentable instanceof RealEstate realEstate) {
			return realEstate.getMonthlyPrice();
		}
		return 0;
	}
//	public abstract int getMonthlyPrice();
	// In Rentable.java
//	public abstract Rentable copy();
/**
 * 
 * @return true=available, false=not available.
 */
	public boolean isStatus() {
		return status;
	}

	public void setStatus() {
		System.out.println(
				getClass().getSimpleName() + " enter status t=available, f=not available: (" + this.status + ")");
		String t = in.next();
		this.status = t.equals("t");
	}

	public String enterDetails(String str) {
		System.out.print(" enter " + str);
		String x = in.next();
		return x;
	}

	@Override
	public String toString() {
		return "Rentable [number=" + number + ", status=" + status + "]";
	}

	public String toString(String str) {
		return "Rentable " + str + " [number=" + number + ", status=" + status + "]";
	}

}
