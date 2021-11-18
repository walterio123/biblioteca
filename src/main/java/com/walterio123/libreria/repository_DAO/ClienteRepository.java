package com.walterio123.libreria.repository_DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.walterio123.libreria.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

	
	
	@Query("SELECT c FROM Cliente c ")
	public List<Cliente>listarCliente();
	
	@Query("SELECT c FROM Cliente c WHERE c.estado IS NOT FALSE")
	public List<Cliente>listarclienteDeAlta();
	
	@Query("SELECT c FROM Cliente c WHERE c.id= :id")
	public Cliente buscarPorId(@Param("id") String id);
	
	@Query("Select a From Cliente a WHERE a.id= :id")
	public void deleteById(String id);
	
	@Query("SELECT a FROM Cliente a WHERE a.nombre like :nombre%")
	public List<Cliente> buscarPorNombre(@Param("nombre") String nombre); 
	
	@Query("SELECT a FROM Cliente a WHERE a.nombre like :nombre%")
	public Optional<Cliente> buscarCliente(@Param("nombre")String nombre);

}
