package at.campus02.mul.hallo;

import javax.jws.WebService;

@WebService(endpointInterface = "at.campus02.mul.hallo.HalloWelt")
public class HalloWeltImpl implements HalloWelt {

	@Override
	public String sayHallo(String name) {
		System.out.println("recieved request for name "+name);
		return "Hallo "+name;
	}

}
