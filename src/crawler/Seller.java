package crawler;

import java.util.ArrayList;
import java.util.List;

import builder.Product;
import googleSheetsManager.Credentials;

public class Seller {

	private String name;
	private String searchLink;
	private List<Product> products;
	
	public Seller(String name, String searchLink){
		this.name = name;
		this.searchLink = searchLink;
	}
	
	public Seller(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchLink() {
		return searchLink;
	}

	public void setSearchLink(String searchLink) {
		this.searchLink = searchLink;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<List<Object>> getGsheetHeader(){
		List<List<Object>> values = new ArrayList<List<Object>>();
		List<Object> productHeader = new ArrayList<Object>();
		for(String x: Credentials.productReportHeader) productHeader.add(x);
		values.add(productHeader);
		return values;		
	}
	public List<List<Object>> getGsheetValues(){
		List<List<Object>> values = new ArrayList<List<Object>>();
		for(Product product: products){
			values.add(product.getGsheetRow());
		}
		return values;		
	}
	
	public void setProductsFromGvalues(List<List<Object>>values){
		for(List<Object>row:values){
			Product product = new Product(row.get(0).toString(),row.get(1).toString(),row.get(2).toString(),row.get(3).toString(),row.get(4).toString());
			products.add(product);
		}
	}
	
	public List<Object> getProductInfo(String productName){
		List<Object> productInfo = new ArrayList<Object>();
		for(Product product:products){
			if (product.getProductName().equals(productName)){
				productInfo.add(product.getProductName());
				productInfo.add(product.getWebLink());
				productInfo.add(product.getDescription());
				productInfo.add(product.getFxInfo());
				productInfo.add(product.getPrice());
			}
		}
		return productInfo;
	}
}
