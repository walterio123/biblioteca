package com.walterio123.libreria.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.walterio123.libreria.entity.Cliente;

import com.walterio123.libreria.repository_DAO.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public void registrar(Long documento,String nombre,String apellido,String telefono,String password,String password2) throws Exception {
		//validando que los datos ingresados sean utiles 
		validarDatos(documento, nombre, apellido, telefono, password,password2);
		
		Cliente cliente=new Cliente();
		cliente.setDocumento(documento);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setTelefono(telefono);
		cliente.setPassword(password);
		//seteado siempre en true cuando se da de alta
		cliente.setEstado(true);
		
		clienteRepository.save(cliente);
		
	}
	
	@Transactional
	public void modificar(String id, Long documento,String nombre,String apellido,String telefono,String password) throws Exception {
		//buscando si el id que ingreso corresponde a un cliente que exista
		
		Optional<Cliente>respuestaOptional=clienteRepository.findById(id);
		if(respuestaOptional.isPresent()) {
			Cliente cliente=respuestaOptional.get();
			cliente.setDocumento(documento);
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setTelefono(telefono);
			cliente.setPassword(password);
			cliente.setEstado(true);
			clienteRepository.save(cliente);
		}else {
			System.out.println("Error al modificar el cliente");
			throw new Exception();	
		}
	}
	public void eliminar(String id) throws Exception {
		Optional<Cliente>respuetaOptional=clienteRepository.findById(id);
		if (respuetaOptional.isPresent()) {
			Cliente cliente=respuetaOptional.get();
			cliente.setEstado(false);
			clienteRepository.save(cliente);
		}else {
			System.err.println("No existe a quien queres dar de baja");
			throw new Exception();
		}
		
	}
	public void darAlta(String id) throws Exception {
		Optional<Cliente>respuetaOptional=clienteRepository.findById(id);
		if (respuetaOptional.isPresent()) {
			Cliente cliente=respuetaOptional.get();
			cliente.setEstado(true);
			clienteRepository.save(cliente);
		}else {
			System.err.println("No existe a quien queres dar de alta");
			throw new Exception();
		}
		
	}
	public Cliente buscarClientePorId(String id) throws Exception {
		
		Optional<Cliente>respuestaOptional=clienteRepository.findById(id);
		if(respuestaOptional.isPresent()) {
			return respuestaOptional.get();
		}else {
			
			throw new Exception("El cliente que Busca por ese id no existe.");
		}
		
	}
public Cliente buscarClientePorApellido(String apellido) throws Exception {
		
		Cliente cliente=clienteRepository.buscarCliente(apellido);
		if(cliente != null) {
			return cliente;
		}else {
			
			throw new Exception("El cliente que Busca por ese id no existe.");
		}
		
	}
	
	public void validarDatos(Long documento,String nombre,String apellido,String telefono,String password,String password2)  throws Exception{

		if(documento == null  ) {
			throw new Exception("Falta el documento o el campo esta vacio.");
			
		}
		if(nombre.isEmpty() || nombre == null ) {
			throw new Exception("Falta el nombre.");
			
		}
		if(apellido == null || apellido.isEmpty()) {
			throw new Exception("Falta el apellido.");
			
		}
		if(password == null || password.isEmpty() || password.length()<6) {
			throw new Exception("Falta la contraseña o es muy corta.");
			
		}
		
		if(!password.equals(password2)) {
			throw new Exception("Las contraseñas no son iguales");
		}
		
		
		
	}

}
