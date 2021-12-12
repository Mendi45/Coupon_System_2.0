package db;

 public interface sql_Actions_Company {
	
	public static final String isCompanyExists = "select * from COMPANIES where email = ? and password = ?";
	public static final String GET_ONE_COMPANY_BY_ID = "select * from COMPANIES where id = " ;
	public static final String ADD_ONE_COMPANY = "INSERT INTO COMPANIES (NAME,EMAIL,PASSWORD) VALUES (?,?,?)";
	public static final String UPDATE_COMPANY = "UPDATE COMPANIES SET NAME = ?, EMAIL = ?, PASSWORD = ? where id = ?";
	public static final String DELETE_COMPANY = "delete from COMPANIES where ID = ";
	public static final String GET_ALL_COMPANIES = "SELECT * FROM COMPANIES";
 
 }
