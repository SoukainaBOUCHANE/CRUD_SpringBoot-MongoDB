package com.example.test.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.AudioSystem;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.exception.TodoCollectionException;
import com.example.test.model.TodoDTO;
import com.example.test.repository.TodoRepository;
import com.example.test.service.TodoService;

@RestController 
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos(){
		List<TodoDTO> todos = todoService.getAllTodos();
		return new ResponseEntity<>(todos, todos.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/todos/add")
	public ResponseEntity<?> createTodos(@RequestBody TodoDTO todo) throws TodoCollectionException{
		try {
			todoService.createTodo(todo);
			System.out.print("test adddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		}catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/todos/getById/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(todoService.getSingleTodos(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/todos/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo){
		try {
			todoService.updateTodo(id, todo);
			return new ResponseEntity<>("Update Todo with id "+ id, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/todos/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		try {
			todoService.deleteTodoById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	

}
