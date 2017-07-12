package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Element;

import builder.Product;
import crawler.DeviceList;
import crawler.ProductDescription;
import crawler.Seller;
import crawler.SellerList;
import crawler.URLparser;
import googleSheetsManager.Credentials;
import googleSheetsManager.GsheetsManager;

public class Manager {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		SellerList sellerlist = new SellerList();
		DeviceList dlist = new DeviceList();
		ProductDescription productInfo = new ProductDescription();	
		GsheetsManager gmanager = new GsheetsManager();
		
		for(Seller seller: sellerlist.getList()){
			String sellerName = seller.getName();
			List<Product> productListToReport = new ArrayList<Product>();			
			ArrayList<Element> products = URLparser.productListParser(seller.getSearchLink());
			for (Element x: products){
				String[] productInformation = dlist.getProductInfoFromStr(x.text());
				if(productInformation[0] instanceof String){					
					String name = productInformation[0];
					String type = productInformation[1];
					System.out.println(name+" "+type);
					String productLink = x.attr("href");
					System.out.println(productLink);
					String price = URLparser.priceParser(productLink);
					String description = productInfo.qualifyDescription(type, productLink);
					String fxDescription = productInfo.qualifyFX(productLink);
					Product product = new Product(name, productLink, description, fxDescription, price);
					productListToReport.add(product);
					System.out.println(product.getProductName());					
				}				
			}
			seller.setProducts(productListToReport);
			gmanager.addSheet(Credentials.reportSpreadsheetId, sellerName);
			String range = sellerName+"!A1:I1";
			gmanager.writeSheet(Credentials.reportSpreadsheetId, range, seller.getGsheetHeader());
			range = sellerName+String.format("!A2:F%s", productListToReport.size()+1);
			gmanager.writeSheet(Credentials.reportSpreadsheetId, range, seller.getGsheetValues());
			
		}
	}
}
