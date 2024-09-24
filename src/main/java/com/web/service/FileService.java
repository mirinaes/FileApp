package com.web.service;

import com.web.components.FileComponent;
import com.web.mapper.FileDTO;
import com.web.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FileService {

    @Autowired
    private FileComponent fc;

    @Autowired
    private FileMapper fm;

    private int save(MultipartFile file, String url){
        log.info("File Service 왔다.");
        Map<String, Object> data = new HashMap<>();
        data = fc.setFile(file);
        FileDTO dto = new FileDTO().builder()
                .nm1(data.get("Name").toString())
                .nm2(data.get("NewName").toString())
                .extension(data.get("Extension").toString())
                .path(url)
                .type("image/png").build();
        fm.save(dto);
        return dto.getNo();
    }

    public Map<String, Object> filesUpload(MultipartFile[] files){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
		FileDTO dto = new FileDTO();
		resultMap.put("state", false);
	
		List<String> list = new ArrayList<>();
			for(int i = 0; i < files.length; i++) {
			dto = findByNo(save(files[i],fc.upload2(files[i])));
				list.add(dto.getPath());
			}
			if(list.size() > 0) {
				resultMap.put("state", true);
				resultMap.put("list", list);
				resultMap.put("comment", "저장 성공");
			}
        return resultMap;
    }

    public Map<String, Object> findAll(){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("state", false);
    	
    	List<String> list = new ArrayList<>();
    	list = fm.findAll();
    	if(list.size() > 0) {
    		resultMap.put("state", true);
    		resultMap.put("list", list);
    		}
        return resultMap;
    }
    
    public FileDTO findByNo(int no) {
    	return fm.findByNo(no);
    }
    
}