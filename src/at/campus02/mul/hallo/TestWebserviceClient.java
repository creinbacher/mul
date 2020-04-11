package at.campus02.mul.hallo;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestWebserviceClient {
	
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8889/hellojaxws/hallo";
	      Service service = Service.create(
	            new URL( url + "?wsdl" ),
	            new QName( "http://hallo.mul.campus02.at/", "HalloWeltImplService" ) );
	      HalloWelt halloWelt = service.getPort( HalloWelt.class );
	      System.out.println( "\n" + halloWelt.sayHallo( args.length > 0 ? args[0] : "Test" ) );
	}
}
