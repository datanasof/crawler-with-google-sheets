package main;

import java.util.ArrayList;

public class Dealer {
	private String dealerName;
	private String webLink;
	private ArrayList<ArrayList<Object>> products;
	
	public Dealer(){
		
	}
	
	public Dealer(String dealerName, String webLink){
		this.dealerName = dealerName;
		this.webLink = webLink;		
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	public ArrayList<ArrayList<Object>> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ArrayList<Object>> products) {
		this.products = products;
	}	

}
