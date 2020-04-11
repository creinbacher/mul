package at.campus02.mul.hallo;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HalloWelt {

	public String sayHallo(@WebParam (name="name") String name);
	
}
