package com.example.demo;

import java.time.LocalDate;
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

	@Autowired
	PiedataRepository piedataRepository;


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

		//円グラフ要素作成
		PieCreate(year,month);
		//円グラフ要素テーブル初期化
		//piedataRepository.deleteAll();

		//表示する月間レポートテーブル
		mv.addObject("monthList", monthList);

		//表示するテーブルの年月(select)
		mv.addObject("year" , year);
		mv.addObject("month", month);


		//month.htmlへ
		mv.setViewName("month");

		return mv;
	}

	//円グラフの要素生成メソッド-------------------------------------------------------
	public void PieCreate(int y,int m) {

		//ログインしているアカウントを判定
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルからログインしているユーザのデータ取得
		List<Money> moneyList = moneyRepository.findByUid(uid);


		//一週目のforか判定
		int roopFlug = 0;

		//選択されている年月と一致するデータのカテゴリごとの合計を求める
		for(Money money : moneyList) {

			//支出の場合のみ処理実行
			if(money.getFlug() == 2) {

				LocalDate date = money.getDate();

				int year = date.getYear();

				int month = date.getMonthValue();

				//入力された年月のデータか判定
				if(year == y && month == m) {

					if(roopFlug == 0) {
						//円グラフの要素の宣言
						//円グラフ要素に新しくデータを追加
						PieData newPie = new PieData(money.getCategory(),money.getCost());
						piedataRepository.saveAndFlush(newPie);
					}

					if(roopFlug != 0) {
						List<PieData> pie = piedataRepository.findAll();


						//既に登録されているカテゴリか判定
						for(PieData p : pie)
						{

							//登録済みのカテゴリ名
							if(p.getName().equals(money.getCategory())) {
								Integer cost = p.getCost() + money.getCost();
								PieData data = new PieData(p.getCode(),p.getName(),cost);
								piedataRepository.saveAndFlush(data);
								pie = piedataRepository.findAll();
								break;
							}

							if(!p.getName().equals(money.getCategory())){
								PieData data = new PieData(money.getCategory(),money.getCost());
								piedataRepository.saveAndFlush(data);
								pie = piedataRepository.findAll();
								break;
							}

						}//既に登録されているカテゴリか判定処理終了

					}
					roopFlug = 1;
					
				}//入力された年月のデータか判定処理終了

			}//支出の時のみ実行するif文終了

		}//選択されている年月と一致するデータのカテゴリごとの合計を求める処理終了

	}//円グラフの要素生成メソッド終了
}
