package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountRepository;

	//新規会員登録画面-------------------------------------------------------------
	//http://localhost:8080/signup
	@GetMapping("/signup")
	public ModelAndView signup(ModelAndView mv) {

		//signup.htmlへ
		mv.setViewName("signup");

		return mv;
	}

	//新規登録処理
	@PostMapping("/signup")
	public ModelAndView signup(
			ModelAndView mv,
			@RequestParam("NAME") String name,
			@RequestParam("PASSWORD") String pass) {

		//入力された文字が空文字の場合エラー
		if (name.equals("") && pass.equals("")) {

			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("signup");
			return mv;
		}
		//既に登録されている場合エラー
		List<Account> accountList = accountRepository.findAll();
		for(Account a : accountList) {
			if(a.getName().equals(name)) {
				mv.addObject("message", "既に使用されているユーザ名です");
				mv.setViewName("signup");

				return mv;
			}
		}


		//登録するデータのインスタンスを生成
		Account account = new Account(name, pass);
		session.setAttribute("account", account);
		mv.addObject("message", "登録が完了しました。");

		//accountエンティティをテーブルに登録
		accountRepository.saveAndFlush(account);
		mv.setViewName("login");
		return mv;
	}

	/**
	 * ログイン画面を表示
	 */
	@RequestMapping("/loginDisp")
	public String login() {
		// セッション情報はクリアする
		session.invalidate();
		return "login";
	}

	/**
	 * ログインを実行
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(
			@RequestParam("USER_ID") String name,
			@RequestParam("PASSWORD") String pass,
			ModelAndView mv) {
		// 名前が空の場合にエラーとする
		if (name == null || name.length() == 0 || pass == null || pass.length() == 0) {
			mv.addObject("message", "未記入の情報があります。");
			mv.setViewName("login");
			return mv;
		}
		//パスワードが間違っていたらエラー
		List<Account> userlist = null;

		//データベースからアカウントデータ取得
		userlist = accountRepository.findByNameLike(name);

		Account user = null;

		boolean ErrorFlug = false;//登録された情報が無いか、PASSが間違っている

		//入力されたnameのアカウントがなかったらloginに戻る
		try {
			user = userlist.get(0);
		} catch (Exception e) {

			ErrorFlug = true;
		}

		if (ErrorFlug == false && user.getPass().equals(pass)) {

			// セッションスコープにログインしているアカウント情報を格納する
			session.setAttribute("user", user);

			mv.setViewName("top");
			return mv;

		} else {
			mv.addObject("message", "登録された情報が無いか、パスワードが間違っております。");
			mv.setViewName("login");
			return mv;
		}
	}

	/**
	 * ログアウトを実行
	 */
	@RequestMapping("/logout")
	public String logout() {
		// ログイン画面表示処理を実行するだけ
		return login();
	}
}
