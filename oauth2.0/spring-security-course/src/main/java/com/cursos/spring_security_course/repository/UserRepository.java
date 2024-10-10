package com.cursos.spring_security_course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursos.spring_security_course.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

}
