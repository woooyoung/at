package com.sbs.cwy.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.dto.Reply;

@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();

	Article getForPrintArticleById(@Param("id") int id);

	void write(Map<String, Object> param);

	void writeReply(Map<String, Object> param);

	List<Reply> getForPrintReplies(Map<String, Object> param);

	void deleteReply(@Param("id") int id);

	Reply getForPrintReplyById(@Param("id") int id);

	void modifyReply(Map<String, Object> param);
}