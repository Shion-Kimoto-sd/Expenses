package com.example.demo;

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
	//収入登録画面へ
	@GetMapping("/inDisp")
	public ModelAndView inMoney(ModelAndView mv) {

		//in.htmlへ
		mv.setViewName("in");

		return mv;
	}
	//支出登録画面へ
	@GetMapping("/outDisp")
	public ModelAndView outMoney(ModelAndView mv) {

		//out.htmlへ
		mv.setViewName("out");

		return mv;
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
