package com.J5VA.service;

import java.util.List;
import com.J5VA.entity.Orders;
import com.J5VA.entity.Report;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrdersService {

	Orders create(JsonNode orderData);

	void deleteById(Integer id);

	List<Orders> findAll();

	Orders save(Orders entity);

	List<Orders> findByUsername(String fullname);

	void delete(Integer id);

	Orders update(Orders order);

	Orders findById(Integer id);

	List<Orders> findAllByStatus(Integer status);

	Integer totalOrders();

	Integer totalOrdersByStatus(Integer status);

	Double findCarsAfterYear(Integer Month);

	List<Report> getInventoryByOrder();
	
	Integer quantityFoodByMonth(Integer month);
}