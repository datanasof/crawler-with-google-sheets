package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import googleSheetsManager.Credentials;
import googleSheetsManager.GsheetsManager;

public class DeviceList {
	List<Device> productList;
	private Pattern unmatches; 
	
	public DeviceList() throws IOException{
		this.productList = new ArrayList<Device>();
		String spreadsheetId = Credentials.infoSpreadsheetId;
	    String range = "devices!A2:B35";
	    GsheetsManager manager = new GsheetsManager();
	    List<List<Object>> values = manager.readSheet(spreadsheetId, range);
	    readQualifyValues(values);
	    this.unmatches = Pattern.compile(getUnmatch());
	}
	
	private void readQualifyValues(List<List<Object>> values){
		if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } 
		else {
        	for (List<?> row : values) {
        		try {
        			String name = (String) row.get(0);
        			String type = (String) row.get(1);
        			Device e = new Device(name,type);
        			productList.add(e);
        			}  
        		catch (Exception e) {
        			System.out.println(e);
        			break;
        		}       	  
        	} 
        }		
	}
	
	private String getUnmatch(){
		String output = null;				
		for (Device product : productList) {
			if (product.getType().equals("unmatch")) {
				output = product.getUnmatchString();							
			}		
		}
		return output;	
	}
	
	public List<Device> getProductList(){
		return productList;
	}
	
	public String getNameFromStr(String text) {
		String finalProduct = null;		
		String input = text;
		
		try{input=input.substring(0,40);		
		} catch(Exception e){}
		
		for (Device product : productList) {			
			Pattern matches = Pattern.compile(product.getSearchString());					
			if (matches.matcher(input.toLowerCase()).find() && !unmatches.matcher(input.toLowerCase()).find()) {
				finalProduct = product.getName();							
			}				
		}		
		return finalProduct;		
	}
	
	public static void main(String[] args) throws IOException {
		DeviceList dlist = new DeviceList();
		/**for(Device d:dlist.getProductList()){
			if(d.getType().equals("unmatch")){
				System.out.println(d.getUnmatchPattern() +" ; "+d.getType());
			} else
			System.out.println(d.getSearchPattern()+" ; "+d.getType());
		}**/
		String text = "Antelope OCX HD Master Clock 4th generation Acoustically Focused Clocking (AFC) jitter management Algorithmus Samplerates von bis zu 768 kHz ?ber W";
		
		System.out.println(dlist.getNameFromStr(text) + dlist.getUnmatch());		 
	}

}
