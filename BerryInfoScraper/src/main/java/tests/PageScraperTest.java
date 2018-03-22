package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import utils.PageScraper;


public class PageScraperTest {
	
	private PageScraper testClass;
	private String pageUrl;
	private Document homePageAsDoc;
	private Document strawberryPage;
	private Document cherryPunnetPage;
	private Document blackcurrantPage;
	private Document cherryStrawPage;
	private Document blueberryPage;
	
	@Before
	public void setUp() {
		testClass = new PageScraper();
		pageUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
		homePageAsDoc = testClass.getHtmlPage(pageUrl);
		strawberryPage = testClass.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html");
		cherryPunnetPage = testClass.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html");
		blackcurrantPage = testClass.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html");
		cherryStrawPage = testClass.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html");
		blueberryPage = testClass.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html");
	}

	@Test
	public void pageScraperTest() throws IOException {
		File in = new File("src/main/resources/berryTest.html");	//Get saved version of website from file
		Document doc = Jsoup.parse(in, "UTF-8");			//Parse file to Jsoup Document object
		
		assertEquals(doc.title(), testClass.getHtmlPage(pageUrl).title());   //Compare title of retrieved version to saved version
	}
	
	
	@Test
	public void getBerryLinksTest() {
		assertEquals(17, testClass.getBerryLinks(homePageAsDoc).size());
		assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html",
				testClass.getBerryLinks(homePageAsDoc).get(0));
		assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html",
				testClass.getBerryLinks(homePageAsDoc).get(10));
	}
	
	@Test
	public void getBerryDescriptionTest() {
		assertEquals("by Sainsbury's strawberries", testClass.getBerryDescription(strawberryPage));
		assertEquals("Cherries", testClass.getBerryDescription(cherryPunnetPage));
		assertEquals("Union Flag", testClass.getBerryDescription(blackcurrantPage));
		assertEquals("British Cherry & Strawberry Mixed Pack", testClass.getBerryDescription(cherryStrawPage));
	}
	
	
	@Test 
	public void getUnitPriceTest() {
		assertEquals(new BigDecimal("1.75"), testClass.getUnitPrice(strawberryPage));
		assertEquals(new BigDecimal("1.50"), testClass.getUnitPrice(cherryPunnetPage));
		assertEquals(new BigDecimal("1.75"), testClass.getUnitPrice(blackcurrantPage));
		assertEquals(new BigDecimal("4.00"), testClass.getUnitPrice(cherryStrawPage));
		assertEquals(new BigDecimal("3.25"), testClass.getUnitPrice(blueberryPage));
	}

}
