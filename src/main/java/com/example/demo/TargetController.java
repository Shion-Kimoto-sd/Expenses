package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TargetController {

	@Autowired
	HttpSession session;

	@Autowired
	TargetRepository targetRepository;

	//目標設定ページへ------------------------------------------------------------
	@GetMapping("/target")
	public ModelAndView target(ModelAndView mv) {

		//target.htmlへ
		mv.setViewName("target");

		return mv;
	}

	//目標設定新規登録--------------------------------------------
	@PostMapping("/addtarget")
	public ModelAndView addtarget(
			ModelAndView mv,
			//target.htmlから入力した新規目標データ受け取り
			@RequestParam("targetCost") Integer targetCost,
			@RequestParam("month") Integer month,
			@RequestParam("year") Integer year
			) {
		//入力されていない値があった場合は画面遷移しない処理
		if(targetCost == null) {

			mv.addObject("message", "目標金額を入力してください");

			mv.setViewName("target");

			return mv;

		}
		//ログインしているユーザのID取得
		Account user =  (Account) session.getAttribute("user");
		Integer uid = user.getCode();

		//登録するデータのインスタンスを生成
		targetCost newTarget = new targetCost(uid,targetCost,month,year);

		//targetエンティティをテーブルに登録
		targetRepository.saveAndFlush(newTarget);

		mv.setViewName("target");
		//入力前画面へ
		return mv;


	}



}
