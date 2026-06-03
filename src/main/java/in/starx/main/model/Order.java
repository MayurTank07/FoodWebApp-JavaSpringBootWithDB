package in.starx.main.model;

import java.time.LocalDateTime;

public class Order {

	private int id;
	private int userId;
	private double totalAmount;
	private String status;
	private String customerName;
	private String phone;
	private String address;
	private LocalDateTime orderDate;
	
	public Order() {
		super();
	}

	public Order(int id, int userId, double totalAmount, String status, String customerName, String phone,
			String address, LocalDateTime orderDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customerName = customerName;
		this.phone = phone;
		this.address = address;
		this.orderDate = orderDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getOrderDate() {
	    return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
	    this.orderDate = orderDate;
	}
	

	
	
}
