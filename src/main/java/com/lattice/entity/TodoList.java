package com.lattice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_list")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "list_Id")
    private Long listId;

    @Column(unique = true)
    String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "list")
    @ApiModelProperty(hidden = true)
    @JsonManagedReference
    Set<TodoItem> items = new HashSet<>();
}
