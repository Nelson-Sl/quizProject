package com.shoppingproject.demo.shoppingDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shoppingproject.demo.shoppingDAO.OrderDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private String itemName;
    private double itemPrice;
    private String itemUnit;
    private String itemImgUrl;
}
