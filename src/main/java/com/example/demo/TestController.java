package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {


	//http://localhost:8080/test
	@GetMapping("/test")
	public ModelAndView test(ModelAndView mv) {

		//start.htmlへ
		mv.setViewName("start3");

		return mv;
	}

	//http://localhost:8080/testPie
	@GetMapping("/testPie")
	public ModelAndView testPie(ModelAndView mv) {

		//PieChart.htmlへ
		mv.setViewName("PieChart");

		return mv;
	}

}