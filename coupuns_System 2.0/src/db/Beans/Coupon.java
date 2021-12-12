package db.Beans;

import java.sql.Date;

public class Coupon {
	private int id;
	private int companyID;
	private Category category;
	private String title;
	private String descreption;
	private Date StartDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	// CTR without id for insert data to DB
	public Coupon(int companyID, Category category, String title, String descreption, Date startDate, Date endDate,
			int amount, double price, String image) {
		super();
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.descreption = descreption;
		StartDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	// CTR with id - for get data from DB
	public Coupon(int id, int companyID, Category category, String title, String descreption, Date startDate,
			Date endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.descreption = descreption;
		StartDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	
	// getters & setters, without set id(), set companyID() because is auto increment
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescreption() {
		return descreption;
	}

	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public int getCompanyID() {
		return companyID;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyID + ", category=" + category + ", title=" + title
				+ ", descreption=" + descreption + ", StartDate=" + StartDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}




}
