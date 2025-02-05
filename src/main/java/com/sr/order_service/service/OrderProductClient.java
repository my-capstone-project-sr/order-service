// package com.sr.order_service.service;

// import org.springframework.cloud.openfeign.FeignClient;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// import com.sr.order_service.pojo.OrderProductPojo;



// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// import com.sr.order_service.pojo.OrderProductPojo;

// import java.util.List;

// @FeignClient(name = "order-product-service", url = "http://localhost:8003/api/orderproduct")
// public interface OrderProductClient {
//     @GetMapping("/by-order/{orderId}")
//     List<OrderProductPojo> getAllOrderProductsByOrderId(@PathVariable int orderId);
// }

package com.sr.order_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sr.order_service.pojo.OrderProductPojo;

import java.util.List;

@FeignClient(name = "order-product-service", url = "http://localhost:8003/api/orderproduct")
public interface OrderProductClient {
    @GetMapping("/by-order/{orderId}")
    List<OrderProductPojo> getAllOrderProductsByOrderId(@PathVariable int orderId);
}