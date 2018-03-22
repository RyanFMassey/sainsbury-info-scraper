package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import utils.PageScraper;


public class PageScraperTest {
	
	private PageScraper testClass;
	private String pageUrl;
	private Document pageAsDoc;
	
	@Before
	public void setUp() {
		testClass = new PageScraper();
		pageUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
		pageAsDoc = testClass.getHtmlPage(pageUrl);
	}

	@Test
	public void pageScraperTest() throws IOException {
		File in = new File("src/main/resources/berryTest.html");	//Get saved version of website from file
		Document doc = Jsoup.parse(in, "UTF-8");			//Parse file to Jsoup Document object
		
		assertEquals(doc.title(), testClass.getHtmlPage(pageUrl).title());   //Compare title of retrieved version to saved version
	}
	
	
	@Test
	public void getBerryLinksTest() {
		assertEquals(17, testClass.getBerryLinks(pageAsDoc).size());
		assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html",
				testClass.getBerryLinks(pageAsDoc).get(0));
		assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html",
				testClass.getBerryLinks(pageAsDoc).get(10));
	}

}
