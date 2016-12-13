package inpe.br.image;

import java.util.Map;

public class Image {
	
	private String name;
	private String directory;
	private Map<String, Integer>  date;
	private Map<String, Integer> time;
 	private String proceded;
	
	//E se o mes for maior que 12 ou menos que 1? dia tbm?
	public Image(String name, String directory, int hour, int minute, int second, int mili, 
			int day, int mounth, int year, String proceded){
		
		this.setName(name);
		this.setDirectory(directory);
		this.time.put("hour", hour);
		this.time.put("minute", minute);
		this.time.put("second", second);
		this.time.put("mili", mili);
		this.date.put("day",day );
		this.date.put("mounth",mounth );
		this.date.put("year", year);
		this.setProceded(proceded);
		
	}
	
	public int getDay(){
		return date.get("day");
	}

	public int getMounth(){
		return date.get("mounth");
	}
	
	public int getYear(){
		return date.get("year");
	}
	
	public int getHour(){
		return time.get("hour");
	}
	
	public int getMinute(){
		return time.get("minute");
	}
	
	public int getSecond(){
		return time.get("second");
	}
	
	public int getMili(){
		return time.get("mili");
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDirectory() {
		return directory;
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public String getProceded() {
		return proceded;
	}


	public void setProceded(String proceded) {
		this.proceded = proceded;
	}
	
	

}
