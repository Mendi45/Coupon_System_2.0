package db.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import db.SQL_ACTIONS_INTERFACE;
import db.Sql_Actions_Customer;
import db.sql_Actions_Company;
import db.Beans.Category;
import db.Beans.Coupon;
import db.Beans.Customer;

public class CustomerDBDAO implements CustomerDAO {

	// this PRIVATE connector will be uses for all methods in this class
	private ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) {

		Connection con = pool.getConnection(); // connecting to local connection
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(Sql_Actions_Customer.isCustomerExists);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			stmt.close();
			rs.close();
			return rs.next();

		} catch (Exception e) {// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}
		return false;
	}

	@Override
	public Customer AddCustomer(Customer customer) {

		Connection con = pool.getConnection(); // connecting to local connection
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(Sql_Actions_Customer.ADD_CUSTOMER);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());

			rs = stmt.executeQuery();

			if (rs.next() && rs.getInt("id") > 0) {
				return new Customer(rs.getInt("id"), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), customer.getPassword(), customer.getCoupons());
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.restoreConnection(con);
		}
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) {

		Connection con = pool.getConnection(); // connecting to local connection

		try {
			PreparedStatement stmt = con.prepareStatement(UPDATE_CUSTOMER);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			stmt.setInt(5, customer.getId());

			
			stmt.executeQuery();
		}
			catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCustomer(int customerID) {

		Connection con = pool.getConnection(); // connecting to local connection
		try {
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(Sql_Actions_Customer.DELETE_CUSTOMER + customerID);
			if (stmt.execute() != true) {
				// throw exception
			}
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}

	}

	/*
	 * public Customer getOneCustomer(int customerID);
	 * 
	 * get one customer by id the customer that Returns could have also coupons,(
	 * and every coupon have Category)
	 * 
	 * this getOneCustomer method have 3 queries: (query: getCustomerByID), (query)
	 * look for coupons by customerID for every coupon (query) look for Category
	 *
	 * return Customer;
	 */
	@Override
	public Customer getOneCustomer(int customerID) {
		Connection con = pool.getConnection(); // connecting to local connection
		PreparedStatement stmt, stmtTemp, stmtCategory = null;
		ResultSet rs, rsTemp, rsCategory = null;
		Category categoryTemp = null;
		ArrayList<Coupon> couponsOfUser = new ArrayList<Coupon>();
		Customer tempCustomer = null;

		try {
			stmt = con.prepareStatement(Sql_Actions_Customer.SELECT_ONE_CUSTOMER_BY_ID + customerID);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stmtTemp = con.prepareStatement(Sql_Actions_Customer.GET_COUPONS_BY_CUSTOMERS + rs.getInt("id"));
				rsTemp = stmtTemp.executeQuery();

				if (rsTemp.next()) {
					stmtCategory = con
							.prepareStatement(Sql_Actions_Customer.GET_CATEGORY_OBJ + rsTemp.getInt("CATEGORY_ID"));
					rsCategory = stmtCategory.executeQuery();
// got category from enum 
					if (rsCategory.next()) {
///// suspect line bug object(1)
						categoryTemp = (Category) rsCategory.getObject(1);
					}

					couponsOfUser.add(new Coupon(rsTemp.getInt("ID"), rsTemp.getInt("COMPANY_ID"), categoryTemp,
							rsTemp.getString("TITLE"), rsTemp.getString("DESCRIPTION"), rsTemp.getDate("START_DATE"),
							rsTemp.getDate("END_DATE"), rsTemp.getInt("AMOUNT"), rsTemp.getDouble("PRICE"),
							rsTemp.getString("IMAGE")));
				}
				tempCustomer = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("email"), rs.getString("password"), couponsOfUser);

				stmt.close();
				stmtTemp.close();
				stmtCategory.close();
				rs.close();
				rsTemp.close();
				rsCategory.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}
		return tempCustomer;

	}

	/*
	 * public ArrayList<Customer> getAllCustomers():::
	 * 
	 * getting all customer returns: ArrayList of <Customer> getAllCustomers
	 * 
	 * 
	 * every customer Returns also his coupons and every coupon have Category
	 * category
	 * 
	 * this getAllCustomers method have 3 queries: query: get all customers for
	 * every customer (query) look for coupons by customerID for every coupon
	 * (query) look for Category collecting all the customers into ArrayList and
	 * return this arraylist
	 * 
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() {

		ArrayList<Customer> allCustomersArrl = new ArrayList<Customer>();
		

		Connection con = pool.getConnection(); // connecting to local connection
		PreparedStatement stmt = null, stmtTemp = null, stmtcategory = null;
		ResultSet rs = null, rsTemp = null, rscategory = null;

		Category categoryTemp = null;

		try {

			stmt = con.prepareStatement(Sql_Actions_Customer.select_all_from_customers);
			rs = stmt.executeQuery();

			while (rs.next()) {
				stmtTemp = con.prepareStatement(Sql_Actions_Customer.GET_COUPONS_BY_CUSTOMERS + rs.getInt("id"));
				rsTemp = stmtTemp.executeQuery();
				ArrayList<Coupon> couponsOfUser = new ArrayList<Coupon>(); // list of coupons for each customer

				while (rsTemp.next()) {
					stmtcategory = con
							.prepareStatement(Sql_Actions_Customer.GET_CATEGORY_OBJ + rsTemp.getInt("CATEGORY_ID"));
					rscategory = stmtcategory.executeQuery();

					if (rscategory.next()) {
		// suspected line
						categoryTemp = (Category) rscategory.getObject(1);
					}

					
					 couponsOfUser.add(new Coupon(rsTemp.getInt("ID"), rsTemp.getInt("COMPANY_ID"), categoryTemp,
							rsTemp.getString("TITLE"), rsTemp.getString("DESCRIPTION"), rsTemp.getDate("START_DATE"),
							rsTemp.getDate("END_DATE"), rsTemp.getInt("AMOUNT"), rsTemp.getDouble("PRICE"),
							rsTemp.getString("IMAGE")));
				}

				// after i have all arguments per customer i will add the user to ArrayList of
				// allCustomersArrl<Customer>
				allCustomersArrl.add(new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("email"), rs.getString("password"), couponsOfUser));
			}

			stmt.close();
			stmtTemp.close();
			stmtcategory.close();
			rs.close();
			rsTemp.close();
			rscategory.close();

			return allCustomersArrl;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}
		return null;
	}

}
