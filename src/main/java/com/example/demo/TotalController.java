package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TotalController {

	@Autowired
	HttpSession session;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	MoneyRepository moneyRepository;

	@Autowired
	MonthRepository monthRepository;

	@Autowired
	YearRepository yearRepository;

	//年間レポートへ-------------------------------------------
	@RequestMapping("/year")
	public ModelAndView yearView(ModelAndView mv) {

		//年間レポートテーブルから全データ取得
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//カテゴリーテーブルから全データ取得

		List<Year> yearList = yearRepository.findByUid(uid);

		//表示する年間レポートテーブル
		mv.addObject("yearList", yearList);

		//表示する年のテーブル(default)
		mv.addObject("year", 2020);

		//year.htmlへ
		mv.setViewName("year");

		return mv;
	}
	//年間レポートへ-------------------------------------------
	@PostMapping("/year")
	public ModelAndView yearSelect(
			ModelAndView mv,
			@RequestParam("YEAR") int year
			) {

		//年間レポートテーブルから全データ取得
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//カテゴリーテーブルから全データ取得

		List<Year> yearList = yearRepository.findByUid(uid);

		//表示する年間レポートテーブル
		mv.addObject("yearList", yearList);

		//表示する年のテーブル(select)
		mv.addObject("year", year);

		//year.htmlへ
		mv.setViewName("year");

		return mv;
	}

	//月間レポートへ-------------------------------------
	@RequestMapping("/month")
	public ModelAndView monthView(ModelAndView mv) {

		//月間レポートテーブルから全データ取得
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		List<Month> monthList = monthRepository.findByUid(uid);

		//表示する月間レポートテーブル
		mv.addObject("monthList", monthList);

		//表示するテーブルの年月(default)
		mv.addObject("year" , 2020);
		mv.addObject("month", 1);


		//month.htmlへ
		mv.setViewName("month");

		return mv;
	}

	//月間レポートへ-------------------------------------
	@PostMapping("/month")
	public ModelAndView monthSelect(
			ModelAndView mv,
			@RequestParam("YEAR") int year,
			@RequestParam("MONTH") int month
			) {

		//月間レポートテーブルから全データ取得
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		List<Month> monthList = monthRepository.findByUid(uid);

		//表示する月間レポートテーブル
		mv.addObject("monthList", monthList);

		//表示するテーブルの年月(select)
		mv.addObject("year" , year);
		mv.addObject("month", month);


		//month.htmlへ
		mv.setViewName("month");

		return mv;
	}




}
