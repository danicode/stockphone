package com.logic;

import java.util.ArrayList;

import com.dao.ProviderSQLiteDAO;
import com.dao.SQLiteDAOFactory;
import com.logic.model.ProviderDTO;

public class ProviderBO {

	private SQLiteDAOFactory df;
	private ProviderSQLiteDAO providerSQLiteDAO;
	
	public ProviderBO() {
		this.df = new SQLiteDAOFactory();
	}
	
	public ArrayList<ProviderDTO> recoveryByIdArticle(int id) throws Exception {
		this.providerSQLiteDAO = this.df.getProviderDAO();
		return this.providerSQLiteDAO.recoveryByIdArticle(id);
	}
}
