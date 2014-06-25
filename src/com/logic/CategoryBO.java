package com.logic;

import com.dao.CategorySQLiteDAO;
import com.dao.SQLiteDAOFactory;
import com.logic.model.CategoryDTO;

public class CategoryBO {

	private SQLiteDAOFactory df;
	private CategorySQLiteDAO categorySQLiteDAO;
	
	public CategoryBO() {
		this.df = new SQLiteDAOFactory();
	}
	
	public CategoryDTO recoveryByIdArticle(int id) throws Exception {
		this.categorySQLiteDAO = this.df.getCategoryDAO();
		return this.categorySQLiteDAO.recoveryByIdArticle(id);
	}
}
