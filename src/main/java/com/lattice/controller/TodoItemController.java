package com.lattice.controller;

import com.lattice.entity.TodoItem;
import com.lattice.service.TodoItemService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TodoItemController {
    @Autowired
    private TodoItemService itemService;

    @GetMapping("/item/{itemId}")
    public TodoItem getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }



    // New todo item
    @PostMapping(value = "/item")
    public ResponseEntity<String> newTodoItem(@RequestBody TodoItem item) {
         TodoItem savedItem =  itemService.saveTodoItem(item);
        JSONObject response = new JSONObject();
        response.put("message", "Item " + savedItem.getTaskName() +   " created successfully");
        response.put("itemId", savedItem.getItemId());
        return ResponseEntity.ok(response.toString());
    }

    // Edit todo item
    @PutMapping("/item")
    public ResponseEntity<String > editTodoItem(@RequestBody TodoItem item){
        itemService.editTodoItem(item);
        JSONObject response = new JSONObject();
        response.put("message", "Item edited successfully");
        return ResponseEntity.ok(response.toString());
    }

    // Delete todo item
    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable Long id){
        itemService.deleteTodoItem(id);
        JSONObject response = new JSONObject();
        response.put("message", "Item deleted successfully");
        return ResponseEntity.ok(response.toString());
    }

    // Change done state
    @PutMapping("/item/{id}")
    public ResponseEntity<String> changeDoneState(@PathVariable Long id) {
        itemService.changeDoneStateForTodoItem(id);
        JSONObject response = new JSONObject();
        response.put("message", "Item marked as done");
        return ResponseEntity.ok(response.toString());

    }
}