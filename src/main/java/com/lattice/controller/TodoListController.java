package com.lattice.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lattice.entity.TodoItem;
import com.lattice.entity.TodoList;
import com.lattice.repository.TodoListRepository;
import com.lattice.service.TodoItemService;
import com.lattice.service.TodoListService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListController {

    @Autowired
    TodoListService listService;

    @Autowired
    TodoItemService itemService;

    @Autowired
    TodoListRepository listRepository;

    @PostMapping(value = "/list")
    public ResponseEntity<String > newList(@RequestBody TodoList list) {
        TodoList createdList = listService.createList(list);
        JSONObject response = new JSONObject();
        response.put("message", "list created successfully");
        response.put("listId" ,createdList.getListId());
        return ResponseEntity.ok(response.toString());
    }

    @PutMapping("/list")
    public ResponseEntity<?> editList(@RequestBody TodoList list) {
        listService.editList(list);
        JSONObject response = new JSONObject();
        response.put("message", "list edited successfully");
        return ResponseEntity.ok(response.toString());
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<String> deleteList(@PathVariable Long id){
        listService.deleteList(id);
        JSONObject response = new JSONObject();
        response.put("message", "list deleted successfully");
        return ResponseEntity.ok(response.toString());
    }


    // Get todo list, based on listId
    @GetMapping("/list/{listId}")
    public List<TodoItem> getItemsInList(@PathVariable long listId) {
        return itemService.getAllTodoItemsForListId(listId);
    }
    // Get todo list, based on listId
    @GetMapping("/list")
    public List<TodoList> getAllLists() {
        return listRepository.findAll();
    }
}
