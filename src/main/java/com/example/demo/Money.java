package com.example.demo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="moneydetail")
public class Money {
	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer code;

	@Column(name="uid")
	private Integer uid;

	@Column(name="category")
	private String category;

	@Column(name="flug")
	private Integer flug;

	@Column(name="date")
	private LocalDate date;

	@Column(name="cost")
	private Integer cost;

	public Money() {

	}
	public Money(Integer code, Integer id, Integer flug, LocalDate date,String category, Integer cost) {
		super();
		this.code = code;
		this.uid = id;
		this.category = category;
		this.flug = flug;
		this.date = date;
		this.cost = cost;
	}



	public Money(Integer id, Integer flug, LocalDate date,String category, Integer cost) {
		super();
		this.uid = id;
		this.category = category;
		this.flug = flug;
		this.date = date;
		this.cost = cost;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer id) {
		this.uid = id;
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

	public LocalDate getDate() {
		return date;
	}

	public LocalDate getViewDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}



}
