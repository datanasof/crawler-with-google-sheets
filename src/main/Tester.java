package main;

import java.io.IOException;
import java.util.List;

import crawler.Seller;
import googleSheetsManager.Credentials;
import googleSheetsManager.GsheetsManager;

public class Tester {
	
	public static void main(String[] args) throws IOException {
		GsheetsManager gmanager = new GsheetsManager();
		gmanager.addSheet(Credentials.reportSpreadsheetId, "Thomann");
		Seller seller = new Seller();
		String range = "GuitarCenter!A2:G24";
		List<List<Object>> values = gmanager.readSheet(Credentials.reportSpreadsheetId, range);
		try{
			for(List<Object> row:values){
				System.out.println(row);
			}
		} catch(Exception e){System.out.printf("New dealer \"%s\" reported", "Thomann");}
	}

}
