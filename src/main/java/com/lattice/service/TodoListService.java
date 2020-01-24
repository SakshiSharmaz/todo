package com.lattice.service;

import com.lattice.entity.TodoItem;
import com.lattice.entity.TodoList;
import com.lattice.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TodoListService")
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public TodoList createList(TodoList list){
         return  todoListRepository.save(list);
    }

    public Boolean deleteList(Long id) {
        TodoList list = todoListRepository.findById(id).orElse(null);
        if (list != null) {
            todoListRepository.delete(list);
            return true;
        }
        return false;
    }

    public boolean editList(TodoList list)
    {
        TodoList existingList = todoListRepository.findById(list.getListId()).orElse(null);
        if (existingList != null) {
            todoListRepository.updateListName(list.getTitle(),list.getListId());
            return true;
        }
        return false;
    }

    public List<TodoList> getAllLists(long listId) {
        return todoListRepository.findAll();
    }

    public TodoList getListItems(long listId) {
        return todoListRepository.findById(listId).get();
    }


}
