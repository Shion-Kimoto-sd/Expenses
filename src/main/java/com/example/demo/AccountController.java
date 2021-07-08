package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	/**
	 * ログイン画面を表示
	 */
	@RequestMapping("/")
	public String login() {
		// セッション情報はクリアする
		session.invalidate();
		return "login";
	}

	/**
	 * ログインを実行
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView doLogin(
			@RequestParam("name") String name,
			@RequestParam("pass") String pass,
			ModelAndView mv
	) {
		// 名前が空の場合にエラーとする
		if(name == null || name.length() == 0 || pass == null || pass.length() == 0) {
			mv.addObject("message", "未記入の情報があります。");
			mv.setViewName("login");
			return mv;
		}
		//パスワードが間違っていたらエラー

		// セッションスコープにログインしているアカウント情報を格納する


		session.setAttribute("name", name);
		session.setAttribute("user", accountRepository.findBynameLike(name));

		mv.setViewName("top");
		return mv;
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
