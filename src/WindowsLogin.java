
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

/*
 * Class contains features for an app to automatically shutdown a computer when an unauthorised individual uses it after logging in.
 * A countdown of 15 seconds will allow the user to quickly create a folder on desktop.
 * The app continuously looks for this folder in the 15s timeframe.
 * If it is found, the user can continue to use the device else it shuts down.
 * @author SS Yali
 */
public class WindowsLogin {
	//Properties of the app
	private String foldername;
	private int timer;
	
	public WindowsLogin(String fd) {
		this.foldername = fd;
		this.timer = 10;
	}
	
	//Getters and Setters of the app properties
	public void setFoldername(String fd) {
		this.foldername = fd;
	}
	public String getFoldername() {
		return this.foldername;
	}
	public void setTimer(int t) {
		this.timer = t;
	}
	public int getTimer() {
		return this.timer;
	}
	
	public static void main(String[] args) {
		(new WindowsLogin("I'm Here")).countdown();
	}
	
	// Detect password successful login
	
	
	
	// Setup countdown timer
	public boolean countdown() {
		boolean found = false;
		int t = this.timer;
		
		try {
			File f = new File("C:\\Users\\Sandiso\\Desktop\\a.txt");
			
			FileWriter fw = new FileWriter("C:\\Users\\Sandiso\\Desktop\\answer.txt", true);
			
			while(t >= 0 && !found) {
				t--;
				if(f.exists()) {
					found = true;
					break;
				}
				
				Thread.sleep(1000); // Delay the program for a second
			}
			
			if(found) {
				fw.write("Found on: " + LocalDateTime.now() + "\n");
			}else {
				fw.write("Not found on: " + LocalDateTime.now() + "\n");
			}

			fw.close();

		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
			return false;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	// Look for the specific folder on desktop
	
	// Setup the shutdown procedure on failed attempt
}




