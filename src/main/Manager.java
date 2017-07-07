package main;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;

import builder.Dealer;
import crawler.DeviceList;
import crawler.ProductDescription;
import crawler.Seller;
import crawler.SellerList;
import crawler.URLparser;

public class Manager {

	public static void main(String[] args) throws IOException {
		SellerList seller = new SellerList();
		DeviceList dlist = new DeviceList();
		ProductDescription productInfo = new ProductDescription();	
		
		
		for(Seller sell: seller.getList()){
			Dealer dealer = new Dealer(sell.getName(),sell.getSearchLink());
			
			ArrayList<Element> products = URLparser.productListParser(dealer.getWebLink());
			for (Element x: products){
				String productName = dlist.getNameFromStr(x.text());
				String productLink = x.attr("href");
				String price = URLparser.priceParser(productLink);
				String description = productInfo.qualifyDescription(type, url)
				product.qualifyDescription("thunderbolt interface", searchUrl) + "; " + product.qualifyFX(searchUrl));
			}
		}
	}
}
