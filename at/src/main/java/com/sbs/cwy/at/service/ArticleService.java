package com.sbs.cwy.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.cwy.at.dao.ArticleDao;
import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.util.Util;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	public List<Article> getForPrintArticles() {
		List<Article> articles = articleDao.getForPrintArticles();

		return articles;
	}

	public Article getForPrintArticleById(int id) {
		Article article = articleDao.getForPrintArticleById(id);

		return article;
	}

	public int write(Map<String, Object> param) {
		articleDao.write(param);

		return Util.getAsInt(param.get("id"));
	}
}