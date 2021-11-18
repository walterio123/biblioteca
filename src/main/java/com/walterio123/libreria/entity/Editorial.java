package com.walterio123.libreria.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

@Entity
public class Editorial implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")

	    private String id;
	    
	    @NotNull
	    @Column(unique = true)
	    private String nombre;
	    private Boolean alta;
	    
	    
		public Editorial() {
			
		}

		public Editorial(String nombre, Boolean alta) {
			this.nombre = nombre;
			this.alta = alta;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Boolean getAlta() {
			return alta;
		}

		public void setAlta(Boolean alta) {
			this.alta = alta;
		}
	    
	    
	    
}
