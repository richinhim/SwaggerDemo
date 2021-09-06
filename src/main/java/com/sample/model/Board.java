package com.sample.model;

import java.beans.ConstructorProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class Board {
	
	@ApiModelProperty(name = "title", dataType= "int", example = "\"1\"")
	private int boardNo;
	
	@ApiModelProperty(name = "title", dataType= "String", example = "\"1번글\"")
	private String title;
	
	@ApiModelProperty(name = "content", dataType= "String", example = "\"1번글 내용\"")
	private String content;
	
	public Board() {}
	
	@ConstructorProperties({"boardNo", "title", "content"})
	public Board(int boardNo, String title, String content) {
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
	}
	

}
