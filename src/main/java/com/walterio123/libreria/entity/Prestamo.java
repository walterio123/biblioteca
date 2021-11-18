package com.walterio123.libreria.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Prestamo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")

	    private String id;
	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date fechaPrestamo;
	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date fechaDevolucion;
	    private boolean alta;
	    
	    public boolean isAlta() {
			return alta;	
		}


		public void setAlta(boolean alta) {
			this.alta = alta;
		}


		@OneToOne
	    private Book book;
	    @OneToOne
	    private Cliente cliente;
	    
	    
		public Prestamo( Date fechaPrestamo, Date fechaDevolucion) {
			this.fechaPrestamo = fechaPrestamo;
			this.fechaDevolucion = fechaDevolucion;
		}


		public Prestamo() {
			
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public Date getFechaPrestamo() {
			return fechaPrestamo;
		}


		public void setFechaPrestamo(Date fechaPrestamo) {
			this.fechaPrestamo = fechaPrestamo;
		}


		public Date getFechaDevolucion() {
			return fechaDevolucion;
		}


		public void setFechaDevolucion(Date fechaDevolucion) {
			this.fechaDevolucion = fechaDevolucion;
		}


		@Override
		public String toString() {
			return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion
					+ ", book=" + book + ", cliente=" + cliente + "]";
		}


		public Book getBook() {
			return book;
		}


		public void setBook(Book book) {
			this.book = book;
		}


		public Cliente getCliente() {
			return cliente;
		}


		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

	    
	    
}
