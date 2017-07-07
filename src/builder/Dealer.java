package builder;

import java.util.List;

public class Dealer {
	private String dealerName;
	private String webLink;
	private List<Product> products;
	
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}	

}
