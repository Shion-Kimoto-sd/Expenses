package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpensesConotroller {


	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	MoneyRepository moneyRepository;

	//トップページへ
	//http://localhost:8080/
	@GetMapping("/")
	public ModelAndView top(ModelAndView mv) {

		//top.htmlへ
		mv.setViewName("top");

		return mv;
	}

	//収入登録入力画面へ
	//http://localhost:8080/in
	@GetMapping("/in")
	public ModelAndView inMoney(ModelAndView mv) {

		List<Category> categoryList = categoryRepository.findAll();

		mv.addObject("category", categoryList);
		for(Category c : categoryList) {
			System.out.println(c.getName());
		}

		//in.htmlへ
		mv.setViewName("in");

		return mv;
	}

	//収入一覧画面へ
	@GetMapping("/inDisp")
	public ModelAndView inDisp(ModelAndView mv) {

		//inDisp.htmlへ
		mv.setViewName("inDisp");

		return mv;
	}

	@PostMapping("inAdd")
	public ModelAndView inAdd(ModelAndView mv) {

		//データベースに収入データ追加


		//収入一覧表示
		return inDisp(mv);
	}

	//支出登録入力画面へ
	@GetMapping("/out")
	public ModelAndView outMoney(ModelAndView mv) {

		//out.htmlへ
		mv.setViewName("out");

		return mv;
	}


	//支出一覧画面へ
	@GetMapping("/outDisp")
	public ModelAndView outDisp(ModelAndView mv) {

		//outDisp.htmlへ
		mv.setViewName("outDisp");

		return mv;
	}

	@PostMapping("outAdd")
	public ModelAndView outAdd(ModelAndView mv) {

		//データベースに支出データ追加


		//支出一覧表示
		return outDisp(mv);
	}


	//情報更新入力画面へ
	@PostMapping("/updataDisp")
	public ModelAndView UpdataDisp(
			@RequestParam("code") Integer code,
			ModelAndView mv
			) {
		mv.addObject("code", code);

		//updata.htmlへ
		mv.setViewName("updata");

		return mv;
	}

	//moneydetailテーブル更新
	@PostMapping("/updata")
	public ModelAndView Updata(
			@RequestParam("code") Integer code,
			ModelAndView mv
			) {

		//更新後一覧表示
		return top(mv);
	}


}
