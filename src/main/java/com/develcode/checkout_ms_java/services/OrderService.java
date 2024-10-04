package com.develcode.checkout_ms_java.services;

import com.develcode.checkout_ms_java.models.OrderModel;
import com.develcode.checkout_ms_java.models.PaymentRequestModel;
import com.develcode.checkout_ms_java.models.PaymentResponseModel;
import com.develcode.checkout_ms_java.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

/**
 * @author Jean Paul - <jeanpaulwebb@gmail.com>
 * @date 03/10/2024
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${payment.gateway.url}")
    private String PAYMENT_GATEWAY_URL;

    @Transactional
    public OrderModel createOrder(OrderModel order) {
        order.setStatus("CRIADO");
        OrderModel savedOrder = orderRepository.save(order);
        PaymentResponseModel paymentResponse = processPayment(savedOrder);
        if ("SUCESSO".equals(paymentResponse.getStatus())) {
            savedOrder.setStatus("PAGO");
        } else {
            savedOrder.setStatus("FALHA");
        }
        return orderRepository.save(savedOrder);
    }

    /**
     * @description Criar o payload para o serviço de pagamento e envia requisição POST para o payment_gateway
     */
    private PaymentResponseModel processPayment(OrderModel order) {
       PaymentRequestModel paymentRequest = PaymentRequestModel.builder().orderId(order.getId()).build();
        try {
            PaymentResponseModel response = restTemplate.postForObject(PAYMENT_GATEWAY_URL, paymentRequest, PaymentResponseModel.class);
            return response != null
                    ? response
                    : PaymentResponseModel.builder()
                    .message("Nenhuma resposta!")
                    .build();
        } catch (Exception e) {
            return PaymentResponseModel.builder().success(false).message(e.getMessage()).build();
        }
    }

    public Optional<OrderModel> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<OrderModel> findAllOrders() {
        return orderRepository.findAll();
    }
}
