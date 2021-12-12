//mendi

package db.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import db.SQL_ACTIONS_INTERFACE;
import db.sql_Actions_Company;
import db.Beans.Company;

/* for safety and usability reasons - all SQL tables-name are not 'hard-coded. 
 * all SQL-commands locate in interface-class "sql_Actions_Company" */

public class CompanyDBDAO implements CompanyDAO {

	// this PRIVATE connector will be uses for all methods in this class
	private ConnectionPool pool = ConnectionPool.getInstance();

	// Method check if company exist in the system. parm: String email and String
	// password.
	@Override
	public boolean isCompanyExists(String email, String password) {

		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isExists = false;

		try {
			stmt = con.prepareStatement(sql_Actions_Company.isCompanyExists);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next() && rs.getInt("id") > 0) {
				return isExists;
			}

		} catch (SQLException e) {
			System.out.println("No Company Found");
			e.getMessage();
		} finally {
			pool.restoreConnection(con);
		}

		return isExists;
	}

	/*
	 * this method will add a new company to <comapnies TABLE> PARMS: user input and
	 * Auto-Increment ID while the creation using Result set to get the new ID if
	 * action did not succeeded - return null.
	 */
	@Override
	public void addCompany(Company company) {

		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql_Actions_Company.ADD_ONE_COMPANY);
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());
			stmt.execute();

			stmt.close();

		} catch (Exception e) {
		} finally {
			pool.restoreConnection(con);
		}
	}

	/*
	 * updateCompany: parm: <Company> company return - boolean isUpdated.
	 */
	@Override
	public boolean updateCompany(Company company) {

		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql_Actions_Company.UPDATE_COMPANY);
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());
			stmt.setInt(4, company.getId());
			rs = stmt.executeQuery();

			stmt.close();
			return rs.next();

		} catch (Exception e) {
		} finally {
			pool.restoreConnection(con);
		}
		return false;
	}

	// delete company by id - parm: companyID
	@Override
	public void deleteCompany(int companyID) {

		Connection con = pool.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql_Actions_Company.DELETE_COMPANY + companyID);
			stmt.execute();

			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}
	}

	// get cone company by id - parm: companyID return <Company>
	@Override
	public Company getOneCompanyById(int companyID) {

		Connection con = pool.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql_Actions_Company.GET_ONE_COMPANY_BY_ID + companyID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Company(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"));
			}
			stmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}
		return null;
	}

	// get all companies - return: ArrayList <Company>
	@Override
	public ArrayList<Company> getAllCompanies() {

		ArrayList<Company> companyArrL = new ArrayList<Company>();
		Connection con = pool.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql_Actions_Company.GET_ALL_COMPANIES);
			rs = stmt.executeQuery();
			while (rs.next()) {
				companyArrL.add(new Company(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("password")));
			}

			stmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			pool.restoreConnection(con);
		}
		return companyArrL;
	}

	
	
}