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

	@Column(name="id")
	private Integer id;


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



	public Month(Integer code, Integer year, Integer month, Integer intotal, Integer outtotal, Integer total) {
		super();
		this.code = code;
		this.year = year;
		this.month = month;
		this.intotal = intotal;
		this.outtotal = outtotal;
		this.total = total;
	}

	public Month(Integer year, Integer month, Integer intotal, Integer outtotal, Integer total) {
		super();
		this.year = year;
		this.month = month;
		this.intotal = intotal;
		this.outtotal = outtotal;
		this.total = total;
	}

	public Month() {

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