package org.corodiak.capstonelibrary.controller;

import java.util.List;

import org.corodiak.capstonelibrary.Exception.DuplicatedDataException;
import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.auth.util.AuthUtil;
import org.corodiak.capstonelibrary.service.GroupService;
import org.corodiak.capstonelibrary.service.GroupUserService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.corodiak.capstonelibrary.type.vo.UserVo;
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
	private final GroupUserService groupUserService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel groupAdd(
		@RequestParam("name") String name,
		@RequestParam("isOpen") boolean isOpen,
		@RequestParam("thumbnail") String thumbnail,
		@RequestParam("description") String description,
		@RequestParam("longtitude") double longtitude,
		@RequestParam("latitude") double latitude
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		groupService.addGroup(name, isOpen, thumbnail, description, userSeq, longtitude, latitude);
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

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseModel groupJoin(
		@RequestParam(name = "groupSeq", required = false, defaultValue = "-1") long groupSeq,
		@RequestParam(name = "authenticationCode", required = false, defaultValue = "") String authenticationCode
	) throws DuplicatedDataException {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();

		if (groupSeq == -1) {
			groupSeq = groupService.findByAuthenticationCode(authenticationCode);
			if (groupSeq != -1) {
				groupUserService.addGroupUser(groupSeq, userSeq);
			}
			throw new SearchResultNotExistException();
		} else {
			if (groupUserService.findByUserSeqAndGroupSeq(userSeq, groupSeq)) {
				throw new DuplicatedDataException();
			} else {
				groupUserService.addGroupUser(groupSeq, userSeq);
			}
		}

		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/resign", method = RequestMethod.DELETE)
	public ResponseModel groupResign(
		@RequestParam(name = "groupSeq") long groupSeq
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		if (!groupUserService.findByUserSeqAndGroupSeq(userSeq, groupSeq)) {
			throw new SearchResultNotExistException();
		} else {
			groupUserService.removeGroupUser(userSeq, groupSeq);
		}

		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/authorize", method = RequestMethod.PATCH)
	public ResponseModel authrizeAdmin(
		@RequestParam(name = "groupSeq") Long groupSeq,
		@RequestParam(name = "userSeq") Long userSeq
	) {
		groupService.authorizeAdmin(groupSeq, userSeq);

		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ResponseModel joinedUserListGet(
		@RequestParam("groupSeq") Long groupSeq
	) {
		List<UserVo> userList = groupUserService.findUserByGroupSeq(groupSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("userList", userList);
		return responseModel;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseModel groupSearch(
		@RequestParam("keyword") String keyword
	) {
		List<GroupVo> groupList = groupService.searchGroup(keyword);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("groupList", groupList);
		return responseModel;
	}
}
