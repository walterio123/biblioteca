package com.walterio123.libreria.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walterio123.libreria.entity.Book;
import com.walterio123.libreria.entity.Cliente;
import com.walterio123.libreria.repository_DAO.ClienteRepository;
import com.walterio123.libreria.repository_DAO.FotoRepository;
import com.walterio123.libreria.service.BookService;
import com.walterio123.libreria.service.ClienteService;
import com.walterio123.libreria.service.FotoService;


@Controller
@RequestMapping("/foto")
public class FotoController {

	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private BookService bookService;
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/libro/{id}")
	public ResponseEntity<byte[]> fotoBook(@PathVariable String id){
		//buscando el libro dueño de la foto
		Book book;
		try {
			book = bookService.buscarbookPorId(id);
			//si el usuario no tiene foto
			if (book.getFoto()== null) {
				throw new Exception("El libro no tiene foto para mostrar");
			}
			byte[] foto= book.getFoto().getContenido();
			
			//creando las cabeceras necesarias para devolver una imagen
			//para indicarle al navegador que va a mostrar una foto
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			//devolviendo un response entity
			return new ResponseEntity<>(foto, headers , HttpStatus.OK);
		} catch (Exception ex) {
			 Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	 @GetMapping("/cliente/{id}")
	    public ResponseEntity<byte[]> fotoMascota(@PathVariable String id){
	        try {
	            Cliente cliente = clienteService.buscarClientePorId(id);
	            if(cliente.getFoto() == null){
	                throw new Exception("El usuario no tiene una foto asignada");               
	            }
	            byte[] foto = cliente.getFoto().getContenido();
	            
	          //creando las cabeceras necesarias para devolver una imagen
				//para indicarle al navegador que va a mostrar una foto
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG);
	          //devolviendo un response entity
	            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
	        } catch (Exception ex) {
	            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }       
	    } 
}
