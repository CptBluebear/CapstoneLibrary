package org.corodiak.capstonelibrary.controller;

import java.util.NoSuchElementException;

import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler({
		NoSuchElementException.class
	})
	public ResponseModel parameterError(Exception e) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
		responseModel.setMessage("요청에 필요한 파라미터가 비어있거나 적절하지 않습니다.");

		return responseModel;
	}
}
