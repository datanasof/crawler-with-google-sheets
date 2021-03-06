package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import googleSheetsManager.Credentials;
import googleSheetsManager.GsheetsManager;

public class ProductDescription {
		
	private List<Description> dlist;
	
	public ProductDescription() throws IOException{
		String spreadsheetId = Credentials.infoSpreadsheetId;
	    String range = "qualify description!A2:C20";
	    GsheetsManager manager = new GsheetsManager();
	    List<List<Object>> values = manager.readSheet(spreadsheetId, range);
	    this.dlist = readQualifyValues(values);
	}
	
	private List<Description> readQualifyValues(List<List<Object>> values){
		List<Description> dList = new ArrayList<Description>();
		if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } 
		else {
        	for (List<?> row : values) {
        		try {
        			String type = (String) row.get(0);
        			String keyWords = (String) row.get(1);
        			String points = (String) row.get(2);
        			
        			Description d = new Description(type, keyWords, points);
        			dList.add(d);
        		} catch (Exception e) {
        			System.out.println("No data found.");
        			break;
        		} 
        	}
        }
		return dList;                
	}
	
	private Map<String,Integer> getValuesByType(String type){
		for(Description d:dlist){
			if(d.getType().equals(type)) 
				return d.getWordsPoints();
		}
		return null;
	}
	
	private String qualifyPoints(int points){
		String result = null;
		if(points >= 70) result = "good";
		else if(points >= 45) result = "ok";
		else if(points >= 10) result = "poor";
		else result = "not present";
				
		return result;
	}
	
	//added description qualification method (string type, string link) return good/ok/not enough
	public String qualifyDescription(String type, String url) throws IOException{
		int qualified = 0;
		String htmlString = Jsoup.connect(url).get().toString().toLowerCase();
		Map<String, Integer> wordpoints = getValuesByType(type);
		for (Map.Entry<String, Integer> entry : wordpoints.entrySet()){
			Pattern matches = Pattern.compile(entry.getKey());					
			if (matches.matcher(htmlString).find()) {
				qualified += entry.getValue();							
			}	    
		}		
		return qualifyPoints(qualified);
	}
	
	public String qualifyFX(String url) throws IOException{
		return qualifyDescription("fx", url);
	}
	
	//added FX descr. qualification method (string type, string link) return good/ok/not enough
	
		
}
