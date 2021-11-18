package com.walterio123.libreria.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.repository_DAO.AutorRepository;


@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Transactional
	public void registrar(String nombre) throws Exception {
		
		Autor autor=new Autor();
		if(!nombre.isEmpty()) {
			autor.setNombre(nombre);
			autor.setAlta(true);
			autorRepository.save(autor);
		}else {
			throw new Exception();
			
		}
		
		
	}
	@Transactional
	public void baja(String nombre) throws Exception{
		//buscando el usuario que queremos dar de baja
		Optional<Autor>respuestaOptional= autorRepository.buscarAutor(nombre);	
		//si esta creamos un autor y le seteamos los datos
		if(respuestaOptional.isPresent()) {
			Autor autor=respuestaOptional.get();
			autor.setAlta(false);
			autorRepository.save(autor);
		}else {
			throw new Exception();
			
		}
	}
	@Transactional
	public List<Autor>listaAutors(){
		
		return autorRepository.findAll();
	}
	
	public void deleteById(String id) {
		autorRepository.deleteById(id);
		
	}
	@Transactional
	public void eliminarAutor(String id) {
		Autor autor=autorRepository.buscarPorId(id);
		autorRepository.delete(autor);
	}

}
