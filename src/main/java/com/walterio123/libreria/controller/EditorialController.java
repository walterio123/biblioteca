package com.walterio123.libreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walterio123.libreria.entity.Autor;
import com.walterio123.libreria.entity.Editorial;
import com.walterio123.libreria.service.AutorService;
import com.walterio123.libreria.service.EditorialService;

@Service
@RequestMapping("/editorial")
public class EditorialController {
	@Autowired
	 private EditorialService editorialService; 

	@RequestMapping("/editorial")
	public String editorial() {
		
		return "editorial";
	}
	@RequestMapping("/lista")
	public String lista(ModelMap model) {
		List<Editorial>listaEditorial=editorialService.listaEditoriales();
		model.addAttribute("editoriales", listaEditorial);
		
		return "editorialLista";
	}
	
	@PostMapping("/registrar")
	public String registrar(ModelMap modelo, @RequestParam String nombre) {
		try {
			editorialService.registrar(nombre);
			modelo.put("message", "Ingreso exitoso");
			
		} catch (Exception e) {
			modelo.put("error", "No se registro al autor por falta de nombre...");
			e.printStackTrace();
		}
		
		return"editorial.html";
	}
	@PostMapping("/baja")
	public String baja(ModelMap modelo, @RequestParam String nombre) {
		try {
			editorialService.baja(nombre);
			modelo.put("message2", "Modificacion exitosa.");
			
		} catch (Exception e) {
			modelo.put("error2", "No se Modifico por no existir...");
			e.printStackTrace();
		}
		
		return"editorial.html";
	}
	@GetMapping("/baja/{id}")
	public String darBaja(ModelMap modelo, @PathVariable String id) {
		try {
			editorialService.darbaja(id);					
			modelo.put("message2", "Modificacion exitosa.");
			
		} catch (Exception e) {
			modelo.put("error2", "No se Modifico por no existir...");
			System.out.println("No se registro");//eliminar
			e.printStackTrace();
		}
		
		return "redirect:/editorial/lista";
	}
	@GetMapping("/alta/{id}")
    public String alta(@PathVariable String id,ModelMap modelo){
        try{
            editorialService.daralta(id);
            modelo.put("message3", "Alta exitosa.");
           
        }catch(Exception e){
        	modelo.put("error3", "No se pudo dar alta..");
           
        }
        return "redirect:/editorial/lista";
	}
	
}
