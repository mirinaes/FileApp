package com.web.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

	private int no; //번호
	private String nm1; // 원래 이름
	private String nm2; // 바뀌는 이름
	private String extension; // 확장자
	private String path; // 파일경로
	private String type; // 형식
	private boolean del; // 삭제 여부
	
}
