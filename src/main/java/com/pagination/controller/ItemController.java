package com.pagination.controller;

import com.pagination.entity.Item;
import com.pagination.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<Item> items = itemService.getAllItemsWithPaginationAndSorting(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(items);
    }
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
        Item createdItem = itemService.createItem(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }
}