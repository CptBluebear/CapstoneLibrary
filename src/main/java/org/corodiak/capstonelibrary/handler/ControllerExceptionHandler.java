package org.corodiak.capstonelibrary.handler;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.capstonelibrary.Exception.ForbiddenRequestException;
import org.corodiak.capstonelibrary.Exception.UnAuthorizeException;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "org.corodiak.capstonelibrary.controller")
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler({
		NoSuchElementException.class,
		MissingServletRequestParameterException.class
	})
	public ResponseModel parameterError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.message("요청에 필요한 파라미터가 비어있거나 적절하지 않습니다.")
			.build();
		response.setStatus(400);
		return responseModel;
	}

	@ExceptionHandler({
		IOException.class,
		MaxUploadSizeExceededException.class
	})
	public ResponseModel fileUploadError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		e.printStackTrace();
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.message("파일 업로드에 오류가 발생했습니다.")
			.build();
		response.setStatus(400);
		return responseModel;
	}

	@ExceptionHandler(UnAuthorizeException.class)
	public ResponseModel unAuthorizeRequestError(HttpServletRequest request, HttpServletResponse response,
		Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.UNAUTHORIZED)
			.message("인증되지 않은 사용자입니다.")
			.build();
		response.setStatus(401);
		return responseModel;
	}

	@ExceptionHandler(ForbiddenRequestException.class)
	public ResponseModel forbiddenRequestError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.FORBIDDEN)
			.message("해당 요청에 권한이 없습니다.")
			.build();
		response.setStatus(403);
		return responseModel;
	}

	@ExceptionHandler(Exception.class)
	public ResponseModel unExceptedError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error(e.toString());
		e.printStackTrace();
		response.setStatus(500);
		return ResponseModel.builder()
			.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
			.message("예기치 못한 오류 발생")
			.build();
	}
}
