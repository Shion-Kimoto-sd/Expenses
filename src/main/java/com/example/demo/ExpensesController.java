package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpensesController {

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
	//http://localhost:8080/g
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

		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//カテゴリーテーブルから全データ取得

		List<Category> categoryList = categoryRepository.findByUid(uid);

		mv.addObject("category", categoryList);

		//in.htmlへ
		mv.setViewName("in");

		return mv;
	}

//収入一覧画面へ--------------------------------------------------------
	@GetMapping("/inDisp")
	public ModelAndView inDisp(ModelAndView mv) {

		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルから全データ取得

		List<Money> moneyList = moneyRepository.findByUid(uid);

		mv.addObject("moneyList", moneyList);


		//inDisp.htmlへ
		mv.setViewName("inDisp");

		return mv;
	}

//moneyDetailテーブルに収入データを新規登録---------------------------------
	@PostMapping("/inAdd")
	public ModelAndView inAdd(
			ModelAndView mv,
			@RequestParam("category") String category,
			@RequestParam(name = "money"  ,defaultValue = "0") Integer cost,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("date") String day
			)throws Exception {

		//金額が入力されていない場合の処理
		if(cost == 0) {
			System.out.println("costが空");
			return inMoney(mv);
		}



        LocalDate date = null;
		try {
			date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (Exception e) {
			System.out.println("日付の変換で失敗");
		}
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//登録するデータのインスタンスを生成
		Money m_data = new Money(uid,1,date,category,cost);

		//収入データをテーブルに登録
		moneyRepository.saveAndFlush(m_data);

		//追加したデータのcodeを取り出す作業
		int code = 0;
		List<Money> mlist = moneyRepository.findAll();
		for(Money m : mlist) {
			if(m.getUid()==uid && m.getFlug()==1 && m.getDate()==date && m.getCategory().equals(category) && m.getCost()== cost) {
				code = m.getCode();
			}
		}


		addMonth(code);

		//収入一覧表示
		return inDisp(mv);
	}

//支出登録入力画面へ------------------------------------------------------
	@GetMapping("/out")
	public ModelAndView outMoney(ModelAndView mv) {

		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//カテゴリーテーブルから全データ取得

		List<Category> categoryList = categoryRepository.findByUid(uid);

		mv.addObject("category", categoryList);


		//out.htmlへ
		mv.setViewName("out");

		return mv;
	}


//支出一覧画面へ---------------------------------------------------------
	@GetMapping("/outDisp")
	public ModelAndView outDisp(ModelAndView mv) {

		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルから全データ取得

		List<Money> moneyList = moneyRepository.findByUid(uid);

		mv.addObject("moneyList", moneyList);

		//outDisp.htmlへ
		mv.setViewName("outDisp");

		return mv;
	}

//moneyDetailテーブルに支出データ新規登録------------------------------
	@PostMapping("/outAdd")
	public ModelAndView outAdd(
			ModelAndView mv,
			@RequestParam("category") String category,
			@RequestParam(name = "money" , defaultValue = "0") Integer cost,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("date") String day
			)throws Exception {

		//金額が入力されていない場合の処理
		if(cost == 0) {
			System.out.println("costが空");
			return outMoney(mv);
		}

        LocalDate date = null;

		try {
			date = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		} catch (Exception e) {
			System.out.println("日付の変換で失敗");
		}

		//データベースに支出データ追加
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//登録するデータのインスタンスを生成
		Money m_data = new Money(uid,2,date,category,cost);

		//categoryエンティティをテーブルに登録
		moneyRepository.saveAndFlush(m_data);

		//追加したデータのcodeを取り出す作業
		int code = 0;
		List<Money> mlist = moneyRepository.findAll();

		for(Money m : mlist) {
			if(m.getUid()==uid && m.getFlug()==2 && m.getDate()==date && m.getCategory().equals(category) && m.getCost()== cost) {
				code = m.getCode();
			}
		}

		addMonth(code);


		//支出一覧表示
		return outDisp(mv);
	}


//情報更新入力画面へ-------------------------------------------------
	@PostMapping("/updataDisp")
	public ModelAndView UpdateDisp(
			@RequestParam("code") Integer code,
			ModelAndView mv
			) {


		Optional<Money> moneyList = moneyRepository.findById(code);

		Money money = moneyList.get();

		//セッションからログイン中ユーザのID取得
		Account user = (Account) session.getAttribute("user");
		Integer uid = user.getCode();

		//カテゴリーテーブルから全データ取得
		List<Category> categoryList = categoryRepository.findByUid(uid);

		mv.addObject("category", categoryList);

		//更新するデータのIDと収入・支出の判定Flugをadd
		mv.addObject("flug",money.getFlug() );
		mv.addObject("code", code);

		//update.htmlへ
		mv.setViewName("update");

		return mv;
	}

//moneydetailテーブル更新-----------------------------------------------
	@PostMapping("/update")
	public ModelAndView Update(
			@RequestParam("code") Integer code,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("date") String day,
			@RequestParam("category") String category,
			@RequestParam(name = "cost" , defaultValue = "0") Integer cost,
			@RequestParam("flug") Integer flug,//更新されたのが収入か支出か
			ModelAndView mv
			) {

		// 要素が空の場合にエラーとする
		if (cost == 0 || year.equals("") || year.length() == 0 || month.equals("") || month.length() == 0 || day.equals("") || day.length()==0) {

			return UpdateDisp(code,mv);
		}

        LocalDate date = null;
		try {
			date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (Exception e) {
			System.out.println("日付の変換で失敗");
		}

		//データベースに支出データ追加
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//月間レポートテーブル更新処理メソッド呼び出し
		UpdateMonth(code);

		//登録するデータのインスタンスを生成
		Money m_data = new Money(code,uid,flug,date,category,cost);

		//categoryエンティティをテーブルに登録
		moneyRepository.saveAndFlush(m_data);

		//更新後一覧表示
		if(flug == 1) {//収入一覧

			return inDisp(mv);

		}else {//支出一覧

			return outDisp(mv);
		}
	}


//moneydetailテーブル要素削除-----------------------------------------------
	@PostMapping("/delete")
	public ModelAndView Delete(
			@RequestParam("code") Integer code,
			@RequestParam("flug") Integer flug,
			ModelAndView mv
			) {

		//月間レポートテーブル変更メソッド呼び出し
		minusMonth(code);

		moneyRepository.deleteById(code);

		//更新後一覧表示
		if(flug == 1) {//収入一覧

			return inDisp(mv);
		}else {//支出一覧
			return outDisp(mv);
		}
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
			Account user =  (Account) session.getAttribute("user");
			Integer uid = user.getCode();

			//登録するデータのインスタンスを生成
			Category category = new Category(uid,name);

			//categoryエンティティをテーブルに登録
			categoryRepository.saveAndFlush(category);

			//支出新規登録画面へ
			return outMoney(mv);

		}
	}

//カテゴリー一覧画面へ---------------------------------------------------------
	@GetMapping("/CategoryDisp")
	public ModelAndView CategoryDisp(ModelAndView mv) {

		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//カテゴリテーブルから全データ取得

		List<Category> categoryList = categoryRepository.findByUid(uid);

		mv.addObject("categoryList", categoryList);

		//categoryList.htmlへ
		mv.setViewName("categoryList");

		return mv;
	}

//Categoryテーブル要素削除-----------------------------------------------
	@PostMapping("/deleteCategory")
	public ModelAndView DeleteCategory(
			@RequestParam("code") Integer code,
			ModelAndView mv
			) {
		categoryRepository.deleteById(code);

		return CategoryDisp(mv);
	}


//月間レポートへ-------------------------------------
	@GetMapping("/month")
	public ModelAndView monthView(ModelAndView mv) {

		//月間レポートテーブルから全データ取得
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		List<Month> monthList = monthRepository.findByUid(uid);

		mv.addObject("monthList", monthList);


		//month.htmlへ
		mv.setViewName("month");

		return mv;
	}

//年間レポートへ-------------------------------------------
	@GetMapping("/year")
	public ModelAndView yearView(ModelAndView mv) {


		//年間レポートテーブルから全データ取得
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//カテゴリーテーブルから全データ取得

		List<Year> yearList = yearRepository.findByUid(uid);

		mv.addObject("yearList", yearList);

		//year.htmlへ
		mv.setViewName("year");

		return mv;
	}

	//月間レポート新規登録&金額追加--------------------------------
	public void addMonth(int code/*追加したデータのcodeを取得*/) {
		//ログインしているアカウントを判定
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルから追加したデータ取得
		Optional<Money> moneyList = moneyRepository.findById(code);
		Money mList = moneyList.get();

		LocalDate date = mList.getDate();

		Integer year = date.getYear();

		Integer month = date.getMonthValue();

		//ログインしているユーザの月間レポート取得
		List<Month> monthTotal = monthRepository.findByUid(uid);


		//同じyear,monthのデータがあるか捜査
		for(Month mindex : monthTotal ) {
			if(mindex.getYear() == year && mindex.getMonth() == month ) {
				//データ更新


				if(mList.getFlug() == 1) {//収入
					Integer intotal = mindex.getIntotal()+ mList.getCost();
					Integer outtotal = mindex.getOuttotal();


					Month newMonth = new Month(mindex.getCode(),uid,year,month,intotal,outtotal,intotal - outtotal);

					monthRepository.saveAndFlush(newMonth);
				}else {//支出

					Integer intotal = mindex.getIntotal();
					Integer outtotal = mindex.getOuttotal()+ mList.getCost();

					Month newMonth = new Month(mindex.getCode(),uid,year,month,intotal,outtotal,intotal - outtotal);

					monthRepository.saveAndFlush(newMonth);

				}

				return;
			}
		}

		//同一データがなかった(新規作成
		if(mList.getFlug() == 2) {//支出追加
			Integer intotal = 0;
			Month newMonth = new Month(uid,year,month,intotal,mList.getCost(),intotal - mList.getCost());

			monthRepository.saveAndFlush(newMonth);

		}else {//収入追加
			Integer outtotal = 0;
			Month newMonth = new Month(uid,year,month,mList.getCost(),outtotal,mList.getCost()- outtotal);

			monthRepository.saveAndFlush(newMonth);
		}

		return ;
	}

	//月間レポート更新---------------------------------------------
	public void UpdateMonth(Integer code) {

		return;
	}

	//月間レポート金額減少処理-------------------------------------
	public void minusMonth(Integer code) {

		//ログインしているアカウントを判定
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルから追加したデータ取得
		Optional<Money> moneyList = moneyRepository.findById(code);
		Money mList = moneyList.get();

		LocalDate date = mList.getDate();

		Integer year = date.getYear();

		Integer month = date.getMonthValue();
		//ログインしているユーザの月間レポート取得
		List<Month> monthTotal = monthRepository.findByUid(uid);

		//同じyear,monthのデータがあるか捜査
		for(Month mindex : monthTotal ) {
			if(mindex.getYear() == year && mindex.getMonth() == month ) {
				//データ更新


				//月間レポートテーブルの要素を減算
				if(mList.getFlug() == 1) {//収入
					Integer intotal = mindex.getIntotal()- mList.getCost();
					Integer outtotal = mindex.getOuttotal();


					Month newMonth = new Month(mindex.getCode(),uid,year,month,intotal,outtotal,intotal - outtotal);

					monthRepository.saveAndFlush(newMonth);
				}else {//支出

					Integer intotal = mindex.getIntotal();
					Integer outtotal = mindex.getOuttotal()- mList.getCost();

					Month newMonth = new Month(mindex.getCode(),uid,year,month,intotal,outtotal,intotal - outtotal);

					monthRepository.saveAndFlush(newMonth);

				}

				return;
			}
		}


		return;
	}


}

