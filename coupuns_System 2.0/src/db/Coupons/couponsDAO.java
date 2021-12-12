package db.Coupons;

import java.util.ArrayList;

import db.Beans.Coupon;

public interface couponsDAO {
	
	void addCoupon (Coupon coupon);
	
	void updateCoupon(Coupon coupon);
	
	void deleteCoupon (int couponID);
	
	Coupon getOneCoupon (int couponID);
	
	ArrayList<Coupon> getAllCoupons ();
	
	void addCouponPurchase(int customerID, int couponID);
	
	void deleteCouponPurchase(int customerID, int CouponID);
	

}
