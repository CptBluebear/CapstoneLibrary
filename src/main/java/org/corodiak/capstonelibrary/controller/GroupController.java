package org.corodiak.capstonelibrary.controller;

import org.corodiak.capstonelibrary.service.GroupService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

	@Autowired
	GroupService groupService;

	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public ResponseModel saveGroup(
		@RequestParam(value = "name") String name,
		@RequestParam(value = "thumbnail") String thumbnail,
		@RequestParam(value = "authenticationCode") String authenticationCode,
		@RequestParam(value = "isOpen") boolean isOpen
	) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Group group = Group.builder()
			.name(name)
			.thumbnail(thumbnail)
			.authenticationCode(authenticationCode)
			.isOpen(isOpen)
			.build();
		groupService.save(group);

		ResponseModel responseModel = new ResponseModel();
		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("요청에 성공하였습니다.");

		return responseModel;
	}

	/*@RequestMapping(value = "/group/list", method = RequestMethod.GET)
	public ResponseModel getAllGroupList() {
		System.out.println(groupService.findAll());
		ResponseModel responseModel = new ResponseModel();
		responseModel.addData("모임 목록", groupService.findAll());
		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("요청에 성공하였습니다.");

		return responseModel;
	}

	@RequestMapping(value = "/group/publicList", method = RequestMethod.GET)
	public ResponseModel getPublicGroupList() {
		System.out.println(groupService.getPublicGroupList());
		ResponseModel responseModel = new ResponseModel();
		responseModel.addData("공개 모임 목록", groupService.getPublicGroupList());
		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("요청에 성공하였습니다.");

		return responseModel;
	}

	@RequestMapping(value = "/group/{idx}", method = RequestMethod.GET)
	public ResponseModel getGroup(@PathVariable(value = "idx") Long idx){
		ResponseModel responseModel = new ResponseModel();
		responseModel.addData("모임 정보", groupService.getById(idx));
		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("요청에 성공하였습니다.");

		return responseModel;
	}*/

	@RequestMapping(value = "/group/{idx}", method = RequestMethod.DELETE)
	public ResponseModel deleteGroup(@PathVariable(value = "idx") Long idx){
		groupService.getById(idx);
		groupService.deleteById(idx);

		ResponseModel responseModel = new ResponseModel();
		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("요청에 성공하였습니다.");

		return responseModel;
	}
}
