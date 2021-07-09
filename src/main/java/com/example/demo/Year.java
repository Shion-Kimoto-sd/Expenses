package com.example.demo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yeartotal")
public class Year {

	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer code;

	@Column(name="uid")
	private Integer id;


	@Column(name="year")
	private Integer year;

	@Column(name="intotal")
	private String category;

	@Column(name="flug")
	private Integer flug;

	@Column(name="date")
	private Date date;

	@Column(name="cost")
	private Integer cost;

	//コンストラクタ
	public Year(String category, Integer flug, Date date, Integer cost) {
		super();
		this.category = category;
		this.flug = flug;
		this.date = date;
		this.cost = cost;
	}

	public Year() {

	}

	public Year(Integer id, String category, Integer flug, Date date, Integer cost) {
		super();
		this.id = id;
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
