package com.shoppingproject.demo.shoppingService;

import com.shoppingproject.demo.shoppingComponent.ItemDto;
import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import com.shoppingproject.demo.shoppingRepository.ItemRepository;
import com.shoppingproject.demo.shoppingRepository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public ShoppingService(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public List<ItemDAO> getItemList() {
        return itemRepository.findAll();
    }
}
