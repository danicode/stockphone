package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author dani
 * 
 * @Summary Clase de utilireria no se instancia, se utiliza para formatear expresiones fechas, atraves de su metodo estatico formatDate
 *
 */
public class MyDate {
	
	/**
	 * @Summary Constructor privado debido a que no se necesita instancias de la misma es una clase
	 * 			de utileria, para poder formatear expresiones fechas, atraves de su metodo estatico. 
	 */
	private MyDate() {}

	/**
	 * @Summary: formatea fechas
	 * 
	 * @param date: recibe como parametros la fecha ha formatear
	 * 
	 * @return: retorna la fecha formateada como un string
	 */
    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return (format.format(date));
    }
	
}
