package com.walterio123.libreria.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.walterio123.libreria.entity.Foto;
import com.walterio123.libreria.repository_DAO.FotoRepository;


@Service
public class FotoService {

	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public Foto guardarFoto(MultipartFile archivo) throws Exception{
		//comprobando que el archivo no esta vacio
		if (archivo != null ) {
			try {
				Foto foto=new Foto();
				foto.setMime(archivo.getContentType());
				foto.setNombre(archivo.getName());
				foto.setContenido(archivo.getBytes());
				
				return fotoRepository.save(foto);
						
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.out.println("ERROR AL CARGAR LA FOTO");
			}
		}
		// si esta vacio devolvemos un null(Sirve)
		return null;
	}
	
	@Transactional
	public  Foto actualizarFoto(String idFoto,MultipartFile archivo) throws Exception{
		
		//comprobando que el archivo no esta vacio
		if (archivo != null) {
			try {
				Foto foto=new Foto();
				//comprobando si la foto existia
				if (idFoto !=null) {
					//buscanmos la foto
					Optional<Foto>respuestaOptional=fotoRepository.findById(idFoto);
					foto=respuestaOptional.get();
				}
				
				foto.setMime(archivo.getContentType());
				foto.setNombre(archivo.getName());
				foto.setContenido(archivo.getBytes());
				
				return fotoRepository.save(foto);
						
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		// si esta vacio devolvemos un null(Sirve)
		return null;
	}
}
