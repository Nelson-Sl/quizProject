package com.shoppingproject.demo.shoppingRepository;

import com.shoppingproject.demo.shoppingDAO.OrderDAO;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDAO,Integer> {
}
