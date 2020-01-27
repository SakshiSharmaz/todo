package com.lattice.repository;

import com.lattice.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TodoListRepository extends JpaRepository<TodoList,Long> {

    @Modifying
    @Transactional
    @Query(value = "update todo_list set title = ?1 where list_id = ?2 ", nativeQuery = true)
    void updateListName(String name, long listId);

    boolean existsTodoListByListId(long listId);
    boolean existsTodoListByTitle(String name);


}
