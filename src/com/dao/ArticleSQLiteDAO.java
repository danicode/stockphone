package com.dao;

import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.dao.SQLiteDAOFactory;
import com.logic.model.ArticleDTO;

public class ArticleSQLiteDAO implements IDAOGeneric<ArticleDTO>{
	
	private SQLiteDatabase db;
	private SQLiteDAOFactory sdf;
	private final static String TABLE_ARTICLE = "article";
	private final static String TABLE_CATEGORY = "category";
	private final static String TABLE_PROVIDER = "provider";
	private final static String TABLE_PROVIDER_ARTICLE = "provider_article";
	private final static String COLUMN_ID_ARTICLE = "id_article";
	private final static String COLUMN_FK_CATEGORY = "fk_category";
	private final static String COLUMN_FK_ARTICLE = "fk_article";
	private final static String COLUMN_FK_PROVIDER = "fk_provider";
	private final static String COLUMN_ID_CATEGORY = "id_category";
	private final static String COLUMN_ID_PROVIDER = "id_provider";
	private final static String COLUMN_COD_ARTICLE = "cod_article";
	private final static String COLUMN_DESCRIPTION = "description";
	private final static String COLUMN_CATEGORY_NAME = "category_name";
	private final static String COLUMN_PROVIDER_NAME = "provider_name";
	private final static String COLUMN_COD_ARTICLE_PROVIDER = "cod_article_provider";
	
	public ArticleSQLiteDAO() {
		this.sdf = new SQLiteDAOFactory();
	}

	public ArrayList<ArticleDTO> recoveryAll() throws Exception {
		
		this.db = this.sdf.open();	
		ArrayList<ArticleDTO> articles = null;
		
		/* QUERY:
		 * SELECT id_article, id_category, id_provider,
		 * cod_article, description, category_name, 
		 * provider_name, cod_article_provider
		 * FROM article, category, provider, provider_article
		 * WHERE id_category = fk_category 
		 * AND fk_article = id_article 
		 * AND fk_provider = id_provider
		 */
		
		String tables = TABLE_ARTICLE.concat(",").concat(TABLE_CATEGORY)
				.concat(",").concat(TABLE_PROVIDER).concat(",")
				.concat(TABLE_PROVIDER_ARTICLE);
		
		String[] columns = { COLUMN_ID_ARTICLE, COLUMN_ID_CATEGORY,
				COLUMN_ID_PROVIDER, COLUMN_COD_ARTICLE, COLUMN_DESCRIPTION,
				COLUMN_COD_ARTICLE_PROVIDER, COLUMN_CATEGORY_NAME,
				COLUMN_PROVIDER_NAME};
		
		String selection = COLUMN_ID_CATEGORY.concat(" = ")
				.concat(COLUMN_FK_CATEGORY).concat(AND)
				.concat(COLUMN_FK_ARTICLE).concat(" = ")
				.concat(COLUMN_ID_ARTICLE).concat(AND)
				.concat(COLUMN_FK_PROVIDER).concat(" = ")
				.concat(COLUMN_ID_PROVIDER);
		
		Cursor cursor = this.db.query(tables, columns, selection, null, null, null, null);		
			
		if(cursor.moveToFirst()) {
			articles = new ArrayList<ArticleDTO>();
			
			do {
	
				int id_article = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_ARTICLE));
				int id_provider = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PROVIDER));
				int id_category = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CATEGORY));
				String category_name = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME));				
				String provider_name = cursor.getString(cursor.getColumnIndex(COLUMN_PROVIDER_NAME));
				String cod_article_provider = cursor.getString(cursor.getColumnIndex(COLUMN_COD_ARTICLE_PROVIDER));
				String cod_article = cursor.getString(cursor.getColumnIndex(COLUMN_COD_ARTICLE));
				String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
				
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setId_article(id_article);
				articleDTO.setId_category(id_category);
				articleDTO.setId_provider(id_provider);
				articleDTO.setCategoryName(category_name);
				articleDTO.setCod_article(cod_article);
				articleDTO.setDescription(description);
				articleDTO.setCod_article_provider(cod_article_provider);
				articleDTO.setProviderName(provider_name);
				
				articles.add(articleDTO);

			} while(cursor.moveToNext());
		}
		cursor.close();
		this.sdf.close();
		this.db.close();	
		return articles;
	}
}