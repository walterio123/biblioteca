package com.walterio123.libreria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.walterio123.libreria.entity.Cliente;

import com.walterio123.libreria.service.ClienteService;

@Controller
@RequestMapping("/")
public class MainController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/")
	public String index() {
		return "inicio-1";
	}
	//@GetMapping("/inicio")
	//public String inicio() {
		//return "inicio-1";
	//}
	
	//To do implementar seguridad
	
	@GetMapping("/login") 
    public String login( ModelMap modelo,@RequestParam String apellido,String password){
       //buscando el la base de datos si el id corresponde a un cliente
	try {
		Cliente cliente=clienteService.buscarClientePorApellido(apellido);
		if(cliente.getPassword().equals(password)) {
			System.out.println("Password correcto ENTRO");
			modelo.put("exito", "Bienvenido");
			return "redirect:/libro/catalogo";
		}else {
			modelo.put("error", "El password no corresponde al Usuario.");
			System.out.println("Password incorrecto");
			throw new Exception();
		}
	} catch (Exception e) {
		System.out.println("No existe el cliente");
		e.printStackTrace();
		return "inicio-1";
	}
	
		
	
    }  

}
