
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * App to automatically shutdown a computer on an unauthorised login.
 * A countdown of 15 seconds will allow the user to quickly create a folder on desktop.
 * The app continuously looks for this folder in the 15s timeframe.
 * If it is found, the user can continue to use the device else it shuts down.
 * @author S.S. Yali
 */
public class WindowsLogin {
	//Properties of the app
	private String file_name;
	private Path file_path;
	private int timer;
	
	public WindowsLogin(String fd, int t) {
		this.file_name = fd;
		this.file_path = Paths.get(
			System.getProperty("user.home"),
			"Desktop",
			this.file_name
		);
		this.timer = t;
	}
	
	//Getters and Setters of the app properties
	public void setFoldername(String fd) {
		this.file_name = fd;
	}
	public String getFoldername() {
		return this.file_name;
	}
	public void setTimer(int t) {
		this.timer = t;
	}
	public int getTimer() {
		return this.timer;
	}
	
	public static void main(String[] args) {
		// Instance of the application on first launch
		WindowsLogin app = new WindowsLogin("a.txt", 14);
		
		// Handle different cases of the device triggers
		switch(args[0]){
			case "BOOT":
				app.onBoot();
				break;
			case "LOCK":
				app.onLock();
				break;
			case "UNLOCK":
				app.onUnlock();
				break;
		}
		
	}

	// Look for the specified file or folder on the desktop 
	public boolean lookup() {
		boolean found = false;
		int timer = this.timer;
		
		try {
			// Search for the file for a specified timer
			while(timer > 0 && !found) {
				
				if(Files.exists(file_path)) {
					// Stop checking if the file is found
					found = true;
					break;
				}
				
				timer--;
				
				Thread.sleep(1000); // Delay the program for a second
			}		

		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
			return false;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return found;
	}
	
	// Operations for device boot (device turning on)
	public void onBoot() {
		if(Files.exists(file_path)) {
			
			//Immediately delete the file/folder before log in is attempted
			try {
				Thread.sleep((long) 1700);
				
				Files.delete(file_path);
			}catch(InterruptedException ie) {
				System.out.println(ie.getMessage());
			}catch(DirectoryNotEmptyException dne) {
				System.out.println(dne.getMessage());
			}catch(NoSuchFileException nsfe) {
				System.out.println(nsfe.getMessage());
			}catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}
				
		}
	}
	
	// Operations to handle file/folder when device unlocks
	public void onUnlock() {
		
		// If the countdown finishes before the file is found, shutdown the device
		if(!lookup()) {
			try {
				// Close all apps without notification on shutdown
				new ProcessBuilder("shutdown", "/p", "/f").start();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//Operations to handle file/folder when device locks
	public void onLock() {
		if(Files.exists(file_path)) {
			
			//Immediately delete the file/folder prior the next login attempt
			try {
				Files.delete(file_path);
			}catch(DirectoryNotEmptyException dne) {
				System.out.println(dne.getMessage());
			}catch(NoSuchFileException nsfe) {
				System.out.println(nsfe.getMessage());
			}catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}
				
		}
	}
	
}




