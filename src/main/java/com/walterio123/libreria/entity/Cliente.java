package com.walterio123.libreria.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cliente implements Serializable {
	
	
	  private static final long serialVersionUID = 1L;
	  @Id
		@GeneratedValue(generator = "uuid")
		@GenericGenerator(name = "uuid", strategy = "uuid2")

	    private String id;
	    private Long documento;
	    private String nombre;
	    private String apellido;
	    private String telefono;
	    private String password;
	    private boolean estado;
	    
	    @OneToOne
	    private Foto foto;
	    
	    public Cliente() {
	    }

	    public Cliente(Long documento, String nombre, String apellido, String telefono,String password) {
	        this.documento = documento;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.telefono = telefono;
	        this.password=password;
	    }
	    
	    
	    
	    

	    public Foto getFoto() {
			return foto;
		}

		public void setFoto(Foto foto) {
			this.foto = foto;
		}

		public boolean isEstado() {
			return estado;
		}

		public void setEstado(boolean estado) {
			this.estado = estado;
		}

		public Long getDocumento() {
	        return documento;
	    }

	    public void setDocumento(Long documento) {
	        this.documento = documento;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getApellido() {
	        return apellido;
	    }

	    public void setApellido(String apellido) {
	        this.apellido = apellido;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }
	      
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    
}
