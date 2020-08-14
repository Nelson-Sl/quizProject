package com.shoppingproject.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingproject.demo.shoppingComponent.ItemDto;
import com.shoppingproject.demo.shoppingDAO.ItemDAO;
import com.shoppingproject.demo.shoppingDAO.OrderDAO;
import com.shoppingproject.demo.shoppingDomain.Item;
import com.shoppingproject.demo.shoppingRepository.ItemRepository;
import com.shoppingproject.demo.shoppingRepository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	private ObjectMapper objectMapper = new ObjectMapper();
	private int idItem1;
	private int idItem2;
	private int orderItem1;

	@BeforeEach
	public void setUp() {
		itemRepository.deleteAll();
		orderRepository.deleteAll();
		Item item1 = Item.builder().itemName("toy")
				.itemPrice(14.5)
				.itemUnit("CNY")
				.itemImgUrl("./toy.jpg")
				.build();
		Item item2 = Item.builder().itemName("fan")
				.itemPrice(10)
				.itemUnit("CNY")
				.itemImgUrl("./fan.jpg")
				.build();
		ItemDAO itemSaved1 = ItemDto.ItemDaoBuilder(item1);
		ItemDAO itemSaved2 = ItemDto.ItemDaoBuilder(item2);
		idItem1 = itemRepository.save(itemSaved1).getId();
		idItem2 = itemRepository.save(itemSaved2).getId();

		List<ItemDAO> orderItems = new ArrayList<>();
		orderItems.add(itemSaved1);
		orderItems.add(itemSaved2);
		OrderDAO newOrder = OrderDAO.builder()
				.itemDAOList(orderItems).build();
		orderItem1 = orderRepository.save(newOrder).getId();
	}
	@Test
	void userShouldGetAllItems() throws Exception {
		mockMvc.perform(get("/shop/item"))
				.andExpect(jsonPath("$",hasSize(2)))
				.andExpect(jsonPath("$[0].itemName",is("toy")))
				.andExpect(jsonPath("$[0].itemPrice",is(14.5)))
				.andExpect(jsonPath("$[0].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[0].itemImgUrl",is("./toy.jpg")))
				.andExpect(jsonPath("$[1].itemName",is("fan")))
				.andExpect(jsonPath("$[1].itemPrice",is(10.0)))
				.andExpect(jsonPath("$[1].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[1].itemImgUrl",is("./fan.jpg")))
				.andExpect(status().isOk());
	}

	@Test
	void userCanAddItems() throws Exception {
		Item newItem = Item.builder().itemName("radio")
				.itemPrice(39.5)
				.itemUnit("CNY")
				.itemImgUrl("./radio.jpg")
				.build();
		String newItemStr = objectMapper.writeValueAsString(newItem);
		mockMvc.perform(put("/shop/item")
				.content(newItemStr).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/shop/item"))
				.andExpect(jsonPath("$",hasSize(3)))
				.andExpect(jsonPath("$[0].itemName",is("toy")))
				.andExpect(jsonPath("$[0].itemPrice",is(14.5)))
				.andExpect(jsonPath("$[0].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[0].itemImgUrl",is("./toy.jpg")))
				.andExpect(jsonPath("$[1].itemName",is("fan")))
				.andExpect(jsonPath("$[1].itemPrice",is(10.0)))
				.andExpect(jsonPath("$[1].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[1].itemImgUrl",is("./fan.jpg")))
				.andExpect(jsonPath("$[2].itemName",is("radio")))
				.andExpect(jsonPath("$[2].itemPrice",is(39.5)))
				.andExpect(jsonPath("$[2].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[2].itemImgUrl",is("./radio.jpg")))
				.andExpect(status().isOk());
	}

	@Test
	void userCanGetOrder() throws Exception {
		mockMvc.perform(get("/shop/order"))
				.andExpect(jsonPath("$",hasSize(1)))
				.andExpect(jsonPath("$[0].id",is(orderItem1)))
				.andExpect(jsonPath("$[0].itemDAOList[0].id",is(idItem1)))
				.andExpect(jsonPath("$[0].itemDAOList[0].itemName",is("toy")))
				.andExpect(jsonPath("$[0].itemDAOList[0].itemPrice",is(14.5)))
				.andExpect(jsonPath("$[0].itemDAOList[0].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[0].itemDAOList[0].itemImgUrl",is("./toy.jpg")))
				.andExpect(jsonPath("$[0].itemDAOList[1].id",is(idItem2)))
				.andExpect(jsonPath("$[0].itemDAOList[1].itemName",is("fan")))
				.andExpect(jsonPath("$[0].itemDAOList[1].itemPrice",is(10.0)))
				.andExpect(jsonPath("$[0].itemDAOList[1].itemUnit",is("CNY")))
				.andExpect(jsonPath("$[0].itemDAOList[1].itemImgUrl",is("./fan.jpg")))
				.andExpect(status().isOk());
	}

	@Test
	void userCanDeleteOrder() throws Exception {
		mockMvc.perform(delete("/shop/order")
				.param("id",String.valueOf(orderItem1)))
				.andExpect(status().isOk());
		assertEquals(0, orderRepository.count());
	}
}
