package proj01;

import java.io.Serializable;
import java.util.ArrayList;

public class OperationSnapshot implements Serializable{

	Date operationDate;
	int operationId;
	String operationType;
	Customer customer;
	// customer
	String customerName;
	int customerId;
	ArrayList<Rentable> customerRentables;
	int noOfUnits;
	int noOfCars;
	Date birthDate;
	// cititzen
	int nationalNo;
	// resident
	int residence;// zipcode/building No
	String passportNo;
	String nationality;
	// company
	String licenceNo;
	String companyLocation;
	Date expiryDate;

	Rentable rentable;
	// rentable
	String number;// just a number in system or building number or car number or just an id number
	boolean rentableStatus;
	int rentableOperationPrice;
	// car
	int plateNo;
	String brand;
	int model;
	// realestate
	String realestateType;
	String realEstateLocation;

	public void setCustomer(Customer customer) {
		this.customer = customer;
		setCustomerName(customer.getName());
		setCustomerId(customer.getId());
		setCustomerRentables(customer.getCustomerRentables());
		setNoOfCars(customer.getNoOfCars());
		setNoOfUnits(customer.getNoOfUnits());
		if (customer instanceof Citizen cit) {
			setNationalNo(cit.getNationalNo());
			setBirthDate(cit.getBirthDate());
		} else if (customer instanceof Resident res) {
			setNationality(res.getNationality());
			setPassportNo(res.getPassportNo());
			setResidence(res.getResidence());
			setBirthDate(res.getBirthDate());
		} else if (customer instanceof Company com) {
			setCompanyLocation(com.getLocation());
			setLicenceNo(com.getLicenceNo());
			setExpiryDate(com.getExpiryDate());
		}

	}

	public void setRentable(Rentable rentable) {
		this.rentable = rentable;
		setNumber(rentable.getNumber());
		setStatus(rentable.isStatus());
		setPrice(rentable.getMonthlyPrice(rentable));
		if (rentable instanceof Car car) {
			setPlateNo(car.getPlateNo());
			setBrand(car.getBrand());
			setModel(car.getModel());
		} else if (rentable instanceof RealEstate real) {
			setRealestateType(real.getType());
			setRealEstateLocation(real.getLocation());
		}
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public ArrayList<Rentable> getCustomerRentables() {
		return customerRentables;
	}

	public void setCustomerRentables(ArrayList<Rentable> customerRentables) {
		this.customerRentables = customerRentables;
	}

	public int getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public int getNoOfCars() {
		return noOfCars;
	}

	public void setNoOfCars(int noOfCars) {
		this.noOfCars = noOfCars;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getNationalNo() {
		return nationalNo;
	}

	public void setNationalNo(int nationalNo) {
		this.nationalNo = nationalNo;
	}

	public int getResidence() {
		return residence;
	}

	public void setResidence(int residence) {
		this.residence = residence;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Rentable getRentable() {
		return rentable;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isStatus() {
		return rentableStatus;
	}

	public void setStatus(boolean status) {
		this.rentableStatus = status;
	}

	public int getPrice() {
		return rentableOperationPrice;
	}

	public void setPrice(int price) {
		this.rentableOperationPrice = price;
	}

	public int getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(int plateNo) {
		this.plateNo = plateNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public String getRealestateType() {
		return realestateType;
	}

	public void setRealestateType(String type) {
		this.realestateType = type;
	}

	public String getRealEstateLocation() {
		return realEstateLocation;
	}

	public void setRealEstateLocation(String realEstateLocation) {
		this.realEstateLocation = realEstateLocation;
	}

	void rentableSnapshot() {

	}

	void customerSnapshot() {

	}

	@Override
	public String toString() {
		return "OperationSnapshot [operationDate=" + operationDate + ", operationId=" + operationId + ", operationType="
				+ operationType + ", customer=" + customer + ", customerName=" + customerName + ", customerId="
				+ customerId + ", customerRentables=" + customerRentables + ", noOfUnits=" + noOfUnits + ", noOfCars="
				+ noOfCars + ", birthDate=" + birthDate + ", nationalNo=" + nationalNo + ", residence=" + residence
				+ ", passportNo=" + passportNo + ", nationality=" + nationality + ", licenceNo=" + licenceNo
				+ ", companyLocation=" + companyLocation + ", expiryDate=" + expiryDate + ", rentable=" + rentable
				+ ", number=" + number + ", rentableStatus=" + rentableStatus + ", rentableOperationPrice="
				+ rentableOperationPrice + ", plateNo=" + plateNo + ", brand=" + brand + ", model=" + model
				+ ", realestateType=" + realestateType + ", realEstateLocation=" + realEstateLocation + "]";
	}

}
