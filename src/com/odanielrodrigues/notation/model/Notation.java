package com.odanielrodrigues.notation.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Notation {
	
	private StringProperty criador;
	private StringProperty titulo;
	
	
	public Notation() {
		this(null, null);
	}
	
	public Notation(String criador, String titulo){
		super();
		this.criador = new SimpleStringProperty(criador);
		this.titulo = new SimpleStringProperty(titulo);

	}
	
	public String getCriador() {
		return this.criador.get();
	}

	public void setCriador(String criador) {
		this.criador.set(criador);
	}

	public StringProperty criadorProperty() {
		return this.criador;
	}

	public String getTitulo() {
		return this.titulo.get();
	}

	public void setTitulo(String titulo) {
		this.titulo.set(titulo);
	}

	public StringProperty tituloProperty() {
		return this.titulo;
	}
	
	
		
}
