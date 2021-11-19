package com.walterio123.libreria.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.entity.Editorial;
import com.walterio123.libreria.repository_DAO.EditorialRepository;

@Service
public class EditorialService {
	
	@Autowired
	private EditorialRepository editorialRepository;

	@Transactional
	public void registrar(String nombre) throws Exception {
		
		Editorial editorial=new Editorial();
		if(!nombre.isEmpty()) {
			editorial.setNombre(nombre);
			editorial.setAlta(true);
			editorialRepository.save(editorial);
		}else {
			throw new Exception();
			
		}
		
		
	}
	@Transactional
	public void baja(String nombre) throws Exception{
		//buscando la editorial que queremos dar de baja
		Optional<Editorial>respuestaOptional= editorialRepository.buscarEditorial(nombre);	
		//si esta creamos una editorial y le seteamos los datos
		if(respuestaOptional.isPresent()) {
			Editorial editorial=respuestaOptional.get();
			editorial.setAlta(false);
			editorialRepository.save(editorial);
		}else {
			throw new Exception();
			
		}

	}
	@Transactional
	public List<Editorial>listaEditoriales(){
		
		return editorialRepository.findAll();
	}
	@Transactional
	public void darbaja(String id) throws Exception{
		//buscando el usuario que queremos dar de baja
		Optional<Editorial>respuestaOptional= editorialRepository.findById(id);	
		//si esta creamos un autor y le seteamos los datos
		if(respuestaOptional.isPresent()) {
			Editorial editorial=respuestaOptional.get();
			editorial.setAlta(false);
			editorialRepository.save(editorial);
		}else {
			throw new Exception();
			
		}
	}
	@Transactional
	public void daralta(String id) throws Exception{
		//buscando el usuario que queremos dar de baja
		Optional<Editorial>respuestaOptional= editorialRepository.findById(id);	
		//si esta creamos un autor y le seteamos los datos
		if(respuestaOptional.isPresent()) {
			Editorial editorial=respuestaOptional.get();
			editorial.setAlta(true);
			editorialRepository.save(editorial);
		}else {
			throw new Exception();
			
		}
	}
	

}
