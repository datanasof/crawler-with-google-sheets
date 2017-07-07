package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import googleSheetsManager.Credentials;
import googleSheetsManager.GsheetsManager;

public class SellerList {
	private List<Seller> slist;
	
	public SellerList() throws IOException{
		String spreadsheetId = Credentials.infoSpreadsheetId;
	    String range = "dealers!A2:B100";
	    GsheetsManager manager = new GsheetsManager();
	    List<List<Object>> values = manager.readSheet(spreadsheetId, range);
	    this.slist = readSellers(values);
	}
	
	private List<Seller> readSellers(List<List<Object>> values){
		List<Seller> sList = new ArrayList<Seller>();
		if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } 
		else {
        	for (List<?> row : values) {
        		try {
        			String name = (String) row.get(0);
        			String link = (String) row.get(1);
        			        			
        			Seller s = new Seller(name, link);
        			sList.add(s);
        		} catch (Exception e) {
        			System.out.println("No data found.");
        			break;
        		} 
        	}
        }
		return sList;                
	}
	
	public List<Seller> getList(){
		return slist;
	}
	
	public static void main(String[] args) throws IOException {
		SellerList s = new SellerList();
		for(Seller sell: s.getList()){
			System.out.println(sell.getName()+" ; "+sell.getSearchLink());
		}
	}
}
