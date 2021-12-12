package db;

public interface Sql_Actions_Customer {

	public static final String isCustomerExists = "select * from customers where email = ? and password = ?";
	public static final String ADD_CUSTOMER = "insert into customers (firstName,lastName,email,password) values (?,?,?,?)";
	public static final String UPDATE_CUSTOMER = "update customers SET firstName = ?,lastName =? ,email =?,password =? where id =?";
	public static final String DELETE_CUSTOMER = "delete from customers where ID = ";
	public static final String SELECT_ONE_CUSTOMER_BY_ID = "select * from customers where id =";
	public static final String select_all_from_customers = "select * from customers";
	public static final String GET_COUPONS_BY_CUSTOMERS = "select coupons.ID , coupons.CATEGORY_ID, coupons.CATEGORY_ID, coupons.TITLE, coupons.DESCRIPTION, coupons.START_DATE, coupons.END_DATE, coupons.AMOUNT,coupons.PRICE, coupons.IMAGE\r\n"
	+ "from coupons, customers,customers_vs_coupons where customers_vs_coupons.CUSTOMER_ID = ";
	
	public static final String GET_CATEGORY_OBJ = "select * from categories where categoryid =";
	
	/*
	 * select customers.ID , coupons.ID , coupons.CATEGORY_ID, coupons.CATEGORY_ID, coupons.TITLE, coupons.DESCRIPTION, coupons.START_DATE, coupons.END_DATE, coupons.AMOUNT,coupons.PRICE, coupons.IMAGE\r\n"
	+ "from coupons, customers,customers_vs_coupons where customers_vs_coupons.CUSTOMER_ID = "
	 */

}
