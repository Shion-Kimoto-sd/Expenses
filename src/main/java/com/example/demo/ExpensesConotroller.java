package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpensesConotroller {

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


//トップページへ-------------------------------------------------------------
	//http://localhost:8080/
	@GetMapping("/")
	public ModelAndView top(ModelAndView mv) {

		//top.htmlへ
		mv.setViewName("top");

		return mv;
	}

//収入登録入力画面へ------------------------------------------------------
	//http://localhost:8080/in
	@GetMapping("/in")
	public ModelAndView inMoney(ModelAndView mv) {

		//カテゴリーテーブルから全データ取得
		List<Category> categoryList = categoryRepository.findAll();

		mv.addObject("category", categoryList);

		//in.htmlへ
		mv.setViewName("in");

		return mv;
	}

//収入一覧画面へ--------------------------------------------------------
	@GetMapping("/inDisp")
	public ModelAndView inDisp(ModelAndView mv) {

		//収入・支出テーブルから全データ取得
		List<money> moneyList = moneyRepository.findAll();

		mv.addObject("moneyList", moneyList);


		//inDisp.htmlへ
		mv.setViewName("inDisp");

		return mv;
	}

//moneyDetailテーブルに収入データを新規登録---------------------------------
	@PostMapping("inAdd")
	public ModelAndView inAdd(ModelAndView mv) {

		//データベースに収入データ追加


		//収入一覧表示
		return inDisp(mv);
	}

//支出登録入力画面へ------------------------------------------------------
	@GetMapping("/out")
	public ModelAndView outMoney(ModelAndView mv) {

		//カテゴリーテーブルから全データ取得
		List<Category> categoryList = categoryRepository.findAll();

		mv.addObject("category", categoryList);


		//out.htmlへ
		mv.setViewName("out");

		return mv;
	}


//支出一覧画面へ---------------------------------------------------------
	@GetMapping("/outDisp")
	public ModelAndView outDisp(ModelAndView mv) {

		//収入・支出テーブルから全データ取得
		List<money> moneyList = moneyRepository.findAll();

		mv.addObject("moneyList", moneyList);

		//outDisp.htmlへ
		mv.setViewName("outDisp");

		return mv;
	}

//moneyDetailテーブルに支出データ新規登録------------------------------
	@PostMapping("outAdd")
	public ModelAndView outAdd(ModelAndView mv) {

		//データベースに支出データ追加


		//支出一覧表示
		return outDisp(mv);
	}


//情報更新入力画面へ-------------------------------------------------
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

//moneydetailテーブル更新-----------------------------------------------
	@PostMapping("/updata")
	public ModelAndView Updata(
			@RequestParam("code") Integer code,
			ModelAndView mv
			) {

		//更新後一覧表示
		return top(mv);
	}

//カテゴリー新規登録画面へ----------------------------------------------
	@GetMapping("/newCategory")
	public ModelAndView newCategory(ModelAndView mv) {

		mv.setViewName("category");

		return mv;
	}

//カテゴリー新規データ登録--------------------------------------------
	@PostMapping("/addCategory")
	public ModelAndView addCategory(
			ModelAndView mv,
			//category.htmlから入力した新カテゴリ名を"name"で受け取り
			@RequestParam("name") String name
			) {
		//入力されていない値があった場合は画面遷移しない処理
		if(name == "") {
			mv.setViewName("category");

			return mv;

		}else {

			//登録するデータのインスタンスを生成
			Category category = new Category(name);


			//categoryエンティティをテーブルに登録
			categoryRepository.saveAndFlush(category);

			//支出新規登録画面へ
			return outMoney(mv);
		}
	}

//月間レポートへ-------------------------------------
	@GetMapping("/month")
	public ModelAndView monthView(ModelAndView mv) {

		//月間レポートテーブルから全データ取得
		List<Month> monthList = monthRepository.findAll();

		mv.addObject("monthList", monthList);


		//month.htmlへ
		mv.setViewName("month");

		return mv;
	}

//年間レポートへ-------------------------------------------
	@GetMapping("/year")
	public ModelAndView yearView(ModelAndView mv) {

		//月間レポートテーブルから全データ取得
		List<Year> yearList = yearRepository.findAll();

		mv.addObject("yearList", yearList);

		//year.htmlへ
		mv.setViewName("year");

		return mv;
	}



}

