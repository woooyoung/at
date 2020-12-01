package com.sbs.cwy.at.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.cwy.at.dto.Article;

@Mapper
public interface ArticleDao {
	List<Article> getForPrintArticles();
}
