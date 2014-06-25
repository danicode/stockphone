package com.logic;

import java.util.ArrayList;

import com.dao.ArticleSQLiteDAO;
import com.dao.SQLiteDAOFactory;
import com.logic.model.ArticleDTO;


public class ArticleBO {

	private SQLiteDAOFactory df;
	private ArticleSQLiteDAO articleSQLiteDAO;
	
	public ArticleBO() {
		this.df = new SQLiteDAOFactory();
	}
	
	public ArrayList<ArticleDTO> recoveryAll() throws Exception {
		this.articleSQLiteDAO = this.df.getArticleDAO();
		return this.articleSQLiteDAO.recoveryAll();
	}
}
