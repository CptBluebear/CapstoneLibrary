package org.corodiak.capstonelibrary;

import java.util.Random;

import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

class CapstoneLibraryApplicationTests {

	@Test
	void contextLoads() {
		Random random = new Random();
		RandomString rs = new RandomString(10, random);
		System.out.println(rs.nextString());

		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("data1", "SAMPLE");
		responseModel.addData("random", random);
		System.out.println(responseModel.toJson());
	}

}
