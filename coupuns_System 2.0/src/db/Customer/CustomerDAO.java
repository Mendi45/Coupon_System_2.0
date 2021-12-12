package db.Customer;

import java.util.ArrayList;

import db.Sql_Actions_Customer;
import db.Beans.Customer;

public interface CustomerDAO extends Sql_Actions_Customer {

	boolean isCustomerExists(String email, String password);

	Customer AddCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(int customerID);

	Customer getOneCustomer(int customerID);

	ArrayList<Customer> getAllCustomers();
}
