// package com.sr.order_service.controller;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;


// import com.sr.order_service.entity.OrderEntity;
// import com.sr.order_service.pojo.OrderPojo;
// import com.sr.order_service.pojo.StorePojo;
// import com.sr.order_service.service.InventoryClient;
// import com.sr.order_service.service.OrderProductClient;
// import com.sr.order_service.service.OrderService;
// import com.sr.order_service.service.ProductClient;
// import com.sr.order_service.service.StoreClient;
// import com.sr.order_service.pojo.OrderProductPojo;
// import com.sr.order_service.pojo.ProductPojo;
// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {
// 	 @Autowired
// 	    OrderService orderService;
	 
// 	 @Autowired
// 	    StoreClient storeClient;
	 
// 	 @Autowired
// 	    OrderProductClient orderProductClient;

// 	 @Autowired
// 	    ProductClient productClient;
	 
// 	 @Autowired
// 	    InventoryClient inventoryClient;
	 
// 	 @PostMapping("/complete/{orderId}")
// 	 public ResponseEntity<OrderEntity> completeOrder(@PathVariable int orderId) {
// 	     OrderEntity order = orderService.getOrderById(orderId);
// 	     order.setOrderStatus("COMPLETED");
// 	     OrderEntity completedOrder = orderService.completeOrder(order);
// 	     return ResponseEntity.ok(completedOrder);
// 	 }


// 	    @GetMapping("/details/{orderId}")
// 	    public ResponseEntity<OrderPojo> getOrderByIdWithDetails(@PathVariable int orderId) {
// 	        try {
// 	            // Fetch the order details
// 	            OrderEntity orderEntity = orderService.getOrderById(orderId);

// 	            if (orderEntity == null) {
// 	                return ResponseEntity.notFound().build(); // 404 if order not found
// 	            }

// 	            // Create the OrderPojo
// 	            OrderPojo orderPojo = new OrderPojo();
// 	            orderPojo.setOrderId(orderEntity.getOrderId());
// 	            orderPojo.setOrderStatus(orderEntity.getOrderStatus());
// 	            orderPojo.setOrderDate(orderEntity.getOrderDate());
// 	            orderPojo.setOrderStoreId(orderEntity.getOrderStoreId());
// 	            orderPojo.setOrderUserId(orderEntity.getOrderUserId());

// 	            // Fetch the order-product details for the given order
// 	            List<OrderProductPojo> orderProductList = orderProductClient.getAllOrderProductsByOrderId(orderId);

// 	            // Enhance the order-product list with product details
// 	            List<OrderProductPojo> enhancedOrderProductList = orderProductList.stream().map(orderProduct -> {
// 	                ProductPojo product = productClient.getProductById(orderProduct.getProductId());
// 	                orderProduct.setProductPojo(product);
// 	                return orderProduct;
// 	            }).collect(Collectors.toList());

// 	            // Attach the enhanced order-product list to the OrderPojo
// 	            orderPojo.setOrderProducts(enhancedOrderProductList);

// 	            return ResponseEntity.ok(orderPojo);
// 	        } catch (Exception e) {
// 	            e.printStackTrace();
// 	            return ResponseEntity.status(500).body(null); // 500 in case of errors
// 	        }
// 	    }

// 	    @PostMapping
// 	    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
// 	        OrderEntity savedOrder = orderService.addOrder(order);
// 	        return ResponseEntity.ok(savedOrder);
// 	    }

// 	    @GetMapping
// 	    public ResponseEntity<List<OrderEntity>> getAllOrders() {
// 	        List<OrderEntity> orders = orderService.getAllOrders();
// 	        return ResponseEntity.ok(orders);
// 	    }

// 	    @GetMapping("/{orderId}")
// 	    public ResponseEntity<OrderEntity> getOrderById(@PathVariable int orderId) {
// 	        OrderEntity order = orderService.getOrderById(orderId);
// 	        return ResponseEntity.ok(order);
// 	    }
	    
	    
	    

// 	    // Get orders by User ID
// //	    @GetMapping("/order/{orderId}")
// //	    public ResponseEntity<List<OrderEntity>> getOrdersByStoreId(@PathVariable int userId) {
// //	        List<OrderEntity> orders = orderService.getOrdersByUserId(userId);
// //	        return ResponseEntity.ok(orders);
// //	    }

// 	    @PutMapping
// 	    public ResponseEntity<OrderEntity> updateOrder(@RequestBody OrderEntity editOrder) {
// 	        OrderEntity updatedOrder = orderService.updateOrder(editOrder);
// 	        return ResponseEntity.ok(updatedOrder);
// 	    }

// 	    @DeleteMapping("/{orderId}")
// 	    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
// 	        orderService.deleteOrderById(orderId);
// 	        return ResponseEntity.ok("Order deleted successfully!");
// 	    }
	    
// 	    @GetMapping("/a/{oid}")
// 		public ResponseEntity<OrderPojo> getAOrder(@PathVariable int oid) {
// 		    Optional<OrderEntity> orderOptional = orderService.getAOrder(oid);
		    
// 		    if (orderOptional.isPresent()) {
// 		        OrderEntity order = orderOptional.get();  // Unwrap the Optional
		        
// 		        OrderPojo orderPojo = new OrderPojo();
		        
// 		        // Now use Feign client to fetch department by departmentI
// 		        StorePojo store = storeClient.getStoreById(order.getOrderStoreId());

		        
// //		        StorePojo store = storeClient.getStoreById(inventory.getInStoreId());
		        
		        
// 		        // Set the department to the employee
// 		        orderPojo.setOrderId(order.getOrderId());
// 		        orderPojo.setOrderStatus(order.getOrderStatus());
// 		        orderPojo.setOrderDate(order.getOrderDate());
// 		        orderPojo.setOrderUserId(order.getOrderUserId());
// 		        orderPojo.setOrderStoreId(order.getOrderStoreId());
// 		        orderPojo.setStorePojo(store);
		        
		        
// 		        return new ResponseEntity<>(orderPojo, HttpStatus.OK);
// 		    } else {
// 		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Employee not found
// 		    }
// 		}
	    
// 	    @PostMapping("/send-to-inventory/{orderId}")
// 	    public ResponseEntity<String> sendOrderProductsToInventory(@PathVariable int orderId) {
// 	        try {
// 	            // Fetch the order-product details for the given order
// 	            List<OrderProductPojo> orderProductList = orderProductClient.getAllOrderProductsByOrderId(orderId);
// 	            System.out.println(orderProductList.toString());

// //	            System.out.println(orderProductList);

// 	            // Send the order-product list to the inventory microservice
// 	            inventoryClient.updateSales(orderProductList);

// 	            return ResponseEntity.ok("Order products sent to inventory and updated successfully.");
// 	        } catch (Exception e) {
// 	            e.printStackTrace();
// 	            return ResponseEntity.status(500).body("Failed to update inventory.");
// 	        }
// 	    }

	    

// }

package com.sr.order_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sr.order_service.entity.OrderEntity;
import com.sr.order_service.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	 @Autowired
	    ProductClient productClient;
	 
	 @Autowired
	    InventoryClient inventoryClient;
	 
	//  @PostMapping("/complete/{orderId}")
	//  public ResponseEntity<OrderEntity> completeOrder(@PathVariable int orderId) {
	//      OrderEntity order = orderService.getOrderById(orderId);
	//      order.setOrderStatus("COMPLETED");
	//      OrderEntity completedOrder = orderService.completeOrder(order);
	//      return ResponseEntity.ok(completedOrder);
	//  }
<<<<<<< Updated upstream
    @Autowired
    OrderService orderService;
=======
>>>>>>> Stashed changes

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
        OrderEntity savedOrder = orderService.addOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable int orderId) {
        OrderEntity order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PutMapping
    public ResponseEntity<OrderEntity> updateOrder(@RequestBody OrderEntity order) {
        OrderEntity updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }
}

