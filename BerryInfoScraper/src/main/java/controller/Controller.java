package controller;

import org.jsoup.nodes.Document;

import utils.PageScraper;

public class Controller {

	public static void main(String args[]) {
		PageScraper ps = new PageScraper();
		Document doc = ps.getHtmlPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html");
		ps.getBerryLinks(doc);
	}
	
}
