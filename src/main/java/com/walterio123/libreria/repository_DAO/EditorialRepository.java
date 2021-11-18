package com.walterio123.libreria.repository_DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.entity.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {

	@Query("SELECT c FROM Editorial c  ORDER BY c.nombre DESC ")
	public List<Editorial>listarEditorial();
	
	@Query("SELECT c FROM Editorial c WHERE c.id= :id")
	public Editorial buscarPorId(@Param("id") String id);
	
	@Query("Select a From Editorial a WHERE a.id= :id")
	public void deleteById(String id);
	
	@Query("SELECT a FROM Editorial a WHERE a.nombre like :nombre%")
	public List<Autor> buscarPorNombre(@Param("nombre") String nombre); 
	
	@Query("SELECT a FROM Editorial a WHERE a.nombre like :nombre%")
	public Optional<Editorial> buscarEditorial(@Param("nombre")String valor);
}
