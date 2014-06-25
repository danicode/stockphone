package com.dao;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.logic.model.ProviderDTO;;

public class ProviderSQLiteDAO implements IDAOGeneric<ProviderDTO> {
	
	private SQLiteDatabase db;
	private SQLiteDAOFactory sdf;
	private final static String TABLE_PROVIDER = "provider";
	private final static String TABLE_PROVIDER_ARTICLE = "provider_article";
	private final static String COLUMN_NAME = "name";
	private final static String COLUM_ID_PROVIDER = "id_provider";
	private final static String COLUMN_ID_ARTICLE = "id_article";
	private final static String COLUMN_COD_ARTICLE_PROVIDER = "cod_article_provider";
	
	public ProviderSQLiteDAO() {
		this.sdf = new SQLiteDAOFactory();
	}

	public ArrayList<ProviderDTO> recoveryByIdArticle(int id) throws Exception {
		this.db = this.sdf.open();
		
		ArrayList<ProviderDTO> providers = null;
		
		/*
		 * query
		 * SELECT provider.id_provider, provider.name, provider_article.cod_article_provider
			FROM provider, provider_article
			WHERE provider.id_provider 
			IN (SELECT provider_article.id_provider
			FROM provider_article WHERE provider_article.id_article = ?)
			AND provider_article.id_article = ?;
		 * */
		
		String sql = SELECT.concat(TABLE_PROVIDER).concat(COLUM_ID_PROVIDER)
				.concat(", ").concat(TABLE_PROVIDER).concat(".")
				.concat(COLUMN_NAME).concat(", ").concat(TABLE_PROVIDER_ARTICLE).concat(".").concat(COLUMN_COD_ARTICLE_PROVIDER);
		
		String from = FROM.concat(TABLE_PROVIDER).concat(", ").concat(TABLE_PROVIDER_ARTICLE);
		
		String where = WHERE.concat(TABLE_PROVIDER).concat(COLUM_ID_PROVIDER)
				.concat(IN).concat("(").concat(SELECT)
				.concat(TABLE_PROVIDER_ARTICLE).concat(".")
				.concat(COLUM_ID_PROVIDER).concat(FROM)
				.concat(TABLE_PROVIDER_ARTICLE).concat(WHERE)
				.concat(TABLE_PROVIDER_ARTICLE).concat(".")
				.concat(COLUMN_ID_ARTICLE).concat(" = ?)").concat(AND)
				.concat(TABLE_PROVIDER_ARTICLE).concat(".")
				.concat(COLUMN_ID_ARTICLE).concat(" = ?");
		
		String [] selectionArgs = {String.valueOf(id),String.valueOf(id)};
		
		sql = sql.concat(from).concat(where);
		
		Cursor cursor = this.db.rawQuery(sql,selectionArgs); 
		
		if(cursor.moveToFirst()) {
			providers = new ArrayList<ProviderDTO>();
			do {
				int id_provider = cursor.getInt(cursor.getColumnIndex(TABLE_PROVIDER.concat(".").concat(COLUM_ID_PROVIDER)));
				String name = cursor.getString(cursor.getColumnIndex(TABLE_PROVIDER.concat(".").concat(COLUMN_NAME)));
				String cod_article_provider = cursor.getString(cursor
						.getColumnIndex(TABLE_PROVIDER_ARTICLE.concat(".")
								.concat(COLUMN_COD_ARTICLE_PROVIDER)));
				
				ProviderDTO providerDTO = new ProviderDTO();
				providerDTO.setId_provider(id_provider);
				providerDTO.setName(name);
				providerDTO.setCod_article_provider(cod_article_provider);
				
				providers.add(providerDTO);
			} while(cursor.moveToNext());
		}
		cursor.close();
		this.sdf.close();
		this.db.close();
		return providers;
	}

	public ArrayList<ProviderDTO> recoveryAll() throws Exception {
		this.db = this.sdf.open();
		
		ArrayList<ProviderDTO> providers = null;
		
		Cursor cursor = this.db.query(TABLE_PROVIDER, null, null, null, null, null, null);  
		
		if(cursor.moveToFirst()) {
			providers = new ArrayList<ProviderDTO>();
			do {
				int id_provider = cursor.getInt(cursor.getColumnIndex(COLUM_ID_PROVIDER));
				String provider_name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
				
				ProviderDTO providerDTO = new ProviderDTO();
				providerDTO.setId_provider(id_provider);
				providerDTO.setName(provider_name);
				providers.add(providerDTO);
				
			} while(cursor.moveToNext());
		} 
		cursor.close();
		this.sdf.close();
		this.db.close();
		return providers;
	}

}
