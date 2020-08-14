package com.shoppingproject.demo.shoppingComponent;

import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import com.shoppingproject.demo.shoppingDomain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDto {
    public static ItemDAO ItemDaoBuilder(Item item) {
        return ItemDAO.builder().itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .itemUnit(item.getItemUnit())
                .itemImgUrl(item.getItemImgUrl())
                .build();
    }
}
