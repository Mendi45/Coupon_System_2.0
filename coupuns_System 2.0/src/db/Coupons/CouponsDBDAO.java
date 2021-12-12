package db.Coupons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import db.Sql_Actions_Coupons;
import db.Beans.Category;
import db.Beans.Coupon;

public class CouponsDBDAO implements couponsDAO {

	// this PRIVATE connector will be uses for all methods in this class
	private ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) {
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(Sql_Actions_Coupons.ADD_COUPON);
			stmt.setInt(1, coupon.getCompanyID());
			stmt.setInt(2, coupon.getCategory().ordinal() + 1);
			stmt.setString(3, coupon.getTitle());
			stmt.setString(4, coupon.getDescreption());
			stmt.setDate(5, coupon.getStartDate());
			stmt.setDate(6, coupon.getEndDate());
			stmt.setInt(7, coupon.getAmount());
			stmt.setDouble(8, coupon.getPrice());
			stmt.setString(9, coupon.getImage());

			stmt.executeQuery();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}		
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		
		Connection con = pool.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement(Sql_Actions_Coupons.UPDATE_COUPON);
			stmt.setInt(1, coupon.getCategory().ordinal() + 1);
			stmt.setString(2, coupon.getTitle());
			stmt.setString(3, coupon.getDescreption());
			stmt.setDate(4, coupon.getStartDate());
			stmt.setDate(5, coupon.getEndDate());
			stmt.setInt(6, coupon.getAmount());
			stmt.setDouble(7, coupon.getPrice());
			stmt.setString(8, coupon.getImage());
			stmt.setInt(9, coupon.getId());
			
			stmt.executeQuery();
			stmt.close();			

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			pool.restoreConnection(con);
		}
	}

	
	/*
	 * this method delete coupon from coupons tables
	 * but before deleting the coupon - must to remove coupon purchases from coupon_VS_coustomer table.
	 */
	
	@Override
	public void deleteCoupon(int couponID) {

		Connection con = pool.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(Sql_Actions_Coupons.DELETE_COUPON + couponID);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public Coupon getOneCoupon(int couponID) {
		
		return null;
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCouponPurchase(int customerID, int CouponID) {
		// TODO Auto-generated method stub

	}

}
