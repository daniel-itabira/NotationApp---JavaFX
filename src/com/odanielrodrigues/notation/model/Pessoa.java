package com.odanielrodrigues.notation.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {
	private StringProperty nome; 
	private StringProperty sobrenome;
	private ObjectProperty<LocalDate> data;

	public Pessoa() {
		this(null, null, null);
	}

	public Pessoa(String nome, String sobrenome, LocalDate data) {
		super();
		this.nome = new SimpleStringProperty(nome);
		this.sobrenome = new SimpleStringProperty(sobrenome);
		this.data = new SimpleObjectProperty<LocalDate>(data);
	}

	public String getNome() {
		return this.nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public String getSobrenome() {
		return this.sobrenome.get();
	}

	public void setSobrenome(String sobrenome) {
		this.nome.set(sobrenome);
	}

	public StringProperty sobrenomeProperty() {
		return this.sobrenome;
	}

	public LocalDate getData() {
		return this.data.get();
	}

	public void setData(LocalDate data) {
		this.data.set(data);
	}

	public ObjectProperty<LocalDate> dataProperty() {
		return this.data;
	}

}
