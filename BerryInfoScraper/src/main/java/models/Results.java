package models;

import java.math.BigDecimal;
import java.util.List;

public class Results {
	List<Product> products;
	BigDecimal totalPrice;
	int numberOfProducts;
	
	
	public Results(List<Product> products) {
		this.products = products;
		
		BigDecimal total = new BigDecimal(0);
		for (int i = 0; i < products.size(); i++) {
			total = total.add(products.get(i).getUnitPrice());
		}
		
		this.totalPrice = total;
		this.numberOfProducts = products.size();
	}
	
	
	public List<Product> getProducts() {
		return products;
	}
	
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	public BigDecimal getTotal() {
		return totalPrice;
	}
	
	
	public void setTotal(BigDecimal total) {
		this.totalPrice = total;
	}
	
	
}
