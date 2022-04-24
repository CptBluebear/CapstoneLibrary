package org.corodiak.capstonelibrary.controller;

import java.util.List;

import org.corodiak.capstonelibrary.auth.util.AuthUtil;
import org.corodiak.capstonelibrary.service.GroupService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

	private final GroupService groupService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel groupAdd(
		@RequestParam("name") String name,
		@RequestParam("isOpen") boolean isOpen,
		@RequestParam("thumbnail") String thumbnail
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		groupService.addGroup(name, isOpen, thumbnail, userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel groupDelete(
		@PathVariable("seq") long seq
	) {
		groupService.removeGroup(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel openGroupList() {
		List<GroupVo> groupList = groupService.findOpenGroup();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("groupList", groupList);
		return responseModel;
	}

	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel groupGet(
		@PathVariable("seq") long seq
	) {
		GroupVo group = groupService.findBySeq(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("group", group);
		return responseModel;
	}
}
