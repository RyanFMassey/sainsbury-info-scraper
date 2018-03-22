package controller;

import java.util.List;

import org.jsoup.nodes.Document;

import models.Product;
import models.Results;
import utils.PageScraper;
import utils.ProductToJSON;

public class Controller {

	public static void main(String args[]) {
		PageScraper ps = new PageScraper();
		Document doc = ps.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html");
		List<Product> berries = ps.getAllProducts(doc);
		ProductToJSON pdtj = new ProductToJSON();
		Results r = new Results(berries);
		pdtj.resultsToJSON(r);
		
	}
	
}
