package db.company;

import java.util.ArrayList;

import db.sql_Actions_Company;
import db.Beans.Company;

public interface CompanyDAO extends sql_Actions_Company {

	/*
	 * this method will check if company exist in DB. in case true will return int
	 * CompanyID, else return -1
	 */
	boolean isCompanyExists(String email, String password);

	// create a new company in DB , return <Company>
	void addCompany(Company company);

	// update company, return boolean for verification.
	boolean updateCompany(Company company);

	void deleteCompany(int companyID);

	Company getOneCompanyById(int companyID);

	ArrayList<Company> getAllCompanies();

}
