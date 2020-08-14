package com.shoppingproject.demo.shoppingRepository;

import com.shoppingproject.demo.shoppingDAO.OrderDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDAO,Integer> {
    List<OrderDAO> findAll();
}
