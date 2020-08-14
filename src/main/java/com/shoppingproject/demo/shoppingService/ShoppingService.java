package com.shoppingproject.demo.shoppingService;

import com.shoppingproject.demo.shoppingComponent.ItemDto;
import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import com.shoppingproject.demo.shoppingDAO.OrderDAO;
import com.shoppingproject.demo.shoppingDomain.Item;
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

    public ItemDAO addItem(Item item) {
        ItemDAO itemSaving = ItemDto.ItemDaoBuilder(item);
        ItemDAO itemSaved = itemRepository.save(itemSaving);
        return itemSaved;
    }

    public List<OrderDAO> getOrderList() {
        return orderRepository.findAll();
    }
}
