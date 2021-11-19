package com.walterio123.libreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.walterio123.libreria.entity.Cliente;
import com.walterio123.libreria.repository_DAO.ClienteRepository;
import com.walterio123.libreria.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/cliente")
	public String registrar() {	
		return "suscripcion";
	}
	
	@PostMapping("/cliente")
	public String registrar(MultipartFile archivo, ModelMap modelo, @RequestParam Long documento,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String telefono,@RequestParam String password,@RequestParam String password2) {
		
	
		try {
			clienteService.registrar(archivo, documento, nombre, apellido, telefono, password,password2);
			modelo.put("exito", "El cliente Fue dado de alta con exito");
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			
			e.printStackTrace();
		}
		
		return "suscripcion";
	}
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		List<Cliente>clientes=clienteRepository.findAll();
		modelo.put("clientes", clientes);
		return "clienteLista";
	}
	
	@GetMapping("/modificar/{id}")
	public String modificar(ModelMap modelo,@PathVariable String id) {
		
		try {
			modelo.put("cliente", clienteService.buscarClientePorId(id));
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "clienteModificacion";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificar(MultipartFile archivo, ModelMap modelo, @PathVariable String id,@RequestParam Long documento,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String telefono,@RequestParam String password) {
		try {
			modelo.put("exito", "DIO DE BAJA EL CLIENTE");
			clienteService.modificar(archivo, id, documento, nombre, apellido, telefono, password);
			System.out.println("Modifico EL CLIENTE");
			return "redirect:/cliente/lista";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			e.printStackTrace();
			System.out.println("NO modifico EL CLIENTE");
		}
		
		return "redirect:/cliente/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(ModelMap modelo,@PathVariable String id) {
		
		try {
			clienteService.eliminar(id);
			System.out.println("Eliminado");
		} catch (Exception e) {
			System.out.println("NO eliminado");
			e.printStackTrace();
		}
		
		return "redirect:/cliente/lista";
	}
	@GetMapping("/darAlta/{id}")
	public String darAlta(ModelMap modelo,@PathVariable String id) {
		
		try {
			clienteService.darAlta(id);
			System.out.println("dado de alta");
		} catch (Exception e) {
			System.out.println("NO dado de alta");
			e.printStackTrace();
		}
		
		return "redirect:/cliente/lista";
	}
}
