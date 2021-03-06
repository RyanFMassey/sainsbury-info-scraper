package controller;

import java.util.List;
import java.util.Scanner;

import org.jsoup.nodes.Document;

import models.Product;
import models.Results;
import utils.PageScraper;
import utils.ProductToJSON;

public class Controller {

	public static void main(String args[]) {
		Boolean exit = false;
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.println("Enter a url, or enter 'd' to use the default link, or type exit to terminate.");
			String url = s.nextLine();
			
			if (url.equals("exit")) {
				exit = true;
			} else  {
				if (url.equals("d")) {
					url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";	
				}
				
				PageScraper ps = new PageScraper();
				Document doc = ps.getHtmlPage(url);
				List<Product> berries = ps.getAllProducts(doc);
				ProductToJSON pdtj = new ProductToJSON();
				Results r = new Results(berries);
				System.out.println(pdtj.resultsToJSON(r));
			}
		} while (!exit);
		
		s.close();
	}
}
