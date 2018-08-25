package com.prestamo;

public class Country {
	private String name;
	private String descripion;
	public Country(String name, String descripion) {
		super();
		this.name = name;
		this.descripion = descripion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescripion() {
		return descripion;
	}
	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}
	
}
