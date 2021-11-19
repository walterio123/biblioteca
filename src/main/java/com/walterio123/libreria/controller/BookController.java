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
import com.walterio123.libreria.entity.Book;
import com.walterio123.libreria.entity.Cliente;
import com.walterio123.libreria.entity.Editorial;
import com.walterio123.libreria.repository_DAO.AutorRepository;
import com.walterio123.libreria.repository_DAO.BookRepository;
import com.walterio123.libreria.repository_DAO.ClienteRepository;
import com.walterio123.libreria.service.AutorService;
import com.walterio123.libreria.service.BookService;
import com.walterio123.libreria.service.EditorialService;



@Controller
@RequestMapping("/libro")
public class BookController {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private EditorialService editorialService;
	
	@RequestMapping("/libro")
	public String libro(ModelMap modelo) {
		//listando los autores
				List<Autor>autores=autorRepository.listarAutorAlta();
				modelo.put("autores", autores);	
				
				//listando la lista de editoriales
				List<Editorial>editoriales=editorialService.listaEditoriales();
				modelo.addAttribute("editoriales", editoriales);
		return "libro";
	}
	@RequestMapping("/catalogo")
	public String catalogo(ModelMap modelo) {
		List<Book>catalogo=bookRepository.findAll();
		//mostrando los posibles alquiladores
		List<Cliente>clientes=clienteRepository.findAll();
		modelo.put("clientes", clientes);
		//
		modelo.addAttribute("catalogo", catalogo);
		
		return "catalogo";
	}
	@PostMapping("/crear")
	public String registrarBook(ModelMap modelo, @RequestParam String titulo,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes,@RequestParam String idautor,@RequestParam String ideditorial) {
		//listando los autores
		List<Autor>autores=autorRepository.listarAutorAlta();
		modelo.put("autores", autores);	
		
		//listando la lista de editoriales
		List<Editorial>editoriales=editorialService.listaEditoriales();
		modelo.addAttribute("editoriales", editoriales);
		
		try {
			modelo.put("exito", "Se registro el libro exitosamente.");
			bookService.registrar(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idautor, ideditorial);
		} catch (Exception e) {
			System.out.println("Error al crear libro");
			e.printStackTrace();
			modelo.put("error", e.getMessage());
		}
		
		
		return "libro";
	}
	
	@GetMapping("/modificar/{id}")
	public String modificarBook(@PathVariable String id,ModelMap modelo) {
		//listando los autores
		List<Autor>autores=autorRepository.findAll();
		modelo.put("autores", autores);	
		
		//listando la lista de editoriales
		List<Editorial>editoriales=editorialService.listaEditoriales();
		modelo.addAttribute("editoriales", editoriales);
		 
		try {
			modelo.put("libro", bookService.buscarbookPorId(id));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return "libroModificar";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarBook(@PathVariable String id, ModelMap modelo ,@RequestParam String titulo,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes,@RequestParam String idautor,@RequestParam String ideditorial) {	
			try {
				bookService.modificar(id, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idautor, ideditorial);
				modelo.put("exito", "modificado con exito");
				System.out.println("Modificado el libro");
				return "redirect:/libro/catalogo";
			} catch (Exception e) {
				modelo.put("error", "No se pudo modificar el libro,revise los datos ingresados");
				e.printStackTrace();
				return "redirect:/libro/catalogo";
			}
		
		
	}
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id){
        try{
            bookService.deleteById(id);
        }catch(Exception e){
        	System.out.println("NO LO ELIMINO");           
        }
        return "redirect:/libro/catalogo";
    }
	
	@GetMapping("/alta/{id}")
    public String darAlta(@PathVariable String id){
        try{
            bookService.darAlta(id);            
        }catch(Exception e){
        	System.out.println("NO LO dio de alta");
            
        }
        return "redirect:/libro/catalogo";
    }

}
