package proj01;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class Resident extends Customer implements Serializable {
    private static final long serialVersionUID = 1L; // Good practice for serialization

	private int residence;// zipcode/building No
	private String passportNo;
	private String nationality;
	private Date birthDate;

	public Resident() throws CancelException {
		super();
		setResidence();
		setPassportNo();
		setNationality();
		setbDate();
	}

	public Resident(int id, String name, int residence, String passportNo, String nationality, Date bDate) {
		super(id, name);
		this.residence = residence;
		this.passportNo = passportNo;
		this.nationality = nationality;
		this.birthDate = bDate;
	}

	/**
	 * copy constructor
	 *
	 * @param resident
	 */
	public Resident(Resident resident) {
		super(resident);
		this.residence = resident.getResidence();
		this.passportNo = resident.getPassportNo();
		this.nationality = resident.getNationality();
		this.birthDate = (resident.getBirthDate() != null) ? new Date(resident.getBirthDate()) : null;
	}

	@Override
	public Map<String, Object> createSnapshot() {
		Map<String, Object> snapshot = super.createSnapshot();
		snapshot.put("residence", this.residence);
		snapshot.put("passportNo", this.passportNo);
		snapshot.put("nationality", this.nationality);
		snapshot.put("birthDate", this.birthDate);
		return snapshot;
	}

	/**
	 * this method is used to compare residents with other residents
	 * objects to determine if they are the same or not.
	 */
	@Override
	public boolean equals(Object resident) {
		if (this == resident) {
			System.out.println("Resident found by refrence");
			return true;
		}
		if (resident == null || getClass() != resident.getClass()) {
			return false;
		}
		Resident r = (Resident) resident;
		boolean sameId = this.getId() == r.getId();
//		boolean sameName = this.getName().equals(r.getName());
//		boolean sameNoUnits = this.getNoOfUnits() == r.getNoOfUnits();
//		boolean sameNoCars = this.getNoOfCars() == r.getNoOfCars();
		boolean sameResidence = this.residence == r.getResidence();
//		boolean samePassport = this.passportNo.equals(r.getPassportNo());
//		boolean sameNationality = this.nationality.equals(r.getNationality());
//		boolean sameBirthDate = this.getStringBirthDate().equals(r.getStringBirthDate());

		//this is the correct way to compare mutable objects it compares the
		//values not the refrences and Object class prevent null exception
		boolean sameName = Objects.equals(this.getName(), r.getName());
		boolean samePassport = Objects.equals(this.passportNo, r.getPassportNo());
		boolean sameNationality = Objects.equals(this.nationality, r.getNationality());
		boolean sameBirthDate = Objects.equals(this.getStringBirthDate(), r.getStringBirthDate());

		if (sameId && sameName && sameResidence && samePassport && sameNationality && sameBirthDate) {
			System.out.println("Resident equals found by value not refrence");
		}
		return sameId && sameName && sameResidence && samePassport && sameNationality && sameBirthDate;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setResidence(int residence) {
		this.residence = residence;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setbDate(Date bDate) {
		this.birthDate = bDate;
	}

	public int getResidence() {
		return residence;
	}

	public void setResidence() {
		try {
			this.residence = Integer
					.parseInt(enterDetails(getClass().getSimpleName() + " residenceNo: (" + this.residence + ")"));
		} catch (NumberFormatException e) {
			System.out.println("-----NumberFormatException-----");
			setResidence();
		}
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo() {
		this.passportNo = enterDetails(getClass().getSimpleName() + " passportNo: (" + this.passportNo + ")");
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality() {
		this.nationality = enterDetails(getClass().getSimpleName() + " nationality: (" + this.nationality + ")");
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
//	public String toString() {
//		return "Resident [residence=" + residence + ", passportNo=" + passportNo + ", nationality=" + nationality
//				+ ", birthDate=" + birthDate + "]";
//	}

	@Override
	public String toString() {
		return "Resident [id=" + getId() + ", name=" + getName() + ", residence=" + residence + ", passportNo="
				+ passportNo + ", nationality=" + nationality + ", bDate=" + birthDate + ", " +  ", noOfCars=" + getNoOfCars() +  ", noOfUnits=" + getNoOfUnits() +"]\n"+ printRentables()+"\n"/*getCustomerRentables()*//*+
				"]"*/;
	}

//	@Override
//	public String toString(String str) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("  Resident [");
//		sb.append("    id=").append(getId()).append(", ");
//		sb.append("    name=").append(getName()).append(", ");
//		sb.append("    nationality=").append(getNationality()).append(", ");
//		sb.append("    residence=").append(getResidence()).append(", ");
//		sb.append("    passportNo=").append(getPassportNo()).append(", ");
//		sb.append("    birth date=").append(getBirthDate()).append(", ");
//
//		sb.append("    customerRentables=").append(getCustomerRentables().isEmpty() ? "[]" : "[ ");
//		for (Rentable r : getCustomerRentables()) {
//			sb.append("    ").append(r).append(" ");
//		}
//		if (!getCustomerRentables().isEmpty())
//			sb.append("  ]\n");
//
//		sb.append("    noOfUnits=").append(getNoOfUnits()).append(", ");
//		sb.append("    noOfCars=").append(getNoOfCars()).append(" ");
//		sb.append("  ]");
//		return sb.toString();
//	}

	@Override
	public String toString(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("Resident [");
		sb.append(" id=").append(getId()).append(", ");
		sb.append(" name=").append(getName()).append(", ");
		sb.append(" nationality=").append(getNationality()).append(", ");
		sb.append(" residence=").append(getResidence()).append(", ");
		sb.append(" passportNo=").append(getPassportNo()).append(", ");
		sb.append(" birth date=").append(getBirthDate()).append(", ");

		sb.append(" customerRentables=").append(getCustomerRentables().isEmpty() ? "[]" : "[\n");
		for (Rentable r : getCustomerRentables()) {
			sb.append("    ").append(r).append("\n");
		}
		if (!getCustomerRentables().isEmpty()) {
			sb.append("  ]\n");
		}

		sb.append("    noOfUnits=").append(getNoOfUnits()).append(", ");
		sb.append("    noOfCars=").append(getNoOfCars()).append("\n");
		sb.append("  ]");
		return sb.toString();
	}
}
