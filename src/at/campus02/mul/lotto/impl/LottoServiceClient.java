package at.campus02.mul.lotto.impl;

import at.campus02.mul.lotto.LottoService;
import at.campus02.mul.lotto.LottoService_Service;

public class LottoServiceClient {

	public static void main(String[] args) {
		System.err.println("Trying to predict numbers via LottoServiceClient");
		LottoService_Service lottoService  = new LottoService_Service();
		LottoService lottoServiceProxy = lottoService.getLottoServicePortBinding();
		String numbers = lottoServiceProxy.predictNumbers();
		System.err.println("Predicted numbers in LottoServiceClient: "+numbers);
	}
}
