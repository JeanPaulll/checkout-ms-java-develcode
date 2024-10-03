package com.develcode.checkout_ms_java.controllers;

import com.develcode.checkout_ms_java.dtos.OrderRecordDto;
import com.develcode.checkout_ms_java.models.OrderModel;
import com.develcode.checkout_ms_java.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jean Paul - <jeanpaulwebb@gmail.com>
 * @date 03/10/2024
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody @Valid OrderRecordDto orderRecordDto) {
        var orderModel = new OrderModel();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        OrderModel created = orderService.createOrder(orderModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrder(@PathVariable Long id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @description Retorna 204 No Content se não houver pedidos
     */
    @GetMapping("/")
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        List<OrderModel> orders = orderService.findAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }
}
