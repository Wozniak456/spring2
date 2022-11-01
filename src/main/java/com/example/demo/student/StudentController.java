package com.example.demo.student;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	StudentRepository studentRepository;
	
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@GetMapping()
	Collection<Student> findAll(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Student> findById(@PathVariable Integer id) {
		Student student = studentRepository.findById(id);
		if(student == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/group/{groupName}")
	ResponseEntity<Collection<Student>> findByGroup(@PathVariable String groupName) {
		Collection<Student> list = studentRepository.findByGroup(groupName);
		if(list.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}
	
	 
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteById(@PathVariable Integer id) {
		if (studentRepository.deleteById(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	void save(@PathVariable("id") Integer id, @RequestBody Student student) {
		studentRepository.save(student);
	} 
	
	
	@PostMapping()
	void create(@RequestBody Student student) {
		studentRepository.save(student);
	}
	
}
