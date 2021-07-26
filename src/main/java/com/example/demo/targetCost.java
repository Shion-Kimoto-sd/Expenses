package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="target")
public class targetCost {

	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer code;

	@Column(name="uid")
	private Integer uid;

	@Column(name="targetcost")
	private Integer targetcost;

	@Column(name="month")
	private Integer month;

	@Column(name="year")
	private Integer year;

	public targetCost(Integer code, Integer uid, Integer targetCost, Integer month, Integer year) {
		super();
		this.code = code;
		this.uid = uid;
		this.targetcost = targetCost;
		this.month = month;
		this.year = year;
	}

	public targetCost(Integer uid, Integer targetCost, Integer month, Integer year) {
		super();
		this.uid = uid;
		this.targetcost = targetCost;
		this.month = month;
		this.year = year;
	}

	public targetCost() {

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

	public Integer getTargetCost() {
		return targetcost;
	}

	public void setTargetCost(Integer targetCost) {
		this.targetcost = targetCost;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}



}