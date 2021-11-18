package com.walterio123.libreria.repository_DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.entity.Book;

public interface BookRepository extends JpaRepository<Book, String>{

	
	@Query("SELECT c FROM Book c ")
	public List<Book>listarBook();
	
	@Query("SELECT c FROM Book c WHERE c.id= :id")
	public Book buscarPorId(@Param("id") String id);
	
	@Query("Select a From Book a WHERE a.id= :id")
	public void deleteById(String id);
	
	@Query("SELECT a FROM Book a WHERE a.titulo like :titulo%")
	public List<Book> buscarPorNombre(@Param("titulo") String titulo); 
	
	@Query("SELECT a FROM Book a WHERE a.titulo like :titulo%")
	public Optional<Book> buscarBook(@Param("titulo")String titulo);
}
