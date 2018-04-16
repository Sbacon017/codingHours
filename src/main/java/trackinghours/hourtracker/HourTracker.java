package trackinghours.hourtracker;


/*
 * Main controller object that operates on data.
 * 
 */
public class HourTracker {
	
	Hours codingHours, gamingHours;
	final int RATIO = 2;
	HoursFactory hourFact = new HoursFactory();
	
	/*
	 * Loads hours from data
	 * !!Not yet implemented, would like to use SQL
	 */
	public void loadHours(){
		Hours[] hourArray = hourFact.getHourObjects();
		this.codingHours = hourArray[0];
		this.gamingHours = hourArray[1];
	}
	
	/*
	 * Saves hours back to SQL
	 */
	public void saveHours() {
		Hours[] hourArray = {codingHours, gamingHours};
		hourFact.saveHours(hourArray);
	}
	
	//Add new hours spent coding
	public void addCodingHours(int numhours) {
		this.codingHours.setHours(getCodingHours() + numhours);
	}
	
	//Add new hours spent gaming
	public void addGamingHours(int numhours) {
		this.gamingHours.setHours(getGamingHours() + numhours);
	}
	
	//Get difference in coding hours
	public int getDifference() {
		return getCodingHours() - getGamingHours()*RATIO;
	}
	
	//Get hours of gaming earned
	public int getHoursEarned() {
		if (getDifference() >= 0) {
			return getDifference();
		}
		else {
			return Math.abs(getDifference());
		}
	}
	
	//Get gaming hours
	public int getGamingHours() {
		return this.gamingHours.getHours();
	}
	
	//Get coding hours
	public int getCodingHours() {
		return this.codingHours.getHours();
	}
	
	
	

}
