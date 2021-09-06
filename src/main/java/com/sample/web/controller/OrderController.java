package com.sample.web.controller;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.OrderRequest;
import com.sample.dto.OrderResponse;
import com.sample.entity.Order;
import com.sample.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "OrderController")
@RequestMapping("/v3/api")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
    @Autowired
    OrderService orderService;
    // DTO to Entity, Entity to DTO를 위해 ModelMapper 사용
    ModelMapper modelMapper = new ModelMapper();

    @ApiOperation(value="Order 정보 생성", notes="Order 정보, 수량, 상태를 입력해 Order 정보를 생성한다.")
    @PostMapping("/order")
    public int saveOrder(@RequestBody OrderRequest orderRequest) {
        // ModelMapper를 이용해 OrderRequest DTO의 값을 Order Entity로 복사한다.
        Order order = modelMapper.map(orderRequest, Order.class);
        return orderService.save(order);
    }

    @ApiOperation(value="Order 정보 변경", notes="Order 정보, 수량, 상태 변경 데이터를 통해 Order 정보를 변경한다.")
    @PutMapping("/order")
    public int changeOrder(@RequestBody OrderRequest orderRequest) {
        // ModelMapper를 이용해 OrderRequest DTO의 값을 Order Entity로 복사한다.
        Order order = modelMapper.map(orderRequest, Order.class);
        return orderService.modify(order);
    }

    @ApiOperation(value="Order 정보 삭제", notes="사용자 화면으로부터 주문 번호(Order id)를 입력 받아 삭제한다.")
    @DeleteMapping("/order/{id}")
    public int deleteOrder(String id) {
        return orderService.delete(id);
    }

    @ApiOperation(value="Order id로 Order 정보 조회", notes="Order 정보를 조회한다.")
    @GetMapping("/order/{id}")
    public OrderResponse getOrder(@PathVariable String id) {
        Order order = orderService.findById(id);
        // ModelMapper를 이용해 order entity의 값을 OrderResponse DTO로 변환한다.
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

        return orderResponse;
    }

    @ApiOperation(value="Order 리스트 조회", notes="등록된 Order 정보들을 조회한다.")
    @GetMapping("/order")
    public List<OrderResponse> getOrders() {
    	
    	logger.info("getOrders()");
    	
        // Persistence에서 List<Order>로 생성된 Entity 리스트를 화면으로 전달하기 위해 List<OrderResponse>로 변환
        List<OrderResponse> postDtoList = Arrays.asList(modelMapper.map(orderService.findAll(), OrderResponse[].class));
        return postDtoList;
    }
}
