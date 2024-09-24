package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.mapper.FileDTO;
import com.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.components.FileComponent;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"http://localhost:3000"})
@Slf4j
@RestController
public class FileController {

	@GetMapping("/")
	public String index() {
	return "File Upload Server !!";
	}
	
	@Autowired
	private FileComponent fc;
	
	@Autowired
	private FileService fs;
//	
//	@PostMapping("/fileUpload")
//	public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file) {
//		log.info("file {}", file);
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		// state의 값을 상황에따라 true 또는 flase로 변경
//		int no = fs.save(file, fc.upload2(file));
//		FileDTO dto = fs.findByNo(no);
//		resultMap.put("state", true);
//		resultMap.put("url", dto.getPath());
//		    return resultMap;
//		}
//		
	@PostMapping("/filesUpload")
	public Map<String, Object> filesUpload(@RequestParam("file") MultipartFile[] files) {
		    return fs.filesUpload(files);
		}
	
	@GetMapping("/filesRead")
	public Map<String, Object> filesRead() {
		return fs.findAll();
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("url") String url) {
		return fc.getFile(url);
	}

}