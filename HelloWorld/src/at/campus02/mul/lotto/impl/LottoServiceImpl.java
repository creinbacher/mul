package at.campus02.mul.lotto.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.jws.WebService;

import at.campus02.mul.lotto.LottoService;

@WebService(endpointInterface = "at.campus02.mul.lotto.LottoService")
public class LottoServiceImpl implements LottoService {

	@Override
	public String predictNumbers() {
		Random random = new Random();
		Set<Integer> numbers= new HashSet<Integer>();
		while(numbers.size()<7) {
			numbers.add(random.nextInt(45)+1);
		}
		StringBuilder result = new StringBuilder();
		numbers.stream().sorted().forEach(number -> result.append(number).append(" "));
		System.out.println("predicted numbers: "+result);
		return result.toString();
	}

}
