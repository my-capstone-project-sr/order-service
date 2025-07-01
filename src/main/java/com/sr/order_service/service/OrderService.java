package com.sr.order_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.order_service.dao.OrderRepo;
import com.sr.order_service.entity.OrderEntity;
import com.sr.order_service.pojo.OrderProductPojo;
import com.sr.order_service.pojo.ProductPojo;

@Service
public class OrderService {
	@Autowired
    OrderRepo orderRepository;
	
	//  @Autowired
	//     KeenService keenService;
<<<<<<< Updated upstream

	    // public OrderEntity completeOrder(OrderEntity order) {
	    //     OrderEntity savedOrder = orderRepository.save(order);

	    //     // Log order details to Keen.io
	    //     Map<String, Object> orderData = new HashMap<>();
	    //     orderData.put("orderId", savedOrder.getOrderId());
	    //     orderData.put("orderStatus", savedOrder.getOrderStatus());
	    //     orderData.put("orderDate", savedOrder.getOrderDate());
	    //     orderData.put("orderStoreId", savedOrder.getOrderStoreId());
	    //     orderData.put("orderUserId", savedOrder.getOrderUserId());
	    //     // orderData.put("totalAmount", savedOrder.getTotalAmount()); // Ensure OrderEntity has this field

	    //     keenService.logEvent("orders", orderData);

	    //     return savedOrder;
	    // }

    @Autowired
    ProductClient productClient;

    @Autowired
    OrderProductClient orderProductClient;

	    public OrderEntity completeOrder(OrderEntity order) {
            // Calculate the total amount
            double totalAmount = calculateTotalAmount(order);
            order.setTotalAmount(totalAmount);
        
            OrderEntity savedOrder = orderRepository.save(order);
        
            // Log order details to Keen.io
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("orderId", savedOrder.getOrderId());
            orderData.put("orderStatus", savedOrder.getOrderStatus());
            orderData.put("orderDate", savedOrder.getOrderDate());
            orderData.put("orderStoreId", savedOrder.getOrderStoreId());
            orderData.put("orderUserId", savedOrder.getOrderUserId());
            orderData.put("totalAmount", savedOrder.getTotalAmount());
        
            keenService.logEvent("orders", orderData);
        
            return savedOrder;
        }
        
        private double calculateTotalAmount(OrderEntity order) {
            // Implement your logic to calculate the total amount
            // For example, summing up the prices of all items in the order
            // double totalAmount = 0.0;
            // totalAmount = ...;
            // return totalAmount;
            List<OrderProductPojo> orderProducts = orderProductClient.getAllOrderProductsByOrderId(order.getOrderId());
                    double totalAmount = 0.0;
                    for (OrderProductPojo orderProduct : orderProducts) {
                        ProductPojo product = productClient.getProductById(orderProduct.getProductId());
                        totalAmount += orderProduct.getSalesQuantity() * product.getProductPrice();
                    }
                    return totalAmount;
        }
        // private double calculateTotalAmount(int orderId) {
        //             List<OrderProductPojo> orderProducts = orderProductClient.getAllOrderProductsByOrderId(orderId);
        //             double totalAmount = 0.0;
        //             for (OrderProductPojo orderProduct : orderProducts) {
        //                 ProductPojo product = productClient.getProductById(orderProduct.getProductId());
        //                 totalAmount += orderProduct.getSalesQuantity() * product.getProductPrice();
        //             }
        //             return totalAmount;
        //         }
=======

	    // public OrderEntity completeOrder(OrderEntity order) {
	    //     OrderEntity savedOrder = orderRepository.save(order);

	    //     // Log order details to Keen.io
	    //     Map<String, Object> orderData = new HashMap<>();
	    //     orderData.put("orderId", savedOrder.getOrderId());
	    //     orderData.put("orderStatus", savedOrder.getOrderStatus());
	    //     orderData.put("orderDate", savedOrder.getOrderDate());
	    //     orderData.put("orderStoreId", savedOrder.getOrderStoreId());
	    //     orderData.put("orderUserId", savedOrder.getOrderUserId());
	    //     // orderData.put("totalAmount", savedOrder.getTotalAmount()); // Ensure OrderEntity has this field

	    //     keenService.logEvent("orders", orderData);

	    //     return savedOrder;
	    // }
>>>>>>> Stashed changes
	
    public OrderEntity addOrder(OrderEntity neworder) {
        return orderRepository.saveAndFlush(neworder);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }
//
//    public List<ProductEntity> getProductsByUserId(int userId) {
//        return productRepository.findByUserId(userId);
//    }

    public Optional<OrderEntity> getAOrder(int orderId) {
		return orderRepository.findById(orderId);
	}
    
    public OrderEntity updateOrder(OrderEntity editorder) {
        return orderRepository.save(editorder);
    }

    public void deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
    }

}


// package com.sr.order_service.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.sr.order_service.dao.OrderRepo;
// import com.sr.order_service.entity.OrderEntity;
// import com.sr.order_service.pojo.OrderProductPojo;
// import com.sr.order_service.pojo.ProductPojo;

// @Service
// public class OrderService {

//     @Autowired
//     OrderRepo orderRepository;

//     @Autowired
//     OrderProductClient orderProductClient;

//     @Autowired
//     ProductClient productClient;

//     public OrderEntity addOrder(OrderEntity newOrder) {
//         double totalAmount = calculateTotalAmount(newOrder.getOrderId());
//         newOrder.setTotalAmount(totalAmount);
//         return orderRepository.saveAndFlush(newOrder);
//     }

//     public List<OrderEntity> getAllOrders() {
//         return orderRepository.findAll();
//     }

//     public OrderEntity getOrderById(int orderId) {
//         return orderRepository.findById(orderId)
//                 .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
//     }

//     public Optional<OrderEntity> getAOrder(int orderId) {
//         return orderRepository.findById(orderId);
//     }

//     public OrderEntity updateOrder(OrderEntity editOrder) {
//         double totalAmount = calculateTotalAmount(editOrder.getOrderId());
//         editOrder.setTotalAmount(totalAmount);
//         return orderRepository.save(editOrder);
//     }

//     public void deleteOrderById(int orderId) {
//         orderRepository.deleteById(orderId);
//     }

//     private double calculateTotalAmount(int orderId) {
//         List<OrderProductPojo> orderProducts = orderProductClient.getAllOrderProductsByOrderId(orderId);
//         double totalAmount = 0.0;
//         for (OrderProductPojo orderProduct : orderProducts) {
//             ProductPojo product = productClient.getProductById(orderProduct.getProductId());
//             totalAmount += orderProduct.getSalesQuantity() * product.getProductPrice();
//         }
//         return totalAmount;
//     }
// }