import java.io.IOException;

import com.app.gui.LoginScreen;
import com.app.service.ServerConnection;


public class ControllerServer {

	public static void main(String[] args) {
		
		try {
			ServerConnection.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
