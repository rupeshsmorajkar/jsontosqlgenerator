package com.mycompany.jsontosqlgenerator.model;

public class Products {
	private String id;
	private String category;
    private String imageUrl;
    private double price;
    private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", category=");
		builder.append(category);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", price=");
		builder.append(price);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}
}
