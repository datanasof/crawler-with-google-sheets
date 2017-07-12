package builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import googleSheetsManager.Credentials;

public class Product {

	private String productName;
	private String webLink;
	private String description;
	private String fxInfo;
	private String price;
	private String updated;
	private String photos;
	private String videos;
	private String recommended;
	
	public Product (){		
	}
	
	public Product(String productName,String webLink,String description, String fxInfo, String price){
		this.productName = productName;
		this.webLink = webLink;
		this.description = description;
		this.fxInfo = fxInfo;
		this.price = price;
		this.updated = "";
		this.photos = "";
		this.videos = "";
		this.recommended="";
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFxInfo() {
		return fxInfo;
	}

	public void setFxInfo(String fxInfo) {
		this.fxInfo = fxInfo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUpdated() {
		return updated;
	}

	public String setUpdated() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return time.format(formatter);
		
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}	
	
	public List<Object> getGsheetRow(){
		List<Object> productRow = new ArrayList<Object>();
		productRow.add(productName);
		productRow.add(webLink);
		productRow.add(description);
		productRow.add(fxInfo);
		productRow.add(price);
		
		return productRow;		
	}
	
	
}
