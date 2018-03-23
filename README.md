# sainsbury-info-scraper
A web scraper that scrapes the product title, cost per unit, kcal per 100g and the first line of the description from a page of products on Sainsbury's website.

### Dependencies
Junit 4.12 (+ Hamcrest core 1.3)
  * Used to perform unit tests
  * https://junit.org/junit5/ (http://hamcrest.org/JavaHamcrest/)

Gson 2.8.2
  * Used to parse Java objects Json and output it in readable format.
  * https://github.com/google/gson
  
Jsoup 1.11.2
  * Used to scrape and parse the HTML and its elements
  *https://jsoup.org/

### How to run
#### Using the jar file
Download and install [Java Runtime Environment](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

Run the jar file from a command line using the following command
```
java -jar SainsburysItemScraper.jar
```

#### Using IDE
Ensure your IDE supports [Maven](https://maven.apache.org/) projects

Import the source into your IDE

Run the controller class, which contains the main method

### Usage
When the program is first run you will be prompted for a URL input. You can enter your own URL, enter "d" for the 
[default](https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html) 
link or "exit" to terminate the program. If the page at the given URL contains any products then the information of each product on the page 
will be scraped and output to the terminal in JSON format.

### Testing
Included in the source is a test class containing unit tests using JUnit. Each method used when scraping can be tested here, and additional tests can be written if necessary.
