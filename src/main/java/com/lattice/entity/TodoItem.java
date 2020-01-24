package com.lattice.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_items")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    @JsonBackReference
    TodoList list;

    @Column(name = "task_name")
    @NotEmpty(message = "* Enter Task Name")
    private String taskName;

    @Column(name = "is_done")
    @ApiModelProperty(hidden = true)
    private Boolean isDone = false; // Default value

    @Column(name = "created_at")
    @ApiModelProperty(hidden = true)
    private Date createdAt = new Date();

}