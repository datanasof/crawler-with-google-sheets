package builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {

	private String productName;
	private String webLink;
	private String description;
	private String fxInfo;
	private String price;
	private String updated;
	private String photos;
	private String videos;
	
	public Product (){		
	}
	
	public Product(String productName,String webLink,String description, String fxInfo, String price, String updated){
		this.productName = productName;
		this.webLink = webLink;
		this.description = description;
		this.fxInfo = fxInfo;
		this.price = price;
		this.updated = updated;
		this.photos = "";
		this.videos = "";
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

	public void setUpdated() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedString = time.format(formatter);
		this.updated = formattedString;
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
	
}
