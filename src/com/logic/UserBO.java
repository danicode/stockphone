package com.logic;

import com.dao.SQLiteDAOFactory;
import com.dao.UserSQLiteDAO;
import com.logic.model.UserDTO;

public class UserBO {
	
	private SQLiteDAOFactory df; 
	private UserSQLiteDAO userSQLiteDAO;
	
	public UserBO() {
		this.df = new SQLiteDAOFactory();
	}
	
	public boolean login(UserDTO userDTO) throws Exception {
		this.userSQLiteDAO = this.df.getUserDAO();
		return this.userSQLiteDAO.login(userDTO);
	}

}
