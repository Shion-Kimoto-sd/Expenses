package com.example.demo;

public class PieDate {

	//登録されたカテゴリ名ごとの合計値
	int total;

	//登録されたカテゴリ名
	String categoryName;


	//コンストラクタ
	public PieDate() {

	}


	public PieDate(int total, String categoryName) {
		super();
		this.total = total;
		this.categoryName = categoryName;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




}