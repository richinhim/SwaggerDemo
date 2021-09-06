package com.sample.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.model.Board;
import com.sample.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "BoardController V1")
@RequestMapping("/v1/api")
public class BoardControllerV1 {
	
	
	@Autowired
	BoardService boardService;
	
	@ApiOperation(value = "Board 전체 목록", notes = "Board 전체 목록을 조회")
	@GetMapping(value = "/board")
	public ResponseEntity<List<Board>> getAllMembers() {
		return new ResponseEntity<>(boardService.getAllBoards(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Board 조회", notes ="BoardNo에 해당하는 특정 글 조회")
	@GetMapping(value = "/board/{boardNo}")
	public ResponseEntity<Board> getBoardByNo(
			@ApiParam(value="글 번호", required=true) @PathVariable("boardNo") String boardNo){
		
		int intBoardNo = Integer.parseInt(boardNo);
		
		Board board = boardService.getBoardByBoardNo(intBoardNo);
		
		if(board != null) {
			return new ResponseEntity<>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Board 추가", notes="새로운 Board를 추가")
	@ApiImplicitParams(
			@ApiImplicitParam(name= "board", value="Board의 model 형태에 해당하는 JSON 문자열(boardNo는 지우고 작성)", paramType = "body", dataType = "Board")
			)
	@PostMapping(value ="/board")
	public ResponseEntity<Board> insertBoard(@RequestBody String board) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Board o = mapper.readValue(board, Board.class);
			boardService.insertBoard(o);
			
			return new ResponseEntity<Board>(o, HttpStatus.OK);
		} catch(JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Board 수정", notes="boardNo에 해당하는 Board 데이터를 수정")
	@ApiImplicitParams(
			@ApiImplicitParam(name= "board", value="Board의 model 형태에 해당하는 JSON 문자열", paramType = "body", dataType = "Board")
			)
	@PutMapping(value ="/board")
	public ResponseEntity<Board> updateBoard(@RequestBody Board board) {
		
		if(boardService.updateBoard(board)) {
		
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "Board 삭제", notes="boardNo에 해당하는 Board 데이터를 삭제")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "성공~~!" ),
			@ApiResponse(code = 404, message = "없는 페이지" )
	})
	@DeleteMapping(value ="/board/{boardNo}")
	public ResponseEntity<Board> deleteBoardByBoardNo(@ApiParam(value="글 번호", required=true) @PathVariable("boardNo") String boardNo){
		
		int intBoardNo = Integer.parseInt(boardNo);
				
		try {
			if(boardService.deleteBoardByBoardNo(intBoardNo)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
