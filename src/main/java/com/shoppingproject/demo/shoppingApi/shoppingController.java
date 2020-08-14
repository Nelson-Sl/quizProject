package com.shoppingproject.demo.shoppingApi;

import com.shoppingproject.demo.shoppingComponent.ItemDto;
import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import com.shoppingproject.demo.shoppingDomain.Item;
import com.shoppingproject.demo.shoppingService.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
