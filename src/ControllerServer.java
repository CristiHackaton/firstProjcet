import java.io.IOException;

import com.app.gui.LoginScreen;
import com.app.service.ServerConnection;


public class ControllerServer {

	public static void main(String[] args) {
//		LoginScreen log=new LoginScreen();
//		log.setVisible(true);
		try {
			ServerConnection.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
