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
		public final String[] unmatch; 
	
	  ProductList(String main, String second, String abbr) {
	    this.main = main;
	    this.second = second;
	    this.abbr = abbr;
	    this.unmatch = new String[] {"bundle","b-stock","bag","rack"};
	  }
	
	  public String getText() {
		  if(this.abbr.equals("plus")){
			  String abbr = "+";
			  return this.main+this.second+abbr;
		  } 
	    return this.main+this.second+this.abbr;
	  }
	  
	  public static String buildPatternString(String[] matchString){
		  StringBuilder sb = new StringBuilder(10);
		  for(String match: matchString){
			  String added = String.format("(?=.*%s)", match);
			  sb.append(added);
		  }		  
		  return sb.toString();
	  }
	
	  public static String fromString(String text) {
		  String finalProduct = null;
		 		  
	    for (ProductList product : ProductList.values()) {
	      if (text.contains(product.main) && text.contains(product.second) && text.contains(product.abbr) && !text.contains("b-stock") && !text.contains("bundle")) {
	        finalProduct = product.getText();
	      }
	    }
	    return finalProduct;
	  }
	  
	public static String fromString2(String text) {
		String finalProduct = null;		
		
		for (ProductList product : ProductList.values()) {
			String[] productName = new String[]{product.main.toLowerCase(),product.second.toLowerCase(),product.abbr.toLowerCase()};
			Pattern matches = Pattern.compile(buildPatternString(productName));
			Pattern unmatches = Pattern.compile(buildPatternString(product.unmatch));
			if (matches.matcher(text.toLowerCase()).find()) {
				finalProduct = product.getText();							
			}			
		}
		return finalProduct;
	}
	  public static void main(String[] args) {
		  String text = "Antelope zen Audio Studio PortableplusInterface B-stock ";
		  System.out.println(ProductList.fromString2(text));
		  
		
	  }
	  
}
