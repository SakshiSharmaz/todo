package com.lattice.controller;

import com.lattice.entity.TodoItem;
import com.lattice.repository.TodoItemRepository;
import com.lattice.repository.TodoListRepository;
import com.lattice.service.TodoItemService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TodoItemController {
    @Autowired
    private TodoItemService itemService;

    @Autowired
    TodoItemRepository itemRepository;

    @Autowired
    TodoListRepository listRepository;

    @GetMapping("/item/{itemId}")
    public TodoItem getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }


    // New todo item
    @PostMapping(value = "/item")
    public ResponseEntity<String> newTodoItem(@RequestBody TodoItem item) {

        JSONObject response = new JSONObject();
        boolean exists = listRepository.existsTodoListByListId(item.getList().getListId());
        if (!exists) {
            response.put("message", "list doesn't exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        }else {
        TodoItem savedItem = itemService.saveTodoItem(item);
        response.put("message", "Item " + savedItem.getTaskName() + " created successfully");
        response.put("itemId", savedItem.getItemId());
        return ResponseEntity.ok(response.toString());
        }
    }

    // Edit todo item
    @PutMapping("/item")
    public ResponseEntity<String> editTodoItem(@RequestBody TodoItem item) {

        JSONObject response = new JSONObject();
        boolean exists = itemRepository.existsTodoItemByItemId(item.getItemId());
        if (!exists) {
            response.put("message", "Item doesn't exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        } else {
            itemService.editTodoItem(item);
            response.put("message", "Item edited successfully");
            return ResponseEntity.ok(response.toString());
        }
    }

    // Delete todo item
    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable Long id) {
        JSONObject response = new JSONObject();
        boolean exists = itemRepository.existsTodoItemByItemId(id);
        if (!exists) {
            response.put("message", "Item doesn't exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        } else {
            itemService.deleteTodoItem(id);
            response.put("message", "Item deleted successfully");
            return ResponseEntity.ok(response.toString());
        }
    }

    // Change done state
    @PutMapping("/item/{id}")
    public ResponseEntity<String> changeDoneState(@PathVariable Long id) {

        JSONObject response = new JSONObject();
        boolean exists = itemRepository.existsTodoItemByItemId(id);
        if (!exists) {
            response.put("message", "Item doesn't exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        } else {
            itemService.changeDoneStateForTodoItem(id);
            response.put("message", "Item marked as done");
            return ResponseEntity.ok(response.toString());
        }

    }
}