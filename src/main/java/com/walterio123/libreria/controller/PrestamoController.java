package com.walterio123.libreria.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.entity.Book;
import com.walterio123.libreria.entity.Cliente;
import com.walterio123.libreria.entity.Prestamo;
import com.walterio123.libreria.repository_DAO.BookRepository;
import com.walterio123.libreria.repository_DAO.ClienteRepository;
import com.walterio123.libreria.repository_DAO.PrestamoRepository;
import com.walterio123.libreria.service.PrestamoService;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@GetMapping("/prestamo")
	public String prestamo(ModelMap modelo) {
		//agregando toda la lista de clientes(mejorar a los clientes solo activos)
		List<Cliente>clientes=clienteRepository.listarclienteDeAlta();
		modelo.put("clientes", clientes);
	
		
		//agregando la lista de libros para alquilar
		List<Book>catalogo=bookRepository.findAll();
		modelo.put("catalogo", catalogo);
		
		return "prestamo";
	}
	@PostMapping("/prestar")
	public String prestar(ModelMap modelo, @RequestParam String  idcliente, @RequestParam String idlibro,@RequestParam (required = false) @DateTimeFormat(pattern = "yyyy-MM-dd" )Date fechaPrestamo,@RequestParam (required = false) @DateTimeFormat(pattern = "yyyy-MM-dd" )Date fechaDevolucion) {
		List<Cliente>clientes=clienteRepository.listarclienteDeAlta();
		modelo.put("clientes", clientes);
		
		List<Book>catalogo=bookRepository.findAll();
		modelo.put("catalogo", catalogo);
		
		
		try {
		prestamoService.prestamo(idlibro, idcliente,fechaPrestamo,fechaDevolucion);
		
		modelo.put("exito", "Ingreso exitoso del prestamo");
		//return "redirect:/prestamo/prestamo";
	} catch (Exception e) {
		
		modelo.put("error", e.getMessage());
		e.printStackTrace();
		
	}
		return "prestamo";
	}
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		//listando los prestamos
		List<Prestamo>prestamos=prestamoRepository.buscarSoloAlta();
		modelo.put("prestamos", prestamos);	
		
		return "listaPrestamo";
	}
	@GetMapping("/modificar/{id}")
	public String modificar(ModelMap modelo,@PathVariable String id) {
		//List<Cliente>clientes=clienteRepository.findAll();
		//modelo.put("clientes", clientes);
		
		//List<Book>catalogo=bookRepository.findAll();
		//modelo.put("catalogo", catalogo);
		
		try {
			modelo.put("prestamo", prestamoRepository.buscarPorId(id));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return "prestamoModificar";
		
	}
	@PostMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, ModelMap modelo ,@RequestParam (required = false) @DateTimeFormat(pattern = "yyyy-MM-dd" )Date fechaPrestamo,@RequestParam (required = false) @DateTimeFormat(pattern = "yyyy-MM-dd" )Date fechaDevolucion) {	
			try {
				prestamoService.modificar(id, fechaPrestamo, fechaDevolucion);
				modelo.put("exito", "modificado con exito");
				System.out.println("Modificado el libro");
				return "redirect:/prestamo/lista";
			} catch (Exception e) {
				modelo.put("error", "No se pudo modificar el libro,revise los datos ingresados");
				e.printStackTrace();
				return "redirect:/prestamo/lista";
			}
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(ModelMap modelo,@PathVariable String id) {
		try {
			prestamoService.eliminar_baja(id);
			modelo.put("exito", "Dio de baja con exito");
			System.out.println("Eliminado");
		}catch(Exception e){
        	System.out.println("NO LO ELIMINO");
        	modelo.put("error", e.getMessage());
           System.out.println("NO ELIMINADO");
        }
		
		
		return "redirect:/prestamo/lista";
		
	}
}
