package com.example.Escola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Escola.models.TeacherModel;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long>{

}
