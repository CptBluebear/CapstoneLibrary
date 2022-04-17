package org.corodiak.capstonelibrary.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String saveFile(MultipartFile multipartFile, String path) throws IOException;

}
