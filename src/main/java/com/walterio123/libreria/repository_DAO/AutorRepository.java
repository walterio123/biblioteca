package com.walterio123.libreria.repository_DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.walterio123.libreria.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {
	
	@Query("SELECT c FROM Autor c WHERE c.alta IS NOT NULL ORDER BY c.nombre DESC ")
	public List<Autor>listarAutor();
	
	@Query("SELECT c FROM Autor c WHERE c.id= :id")
	public Autor buscarPorId(@Param("id") String id);
	
	@Query("Select a From Autor a WHERE a.id= :id")
	public void deleteById(String id);
	
	@Query("SELECT a FROM Autor a WHERE a.nombre like :nombre%")
	public List<Autor> buscarPorNombre(@Param("nombre") String nombre); 
	
	@Query("SELECT a FROM Autor a WHERE a.nombre like :nombre%")
	public Optional<Autor> buscarAutor(@Param("nombre")String nombre);


}

