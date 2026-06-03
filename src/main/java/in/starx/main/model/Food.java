package in.starx.main.model;

public class Food {
	
	private int id;
	private String foodName;
	private double price;
	private String image;
	private String category;
	
	public Food() {
		
	}

	public Food(int id, String foodName, double price, String image, String category) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.price = price;
		this.image = image;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
	    return "Food [id=" + id + ", foodName=" + foodName + ", price=" + price + ", image=" + image
	            + ", category=" + category + "]";
	}
	
	
}
