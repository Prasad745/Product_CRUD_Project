package com.neoquant.productcrud.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductRequest {

	private int id;
	@NotNull (message = "product name is should not be null")
	private String name;
	@Min(1)
	@Max(10)
	private int quantity;
	private String color;
	private double price;
	private float weight;
	public ProductRequest(int id, @NotNull(message = "product name is should not be null") String name,
			@Max(10) @Min(1) int quantity, String color, double price, float weight) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.color = color;
		this.price = price;
		this.weight = weight;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "ProductRequest [id=" + id + ", name=" + name + ", quantity=" + quantity + ", color=" + color
				+ ", price=" + price + ", weight=" + weight + "]";
	}
}
