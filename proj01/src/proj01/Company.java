package proj01;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class Company extends Customer implements Serializable {
	private static final long serialVersionUID = 1L; // Good practice for serialization

	private String licenceNo;
	private String location;
	private Date expiryDate;

	/**
	 * copy constructor
	 *
	 * @param car
	 */
	public Company(Company company) {
		super(company);
		this.licenceNo = company.getLicenceNo();
		this.location = company.getLocation();
		this.expiryDate = (company.getExpiryDate() != null) ? new Date(company.getExpiryDate()) : null;
	}

	@Override
	public Map<String, Object> createSnapshot() {
		Map<String, Object> snapshot = super.createSnapshot();
		snapshot.put("licenceNo", this.licenceNo);
		snapshot.put("location", this.location);
		snapshot.put("expiryDate", this.expiryDate);
		return snapshot;
	}

	/**
	 * test
	 *
	 * @param company
	 * @return
	 */
	@Override
	public boolean equals(Object company) {
		if (this == company) {
			System.out.println("Company found by refrence");

			return true;
		}
		if (company == null || getClass() != company.getClass()) {
			return false;
		}
		Company c = (Company) company;
		boolean sameId = this.getId() == c.getId();
//		boolean sameName = this.getName().equals(c.getName());
////		boolean sameNoUnits = this.getNoOfUnits() == c.getNoOfUnits();
////		boolean sameNoCars = this.getNoOfCars() == c.getNoOfCars();
//		boolean sameLicenceNo = this.licenceNo.equals(c.getLicenceNo());
		boolean sameLocation = this.location.equals(c.getLocation());
		boolean sameExpiryDate = this.getStringExpiryDate().equals(c.getStringExpiryDate());

		boolean sameName = Objects.equals(this.getName(), c.getName());
		boolean sameLicenceNo = Objects.equals(this.licenceNo, c.getLicenceNo());
		boolean sameBirthDate = Objects.equals(this.getStringExpiryDate(), c.getStringExpiryDate());

		if (sameId && sameName && sameLicenceNo && sameLocation && sameExpiryDate) {
			System.out.println("Company equals found by value not refrence");
		}
		return sameId && sameName && sameLicenceNo && sameLocation && sameExpiryDate;
	}

	/**
	 * returns date as string with no breaks as DayMonthYear e.x: 01011999 =
	 * 01/01/1999
	 *
	 * @return
	 */
	public String getStringExpiryDate() {
		int year = expiryDate.getYear();
		int month = expiryDate.getMonth();
		int day = expiryDate.getDay();
		return String.format("%02d", day) + String.format("%02d", month) + String.valueOf(year);
	}

	public Company(int id, String name, String licenceNo, String location, Date expiryDate) {
		super(id, name);
		this.licenceNo = licenceNo;
		this.location = location;
		this.expiryDate = expiryDate;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Company() throws CancelException {
		super();
		setLicenceNo();
		setLocation();
		setExpiryDate();
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo() {
		this.licenceNo = enterDetails(
				getClass().getSimpleName() + " licence number str \"ABC123\": (" + this.licenceNo + ")");
	}

	public String getLocation() {
		return location;
	}

	public void setLocation() {
		this.location = enterDetails(getClass().getSimpleName() + " location str \"doha\": (" + this.location + ")");
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate() throws CancelException {
		this.expiryDate = new Date(true);
	}
/**
 * 
 * @return True=expired, False=not expired.
 */
	public boolean isExpired() {
		if (expiryDate.getYear() < LocalDate.now().getYear()) {
			return true;// expired
		} else if (expiryDate.getYear() == LocalDate.now().getYear()) {
			if (expiryDate.getMonth() < LocalDate.now().getMonthValue()) {
				return true;// expired
			}
		}
		return false;// not expired
	}

	@Override
	public String toString(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("  Company [");
		sb.append("    id=").append(getId()).append(", ");
		sb.append("    name=").append(getName()).append(", ");
		sb.append("    licenceNo=").append(getLicenceNo()).append(", ");
		sb.append("    location=").append(getLocation()).append(", ");
		sb.append("    expiry date=").append(getExpiryDate()).append(", ");

		sb.append("    customerRentables=").append(getCustomerRentables().isEmpty() ? "[]" : "[ ");
		for (Rentable r : getCustomerRentables()) {
			sb.append("    ").append(r).append(" ");
		}
		if (!getCustomerRentables().isEmpty()) {
			sb.append("  ] ");
		}

		sb.append("    noOfUnits=").append(getNoOfUnits()).append(", ");
		sb.append("    noOfCars=").append(getNoOfCars()).append(" ");
		sb.append("  ]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Company [id=" + getId() + ", name=" + getName() + ", licenceNo=" + licenceNo + ", location=" + location
				+ ", expiryDate=" + expiryDate + ", noOfUnits=" + getNoOfUnits() + ", noOfCars=" + getNoOfCars() + "]\n"
				+ printRentables() + "\n";
	}

//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("  Company [");
//		sb.append("    id=").append(getId()).append(", ");
//		sb.append("    name=").append(getName()).append(", ");
//		sb.append("    licenceNo=").append(getLicenceNo()).append(", ");
//		sb.append("    location=").append(getLocation()).append(", ");
//		sb.append("    expiry date=").append(getExpiryDate()).append(", ");
//
//		sb.append("    customerRentables=").append(getCustomerRentables().isEmpty() ? "[]" : "[ ");
//		for (Rentable r : getCustomerRentables()) {
//			sb.append("    ").append(r).append(" ");
//		}
//		if (!getCustomerRentables().isEmpty())
//			sb.append("  ] ");
//
//		sb.append("    noOfUnits=").append(getNoOfUnits()).append(", ");
//		sb.append("    noOfCars=").append(getNoOfCars()).append(" ");
//		sb.append("  ]");
//		return sb.toString();
//	}
}
