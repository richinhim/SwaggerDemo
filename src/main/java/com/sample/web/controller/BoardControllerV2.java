package com.sample.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "BoardController V2")
@RequestMapping("/v2/api")
public class BoardControllerV2 {

	@ApiOperation(value = "exam", notes = "에러 메시지 ")
	@ApiResponses({
		 @ApiResponse(code = 200, message = "OK !!"),
         @ApiResponse(code = 500, message = "Internal Server Error !!"),
         @ApiResponse(code = 404, message = "Not Found !!")
	})
	@GetMapping(value = "/board")
    public Map<String, String> selectOneBoard(@ApiParam(value = "글 번호", required = true, example = "1") @RequestParam String no) {
        Map<String, String> result = new HashMap<>();
        result.put("author", "victolee");
        result.put("content", "V2 API Docs");
        return result;
    }
}
