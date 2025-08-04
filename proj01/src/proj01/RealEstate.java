package proj01;

import java.io.Serializable;
import java.util.Objects;

public class RealEstate extends Rentable implements Serializable {
	private static final long serialVersionUID = 1L; // Good practice for serialization

	private String type;
	private String location;
	private int monthlyPrice;

	/**
	 * copy constructor
	 *
	 * @param realEstate
	 */
	public RealEstate(RealEstate realEstate) {
		super(realEstate);
		this.type = realEstate.getType();
		this.location = realEstate.getLocation();
		this.monthlyPrice = realEstate.getMonthlyPrice();
	}

//	@Override
//    public Map<String, Object> createSnapshot() {
//        // Start with the base class snapshot (name, price)
//        Map<String, Object> snapshot = super.createSnapshot();
//
//        // Add Car-specific details
//        snapshot.put("type", this.type);
//        snapshot.put("location", this.location);
//        snapshot.put("monthlyPrice", this.monthlyPrice);
//		snapshot.put("Class", this.getClass().getSimpleName());
//
//        return snapshot;
//    }

	@Override
	public boolean equals(Object realEstate) {
		if (this == realEstate) {
			System.out.println("RealEstate found by refrence");

			return true;
		}
		if (realEstate == null || getClass() != realEstate.getClass()) {
			return false;
		}
		RealEstate r = (RealEstate) realEstate;
//		boolean sameNumber = this.getNumber().equals(r.getNumber());
		boolean sameType = this.type == r.getType();
//		boolean sameLocation = this.location.equals(r.getLocation());
		boolean sameMonthlyPrice = this.monthlyPrice == r.getMonthlyPrice();

		boolean sameNumber = Objects.equals(this.getNumber(), r.getNumber());
		boolean sameLocation = Objects.equals(this.location, r.getLocation());

		if (sameNumber && sameType && sameLocation && sameMonthlyPrice) {
			System.out.println("RealEstate equals found by value not refrence");
		}
		return sameNumber && sameType && sameLocation && sameMonthlyPrice;
	}

	public RealEstate(String number, String type, String location, int monthlyPrice) {
		super(number);
		this.type = type;
		this.location = location;
		this.monthlyPrice = monthlyPrice;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMonthlyPrice(int monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	public RealEstate() {
		super();
		setType();
		setLocation();
		setMonthlyPrice();
	}

	public String getType() {
		return type;
	}

	public void setType() {
//		System.out.println("");
		this.type = enterDetails(getClass().getSimpleName() + " type \"villa,appartment\": (" + this.type + ")");
	}

	public String getLocation() {
		return location;
	}

	public void setLocation() {
		this.location = enterDetails(getClass().getSimpleName() + " location: (" + this.location + ")");
	}

//	@Override
	public int getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice() {
		try {
			this.monthlyPrice = Integer.parseInt(enterDetails(
					getClass().getSimpleName() + " monthlyPrice \"1000qar\": (" + this.monthlyPrice + ")"));
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setMonthlyPrice();
		}
	}

	@Override
	public String toString(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tRealEstate [");
		sb.append(" id=").append(getNumber()).append(", ");
		sb.append(" status=").append(isStatus()).append(", ");
		sb.append(" monthlyPrice=").append(getMonthlyPrice()).append(", ");
		sb.append(" type=").append(getType()).append(", ");
		sb.append(" location=").append(getLocation());
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "RealEstate [number=" + getNumber() + ", status=" + isStatus() + ", type=" + type + ", location="
				+ location + ", monthlyPrice=" + monthlyPrice + "]";
	}

}
