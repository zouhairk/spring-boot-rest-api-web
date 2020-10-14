package com.webisystems.app.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webisystems.app.bo.Author;

@RestController
 public interface RestTemplate<T, ID> {
	
	/*

 	public JpaRepository<Object, Long> service=null;

	public  String MODEL_NAME = "";

	@GetMapping("/" + MODEL_NAME + "/{id}")
	public default ResponseEntity<Object> get(@PathVariable Long id) {
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>(service.getOne(id), HttpStatus.ACCEPTED);
		return res;
	}

	@GetMapping("/" + MODEL_NAME + "")
	public default ResponseEntity<Object> list() {
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>(service.findAll(), HttpStatus.ACCEPTED);
		return  res;
	}
	
	@PostMapping("/" + MODEL_NAME + "/add")
	public default ResponseEntity<Object> add(@RequestBody Author object ) {
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>( service.save(object), HttpStatus.CREATED);
		return  res;
	}
	
	@DeleteMapping("/" + MODEL_NAME + "/{id}")
	public default ResponseEntity<Object> delete(@PathVariable Long id) {
		service.deleteById(id);
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>( HttpStatus.ACCEPTED);
		return res;
	}
	
	 */

 
}
