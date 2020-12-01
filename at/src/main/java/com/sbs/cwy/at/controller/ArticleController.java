package com.sbs.cwy.at.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/article/list")
	public String showList(Model model) {

		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("articles", articles);

		return "article/list";
	}

	@RequestMapping("/article/detail")
	public String showDetail(Model model, @RequestParam Map<String, Object> param) {
		int id = Integer.parseInt((String) param.get("id"));

		Article article = articleService.getForPrintArticleById(id);

		model.addAttribute("article", article);

		return "article/detail";
	}
}
