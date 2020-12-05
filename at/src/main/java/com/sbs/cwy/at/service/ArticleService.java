package com.sbs.cwy.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.cwy.at.dao.ArticleDao;
import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.dto.ArticleReply;
import com.sbs.cwy.at.dto.Member;
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

	public int writeReply(Map<String, Object> param) {
		articleDao.writeReply(param);

		return Util.getAsInt(param.get("id"));
	}

	public List<ArticleReply> getForPrintArticleReplies(@RequestParam Map<String, Object> param) {
		List<ArticleReply> articleReplies = articleDao.getForPrintArticleReplies(param);

		Member actor = (Member) param.get("actor");

		for (ArticleReply articleReply : articleReplies) {
			// 출력용 부가데이터를 추가한다.
			updateForPrintInfo(actor, articleReply);
		}

		return articleReplies;
	}

	private void updateForPrintInfo(Member actor, ArticleReply articleReply) {
		articleReply.getExtra().put("actorCanDelete", actorCanDelete(actor, articleReply));
		articleReply.getExtra().put("actorCanUpdate", actorCanUpdate(actor, articleReply));
	}

	// 액터가 해당 댓글을 수정할 수 있는지 알려준다.
	private Object actorCanUpdate(Member actor, ArticleReply articleReply) {
		return actor != null && actor.getId() == articleReply.getMemberId() ? true : false;
	}

	// 액터가 해당 댓글을 삭제할 수 있는지 알려준다.
	private Object actorCanDelete(Member actor, ArticleReply articleReply) {
		return actorCanUpdate(actor, articleReply);
	}

	public void deleteReply(int id) {
		articleDao.deleteReply(id);
	}
}