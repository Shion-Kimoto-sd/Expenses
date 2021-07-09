package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="monthtotal")
public class Month {

	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer code;

	@Column(name="uid")
	private Integer uid;


	@Column(name="year")
	private Integer year;

	@Column(name="month")
	private Integer month;

	@Column(name="intotal")
	private Integer intotal;

	@Column(name="outtotal")
	private Integer outtotal;

	@Column(name="total")
	private Integer total;



	public Month(Integer id, Integer year, Integer month, Integer intotal, Integer outtotal, Integer total) {
		super();
		this.uid = id;
		this.year = year;
		this.month = month;
		this.intotal = intotal;
		this.outtotal = outtotal;
		this.total = total;
	}


	public Month() {

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


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Integer getMonth() {
		return month;
	}


	public void setMonth(Integer month) {
		this.month = month;
	}


	public Integer getIntotal() {
		return intotal;
	}


	public void setIntotal(Integer intotal) {
		this.intotal = intotal;
	}


	public Integer getOuttotal() {
		return outtotal;
	}


	public void setOuttotal(Integer outtotal) {
		this.outtotal = outtotal;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


}