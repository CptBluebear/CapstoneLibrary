package org.corodiak.capstonelibrary.controller;

import java.util.List;

import org.corodiak.capstonelibrary.auth.util.AuthUtil;
import org.corodiak.capstonelibrary.service.UserService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@RequestMapping(value = {"/info"}, method = RequestMethod.GET)
	public ResponseModel getUserInfo() {
		Long seq = AuthUtil.getAuthenticationInfoSeq();

		String nickname = userService.findNicknameByUserSeq(seq);
		List<String> emails = userService.findEmailByUserSeq(seq);

		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("nickname", nickname);
		responseModel.addData("emails", emails);

		return responseModel;
	}

}
