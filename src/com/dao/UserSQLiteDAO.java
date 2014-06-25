	package com.dao;

import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.dao.IDAOGeneric;
import com.logic.model.UserDTO;


public class UserSQLiteDAO implements IDAOGeneric<UserDTO>{

	private SQLiteDAOFactory sdf;
	private SQLiteDatabase db;
	private static final String TABLE_USER = "user";
	private static final String COLUM_ID_USER = "id_user";
	private static final String COLUM_USER = "user";
	private static final String COLUM_PASSWORD = "password";
	private static final String COLUM_EMAIL = "email";
	
	public UserSQLiteDAO() {		
		this.sdf = new SQLiteDAOFactory();
	}

	public ArrayList<UserDTO> recoveryAll() throws Exception {
		
		this.db = this.sdf.open();		
		ArrayList<UserDTO> users = null;
		
		Cursor cursor = this.db.query(TABLE_USER, null, null, null, null, null, null);  
		
		if(cursor.moveToFirst()) {
			users = new ArrayList<UserDTO>();
			do {
				
				int id_user = cursor.getInt(cursor.getColumnIndex(COLUM_ID_USER));
				String user = cursor.getString(cursor.getColumnIndex(COLUM_USER));
				String password = cursor.getString(cursor.getColumnIndex(COLUM_PASSWORD));
				String email = cursor.getString(cursor.getColumnIndex(COLUM_EMAIL));
				
				UserDTO u = new UserDTO();
				u.setId_user(id_user);
				u.setUser(user);
				u.setPassword(password);
				u.setEmail(email);
				users.add(u);
				
			} while(cursor.moveToNext());
		}
		
		cursor.close();
		this.sdf.close();
		this.db.close();
		return users;
	}
	
	public boolean login(UserDTO u) throws Exception {
		
		this.db = this.sdf.open();			
		boolean flag = false;
		
		String [] columns = {COLUM_ID_USER,COLUM_EMAIL};						
		String selection = COLUM_USER
				.concat("= ? AND ")
				.concat(COLUM_PASSWORD)
			    .concat("= ?");
		String [] selectionArgs = {u.getUser(),u.getPassword()};
		
		Cursor cursor =  this.db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null); 
		
		if(cursor.moveToFirst()) {
			flag = true;
			int id = cursor.getInt(cursor.getColumnIndex(COLUM_ID_USER));
			String email = cursor.getString(cursor.getColumnIndex(COLUM_EMAIL));
			u.setId_user(id);
			u.setEmail(email);
		}		
		cursor.close();
		this.sdf.close();
		this.db.close();
		return flag;		
	}
}
