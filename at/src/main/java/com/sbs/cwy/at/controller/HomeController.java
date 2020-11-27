package com.sbs.cwy.at.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping("/home/main")
	@ResponseBody
	public String showMain() {
		return "HI";
	}

	@RequestMapping("/home/main2")
	@ResponseBody
	public String showMain2() {
		return "Bye";
	}

	@RequestMapping("/home/plus")
	@ResponseBody
	public int showPlus(int a, int b) {
		return a + b;
	}

	@RequestMapping("/home/increase")
	@ResponseBody
	public int showIncrease() {
		return 14;
	}
}
