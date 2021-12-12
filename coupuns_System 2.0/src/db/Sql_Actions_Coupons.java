package db;

public class Sql_Actions_Coupons {

	public static final String ADD_COUPON = "insert into coupons (COMPANY_ID, CATEGORY_ID, TITLE, DESCRIPTION, START_DATE, END_DATE, AMOUNT, PRICE, IMAGE) values (?,?,?,?,?,?,?,?,?)";
	public static final String DELETE_COUPON = "delete from coupons where id = ";
	public static final String UPDATE_COUPON = "update coupons SET category_id = ? , title = ?, description = ?"
			+ ", start_date = ?, end_date = ?, amount = ?, price = ?, image = ? where id = ?";

}
