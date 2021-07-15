package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="piedata")
public class PieData {

	//登録されたデータのid
	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer code;

	//登録されたカテゴリ名
	@Column(name="name")
	String name;

	//登録されたカテゴリ名ごとの合計値
	@Column(name="cost")
	int cost;



	//コンストラクタ
	public PieData() {

	}



	public PieData(Integer code, String name, int cost) {
		super();
		this.code = code;
		this.name = name;
		this.cost = cost;
	}



	public PieData(String name, int cost) {
		super();
		this.name = name;
		this.cost = cost;
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



	public int getCost() {
		return cost;
	}



	public void setCost(int cost) {
		this.cost = cost;
	}



}