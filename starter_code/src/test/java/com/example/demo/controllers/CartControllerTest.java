package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;

public class CartControllerTest {
    private CartController cartController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp() {
        cartController = new CartController();
        TestUtils.injectObjects(cartController, "userRepository", userRepository);
        TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepository);
    }

    @Test
    public void add_to_cart_happy_path() throws Exception {
        User u = new User();
        u.setId(0);
        u.setUsername("test");
        u.setPassword("testPassword");
        when(userRepository.findByUsername("test")).thenReturn(u);

        Item i = new Item();
        i.setId(0L);
        i.setName("Round Widget");
        i.setPrice(BigDecimal.valueOf(2.99));
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(i));

        ModifyCartRequest r = new ModifyCartRequest();
        r.setUsername("test");
        r.setItemId(0L);
        r.setQuantity(1);

        final ResponseEntity<Cart> response = cartController.addTocart(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        Cart c = response.getBody();
        assertNotNull(c);
        assertEquals(1, c.getItems().size());
        assertEquals(BigDecimal.valueOf(2.99), c.getTotal());
    }

    @Test
    public void remove_from_cart_happy_path() throws Exception {
        User u = new User();
        u.setId(0);
        u.setUsername("test");
        u.setPassword("testPassword");
        when(userRepository.findByUsername("test")).thenReturn(u);

        Item i = new Item();
        i.setId(0L);
        i.setName("Round Widget");
        i.setPrice(BigDecimal.valueOf(2.99));
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(i));

        ModifyCartRequest r = new ModifyCartRequest();
        r.setUsername("test");
        r.setItemId(0L);
        r.setQuantity(1);

        final ResponseEntity<Cart> response = cartController.removeFromcart(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        Cart c = response.getBody();
        assertNotNull(c);
        assertEquals(0, c.getItems().size());
        assertEquals(BigDecimal.valueOf(0), c.getTotal());
    }
}
