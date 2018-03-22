package models;

import java.math.BigDecimal;

public class Product {
	String title;
	BigDecimal unitPrice;
	int kcalPer100g;
	String description;
	
	public Product(String title, BigDecimal unitPrice, int kcalPer100g, String description) {
		this.title = title;
		this.unitPrice = unitPrice;
		this.kcalPer100g = kcalPer100g;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getKcalPer100g() {
		return kcalPer100g;
	}
	
	public void setKcalPer100g(int kcalPer100g) {
		this.kcalPer100g = kcalPer100g;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
