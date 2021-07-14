package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {


	//新規会員登録画面-------------------------------------------------------------
	//http://localhost:8080/signup
	@GetMapping("/test")
	public ModelAndView signup(ModelAndView mv) {

		//signup.htmlへ
		mv.setViewName("start");

		return mv;
	}
}