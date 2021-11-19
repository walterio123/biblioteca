package com.walterio123.libreria.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;





@Entity
@Table(name = "Books")
public class Book implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")

	   
	    private String id;
		@Column(length = 50)
		@NotNull
	    private String titulo;
	    private Integer anio;
	    private Integer ejemplares;
	    private Integer ejemplaresPrestados;
	    private Integer ejemplaresRestantes;
	    private boolean alta;
	    
	    @OneToOne(cascade = CascadeType.REMOVE)
	    private Autor autor;
	    @OneToOne(cascade = CascadeType.REMOVE)
	    private Editorial editorial;
	    
	    @OneToOne
	    private Foto foto;
	    
		public Foto getFoto() {
			return foto;
		}


		public void setFoto(Foto foto) {
			this.foto = foto;
		}


		public Book() {
			
		}

		
		public Book(String id, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
				Integer ejemplaresRestantes, boolean alta) {
		
			this.id = id;
			this.titulo = titulo;
			this.anio = anio;
			this.ejemplares = ejemplares;
			this.ejemplaresPrestados = ejemplaresPrestados;
			this.ejemplaresRestantes = ejemplaresRestantes;
			this.alta = alta;
		}


		public String getId() {
			return id;
		}


		public void setIsbn(String id) {
			this.id = id;
		}


		public String getTitulo() {
			return titulo;
		}


		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}


		public Integer getAnio() {
			return anio;
		}


		public void setAnio(Integer anio) {
			this.anio = anio;
		}


		public Integer getEjemplares() {
			return ejemplares;
		}


		public void setEjemplares(Integer ejemplares) {
			this.ejemplares = ejemplares;
		}


		public Integer getEjemplaresPrestados() {
			return ejemplaresPrestados;
		}


		public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
			this.ejemplaresPrestados = ejemplaresPrestados;
		}


		public Integer getEjemplaresRestantes() {
			return ejemplaresRestantes;
		}


		public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
			this.ejemplaresRestantes = ejemplaresRestantes;
		}


		


		public Autor getAutor() {
			return autor;
		}


		public void setAutor(Autor autor) {
			this.autor = autor;
		}


		public Editorial getEditorial() {
			return editorial;
		}


		public void setEditorial(Editorial editorial) {
			this.editorial = editorial;
		}


		public boolean isAlta() {
			return alta;
		}


		public void setAlta(boolean alta) {
			this.alta = alta;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	   
		
}

