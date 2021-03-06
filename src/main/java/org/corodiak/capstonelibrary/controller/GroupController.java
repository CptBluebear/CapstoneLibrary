package org.corodiak.capstonelibrary.controller;

import java.util.List;

import org.corodiak.capstonelibrary.Exception.DuplicatedDataException;
import org.corodiak.capstonelibrary.Exception.ForbiddenRequestException;
import org.corodiak.capstonelibrary.Exception.SearchResultNotExistException;
import org.corodiak.capstonelibrary.auth.util.AuthUtil;
import org.corodiak.capstonelibrary.service.GroupService;
import org.corodiak.capstonelibrary.service.GroupUserService;
import org.corodiak.capstonelibrary.service.LocationService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.vo.GroupVo;
import org.corodiak.capstonelibrary.type.vo.UserVo;
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
	private final LocationService locationService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel groupAdd(
		@RequestParam("name") String name,
		@RequestParam("isOpen") boolean isOpen,
		@RequestParam("thumbnail") String thumbnail,
		@RequestParam("description") String description,
		@RequestParam("longitude") double longitude,
		@RequestParam("latitude") double latitude
	) {
		long userSeq = AuthUtil.getAuthenticationInfoSeq();
		groupService.addGroup(name, isOpen, thumbnail, description, userSeq, longitude, latitude);
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
	public ResponseModel openGroupList(
		@RequestParam(value = "start", required = false, defaultValue = "0") long start,
		@RequestParam(value = "display", required = false, defaultValue = "20") long display
	) {
		List<GroupVo> groupList = groupService.findOpenGroup(start, display);
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

	@RequestMapping(value = "/search/location", method = RequestMethod.GET)
	public ResponseModel groupSearchByKeywordAndLocation(
		@RequestParam("keyword") String keyword,
		@RequestParam("longitude") double longitude,
		@RequestParam("latitude") double latitude,
		@RequestParam(value = "distance", required = false, defaultValue = "3000") int distance
	) {
		List<GroupVo> groupVoList = groupService.searchGroupByKeywordAndLocation(keyword, longitude, latitude,
			distance);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("groupList", groupVoList);
		return responseModel;
	}

	@RequestMapping(value = "/authcode", method = RequestMethod.GET)
	public ResponseModel groupAuthenticationCodeGet(
		@RequestParam("groupSeq") Long groupSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		String authenticationCode = groupService.getAuthenticationCode(groupSeq, userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("authenticationCode", authenticationCode);
		return responseModel;
	}

	@RequestMapping(value = "/signed", method = RequestMethod.GET)
	public ResponseModel groupCheckUserIsSigned(
		@RequestParam("groupSeq") Long groupSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		boolean result = groupUserService.checkUserIsSignedGroup(userSeq, groupSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("result", result);
		return responseModel;
	}

	@RequestMapping(value = "/block", method = RequestMethod.DELETE)
	public ResponseModel blockUser(
		@RequestParam("groupSeq") Long groupSeq,
		@RequestParam("blockUserSeq") Long blockUserSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		if (!groupUserService.findByUserSeqAndGroupSeq(blockUserSeq, groupSeq)) {
			throw new SearchResultNotExistException();
		} else {
			GroupVo.GroupVoWithAdmin group = (GroupVo.GroupVoWithAdmin)groupService.findBySeq(groupSeq);
			if (group.getAdmin().getSeq() == userSeq) {
				groupUserService.removeGroupUser(blockUserSeq, groupSeq);
			} else {
				throw new ForbiddenRequestException();
			}
		}

		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/myGroup", method = RequestMethod.GET)
	public ResponseModel myGroup() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<GroupVo> groupList = groupUserService.findByUserSeq(userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("groupList", groupList);
		return responseModel;
	}
}
