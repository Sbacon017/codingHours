package trackinghours.hourtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HourtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HourtrackerApplication.class, args);
		home();
	}
	
	public static void home() {
        HourTrackerUI ui = new HourTrackerUI();
        ui.start();
	}
}
