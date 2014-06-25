package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Directory {
	
	public final static boolean VALIDATE_CREATE_DATABASE = true;
	public final static boolean VALIDATE_NO_CREATE_DATABASE = false;

	public static void fileMove(String sourceFile, String destinationFile) throws IOException {

		
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while ((c = in.read()) != -1)
				out.write(c);

			in.close();
			out.close();

			File file = new File(sourceFile);
			if (file.exists()) {
				file.delete();
			}

//		} catch (IOException e) {
//			System.err.println("Hubo un error de entrada/salida!!!");
//			
//		}
	}

	public static boolean validateDir(String path, boolean action) {
		File file = new File(path);
		boolean isDirectory = file.isDirectory();
		if(action) {
			if(!file.mkdirs()) {
				return false;
			}
		}
		return isDirectory;		
	}
	
	public static boolean fileExist(String path) {
		File file = new File(path);		
		return file.exists();
	}
	
	public static boolean deleteFile(String path) throws Exception {
		File file = new File(path);
		return file.delete();
	} 
	
	public static void copyFile(String sourceFile, String destinationFile) throws IOException { 
	
		File inFile = new File(sourceFile);
		File outFile = new File(destinationFile);
 
		FileInputStream in = new FileInputStream(inFile);
		FileOutputStream out = new FileOutputStream(outFile);
 
		int c;
		while( (c = in.read() ) != -1)
			out.write(c);
 
		in.close();
		out.close();
	}
	
	public static boolean createFile(String path, String fileName) throws IOException {
		File file = new File(path,fileName);
		return file.createNewFile();
	}

	public static void readFile(String path) {
		  File file = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         file = new File (path);
	         fr = new FileReader (file);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String line;
	         while((line=br.readLine())!=null)
	            System.out.println(line); // muestra por pantalla
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	public static void writeFile(String path) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(path);
			pw = new PrintWriter(file);

			for (int i = 0; i < 10; i++)
				pw.println("Linea " + i);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != file)
					file.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void escribir(String nombreArchivo) {

		File f;
		f = new File("nombreArchivo");
		PrintWriter wr = null;
		BufferedWriter bw = null;
		
		// Escritura
		try {
			FileWriter w = new FileWriter(f);
			bw = new BufferedWriter(w);
			wr = new PrintWriter(bw);
			wr.write("Esta es una linea de codigo");// escribimos en el archivo
			wr.append(" - y aqui continua"); // concatenamos en el archivo sin
												// borrar lo existente
			// ahora cerramos los flujos de canales de datos, al cerrarlos el
			// archivo quedará guardado con información escrita
			// de no hacerlo no se escribirá nada en el archivo
			wr.close();
			
		} catch (IOException e) {
		} finally {
			wr.close();	
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
