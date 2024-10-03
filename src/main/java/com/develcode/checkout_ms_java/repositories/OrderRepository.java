package com.develcode.checkout_ms_java.repositories;

import com.develcode.checkout_ms_java.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jean Paul - <jeanpaulwebb@gmail.com>
 * @date 03/10/2024
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
