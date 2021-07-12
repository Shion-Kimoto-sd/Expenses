package com.example.demo;

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
	public void addMonth(Integer code/*追加したデータのcodeを取得*/) {
		//ログインしているアカウントを判定
		Account user = (Account) session.getAttribute("user");

		Integer uid = user.getCode();

		//収入・支出テーブルから追加したデータ取得
		Optional<Money> moneyList = moneyRepository.findById(code);
		Money mList = moneyList.get();

		//ログインしているユーザの月間レポート取得
		List<Month> monthTotal = monthRepository.findByUid(uid);


//		for(Month mindex : monthTotal ) {
//			if(mindex.getYear() == mList.get )
//		}


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
