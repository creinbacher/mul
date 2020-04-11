package at.campus02.mul.hallo;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

public class TestWebserver {

	public static void main(String[] args) {
		String url = "http://localhost:8889/hellojaxws/hallo";
	    System.out.println("Starting server on "+url);
	    Endpoint endpoint = Endpoint.publish( url, new HalloWeltImpl() );
		System.out.println("Server running on "+url);
		JOptionPane.showMessageDialog(null, "Server beenden");
		endpoint.stop();
		System.out.println("Server stopped");
	}
	
}
