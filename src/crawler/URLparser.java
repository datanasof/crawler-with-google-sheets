package crawler;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import googleSheetsManager.Credentials;

public class URLparser {
	public static ArrayList<Element> productListParser(String searchUrl) throws IOException{
		Document doc = Jsoup.connect(searchUrl).get();
		Elements certainLinks = doc.select("a[href]:contains(antelope)");
		return certainLinks;		
	}
		
	public static String priceParser(String url) throws IOException{	
		String price;
		Document doc = Jsoup.connect(url).get();
		Element span = doc.select(Credentials.priceParserTag).first();
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
	
	
}
