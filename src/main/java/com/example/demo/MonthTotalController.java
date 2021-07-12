package com.example.demo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MonthTotalController {

	@Autowired
	HttpSession session;


	@Autowired
	MoneyRepository moneyRepository;

	@Autowired
	MonthRepository monthRepository;


	//月間レポート新規登録&金額追加--------------------------------
	public void addMonth(int code/*追加したデータのcodeを取得*/) {
		//ログインしているアカウントを判定
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルから追加したデータ取得
		Optional<Money> moneyList = moneyRepository.findById(code);
		Money mList = moneyList.get();

		Date date = mList.getDate();

		@SuppressWarnings("deprecation")
		Integer year = date.getYear();

		@SuppressWarnings("deprecation")
		Integer month = date.getMonth();

		//ログインしているユーザの月間レポート取得
		List<Month> monthTotal = monthRepository.findByUid(uid);


		//同じyear,monthのデータがあるか捜査
		for(Month mindex : monthTotal ) {
			if(mindex.getYear() == year && mindex.getMonth() == month ) {
				//データ更新

				Integer intotal = mindex.getIntotal();
				Integer outtotal = mindex.getOuttotal();

				if(mList.getFlug() == 1) {//収入

					Month newMonth = new Month(mindex.getCode(),uid,year,month,intotal + mList.getCost(),outtotal,intotal - outtotal);

					monthRepository.saveAndFlush(newMonth);
				}else {//支出
					Month newMonth = new Month(mindex.getCode(),uid,year,month,intotal,outtotal + mList.getCost(),intotal - outtotal);

					monthRepository.saveAndFlush(newMonth);

				}

				return;
			}
		}

		//同一データがなかった(新規作成
		if(mList.getFlug() == 1) {//収入追加
			Integer intotal = 0;
			Month newMonth = new Month(uid,year,month,intotal,mList.getCost(),intotal - mList.getCost());

			monthRepository.saveAndFlush(newMonth);

		}else {//支出追加
			Integer outtotal = 0;
			Month newMonth = new Month(uid,year,month,mList.getCost(),outtotal,outtotal - mList.getCost());

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

		return;
	}

}
