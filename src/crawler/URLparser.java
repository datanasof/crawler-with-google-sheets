package crawler;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLparser {
	private String url;
	private String priceParserTag = "span[itemprop=\"price\"]";
	private String priceParserTag2 = "div[itemprop=\"price\"]";

	public static ArrayList<Element> productListParser(String searchUrl) throws IOException{
		Document doc = Jsoup.connect(searchUrl).get();
		Elements certainLinks = doc.select("a[href]:contains(antelope)");
		return certainLinks;		
	}
		
	public static String priceParser(String url, String priceParserTag) throws IOException{	
		String price;
		Document doc = Jsoup.connect(url).get();
		Element span = doc.select(priceParserTag).first();
		if(span != null) {
			price = span.text();
		} else price = "";
		return price;		
	}
	
	
	public static double priceEditor(String price){
		String parsed = price.replaceAll("[^\\d.]+", "");
		double newPrice = Double.parseDouble(parsed);
		return newPrice;
	}
	
	public static void main(String[] args) throws IOException {
		String searchUrl = "https://www.bax-shop.co.uk/products/search?filters[order]=score&filters[price][min]=0&filters[price][max]=6100&filters[q]=antelope&grid=grid&p=1";		
		String forprice = "https://www.thomann.de/de/antelope_goliath_hd.htm?ref=search_rslt_antelope_414445_23";
		String priceParserTag = "span[itemprop=\"price\"]";
		
		ArrayList<Element> test = URLparser.productListParser(searchUrl);
		for (Element x: test){
			System.out.println(ProductList.fromString(x.text())+": "+x.attr("href"));
			//System.out.println(x.attr("href"));
		}
	/**	ArrayList<Element> test2 = URLparser.testParser();
		for (Element x: test2){
			System.out.println(x.text());
		}**/
		String price = URLparser.priceParser(forprice, priceParserTag);
		System.out.println(price);
		System.out.println(URLparser.priceEditor("6.995,00 ˆ"));
	}

}
