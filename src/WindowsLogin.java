
/*
 * Class contains features for an app to automatically shutdown a computer when an     unauthorised individual uses it after typing in the password.
 * A countdown of 15 seconds will allow the user to quickly create a folder on desktop.
 * The app continuously looks for this folder in the 15s timeframe.
 * If it is found, the user can continue to use the device else it shuts down.
 * @author SS Yali
 */
public class WindowsLogin {
	private String foldername;
	
	public WindowsLogin(String fd) {
		this.foldername = fd;
	}
	
	public void setFoldername(String fd) {
		this.foldername = fd;
	}
	public String getFoldername() {
		return this.foldername;
	}
	
	public static void main(String[] args) {
		WindowsLogin WL = new WindowsLogin("I'm Here");
		System.out.println(WL.getFoldername());
	}
	
	// Detect password successful login
	
	// Setup countdown timer
	
	// Look for the specific folder on desktop
	
	// Setup the shutdown procedure on failed attempt
}
