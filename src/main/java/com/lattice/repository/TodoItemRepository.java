package com.lattice.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.lattice.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TodoItemRepository")
public interface TodoItemRepository extends JpaRepository<TodoItem, Long>
{
    TodoItem findByItemId(Long itemId);
    List<TodoItem> findByListListId(long listId);
    boolean existsTodoItemByItemId(long itemId);

}