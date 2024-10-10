package com.cursos.spring_security_course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursos.spring_security_course.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
