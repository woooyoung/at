package com.sbs.cwy.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.cwy.at.dto.Article;
import com.sbs.cwy.at.dto.Board;

@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();

	Article getForPrintArticleById(@Param("id") int id);

	void write(Map<String, Object> param);

	void modify(Map<String, Object> param);

	Article getArticleById(@Param("id") int id);

	Board getBoardByCode(String boardCode);
}