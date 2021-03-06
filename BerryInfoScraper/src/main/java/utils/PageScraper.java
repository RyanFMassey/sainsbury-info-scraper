package utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.Product;

public class PageScraper {
	
	public Document getHtmlPage(String url) {
		Connection conn = Jsoup.connect(url);    //Open connection to specified url
		Document doc = null;
		try {
			doc = conn.timeout(6000).get();   //Execute get request and parse to Document
		} catch (Exception e) {
			
		}
		return doc;		
	}
	
	
	public List<String> getBerryLinks(Document doc) {
		Elements berryElements = doc.select("div.productInfo");  //get all divs with class productInfo
		List<String> berryLinks = new ArrayList<String>();
		
		for (Element berryInfo : berryElements) {  // for each element in the list
			String link = berryInfo.getElementsByTag("a").first().attr("abs:href");		//store absolut link from elements with "a" tag
			berryLinks.add(link);  //add string to list
		}
		
		return berryLinks;
	}
	
	
	public Product getBerryInfoFromPage(Document doc) {
		String title = getBerryTitle(doc);
		BigDecimal price = getUnitPrice(doc);
		int kcalEnergy = getBerryEnergy(doc);
		String desc = getBerryDescription(doc);
		
		Product berry = new Product(title, price, kcalEnergy, desc);
		return berry;
	}
	
	
	public String getBerryTitle(Document doc) {
		Element titleDiv = doc.select("div.productTitleDescriptionContainer").first();  //get the first div element with class productTitleDescriptionContainer
		String title = titleDiv.select("h1").first().text();  //store the text of the first h1 element
		return title;
	}
	
	
	public String getBerryDescription(Document doc) {
		Element info = doc.select("div#information").first();
		String desc = null;
		int i = 0;
		do {
			desc = info.select("p").get(i).text();
			i++;
		} while (desc.equals(null) || desc.equals("") || i > info.childNodeSize());
		
		return desc;
	}
	
	
	public BigDecimal getUnitPrice(Document doc) {
		String stringPrice = doc.select("p.pricePerUnit").first().text();
		stringPrice = stringPrice.substring(1, stringPrice.length() - 5);
		BigDecimal unitPrice = new BigDecimal(stringPrice);
		return unitPrice;
	}
	
	
	public int getBerryEnergy(Document doc) {
		Elements nutritionTable = doc.select("table.nutritionTable");
		
		if (nutritionTable.isEmpty()) {
			return -1;
		} else {
			Elements rows = nutritionTable.select("tr");
			Element kcalRow = rows.get(2);
			String kcalData = kcalRow.select("td").get(0).text();
			
			//Manual Version
			/*if (kcalData.contains("kcal")) {
				kcalData = kcalData.substring(0, kcalData.length()-4);
				return Integer.parseInt(kcalData);
			} else {
				return Integer.parseInt(kcalData);
			}*/
			
			//Regex version
			int kcal = Integer.parseInt(kcalData.replaceAll("\\D+",  ""));
			
			return kcal;
		}
	}
	
	
	public List<Product> getAllProducts(Document doc) {
		List<String> berryLinks = getBerryLinks(doc);
		List<Product> berries = new ArrayList<Product>();
		
		for (int i = 0; i < berryLinks.size(); i++) {
			Document temp = getHtmlPage(berryLinks.get(i));
			Product berry = getBerryInfoFromPage(temp);
			berries.add(berry);	
		}
		
		return berries;
	}

	
}
