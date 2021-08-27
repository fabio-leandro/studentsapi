package br.com.fabio.studentapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabio.studentapi.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
