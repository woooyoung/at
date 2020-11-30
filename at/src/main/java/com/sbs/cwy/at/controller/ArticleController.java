package com.sbs.cwy.at.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/article/list")
	public String showList(Model model) {
		int count = articleService.getCount();

		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("count", count);
		model.addAttribute("articles", articles);
		
		return "article/list";
	}
}
