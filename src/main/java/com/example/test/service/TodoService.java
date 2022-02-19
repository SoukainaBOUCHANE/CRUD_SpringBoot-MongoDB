package com.example.test.service;

import java.util.List;

import com.example.test.exception.TodoCollectionException;
import com.example.test.model.TodoDTO;

public interface TodoService {
 
	public void createTodo(TodoDTO todo) throws TodoCollectionException;
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO getSingleTodos(String id) throws TodoCollectionException;
	
	public void updateTodo(String id,TodoDTO todo) throws TodoCollectionException;
	
	public void deleteTodoById(String id) throws TodoCollectionException;
	
}
