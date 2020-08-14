package com.shoppingproject.demo.shoppingRepository;

import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<ItemDAO,Integer> {
    List<ItemDAO> findAll();
}
