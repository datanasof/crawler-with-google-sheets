package main;

import java.io.IOException;
import java.util.List;
import com.google.api.services.sheets.v4.Sheets;


public class GsheetsManager {
	private static Sheets service;
	
	public GsheetsManager() throws IOException{
		GsheetsManager.service = GsheetsService.getSheetsService();
	}
	
	public static List<List<Object>> readSheet(String spreadsheetId,String range) throws IOException{
	    	return GsheetsService.readSheet(service, spreadsheetId, range);
	}
		    
	public static void writeSheet(String spreadsheetId,String range, List<List<Object>> values) throws IOException{
		GsheetsService.writeSheet(service, spreadsheetId, range, values);
	}
	    
	public static void addSheet(String spreadsheetId, String nameOfSheet){
		GsheetsService.addSheet(service, spreadsheetId, nameOfSheet);
	}

		
	public static void main(String[] args) throws IOException {
        String spreadsheetId = "1pJn9MCc7Ny8F8yKCstdi2Ut3ZMINXCWrJESoEcUzeuU";
        String range = "dealer1!A2:E5";
        String nameOfSheet = "hello";
        GsheetsManager manager = new GsheetsManager();
        List<List<Object>> values = manager.readSheet(spreadsheetId, range);
        spreadsheetId = "1-bnlxj4hG6CxPmSffkc3zJSLDolo_0jNaf6N-qKT4i0";
        manager.addSheet(spreadsheetId, nameOfSheet);
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } else {
          System.out.println("Name, Major");
          for (List<?> row : values) {
            // Print columns A and E, which correspond to indices 0 and 4.
            System.out.printf("%s, %s\n", row.get(0), row.get(4));
          }
        }
        
	}

}
