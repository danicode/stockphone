package com.dao;

import java.util.ArrayList;

public interface IDAOGeneric<T> {

	public static final String SELECT = "SELECT ";
	public static final String DISTINCT = " DISTINCT ";
	public static final String FROM = " FROM ";
	public static final String WHERE= " WHERE ";
	public static final String JOIN = " JOIN ";
	public static final String ON = " ON ";	
	public static final String IN = " IN ";
	public static final String AND = " AND ";
	public static final String ORDER_BY = " ORDER BY ";
	
	public ArrayList<T> recoveryAll() throws Exception;
}
