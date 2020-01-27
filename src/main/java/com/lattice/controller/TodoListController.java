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
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> newList(@RequestBody TodoList list) {

        JSONObject response = new JSONObject();
        boolean exists = listRepository.existsTodoListByTitle(list.getTitle());
        if (!exists) {
            response.put("message", "list with same name already exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        } else {

            TodoList createdList = listService.createList(list);
            response.put("message", "list created successfully");
            response.put("listId", createdList.getListId());
            return ResponseEntity.ok(response.toString());
        }
    }

    @PutMapping("/list")
    public ResponseEntity<?> editList(@RequestBody TodoList list) {
        JSONObject response = new JSONObject();
        boolean exists = listRepository.existsTodoListByListId(list.getListId());
        if (!exists) {
            response.put("message", "list doesn't exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        } else {
            listService.editList(list);
            response.put("message", "list edited successfully");
            return ResponseEntity.ok(response.toString());

        }
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<String> deleteList(@PathVariable Long id) {

        JSONObject response = new JSONObject();
        boolean exists = listRepository.existsTodoListByListId(id);
        if (!exists) {
            response.put("message", "list doesn't exists");
            return new ResponseEntity(response.toString(), HttpStatus.NOT_IMPLEMENTED);
        }else {
            listService.deleteList(id);
            response.put("message", "list deleted successfully");
            return ResponseEntity.ok(response.toString());
        }
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
