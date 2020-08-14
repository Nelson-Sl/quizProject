package com.shoppingproject.demo.shoppingApi;

import com.shoppingproject.demo.shoppingComponent.ItemDto;
import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import com.shoppingproject.demo.shoppingDAO.OrderDAO;
import com.shoppingproject.demo.shoppingDomain.Item;
import com.shoppingproject.demo.shoppingService.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class shoppingController {
    private final ShoppingService shoppingService;

    public shoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/shop/item")
    public ResponseEntity<List<ItemDAO>> getAllItems() {
        List<ItemDAO> itemList = shoppingService.getItemList();
        return ResponseEntity.ok(itemList);
    }

    @PutMapping("/shop/item")
    public ResponseEntity addSingleItem(@RequestBody Item item) {
        ItemDAO newItem = shoppingService.addItem(item);
        return ResponseEntity.created(null).body(newItem.getId());
    }

    @GetMapping("/shop/order")
    public ResponseEntity getAllOrders() {
        List<OrderDAO> orderList = shoppingService.getOrderList();
        return ResponseEntity.ok(orderList);
    }

    @DeleteMapping("/shop/order")
    public ResponseEntity deleteOrdersById(@RequestParam String id) {
        shoppingService.deleteSingleOrder(Integer.valueOf(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
