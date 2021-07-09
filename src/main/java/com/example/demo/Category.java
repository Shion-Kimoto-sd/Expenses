package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer code;

	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	public Category() {

	}

	public Category(Integer code, String name) {
		super();
		this.code = code;
		this.name = name;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
