package in.starx.main.model;

public class Cart {

	private int id;
	private int userId;
	private int foodId;
	private String foodName;
	private double price;
	private String image;
	private int quantity;
	private double totalPrice;
	
	public Cart() {
		super();
	}

	public Cart(int id, int userId, int foodId, String foodName, double price, String image, int quantity,
			double totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.image = image;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
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

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
