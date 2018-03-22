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
	
	@Before
	public void setUp() {
		testClass = new PageScraper();
		pageUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
	}

	@Test
	public void pageScraperTest() throws IOException {
		File in = new File("src/main/resources/berryTest.html");	//Get saved version of website from file
		Document doc = Jsoup.parse(in, "UTF-8");			//Parse file to Jsoup Document object
		
		assertEquals(doc.title(), testClass.getHtmlPage(pageUrl).title());   //Compare title of retrieved version to saved version
	}

}
