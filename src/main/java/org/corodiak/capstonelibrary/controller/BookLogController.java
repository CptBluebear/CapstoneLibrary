package org.corodiak.capstonelibrary.controller;

import java.util.List;

import org.corodiak.capstonelibrary.auth.util.AuthUtil;
import org.corodiak.capstonelibrary.service.BookLogService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.etc.BookLogStatus;
import org.corodiak.capstonelibrary.type.vo.BookLogVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookLog")
public class BookLogController {

	private final BookLogService bookLogService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseModel bookLogAdd(
		@RequestParam("bookLogStatus") String bookLogStatus,
		@RequestParam("bookSeq") long bookSeq,
		@RequestParam("groupSeq") long groupSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		bookLogService.addBookLog(BookLogStatus.ofCode(bookLogStatus), userSeq, bookSeq, groupSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
	public ResponseModel bookLogDelete(
		@PathVariable("seq") long seq
	) {
		bookLogService.deleteBySeq(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		return responseModel;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel allBookLogList() {
		List<BookLogVo> bookLogList = bookLogService.findAll();
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookLogList", bookLogList);
		return responseModel;
	}

	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	public ResponseModel bookLogGet(
		@PathVariable("seq") long seq
	) {
		BookLogVo bookLog = bookLogService.findBySeq(seq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookLog", bookLog);
		return responseModel;
	}

	@RequestMapping(value = "/myBookLog", method = RequestMethod.GET)
	public ResponseModel myBookLogList() {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<BookLogVo> bookLogList = bookLogService.findByUserSeq(userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookLogList", bookLogList);
		return responseModel;
	}

	@RequestMapping(value = "/groupBookLog/{groupSeq}", method = RequestMethod.GET)
	public ResponseModel groupBookLogList(
		@PathVariable("groupSeq") Long groupSeq
	) {
		List<BookLogVo> bookLogList = bookLogService.findByGroupSeq(groupSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookLogList", bookLogList);
		return responseModel;
	}

	@RequestMapping(value = "/bookLog/{bookSeq}", method = RequestMethod.GET)
	public ResponseModel bookLogList(
		@PathVariable("bookSeq") Long bookSeq
	) {
		List<BookLogVo> bookLogList = bookLogService.findByBookSeq(bookSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookLogList", bookLogList);
		return responseModel;
	}

	@RequestMapping(value = "/userInGroupBookLog/{groupSeq}", method = RequestMethod.GET)
	public ResponseModel userInGroupBookLogList(
		@PathVariable("groupSeq") Long groupSeq
	) {
		Long userSeq = AuthUtil.getAuthenticationInfoSeq();
		List<BookLogVo> bookLogList = bookLogService.findByGroupAndUserSeq(groupSeq, userSeq);
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("bookLogList", bookLogList);
		return responseModel;
	}
}
