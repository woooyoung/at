package com.sbs.cwy.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.cwy.at.dao.ArticleDao;
import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.dto.ArticleReply;
import com.sbs.cwy.at.util.Util;

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

	public int write(Map<String, Object> param) {
		articleDao.write(param);
		return Util.getAsInt(param.get("id"));
	}

	public int writeReply(Map<String, Object> param) {
		articleDao.writeReply(param);
		return Util.getAsInt(param.get("id"));
	}

	public List<ArticleReply> getForPrintArticleReplies(@RequestParam Map<String, Object> param) {
		return articleDao.getForPrintArticleReplies(param);
	}
}
