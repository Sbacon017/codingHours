package trackinghours.hourtracker;


/*
 * Main controller object that operates on data.
 * 
 */
public class HourTracker {
	
	Hours codingHours, gamingHours, auxHours;
	final int RATIO = 2;
	final int AUXRATIO = 4;
	HoursFactory hourFact = new HoursFactory();
	
	/*
	 * Loads hours from data
	 * !!Not yet implemented, would like to use SQL
	 */
	public void loadHours(){
		Hours[] hourArray = hourFact.getHourObjects();
		this.codingHours = hourArray[0];
		this.gamingHours = hourArray[1];
		this.auxHours = hourArray[2];
	}
	
	/*
	 * Saves hours back to SQL
	 */
	public void saveHours() {
		Hours[] hourArray = {codingHours, gamingHours, auxHours};
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
	
	//Add new hours spent reading/learning about code
	public void addAuxHours(int numHours) {
		this.auxHours.setHours(getAuxHours() + numHours);
	}
	

	
	//Get hours of gaming earned
	public int getHoursEarned() {
		int hoursEarned = getCodingHours()/RATIO + getAuxHours()/AUXRATIO - getGamingHours();
		return hoursEarned;
	}
	
	//Get gaming hours
	public int getGamingHours() {
		return this.gamingHours.getHours();
	}
	
	//Get coding hours
	public int getCodingHours() {
		return this.codingHours.getHours();
	}
	
	public int getAuxHours() {
		return this.auxHours.getHours();
	}
	
	public void instantiateAux() {
		hourFact.instantiateAux();
	}
	
	

}
