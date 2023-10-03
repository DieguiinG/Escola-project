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

import com.example.Escola.models.TeacherModel;
import com.example.Escola.models.dtos.TeacherRecordDTO;
import com.example.Escola.repositories.TeacherRepository;

import jakarta.validation.Valid;

@RestController
public class TeacherController {

	@Autowired
	TeacherRepository teacherRepository;
	

	@PostMapping("/teachers")
	public ResponseEntity<TeacherModel> saveTeacher(@RequestBody @Valid TeacherRecordDTO teacherRecordDTO) {
		var teacherModel = new TeacherModel();
		BeanUtils.copyProperties(teacherRecordDTO, teacherModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherRepository.save(teacherModel));
	}
	

	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherModel>> getAllTeachers(@RequestBody @Valid TeacherRecordDTO teacherRecordDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(teacherRepository.findAll());
	}
	

	@GetMapping("/teachers/{id}")
	public ResponseEntity<Object> getOneTeacher(@PathVariable(value = "id") long id) {
		Optional<TeacherModel> teacherModel0 = teacherRepository.findById(id);

		if (teacherModel0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(teacherModel0.get());
	}
	

	@PutMapping("/teachers/{id}")
	public ResponseEntity<Object> updateTeacher(@PathVariable(value = "id") Long id,
			@RequestBody @Valid TeacherRecordDTO teacherRecordDTO) {

		Optional<TeacherModel> teacher0 = teacherRepository.findById(id);
		if (teacher0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Teacher Not found");
		}
		var teacherModel = teacher0.get();
		BeanUtils.copyProperties(teacherRecordDTO, teacherModel);
		return ResponseEntity.status(HttpStatus.OK).body(teacherRepository.save(teacherModel));
	}
	

	@DeleteMapping("/teachers/{id}")
	public ResponseEntity<Object> deleteTeacher(@PathVariable(value = "id") Long id) {
		Optional<TeacherModel> teacher0 = teacherRepository.findById(id);
		if (teacher0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Teacher not found!");
		}
		teacherRepository.delete(teacher0.get());
		return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted sucessfully!");
	}

}
