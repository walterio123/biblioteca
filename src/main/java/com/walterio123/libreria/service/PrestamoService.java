package com.walterio123.libreria.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.walterio123.libreria.entity.Book;
import com.walterio123.libreria.entity.Cliente;
import com.walterio123.libreria.entity.Prestamo;
import com.walterio123.libreria.repository_DAO.BookRepository;
import com.walterio123.libreria.repository_DAO.ClienteRepository;
import com.walterio123.libreria.repository_DAO.PrestamoRepository;

@Service
public class PrestamoService {
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public String prestamo(String idlibro,String idcliente,Date fechaPrestamo ,Date fechaDevolucion ) throws Exception {
		
		validar(idlibro, idcliente, fechaPrestamo , fechaDevolucion);
		
		Cliente cliente=clienteRepository.findById(idcliente).get();
		Book book=bookRepository.findById(idlibro).get();
		
		Prestamo prestamo=new Prestamo();
		prestamo.setFechaPrestamo(fechaPrestamo);
		prestamo.setBook(book);
		prestamo.setCliente(cliente);
		prestamo.setFechaDevolucion(fechaDevolucion);
		prestamo.setAlta(true);
		
		prestamoRepository.save(prestamo);
		
		
		return"prestamo";
	
}
	
	public void modificar(String id, String idcliente, String idlibro, Date fechaPrestamo, Date fechaDevolucion) throws Exception {
		validar(idlibro, idcliente, fechaPrestamo , fechaDevolucion);
		Optional<Prestamo>respuestOptional=prestamoRepository.findById(id);
		if(respuestOptional.isPresent()) {
			Prestamo prestamo=respuestOptional.get();
			//buscando y creando cliente
			Cliente cliente=clienteRepository.findById(idcliente).get();
			//buscando y creando libro
			Book libro= bookRepository.findById(idlibro).get();
			prestamo.setCliente(cliente);
			prestamo.setBook(libro);
			prestamo.setAlta(true);
			prestamo.setFechaPrestamo(fechaPrestamo);
			prestamo.setFechaDevolucion(fechaDevolucion);
			
			prestamoRepository.save(prestamo);
		}else {
			throw new Exception("No se pudo modificar el prestamo");
		}	
		
	}
	
	public void eliminar_baja(String id) throws Exception {
		Optional<Prestamo>respuetaOptional=prestamoRepository.findById(id);
		if (respuetaOptional.isPresent()) {
			Prestamo prestamo=respuetaOptional.get();
			prestamo.setAlta(false);
			prestamoRepository.save(prestamo);
		}else {
			System.err.println("No existe el prestamo que queres dar de baja");
			throw new Exception();
		}
		
	}
	
	public Prestamo buscarPrestamoPorId(String id) throws Exception {
		
		Optional<Prestamo>respuestaOptional=prestamoRepository.findById(id);
		if(respuestaOptional.isPresent()) {
			return respuestaOptional.get();
		}else {
			
			throw new Exception("El prestamo que busca por ese id no existe.");
		}
		
	}
	
	public void validar(String idlibro,String idcliente ,Date fechaPrestamo ,Date fechaDevolucion) throws Exception {
		
		if(idlibro == null || idlibro.isEmpty()) {
			throw new Exception("Falta el libro");
		}
		if(idcliente == null || idcliente.isEmpty()) {
			throw new Exception("Falta el cliente");
		}
		if(fechaDevolucion == null) {
			throw new Exception("Falta la fecha de devolucion");
		}
		if(fechaPrestamo == null) {
			throw new Exception("Falta la fecha de prestamo");
		}
		if(fechaDevolucion.before(fechaPrestamo)) {
			throw new Exception("La fecha de devolucion no esta bien ingresada");
		}
		
	}
	
}