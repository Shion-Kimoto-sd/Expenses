package com.example.demo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="moneydetail")
public class money {
	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer code;

	@Column(name="id")
	private Integer id;


	@Column(name="category")
	private String category;

	@Column(name="flug")
	private Integer flug;

	@Column(name="date")
	private Date date;

	@Column(name="cost")
	private Integer cost;

	public money(Integer id, String category, Integer flug, Date date, Integer cost) {
		super();
		this.id = id;
		this.category = category;
		this.flug = flug;
		this.date = date;
		this.cost = cost;
	}

	public money() {

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getFlug() {
		return flug;
	}

	public void setFlug(Integer flug) {
		this.flug = flug;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}


}
