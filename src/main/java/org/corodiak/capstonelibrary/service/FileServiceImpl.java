package org.corodiak.capstonelibrary.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String saveFile(MultipartFile multipartFile, String path) throws IOException {
		String ext = multipartFile.getOriginalFilename()
			.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
		String newName = System.nanoTime() + "-" + UUID.randomUUID().toString() + "." + ext;
		File file = new File(path + File.separator + newName);
		multipartFile.transferTo(file);
		return newName;
	}
}
