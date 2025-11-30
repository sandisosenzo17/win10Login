
import java.io.FileWriter;

/*
 * Class contains features for an app to automatically shutdown a computer when an     unauthorised individual uses it after logging in.
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
		this.timer = 15;
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
		WindowsLogin WL = new WindowsLogin("I'm Here");
		WL.countdown();
	}
	
	// Detect password successful login
	
	
	
	// Setup countdown timer
	public boolean countdown() {
		try {
			for(int t = this.timer; t >= 0; t--) {
				Thread.sleep(1000); // Delay the program for a second
				
				// Make a file every second on desktop
				FileWriter fw = null;
				try {
					// Name each file with the number of the current second
					fw = new FileWriter("C:\\Users\\Sandiso\\Desktop\\" + t + ".txt", true);
					
					// Store the number of the second in the file
					fw.write(t + "\n");
					
					fw.close();
				}catch(Exception e) {
					System.out.println("Error! " + e.getMessage());
					return false;
				}
			}
			
		}catch(InterruptedException ie) {
			return false;
		}
		
		return true;
	}
	
	// Look for the specific folder on desktop
	
	// Setup the shutdown procedure on failed attempt
}




