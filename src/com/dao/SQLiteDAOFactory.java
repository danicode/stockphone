package com.dao;

import java.io.File;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class SQLiteDAOFactory {

	// generic: memory_sd_folder/app_folder/database_folder/db_app.db ->
	// specifically: for app_stockphone: sd/stockphone/database/db_stocker.db
	private SQLiteDatabase sqliteDB;	
	private final static String APP_FOLDER = "stockphone";
	private final static String DATABASE_FOLDER = "database";
	private final static String DATABASE_NAME = "db_stockphone.db";
	private final static String PATH_DATABASE = APP_FOLDER.concat(File.separator).concat(DATABASE_FOLDER).concat(File.separator).concat(DATABASE_NAME);	
	
	public SQLiteDatabase open() {
		if(this.sqliteDB == null || !this.sqliteDB.isOpen()) { 		
			String pathRoot = Environment.getExternalStorageDirectory().getPath();
			pathRoot = pathRoot.concat(File.separator).concat(PATH_DATABASE);
			this.sqliteDB = SQLiteDatabase.openDatabase(pathRoot, null,
					SQLiteDatabase.OPEN_READWRITE
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);	
		} 			
		return this.sqliteDB;
	}	
	
	public void close() {		
		this.sqliteDB.close();
	}
	
	public void commit() {		
		this.sqliteDB.setTransactionSuccessful();
		this.sqliteDB.endTransaction();
	}
	
	public void rollBack() {
		this.sqliteDB.endTransaction();
	}
	
	public void initTransaction() {
		this.sqliteDB.beginTransaction();
	}
	
	public ArticleSQLiteDAO getArticleDAO() {
		return new ArticleSQLiteDAO();
	}
	
	public CategorySQLiteDAO getCategoryDAO() {
		return new CategorySQLiteDAO();
	}
	
	public ProviderSQLiteDAO getProviderDAO() {
		return new ProviderSQLiteDAO();
	}
	
	public UserSQLiteDAO getUserDAO() {
		return new UserSQLiteDAO();
	}
}
