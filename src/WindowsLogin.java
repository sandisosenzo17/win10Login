
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Class contains features for an app to automatically shutdown a computer when an unauthorised individual uses it after logging in.
 * A countdown of 15 seconds will allow the user to quickly create a folder on desktop.
 * The app continuously looks for this folder in the 15s timeframe.
 * If it is found, the user can continue to use the device else it shuts down.
 * @author SS Yali
 */
public class WindowsLogin {
	//Properties of the app
	private String filename;
	private int timer;
	
	public WindowsLogin(String fd) {
		this.filename = fd;
		this.timer = 10;
	}
	
	//Getters and Setters of the app properties
	public void setFoldername(String fd) {
		this.filename = fd;
	}
	public String getFoldername() {
		return this.filename;
	}
	public void setTimer(int t) {
		this.timer = t;
	}
	public int getTimer() {
		return this.timer;
	}
	
	public static void main(String[] args) {
		(new WindowsLogin("a.txt")).countdown();
	}

	// Setup countdown timer
	public boolean countdown() {
		boolean found = false;
		int t = this.timer;
		
		try {
			Path file_path = Paths.get(
				System.getProperty("user.home"),
				"Desktop",
				this.filename
			);
			
			while(t >= 0 && !found) {
				t--;
				
				// Stop counting if file is found
				if(Files.exists(file_path)) {
					found = true;
					break;
				}
				
				Thread.sleep(1000); // Delay the program for a second
			}
			
			if(!found) {
				try {
					// Shutdown device without notifying and close all apps if file not found
					new ProcessBuilder("shutdown", "/p", "/f").start();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}


		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
			return false;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	// Setup the shutdown procedure on failed attempt
}




