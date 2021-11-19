package com.walterio123.libreria.service;


import java.util.Optional;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.entity.Book;
import com.walterio123.libreria.entity.Cliente;
import com.walterio123.libreria.entity.Editorial;
import com.walterio123.libreria.repository_DAO.AutorRepository;
import com.walterio123.libreria.repository_DAO.BookRepository;
import com.walterio123.libreria.repository_DAO.EditorialRepository;



@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private EditorialRepository editorialRepository;
	
	@Transactional
	public void registrar(String titulo,Integer anio,Integer ejemplares,Integer ejemplaresPrestados,Integer ejemplaresRestantes,String idautor,String ideditorial) throws Exception {
		
		//buscar el autor ingresado
		Autor autor=autorRepository.buscarPorId(idautor);
		
		//buscar la editorial ingresada
		Editorial editorial=editorialRepository.buscarPorId(ideditorial);
		//validando que todos los datos esten bien
		validarDatos(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
		
		Book book=new Book();
		book.setTitulo(titulo);
		book.setAnio(anio);
		book.setEjemplares(ejemplares);
		book.setEjemplaresPrestados(ejemplaresPrestados);
		book.setEjemplaresRestantes(ejemplaresRestantes);
		book.setAutor(autor);
		book.setEditorial(editorial);
		
		//guardando el ente creado y seteado
		bookRepository.save(book);
		
	}
	

	@Transactional
	public void modificar(String id, String titulo,Integer anio,Integer ejemplares,Integer ejemplaresPrestados,Integer ejemplaresRestantes,String idautor,String ideditorial) throws Exception {
		
		//buscando si el libro que intenta modificar existe
		
		Optional<Book>respuestaOptional=bookRepository.findById(id);
		if(respuestaOptional.isPresent()) {
			
			Book book=respuestaOptional.get();

			//buscar el autor ingresado
			Autor autor=autorRepository.buscarPorId(idautor);
			
			//buscar la editorial ingresada
			Editorial editorial=editorialRepository.buscarPorId(ideditorial);
			validarDatos(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
			book.setTitulo(titulo);
			book.setAnio(anio);
			book.setEjemplares(ejemplares);
			book.setEjemplaresPrestados(ejemplaresPrestados);
			book.setEjemplaresRestantes(ejemplaresRestantes);
			book.setAutor(autor);
			book.setEditorial(editorial);
			
			//guardando los cambios si todo esta correcto
			bookRepository.save(book);
			
			
		}else {
			System.out.println("Error al modificar el libro");
			throw new Exception();
					}
	}
	public void eliminar(String id) throws Exception {
		Optional<Book>respuetaOptional=bookRepository.findById(id);
		if (respuetaOptional.isPresent()) {
			Book book=respuetaOptional.get();
			book.setAlta(false);
			bookRepository.save(book);
		}else {
			System.err.println("No existe el libro que queres dar de baja");
			throw new Exception();
		}
		
	}
	public void darAlta(String id) throws Exception {
		Optional<Book>respuetaOptional=bookRepository.findById(id);
		if (respuetaOptional.isPresent()) {
			Book book=respuetaOptional.get();
			book.setAlta(true);
			bookRepository.save(book);
		}else {
			System.err.println("No existe el libro que queres dar de alta");
			throw new Exception();
		}
		
	}
	public Book buscarbookPorId(String id) throws Exception {
		
		Optional<Book>respuestaOptional=bookRepository.findById(id);
		if(respuestaOptional.isPresent()) {
			return respuestaOptional.get();
		}else {
			
			throw new Exception("El libro que busca por ese id no existe.");
		}
		
	}
	
	
	
	

	public void validarDatos(String titulo,Integer anio,Integer ejemplares,Integer ejemplaresPrestados,Integer ejemplaresRestantes,Autor autor,Editorial editorial)  throws Exception{

		if(titulo == null || titulo.isEmpty()) {
			throw new Exception("El titulo no puede ser nulo....");
			
		}
		if(anio == null ) {
			throw new Exception("El anio no puede ser nulo....");
			
		}
		if(ejemplares == null ) {
			throw new Exception("Faltan la cantidad de ejemplares.");
			
		}
		if(ejemplaresPrestados == null ) {
			throw new Exception("Faltan la cantidad de ejemplares Prestados.");
			
		}
		if(ejemplaresRestantes == null ) {
			throw new Exception("Faltan la cantidad de ejemplares Resptantes.");
			
		}
		if (autor == null ) {
			throw new Exception("Faltan el Autor.");
		}
		if (editorial == null ) {
			throw new Exception("Faltan la Editorial.");
		}
		
		
	}
	public void deleteById(String id) {
		bookRepository.deleteById(id);
		
	}
}
