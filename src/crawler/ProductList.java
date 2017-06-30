package crawler;

import java.util.regex.Pattern;

public enum ProductList {
	
	  a("Goliath","",""),
	  b("Goliath","HD",""),
	  c("Orion","Studio",""),
	  d("Orion","Studio","HD"),
	  e("Orion","Studio","2017"),
	  f("Orion","32",""),
	  g("Orion","32","HD"),
	  h("Orion","32","+"),
	  i("Orion","32","plus"),
	  j("Zen","Studio",""),
	  k("Zen","Studio","+"),
	  l("Zen","Studio","plus"),
	  m("Zen","Tour",""),
	  n("Zen","Tour","2017"),
	  o("Live","Clock",""),
	  p("10","MX",""),
	  q("OCX","HD",""),
	  r("Pure","2",""),
	  s("MP","32",""),
	  t("MP","8d",""),
	  w("Satori","","");
	
		private final String main;
		private final String second;
		private final String abbr;
		public final String[] unmatch = new String[] {"bundle","b-stock","bag","rack", "used"}; 
	
	  ProductList(String main, String second, String abbr) {
	    this.main = main;
	    this.second = second;
	    this.abbr = abbr;
	    //this.unmatch = new String[] {"bundle","b-stock","bag","rack"};
	  }
	
	  private String getText() {
		  if(this.abbr.equals("plus")){
			  String abbr = "+";
			  return this.main+this.second+abbr;
		  } 
	    return this.main+this.second+this.abbr;
	  }
	  
	  private static String matchPatternString(String[] matchString){
		  StringBuilder sb = new StringBuilder(5);
		  for(String match: matchString){
			  String added = String.format("(?=.*%s)", match);
			  sb.append(added);
		  }		  
		  return sb.toString();		  
	  }
	  
	  private static String unmatchPatternString(String[] unmatchString){
		  StringBuilder sb = new StringBuilder(10);
		  sb.append("("); 
		  for(int i=0; i<unmatchString.length-1; i++){
			  String added = String.format("%s|", unmatchString[i]);
			  sb.append(added);
		  }		 
		  sb.append(unmatchString[unmatchString.length-1]+")"); 
		  return sb.toString();
	  }
	  
	public static String fromString(String text) {
		String finalProduct = null;		
		
		for (ProductList product : ProductList.values()) {
			String[] productName = new String[]{product.main.toLowerCase(),product.second.toLowerCase(),product.abbr.toLowerCase()};
			Pattern matches = Pattern.compile(matchPatternString(productName));
			Pattern unmatches = Pattern.compile(unmatchPatternString(product.unmatch));
			if (matches.matcher(text.toLowerCase()).find() && !unmatches.matcher(text.toLowerCase()).find()) {
				finalProduct = product.getText();							
			}			
		}
		return finalProduct;		
	}
	
	
	
	  public static void main(String[] args) {
		  String text = "Antelope zen Audio Studio PortableplusInterface use ";
		  System.out.println(ProductList.fromString(text));		 
		
	  }
	  
}
