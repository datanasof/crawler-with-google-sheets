package googleSheetsManager;

import java.io.IOException;
import java.util.List;
import com.google.api.services.sheets.v4.Sheets;


public class GsheetsManager {
	private static Sheets service;
	
	public GsheetsManager() throws IOException{
		GsheetsManager.service = GsheetsService.getSheetsService();
	}
	
	public List<List<Object>> readSheet(String spreadsheetId,String range) throws IOException{
	    	return GsheetsService.readSheet(service, spreadsheetId, range);
	}
		    
	public void writeSheet(String spreadsheetId,String range, List<List<Object>> values) throws IOException{
		GsheetsService.writeSheet(service, spreadsheetId, range, values);
	}
	    
	public void addSheet(String spreadsheetId, String nameOfSheet){
		GsheetsService.addSheet(service, spreadsheetId, nameOfSheet);
	}
}
