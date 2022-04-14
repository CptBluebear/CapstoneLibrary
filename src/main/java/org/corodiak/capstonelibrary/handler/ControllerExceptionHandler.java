package org.corodiak.capstonelibrary.handler;

import java.util.NoSuchElementException;

import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "org.corodiak.capstonelibrary.controller")
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler({
		NoSuchElementException.class
	})
	public ResponseModel parameterError(Exception e) {
		ResponseModel responseModel = ResponseModel.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.message("요청에 필요한 파라미터가 비어있거나 적절하지 않습니다.")
			.build();

		return responseModel;
	}

	@ExceptionHandler(Exception.class)
	public ResponseModel unExceptedError(Exception e) {
		log.error(e.toString());
		return ResponseModel.builder()
			.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
			.message("예기치 못한 오류 발생")
			.build();
	}
}
