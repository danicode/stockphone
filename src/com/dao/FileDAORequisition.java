package com.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import com.logic.model.ArticleDTO;
import com.logic.model.RequisitionDTO;
import com.util.MemorySD;

public class FileDAORequisition {	
	
	private final static String SEPARATE = ";";	
	public final static String APP_FOLDER = "stockphone";
	public final static String REQUISITION_FOLDER = "pedidos";
	 
	public void generationCSV(RequisitionDTO requisitionDTO) throws Exception {
		
		String fileName = requisitionDTO.getFileName();
		
		String pathRoot = MemorySD.PATH_ROOT.concat(File.separator).concat(APP_FOLDER).concat(File.separator).concat(REQUISITION_FOLDER);
		File file = new File(pathRoot,fileName);
		
		FileOutputStream fout = new FileOutputStream(file);
		OutputStreamWriter ows = new OutputStreamWriter(fout);
		
		String userTitle = requisitionDTO.getUser();
		String dateTitle = requisitionDTO.getDate();
		String hourTitle = requisitionDTO.getTime();
		
		ows.write(userTitle.concat(SEPARATE).concat(dateTitle).concat(SEPARATE).concat(hourTitle));
		ows.write("\n");
		
		String userName = requisitionDTO.getUserDTO().getUser();
		String dateValue = requisitionDTO.getDateValue();
		String hourValue = requisitionDTO.getTimeValue();
		
		ows.write(userName.concat(SEPARATE).concat(dateValue).concat(SEPARATE).concat(hourValue));
		ows.write("\n");
		ows.write("\n");
		
		String cod_art = requisitionDTO.getCod_art();
		String description = requisitionDTO.getDescription();
		String provider = requisitionDTO.getProvider();
		String cod_art_prov = requisitionDTO.getCod_art_prov();
		String quantity = requisitionDTO.getQuantity();
		String articleQuantity = requisitionDTO.getArticleQuantity();
		String articleTotal = requisitionDTO.getArticleTotal();
		
		ows.write(cod_art.concat(SEPARATE).concat(description).concat(SEPARATE)
				.concat(provider).concat(SEPARATE).concat(cod_art_prov)
				.concat(SEPARATE).concat(quantity));	
		ows.write("\n");
		
		String codArt;
		String descrip;
		String prov;
		String codArtProv;
		String quant;
		
		ArticleDTO articleDTO = null;
		int total = 0;
		
		int size = requisitionDTO.getArticles().size();
		for(int i = 0; i < size; i++) {
			articleDTO = requisitionDTO.getArticles().get(i);
			codArt = articleDTO.getCod_article();
			descrip = articleDTO.getDescription();
			prov = articleDTO.getProviderName();
			codArtProv = articleDTO. getCod_article_provider();
			quant = String.valueOf(articleDTO.getQuantity());
			
			ows.write(codArt.concat(SEPARATE).concat(descrip).concat(SEPARATE)
					.concat(prov).concat(SEPARATE).concat(codArtProv)
					.concat(SEPARATE).concat(quant));
			ows.write("\n");
			total += articleDTO.getQuantity();
		}
		
		String totalValue = String.valueOf(total);
		String quantityValue = String.valueOf(requisitionDTO.getArticles().size());
		
		ows.write("\n");
		ows.write("\n");		
		ows.write(articleQuantity.concat(SEPARATE).concat(articleTotal));		
		ows.write("\n");
		ows.write(quantityValue.concat(SEPARATE).concat(totalValue));		
		
		ows.close();
	}
}
