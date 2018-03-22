package utils;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Product;
import models.Results;

public class ProductToJSON {
	
	public void resultsToJSON(Results r) {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Product.class, new ProductSerializer());
		gb.setPrettyPrinting().disableHtmlEscaping().create(); //Disable html escaping to show apostrophes rather than unicode 
		Gson gson = gb.setPrettyPrinting().disableHtmlEscaping().create(); //Disable html escaping to show apostrophes rather than unicode 
		
		System.out.println(gson.toJson(r));
	}
}
