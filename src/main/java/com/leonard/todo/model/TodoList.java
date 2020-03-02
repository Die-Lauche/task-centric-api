package com.leonard.todo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "todo_list")
public class TodoList implements Serializable {
}
