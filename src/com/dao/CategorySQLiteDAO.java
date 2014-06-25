package com.dao;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.logic.model.CategoryDTO;

public class CategorySQLiteDAO implements IDAOGeneric<CategoryDTO> {

	private SQLiteDatabase db;
	private SQLiteDAOFactory sdf;
	private final static String TABLE_CATEGORY = "category";
	private final static String COLUMN_ID_CATEGORY = "id_category";
	private final static String COLUMN_CATEGORY_NAME = "category_name";
	
	public CategorySQLiteDAO() {
		this.sdf = new SQLiteDAOFactory();
	}
	
	public CategoryDTO recoveryByIdArticle(int id) throws Exception {
		this.db = this.sdf.open();			
		CategoryDTO category = null;
		
		/* QUERY:
		 * SELECT id_category, category_name
		 * FROM category
		 * WHERE id_category = ?
		 */
		
		String [] columns = {COLUMN_CATEGORY_NAME};
		String selection = COLUMN_ID_CATEGORY.concat(" = ?");
		String idvalue = String.valueOf(id);
		String [] selectionArgs = {idvalue};
		
		Cursor cursor =  this.db.query(TABLE_CATEGORY, columns, selection, selectionArgs, null, null, null); 
		
		if(cursor.moveToFirst()) {
			category = new CategoryDTO();
			String category_name = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME));
			category.setId_category(id);
			category.setName(category_name);
		}
		cursor.close();
		this.sdf.close();
		this.db.close();
		return category;
	}

	public ArrayList<CategoryDTO> recoveryAll() throws Exception {
		this.db = this.sdf.open();	
		
		ArrayList<CategoryDTO> categories = null;
		
		/* QUERY:
		 * SELECT id_category, category_name
		 * FROM category
		 */
		
		Cursor cursor = this.db.query(TABLE_CATEGORY, null, null, null, null, null, null);  
		
		if(cursor.moveToFirst()) {
			categories = new ArrayList<CategoryDTO>();
			do {
				
				int id_category = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CATEGORY));
				String category_name = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME));
				
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setId_category(id_category);
				categoryDTO.setName(category_name);
				categories.add(categoryDTO);				
				
			} while(cursor.moveToNext());
		}
		cursor.close();
		this.sdf.close();
		this.db.close();
		return categories;
	}

}
