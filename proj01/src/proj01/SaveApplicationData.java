package proj01;

import java.io.Serializable;
import java.util.List;

public class SaveApplicationData implements Serializable {
    private static final long serialVersionUID = 1L; // Good practice for serialization

    private List<Customer> customers;
    private List<Rentable> rentables;
    private List<Operation> operations;

    public SaveApplicationData(List<Customer> customers, List<Rentable> rentables, List<Operation> operations) {
        this.customers = customers;
        this.rentables = rentables;
        this.operations = operations;
    }

    // Getters for each list
    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Rentable> getRentables() {
        return rentables;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}