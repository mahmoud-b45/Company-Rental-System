package proj01;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mahmoud
 *
 */
public class Citizen extends Customer implements Serializable {
    private static final long serialVersionUID = 1L; // Good practice for serialization

	private int nationalNo;
	private Date birthDate;

	/**
	 * copy constructor
	 *
	 * @param citizen
	 */
	public Citizen(Citizen citizen) {
		super(citizen);
		this.nationalNo = citizen.getNationalNo();
		this.birthDate = (citizen.getbDate() != null) ? new Date(citizen.getBirthDate()) : null;
	}

	@Override
	public Map<String, Object> createSnapshot() {
		Map<String, Object> snapshot = super.createSnapshot();
		snapshot.put("nationalNo", this.nationalNo);
		snapshot.put("bitrthDate", this.birthDate);
		return snapshot;
	}

	@Override
	public boolean equals(Object citizen) {
		if (this == citizen) {
			System.out.println("Citizen found by refrence");

			return true;
		}
		if (citizen == null || getClass() != citizen.getClass()) {
			return false;
		}
		Citizen c = (Citizen) citizen;
		boolean sameId = this.getId() == c.getId();
//		boolean sameName = this.getName().equals(c.getName());
//		boolean sameNoUnits = this.getNoOfUnits() == c.getNoOfUnits();
//		boolean sameNoCars = this.getNoOfCars() == c.getNoOfCars();
		boolean sameNationalNo = this.nationalNo == c.getNationalNo();
//		boolean sameBirthDate = this.getStringBirthDate().equals(c.getStringBirthDate());

		boolean sameName = Objects.equals(this.getName(), c.getName());
		boolean sameBirthDate = Objects.equals(this.getStringBirthDate(), c.getStringBirthDate());

		if (sameId && sameName && sameNationalNo && sameBirthDate) {
			System.out.println("citizen equals found by value not refrence");
		}
		return sameId && sameName && sameNationalNo && sameBirthDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Citizen(int id, String name, int nationalNo, Date bDate) {
		super(id, name);
		this.nationalNo = nationalNo;
		this.birthDate = bDate;
	}

	public void setNationalNo(int nationalNo) {
		this.nationalNo = nationalNo;
	}

	public void setbDate(Date bDate) {
		this.birthDate = bDate;
	}

	public Citizen() throws CancelException {
		super();
		setNationalNo();
		setbDate();
	}

	public int getNationalNo() {
		return nationalNo;
	}

	public void setNationalNo() {
		try {
			this.nationalNo = Integer.parseInt(enterDetails(
					getClass().getSimpleName() + " nationalNo or phone \"974\" \"123\": (" + this.nationalNo + ")"));
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setNationalNo();
		}
	}

	/**
	 * returns date as string with no breaks as DayMonthYear e.x: 01011999 =
	 * 01/01/1999
	 *
	 * @return
	 */
	public String getStringBirthDate() {
		int year = birthDate.getYear();
		int month = birthDate.getMonth();
		int day = birthDate.getDay();
		return String.format("%02d", day) + String.format("%02d", month) + String.valueOf(year);
	}

	public Date getbDate() {
		return birthDate;
	}

	public int getAgeInYears() {
		int age = LocalDate.now().getYear() - this.birthDate.getYear();
		return age;
	}

	public void setbDate() throws CancelException {
		this.birthDate = new Date(true);
	}

//	@Override
//	public String toString(String str) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("  Cititzen [");
//		sb.append("    id=").append(getId()).append(", ");
//		sb.append("    name=").append(getName()).append(", ");
//		sb.append("    nationalNo=").append(getNationalNo()).append(", ");
//		sb.append("    birth date=").append(getBirthDate()).append(", ");
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

	@Override
	public String toString() {
		return "Citizen [id=" + getId() + ", name=" + getName() + ", nationalNo=" + nationalNo + ", bDate=" + birthDate
				+", noOfUnits=" + getNoOfUnits() + ", noOfCars=" + getNoOfCars() + "]\n " + printRentables()+"\n"/*getCustomerRentables() + "]"*/;
	}

	@Override
	public String toString(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("  Citizen [\n");
		sb.append("    id=").append(getId()).append(",\n");
		sb.append("    name=").append(getName()).append(",\n");
		sb.append("    nationalNo=").append(getNationalNo()).append(",\n");
		sb.append("    birth date=").append(getBirthDate()).append(",\n");

		sb.append("    customerRentables=").append(getCustomerRentables().isEmpty() ? "[]" : "[\n");
		for (Rentable r : getCustomerRentables()) {
			sb.append("    ").append(r).append("\n");
		}
		if (!getCustomerRentables().isEmpty()) {
			sb.append("  ]\n");
		}

		sb.append("    noOfUnits=").append(getNoOfUnits()).append(",\n");
		sb.append("    noOfCars=").append(getNoOfCars()).append("\n");
		sb.append("  ]");
		return sb.toString();
	}

}
