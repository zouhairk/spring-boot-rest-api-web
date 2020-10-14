package com.webisystems.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webisystems.app.bo.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	
}
