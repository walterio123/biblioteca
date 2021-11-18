package com.walterio123.libreria.repository_DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.walterio123.libreria.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, String> {

	@Query("SELECT c FROM Prestamo c WHERE c.id= :id AND c.alta IS NOT FALSE")
	public Prestamo buscarPorId(@Param("id") String id);
	
	@Query("SELECT c FROM Prestamo c WHERE c.alta IS NOT FALSE")
	public List<Prestamo> buscarSoloAlta();
	
}
