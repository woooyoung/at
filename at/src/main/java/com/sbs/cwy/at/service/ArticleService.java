package com.sbs.cwy.at.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.cwy.at.dao.ArticleDao;
import com.sbs.cwy.at.dto.Article;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public int getCount() {
		return 2;
	}

	public List<Article> getForPrintArticles() {

		List<Article> articles = articleDao.getForPrintArticles();

		return articles;
	}

	public Article getForPrintArticleById(int id) {
		Article article = articleDao.getForPrintArticleById(id);

		return article;
	}
}
