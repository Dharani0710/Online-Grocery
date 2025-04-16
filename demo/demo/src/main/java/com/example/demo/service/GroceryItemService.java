package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.GroceryItem;
import com.example.demo.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {
    @Autowired
    private GroceryItemRepository repository;

    public List<GroceryItem> getAll() {
        return repository.findAll();
    }

    public GroceryItem getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

    public GroceryItem save(GroceryItem item) {
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
