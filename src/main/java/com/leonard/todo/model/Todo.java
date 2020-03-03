package com.leonard.todo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "todo")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "isCompleted")
    private Boolean completed;

    @Column(name = "isInProgress")
    private Boolean inProgress;

    @Column(name = "isInTodo")
    private Boolean inTodo;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private Integer todoListId;

    public Todo() {
    }

    public Todo(Integer id, String content, Boolean completed, Boolean inProgress, Boolean inTodo, Integer todoListId) {
        this.id = id;
        this.content = content;
        this.completed = completed;
        this.inProgress = inProgress;
        this.inTodo = inTodo;
        this.todoListId = todoListId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Boolean getInTodo() {
        return inTodo;
    }

    public void setInTodo(Boolean inTodo) {
        this.inTodo = inTodo;
    }

    public Integer getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Integer todoListId) {
        this.todoListId = todoListId;
    }
}
