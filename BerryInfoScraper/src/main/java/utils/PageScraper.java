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
		Connection conn = Jsoup.connect(url);
		Document doc = null;
		try {
			doc = conn.timeout(6000).get();
		} catch (Exception e) {
			
		}
		return doc;		
	}
	
	
	public List<String> getBerryLinks(Document doc) {
		Elements berryElements = doc.select("div.productInfo");
		List<String> berryLinks = new ArrayList<String>();
		
		for (Element berryInfo : berryElements) {
			String link = berryInfo.getElementsByTag("a").first().attr("abs:href");
			berryLinks.add(link);
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
		Element titleDiv = doc.select("div.productTitleDescriptionContainer").first();
		String title = titleDiv.select("h1").first().text();
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

	
}
