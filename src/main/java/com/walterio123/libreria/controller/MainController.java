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
		return "index";
	}
	
	//To do implementar seguridad
	
	/*@GetMapping("/login") 
    public String login( ModelMap modelo,@RequestParam String id,String password){
       //buscando el la base de datos si el id corresponde a un cliente
		Optional<Cliente>respuestaOptional= clienteService.buscarClientePorId(id);
		if (respuestaOptional.isPresent()) {
			Cliente cliente=respuestaOptional.get();
			if (cliente.getPassword().equals(password)) {
				return "catalogo";
			}else {
				throw new Exception();
				modelo.put("error", "El password ingresado no corresponde al usuario");
				return "index";
			}
			
		}else {
			throw new Exception();
			modelo.put("error", "El nombre es incorrecto o no existe");
			return "index";
		}
	;
		
		
       
        return "index";
    }  */

}
