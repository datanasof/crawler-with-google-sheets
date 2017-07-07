package crawler;

public class Seller {

	private String name;
	private String searchLink;
	
	public Seller(String name, String searchLink){
		this.name = name;
		this.searchLink = searchLink;
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
	
	
	
}
