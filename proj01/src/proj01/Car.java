package proj01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Car extends Rentable implements Serializable {
    private static final long serialVersionUID = 1L; // Good practice for serialization

	private int plateNo;
	private String brand;
	private int model;
	private int monthlyPrice;

	/**
	 * copy constructor
	 * 
	 * @param car
	 */
	public Car(Car car) {
		super(car);
		this.plateNo = car.getPlateNo();
		this.brand = car.getBrand();
		this.model = car.getModel();
		this.monthlyPrice = car.getMonthlyPrice();
	}

//	@Override
//	public Map<String, Object> createSnapshot() {
//		// Start with the base class snapshot (name, price)
//		Map<String, Object> snapshot = super.createSnapshot();
//
//		// Add Car-specific details
//		snapshot.put("plateNo", this.plateNo);
//		snapshot.put("model", this.model);
//		snapshot.put("brand", this.brand);
//		snapshot.put("monthlyPrice", this.monthlyPrice);
//		snapshot.put("Class", this.getClass().getSimpleName());
//
//		return snapshot;
//	}
	
//	public ArrayList<ArrayList<Object>> createSnapshot2() {
//		// Start with the base class snapshot (name, price)
//		ArrayList<ArrayList<Object>> snapshot = super.createSnapshot2();
//
//		// Add Car-specific details
//		snapshot.put("plateNo", this.plateNo);
//		snapshot.put("model", this.model);
//		snapshot.put("brand", this.brand);
//		snapshot.put("monthlyPrice", this.monthlyPrice);
//		snapshot.put("Class", this.getClass().getSimpleName());
//
//		return snapshot;
//	}

	@Override
	public boolean equals(Object car) {
		if (this == car) {
			System.out.println("Car found by refrence");

			return true;
		}
		if (car == null || getClass() != car.getClass()) {
			return false;
		}
		Car c = (Car) car;
//		boolean sameNumber = this.getNumber().equals(c.getNumber());
		boolean samePlateNo = this.plateNo == c.getPlateNo();
//		boolean sameBrand = this.brand.equals(c.getBrand());
		boolean sameModel = this.model == c.getModel();
		boolean sameMonthlyPrice = this.monthlyPrice == c.getMonthlyPrice();

		boolean sameNumber = Objects.equals(this.getNumber(), c.getNumber());
		boolean sameBrand = Objects.equals(this.getBrand(), c.getBrand());

		if (sameNumber && samePlateNo && sameBrand && sameModel && sameMonthlyPrice)
			System.out.println("Car equals found by value not refrence");
		return sameNumber && samePlateNo && sameBrand && sameModel && sameMonthlyPrice;
	}

	public Car(String number, int plateNo, String brand, int model, int monthlyPrice) {
		super(number);
		this.plateNo = plateNo;
		this.brand = brand;
		this.model = model;
		this.monthlyPrice = monthlyPrice;
	}

	public void setPlateNo(int plateNo) {
		this.plateNo = plateNo;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public void setMonthlyPrice(int monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	public Car() {
		super();
		setPlateNo();
		setBrand();
		setModel();
		setMonthlyPrice();
	}

	public int getPlateNo() {
		return plateNo;
	}

	public void setPlateNo() {
		try {
			this.plateNo = Integer.parseInt(enterDetails("palteNo int \"123\": "));
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setPlateNo();
		}
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand() {
		this.brand = enterDetails("brand str \"BMW\": ");
	}

	public int getModel() {
		return model;
	}

	public void setModel() {
		try {
			this.model = Integer.parseInt(enterDetails("model int\"2015\": "));
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setModel();
		}
	}

//	@Override
	public int getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice() {
		try {
			this.monthlyPrice = Integer.parseInt(enterDetails("monthlyPrice int\"1000qar\": "));
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setMonthlyPrice();
		}
	}

	@Override
	public String toString() {
		return "Car [number=" + getNumber() + ", status=" + isStatus() + ", plateNo=" + plateNo + ", brand=" + brand
				+ ", model=" + model + ", monthlyPrice=" + monthlyPrice + "]";
	}
	
	@Override
	public String toString(String str) {
//		return "Car "+str+" [number=" + getNumber() + ", status=" + isStatus() + ", plateNo=" + plateNo + ", brand=" + brand
//				+ ", model=" + model + ", monthlyPrice=" + monthlyPrice + "]";
		StringBuilder sb = new StringBuilder();
		sb.append("\tCar        [");
		sb.append(" id=").append(getNumber()).append(", ");
		sb.append(" status=").append(isStatus()).append(", ");
		sb.append(" monthlyPrice=").append(getMonthlyPrice()).append(", ");
		sb.append(" brand=").append(getBrand()).append(", ");
		sb.append(" model=").append(getModel());
		sb.append("]");
		return sb.toString();
	}

//	@Override
//	public String toString() {
//		return "Car [number=" + getNumber() + ", status=" + isStatus() + ",plateNo=" + plateNo + ", brand=" + brand
//				+ ", model=" + model + ", monthlyPrice=" + monthlyPrice + "]";
//	}

}
