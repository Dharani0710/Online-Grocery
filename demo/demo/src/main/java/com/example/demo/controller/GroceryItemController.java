package com.example.demo.controller;

import com.example.demo.model.GroceryItem;
import com.example.demo.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class GroceryItemController {
    @Autowired
    private GroceryItemService service;

    @GetMapping
    public List<GroceryItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GroceryItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public GroceryItem create(@RequestBody GroceryItem item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public GroceryItem update(@PathVariable Long id, @RequestBody GroceryItem item) {
        item.setId(id);
        return service.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
