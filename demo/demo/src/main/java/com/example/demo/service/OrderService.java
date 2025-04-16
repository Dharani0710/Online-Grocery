package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.GroceryItem;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.GroceryItemRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private GroceryItemRepository itemRepo;

    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Order getById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public Order save(Long customerId, List<Long> itemIds) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<GroceryItem> items = itemRepo.findAllById(itemIds);

        double totalPrice = items.stream().mapToDouble(GroceryItem::getPrice).sum();

        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(items);
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(totalPrice);

        return orderRepo.save(order);
    }

    public void delete(Long id) {
        orderRepo.deleteById(id);
    }
}
