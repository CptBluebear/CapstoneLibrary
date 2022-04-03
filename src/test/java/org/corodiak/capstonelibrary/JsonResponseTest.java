package org.corodiak.capstonelibrary;

import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

public class JsonResponseTest {

	@Test
	public void test() {
		ResponseModel responseModel = new ResponseModel();

		String tokenData = "123123sdkljashfdlidsakfjis";

		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("Token Data");
		responseModel.addData("token", tokenData);

		System.out.println(responseModel.toJson());

	}

}
