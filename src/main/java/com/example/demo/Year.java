package com.example.demo;

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
	private Integer uid;


	@Column(name="year")
	private Integer year;

	@Column(name="intotal")
	private Integer intotal;

	@Column(name="outtotal")
	private Integer outtotal;

	@Column(name="total")
	private Integer total;

	//コンストラクタ
	public Year(Integer code, Integer uid, Integer year, Integer intotal, Integer outtotal, Integer total) {
		super();
		this.code = code;
		this.uid = uid;
		this.year = year;
		this.intotal = intotal;
		this.outtotal = outtotal;
		this.total = total;
	}

	public Year(Integer uid, Integer year, Integer intotal, Integer outtotal, Integer total) {
		super();
		this.uid = uid;
		this.year = year;
		this.intotal = intotal;
		this.outtotal = outtotal;
		this.total = total;
	}

	public Year() {

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

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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