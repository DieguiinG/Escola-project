package com.example.Escola.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

import com.example.Escola.models.StudentModel;
import com.example.Escola.models.dtos.StudentRecordDTO;
import com.example.Escola.repositories.StudentRepository;

import jakarta.validation.Valid;

@RestController
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/student")
	public ResponseEntity<StudentModel> saveStudent(@RequestBody @Valid StudentRecordDTO studentRecordDTO){
		var studentModel = new StudentModel();
		BeanUtils.copyProperties(studentRecordDTO, studentModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.save(studentModel));
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentModel>> getAllStudents(){
		return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findAll());
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Object> getOneStudent(@PathVariable(value="id") Long id){
		Optional<StudentModel> student0 = studentRepository.findById(id);
		if(student0.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(student0.get());
		
		
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable(value="id") Long id, @RequestBody @Valid StudentRecordDTO studentRecordDTO){
		
		Optional<StudentModel> student0 = studentRepository.findById(id);
		if(student0.isEmpty()){
			return ResponseEntity.status(HttpStatus.OK).body("Product Not found");
		}
		var studentModel = student0.get();
		BeanUtils.copyProperties(studentRecordDTO, studentModel);
		return ResponseEntity.status(HttpStatus.OK).body(studentRepository.save(studentModel));
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable(value="id") Long id){
		Optional<StudentModel> student0 = studentRepository.findById(id);
		if(student0.isEmpty()){
			return ResponseEntity.status(HttpStatus.OK).body("Student not found!");
		}
		studentRepository.delete(student0.get());
		return ResponseEntity.status(HttpStatus.OK).body("Student deleted sucessfully!");
	}

	
}
