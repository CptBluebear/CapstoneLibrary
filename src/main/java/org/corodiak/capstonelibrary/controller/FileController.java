package org.corodiak.capstonelibrary.controller;

import java.io.IOException;

import org.corodiak.capstonelibrary.service.FileService;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/image")
@RequiredArgsConstructor
public class FileController {

	@Value("${resource.image.realpath}")
	private String imagePath;

	@Value("${resource.image.path}")
	private String imageUrlPath;

	private final FileService fileService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseModel save(
		@RequestParam("file") MultipartFile multipartFile
	) throws IOException {
		String fileName = fileService.saveFile(multipartFile, imagePath);
		String fileUrl = imageUrlPath + "/" + fileName;
		ResponseModel responseModel = ResponseModel.builder().build();
		responseModel.addData("url", fileUrl);
		return responseModel;
	}

}
