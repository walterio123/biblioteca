package com.walterio123.libreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.service.AutorService;
import com.walterio123.libreria.service.EditorialService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	
	@Autowired
	 private AutorService autorService; 

	@RequestMapping("/autor")
	public String autor() {
		
		return "autor";
	}
	@RequestMapping("/lista")
	public String lista(ModelMap model) {
		List<Autor>listaAutores=autorService.listaAutors();
		model.addAttribute("autores", listaAutores);
		
		return "autorLista.html";
	}
	
	@PostMapping("/registrar")
	public String registrar(ModelMap modelo, @RequestParam String nombre) {
		try {
			autorService.registrar(nombre);
			//prueba de datos
			System.out.println("nombre: "+nombre);
			modelo.put("message", "Ingreso exitoso");
			
		} catch (Exception e) {
			modelo.put("error", "No se registro al autor por falta de nombre...");
			System.out.println("No se registro");
			e.printStackTrace();
		}
		
		return"autor.html";
	}
	@PostMapping("/baja")
	public String baja(ModelMap modelo, @RequestParam String nombre) {
		try {
			autorService.baja(nombre);
			//prueba de datos
			System.out.println("nombre: "+nombre);//eliminar
			modelo.put("message2", "Modificacion exitosa.");
			
		} catch (Exception e) {
			modelo.put("error2", "No se Modifico por no existir...");
			System.out.println("No se registro");//eliminar
			e.printStackTrace();
		}
		
		return"autor.html";
	}
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id,ModelMap modelo){
        try{
            autorService.eliminarAutor(id);
            modelo.put("message3", "Eliminacion exitosa.");
            System.out.println("SUPUESTAMENTE LO ELIMINO");
            return "autorLista";
        }catch(Exception e){
        	modelo.put("error3", "No se Elimino por tener libro...");
        	System.out.println("NO LO ELIMINO");
            return "autorLista";
        }
	}
}
