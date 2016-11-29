package com.twitter.app.entity;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {

	private String nome;
	private Date data;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
