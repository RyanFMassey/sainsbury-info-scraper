package utils;

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
	
	
	
	
}
