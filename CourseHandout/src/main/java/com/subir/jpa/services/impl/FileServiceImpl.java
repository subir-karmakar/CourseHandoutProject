
package com.subir.jpa.services.impl;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.subir.jpa.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String name = file.getOriginalFilename();
	    String filePath = path + File.separator + name;
	    File f = new File(path);
	    if (!f.exists()) {
	        f.mkdir();
	    }
	    
	    byte[] content = file.getBytes();
	    Files.write(Paths.get(filePath), content);
	    
	    return name;
	}

	
}
