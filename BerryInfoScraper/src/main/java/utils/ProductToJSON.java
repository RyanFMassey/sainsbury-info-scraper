package utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Product;
import models.Results;

public class ProductToJSON {
	
	public String resultsToJSON(Results r) {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Product.class, new ProductSerializer());	//Configures Gson for custom serialization.
		gb.setPrettyPrinting().disableHtmlEscaping().create(); //Disable html escaping to show apostrophes rather than unicode 
		Gson gson = gb.setPrettyPrinting().disableHtmlEscaping().create(); //Disable html escaping to show apostrophes rather than unicode. Enables prettyprinting, which outputs the json in a line by line format. 
		
		return gson.toJson(r);
	}
}
