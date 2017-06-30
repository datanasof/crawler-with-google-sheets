package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import googleSheetsManager.Credentials;
import googleSheetsManager.GsheetsManager;

public class ProductDescription {
		
	private Map<String, Integer> thInerface = new HashMap<String, Integer>(); 
	private Map<String, Integer> usbInerface = new HashMap<String, Integer>(); 
	private Map<String, Integer> clock = new HashMap<String, Integer>(); 
	private Map<String, Integer> preamp = new HashMap<String, Integer>(); 
	private Map<String, Integer> converter = new HashMap<String, Integer>(); 
	private Map<String, Integer> asigProcessor = new HashMap<String, Integer>(); 
	private Map<String, Integer> fx = new HashMap<String, Integer>(); 
	
	public ProductDescription() throws IOException{
		String spreadsheetId = Credentials.infoSpreadsheetId;
	    String range = "qualify description!A2:C8";
	    GsheetsManager manager = new GsheetsManager();
	    List<List<Object>> values = manager.readSheet(spreadsheetId, range);
	    readQualifyValues(values);
	}
	
	private void readQualifyValues(List<List<Object>> values){
		if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } 
		else {
        	for (List<?> row : values) {
        		try {
        			String deviceType = (String) row.get(0);
        			switch(deviceType){
        			case "thunderbolt interface": buildMap(thInerface, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;						
        			case "usb interface": buildMap(usbInerface, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;
        			case "clock": buildMap(clock, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;
        			case "preamp": buildMap(preamp, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;
        			case "converter": buildMap(converter, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;
        			case "analogsignal processor": buildMap(asigProcessor, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;
        			case "fx": buildMap(fx, splitString((String) row.get(1)), splitInt((String) row.get(2)));
        			break;       			
        			}        			
        		} catch (Exception e) {
        			System.out.println("No data found.");
        			break;
        		}       	  
        	} 
        }        
	}
	
	private List<String> splitString(String input){
		List<String> output = Arrays.asList(input.split(","));
		return output;
	}
	
	private List<Integer> splitInt(String input){
		List<String> out = splitString(input);
		List<Integer> output = new ArrayList<Integer>();
		for(String value: out) {
		    output.add(Integer.parseInt(value));
		}
		return output;
	}
	
	private void buildMap(Map<String, Integer> type, List<String> words, List<Integer> points){
		for(int i=0; i<words.size(); i++){
			type.put(words.get(i), points.get(i));
		}
	}
	
	//to add description qualification method (string type, string link) return good/ok/not enough
	
	//to add FX descr. qualification method (string type, string link) return good/ok/not enough
	
	public static void main(String[] args) throws IOException {
		ProductDescription product = new ProductDescription();
		System.out.println(product.thInerface);
		System.out.println(product.usbInerface);
	}
	
}
