package utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Product;

public class ProductSerializer implements JsonSerializer<Product> {

	public JsonElement serialize(Product berry, Type type, JsonSerializationContext jsc) {
		Gson gson = new Gson();
		JsonObject jObj = (JsonObject)gson.toJsonTree(berry);
		
		if (berry.getKcalPer100g()==-1) {
			jObj.remove("kcalPer100g");
		}
		
		return jObj;
	}
	

}
