package crawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Description {
	private String type;
	private Map<String, Integer> wordsPoints;
	
	public Description(String type, String keyWords, String points){
		this.type = type;	
		this.wordsPoints = buildMap(splitString(keyWords), splitInt(points));
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
	
	private Map<String, Integer> buildMap(List<String> words, List<Integer> points){
		Map<String, Integer> output = new HashMap<String, Integer>();
		for(int i=0; i<words.size(); i++){
			try{
				output.put(words.get(i), points.get(i));
			} catch(Exception e){System.out.println(e);}
		}
		return output;
	}
	
	public String getType(){
		return type;
	}
	
	public Map<String, Integer> getWordsPoints(){
		return wordsPoints;
	}
	
	
}
