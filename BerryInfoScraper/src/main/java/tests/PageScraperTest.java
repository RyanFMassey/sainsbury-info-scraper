package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import models.Product;
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
	public void getBerryTitleTest() {
		assertEquals("Sainsbury's Strawberries 400g", testClass.getBerryTitle(strawberryPage));
		assertEquals("Sainsbury's Cherry Punnet 200g", testClass.getBerryTitle(cherryPunnetPage));
		assertEquals("Sainsbury's Blackcurrants 150g", testClass.getBerryTitle(blackcurrantPage));
		assertEquals("Sainsbury's British Cherry & Strawberry Pack 600g", testClass.getBerryTitle(cherryStrawPage));
		assertEquals("Sainsbury's Blueberries 400g", testClass.getBerryTitle(blueberryPage));
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
	
	
	@Test 
	public void getBerryEnergyTest() {
		assertEquals(33, testClass.getBerryEnergy(strawberryPage));
		assertEquals(52, testClass.getBerryEnergy(cherryPunnetPage));
		assertEquals(null, testClass.getBerryEnergy(blackcurrantPage));
		assertEquals(null, testClass.getBerryEnergy(cherryStrawPage));
		assertEquals(45, testClass.getBerryEnergy(blueberryPage));

	}
	
	@Test
	public void getBerryInfoTest() {
		Product berry1 = new Product("Sainsbury's Strawberries 400g", new BigDecimal(1.75), 33, "by Sainsbury's strawberries");
		assertEquals(berry1.getTitle(), testClass.getBerryInfoFromPage(strawberryPage).getTitle());
		assertEquals(berry1.getUnitPrice(), testClass.getBerryInfoFromPage(strawberryPage).getUnitPrice());
		assertEquals(berry1.getKcalPer100g(), testClass.getBerryInfoFromPage(strawberryPage).getKcalPer100g());
		assertEquals(berry1.getDescription(), testClass.getBerryInfoFromPage(strawberryPage).getDescription());
		
		berry1 = new Product("Sainsbury's Blackcurrants 150g", new BigDecimal(1.75), -1, "Union Flag");
		assertEquals(berry1.getTitle(), testClass.getBerryInfoFromPage(blackcurrantPage).getTitle());
		assertEquals(berry1.getUnitPrice(), testClass.getBerryInfoFromPage(blackcurrantPage).getUnitPrice());
		assertEquals(berry1.getKcalPer100g(), testClass.getBerryInfoFromPage(blackcurrantPage).getKcalPer100g());
		assertEquals(berry1.getDescription(), testClass.getBerryInfoFromPage(blackcurrantPage).getDescription());
		
		berry1 = new Product("Sainsbury's Blueberries 400g", new BigDecimal(3.25), 45, "by Sainsbury's blueberries");
		assertEquals(berry1.getTitle(), testClass.getBerryInfoFromPage(blueberryPage).getTitle());
		assertEquals(berry1.getUnitPrice(), testClass.getBerryInfoFromPage(blueberryPage).getUnitPrice());
		assertEquals(berry1.getKcalPer100g(), testClass.getBerryInfoFromPage(blueberryPage).getKcalPer100g());
		assertEquals(berry1.getDescription(), testClass.getBerryInfoFromPage(blueberryPage).getDescription());
	}
	
	
	@Test
	public void getAllProductsTest() {
		List<Product> berriesFromPage = testClass.getAllProducts(homePageAsDoc);
		
		assertEquals(17, berriesFromPage.size());
		assertEquals("Sainsbury's Raspberries, Taste the Difference 150g", berriesFromPage.get(6).getTitle());
		assertEquals(null, berriesFromPage.get(12).getKcalPer100g());
	}

}
